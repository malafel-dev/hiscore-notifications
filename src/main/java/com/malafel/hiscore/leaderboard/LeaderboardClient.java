package com.malafel.hiscore.leaderboard;

import com.google.gson.Gson;
import com.malafel.hiscore.util.RateLimitedHttpClientInterface;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Skill;
import okhttp3.*;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Facilitates making requests to the OSRS hiscores website, specifically for "leaderboard" pages under specific skills.
 * The interface returns that data as `LeaderboardResult` objects, which currently must be assembled by parsing HTML
 * pages with `LeaderboardParser`. This parsing is done because there is no known public-facing API for the leaderboard
 * data.
 */
@Slf4j
public class LeaderboardClient {
    private final OkHttpClient client;
    private final Gson gson;
    private final RateLimitedHttpClientInterface clientInterface;

    @Inject
    private LeaderboardClient(OkHttpClient client, Gson gson, RateLimitedHttpClientInterface clientInterface)
    {
        this.client = client;
        this.gson = gson;
        this.clientInterface = clientInterface;
    }

    public CompletableFuture<SkillLeaderboardResult> lookupSkillAsync(Skill skill, int page, LeaderboardEndpoint endpoint) {
        HttpUrl url = endpoint.getLeaderboardURL().newBuilder()
            .addQueryParameter("table", String.valueOf(SkillTable.valueOf(skill.name()).tableNumber))
            .addQueryParameter("page", String.valueOf(page))
            .build();

        Request request = new Request.Builder()
            .url(url)
            .build();

        return clientInterface.call(request).thenApply(new Function<Response, SkillLeaderboardResult>() {
            @SneakyThrows
            @Override
            public SkillLeaderboardResult apply(Response response) {
                String documentContents = response.body().string();
                return LeaderboardParser.parseSkillDocument(documentContents);
            }
        });
    }

    public CompletableFuture<BossLeaderboardResult> lookupBossAsync(BossInfo boss, int page, LeaderboardEndpoint endpoint) {
        HttpUrl url = endpoint.getLeaderboardURL().newBuilder()
                .addQueryParameter("category_type", "1")
                .addQueryParameter("table", String.valueOf(boss.tableNumber))
                .addQueryParameter("page", String.valueOf(page))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        return clientInterface.call(request).thenApply(new Function<Response, BossLeaderboardResult>() {
            @SneakyThrows
            @Override
            public BossLeaderboardResult apply(Response response) {
                String documentContents = response.body().string();
                return LeaderboardParser.parseBossDocument(documentContents);
            }
        });
    }
}
