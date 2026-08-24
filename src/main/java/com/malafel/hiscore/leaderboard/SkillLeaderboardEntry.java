package com.malafel.hiscore.leaderboard;

import net.runelite.api.Experience;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Represents a single row from the skill leaderboard tables on the OSRS hiscores website.
 */
public class SkillLeaderboardEntry {
    public SkillLeaderboardEntry(String name, int rank, int level, int xp) {
        this.name = name;
        this.rank = rank;
        this.level = level;
        this.xp = xp;
    }

    public SkillLeaderboardEntry(LeaderboardAPIEntry apiEntry) {
        this.name = apiEntry.name;
        int rank;
        int score;
        try {
            rank = NumberFormat.getNumberInstance(java.util.Locale.US).parse(apiEntry.rank).intValue();
            score = NumberFormat.getNumberInstance(java.util.Locale.US).parse(apiEntry.score).intValue();
        } catch (ParseException e) {
            rank = 0;
            score = 0;
        }
        this.xp = score;
        this.rank = rank;
        this.level = Experience.getLevelForXp(score);
    }

    public final String name;
    public final int rank;
    public final int level;
    public final int xp;
}
