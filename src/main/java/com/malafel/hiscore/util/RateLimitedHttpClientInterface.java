package com.malafel.hiscore.util;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import okhttp3.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Singleton
public class RateLimitedHttpClientInterface {
    private static final int MAX_ACTIVE_CALLS = 4;
    private static final Duration MIN_TIME_BETWEEN_CALLS = Duration.ofMillis(300);

    private class CallWrapper {
        public CompletableFuture<Response> future;
        public Request request;
    }

    private final OkHttpClient client;
    private Queue<CallWrapper> callQueue = new LinkedList<CallWrapper>();
    private Instant timeOfLastRequest;
    private AtomicInteger numActiveCalls = new AtomicInteger(0);

    @Inject
    public RateLimitedHttpClientInterface(OkHttpClient client) {
        this.client = client;
        this.timeOfLastRequest = Instant.now();
    }

    public void cancelAll() {
        // This empties the queue and sets all futures as canceled. The setting to canceled is probably not needed, but
        // it does provide context to any consumers of the Future.
        while (!callQueue.isEmpty()) {
            CallWrapper call = callQueue.poll();
            call.future.cancel(true);
        }
    }

    public CompletableFuture<Response> call(Request request) {
        CallWrapper w = new CallWrapper();
        w.request = request;
        w.future = new CompletableFuture<Response>();
        callQueue.add(w);
        return w.future;
    }

    public void process(GameTick event) {
        while (numActiveCalls.get() < MAX_ACTIVE_CALLS && !callQueue.isEmpty()) {
            if (timeOfLastRequest.plus(MIN_TIME_BETWEEN_CALLS).isAfter(Instant.now())) {
                break;
            }

            CallWrapper front = callQueue.poll();
            assert front != null;
            if (front.future.isCancelled()) {
                continue;
            }
            numActiveCalls.incrementAndGet();
            timeOfLastRequest = Instant.now();
            client.newCall(front.request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    numActiveCalls.decrementAndGet();
                    front.future.completeExceptionally(e);
                }
                @Override
                public void onResponse(Call call, Response response) {
                    numActiveCalls.decrementAndGet();
                    try (response) {
                        front.future.complete(response);
                    } catch (Exception e) {
                        front.future.completeExceptionally(e);
                    }
                }
            });
            break;
        }
    }

}
