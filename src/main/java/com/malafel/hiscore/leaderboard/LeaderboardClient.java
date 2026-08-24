package com.malafel.hiscore.leaderboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.malafel.hiscore.util.RateLimitedHttpClientInterface;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Skill;
import okhttp3.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Facilitates making requests to the OSRS hiscores website, specifically for "leaderboard" pages under specific skills.
 * The interface returns that data as `LeaderboardResult` objects.
 */
@Slf4j
public class LeaderboardClient {
    private final OkHttpClient client;
    private final Gson gson;
    private final RateLimitedHttpClientInterface clientInterface;

    private static final int SKILL_CATEGORY = 0;
    private static final int BOSS_CATEGORY = 1;
    private static final int PAGE_SIZE = 25;

    @Inject
    private LeaderboardClient(OkHttpClient client, Gson gson, RateLimitedHttpClientInterface clientInterface)
    {
        this.client = client;
        this.gson = gson;
        this.clientInterface = clientInterface;
    }

    public void reset() {
        if (clientInterface != null) {
            clientInterface.cancelAll();
        }
    }

    public CompletableFuture<SkillLeaderboardResult> lookupSkillAsync(Skill skill, int page, LeaderboardEndpoint endpoint) {
        HttpUrl url = endpoint.getLeaderboardURL().newBuilder()
            .addQueryParameter("table", String.valueOf(SkillTable.valueOf(skill.name()).tableNumber))
            .addQueryParameter("category", String.valueOf(SKILL_CATEGORY))
            .addQueryParameter("size", String.valueOf(PAGE_SIZE))
            .addQueryParameter("toprank", String.valueOf(pageToTopRank(page)))
            .build();

        Request request = new Request.Builder()
            .url(url)
            .build();

        return clientInterface.call(request).thenApply(new Function<Response, SkillLeaderboardResult>() {
            @SneakyThrows
            @Override
            public SkillLeaderboardResult apply(Response response) {
                LeaderboardAPIEntry[] apiEntries = new GsonBuilder().create().fromJson(response.body().charStream(), LeaderboardAPIEntry[].class);
                ArrayList<SkillLeaderboardEntry> entries = new ArrayList<SkillLeaderboardEntry>();
                for (LeaderboardAPIEntry entry: apiEntries) {
                    entries.add(new SkillLeaderboardEntry(entry));
                }
                return new SkillLeaderboardResult(entries);
            }
        });
    }

    public CompletableFuture<BossLeaderboardResult> lookupBossAsync(BossInfo boss, int page, LeaderboardEndpoint endpoint) {
        HttpUrl url = endpoint.getLeaderboardURL().newBuilder()
                .addQueryParameter("table", String.valueOf(boss.tableNumber))
                .addQueryParameter("category", String.valueOf(BOSS_CATEGORY))
                .addQueryParameter("size", String.valueOf(PAGE_SIZE))
                .addQueryParameter("toprank", String.valueOf(pageToTopRank(page)))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        return clientInterface.call(request).thenApply(new Function<Response, BossLeaderboardResult>() {
            @SneakyThrows
            @Override
            public BossLeaderboardResult apply(Response response) {
                LeaderboardAPIEntry[] apiEntries = new GsonBuilder().create().fromJson(response.body().charStream(), LeaderboardAPIEntry[].class);
                ArrayList<BossLeaderboardEntry> entries = new ArrayList<BossLeaderboardEntry>();
                for (LeaderboardAPIEntry entry: apiEntries) {
                    entries.add(new BossLeaderboardEntry(entry));
                }
                return new BossLeaderboardResult(entries);
            }
        });
    }

    /**
     * The leaderboard API takes a `toprank` param instead of a page param. This does the conversion from page to
     * toprank in order to fetch the same results that the hiscores website would display.
     *
     * TODO: This is an extra step. We already convert rank to page in order to call this function. Remove eventually.
     */
    private int pageToTopRank(int page) {
        return (page-1)*25;
    }
}
