package com.malafel.hiscore.leaderboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.malafel.hiscore.util.RateLimitedHttpClientInterface;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Slf4j
@Singleton
public class BossInfoRegistry {
    private static final int MAX_RETRIES = 3;
    private static final HttpUrl BOSS_INFO_URL = HttpUrl.get("https://raw.githubusercontent.com/malafel-dev/hiscore-notifications/refs/heads/master/bossinfo.json");
    private static final BossInfo INVALID_BOSS = new BossInfo("", "",-1);

    @Getter
    private boolean ready = false;
    private ArrayList<BossInfo> allBosses = new ArrayList<>();

    private CompletableFuture<List<BossInfo>> bossInfoResponse;
    private int retryCount = 0;

    private final RateLimitedHttpClientInterface clientInterface;
    private final Gson gson;

    @Inject
    private BossInfoRegistry(RateLimitedHttpClientInterface clientInterface, Gson gson)
    {
        this.clientInterface = clientInterface;
        this.gson = gson;
    }

    public void process(GameTick event) {
        if (!ready) {
            if (bossInfoResponse == null && retryCount < MAX_RETRIES) {
                Request request = new Request.Builder()
                        .url(BOSS_INFO_URL)
                        .build();
                bossInfoResponse = clientInterface.call(request).thenApply(new Function<Response, List<BossInfo>>() {
                    @SneakyThrows
                    @Override
                    public List<BossInfo> apply(Response response) {
                        List<BossInfo> bossInfo = List.of(new GsonBuilder().create().fromJson(
                                response.body().charStream(), BossInfo[].class));
                        return bossInfo;
                    }
                });
            } else if (bossInfoResponse.isDone()) {
                try {
                    allBosses.clear();
                    allBosses.addAll(bossInfoResponse.get());
                    ready = true;
                    bossInfoResponse = null;
                } catch (Exception e) {
                    log.warn("Encountered an exception when trying to fetch boss info.", e);
                    bossInfoResponse = null;
                    retryCount++;
                }
            }
        }
    }

    public List<BossInfo> getAllBosses() {
        return Collections.unmodifiableList(allBosses);
    }

    public BossInfo get(String name) {
        for (BossInfo i: allBosses) {
            if (i.chatCommandsLongName.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return INVALID_BOSS;
    }

    public BossInfo fromChatCommandsLongName(String chatCommandsLongName) {
        for (BossInfo i: allBosses) {
            if (i.chatCommandsLongName.equalsIgnoreCase(chatCommandsLongName)) {
                return i;
            }
        }
        return INVALID_BOSS;
    }
}
