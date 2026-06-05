package com.malafel.hiscore.leaderboard;

/**
 * Represents a single row from the skill leaderboard tables on the OSRS hiscores website.
 */
public class BossLeaderboardEntry {
    public BossLeaderboardEntry(String name, int rank, int score) {
        this.name = name;
        this.rank = rank;
        this.score = score;
    }

    public final String name;
    public final int rank;
    public final int score;
}
