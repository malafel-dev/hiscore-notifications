package com.malafel.hiscore.leaderboard;

public class LeaderboardAPIEntry {
    public LeaderboardAPIEntry(String name, String score, String rank) {
        this.name = name;
        this.score = score;
        this.rank = rank;
    }

    public final String name;
    public final String score;
    public final String rank;
}
