package com.malafel.hiscore.leaderboard;

import lombok.Value;

import java.util.Collections;
import java.util.List;

/**
 * Array of LeaderboardEntries that result from requesting and processing a single page from the OSRS hiscores website.
 */
@Value
public class SkillLeaderboardResult {
    private List<SkillLeaderboardEntry> entries;

    public List<SkillLeaderboardEntry> getEntries() {
        return Collections.unmodifiableList(entries);
    }
}
