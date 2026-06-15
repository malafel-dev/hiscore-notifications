package com.malafel.hiscore.leaderboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * "POD" object that contains all data relevant to managing queries over leaderboard data for a single boss.
 *
 * See LeaderboardManager for implementation details and notes.
 */
public class LeaderboardBossState {
    // Indicates whether leaderboard tracking of this skill has been disabled due to an error.
    public boolean isDisabledFromError = false;

    // Indicates the number of retries that have been made on the current leaderboard page. Resets to zero after
    // successfully processing a page.
    public int currentPageRetryCount = 0;

    // If not null, this is the Future pending an active request for leaderboard data.
    public Future<BossLeaderboardResult> leaderboardFuture = null;

    // The next rank that should be measured on the leaderboards. Gets set to the player's current rank-1 upon entering
    // the active state. It is used to determine which page to search for data. "Lowest" in this case means lowest
    // numerically, where rank 1 is lower than rank 2.
    public int nextRankToMeasure = 0;

    // Enabled after the first new KC on this boss. Required in order to issue the first request for the boss's
    // leaderboard data, minimizing outgoing requests.
    public boolean isActive = false;

    // The player's most recently known KC for the boss in question.
    public int currentKc = 0;

    // List of leaderboard entries that are currently being used to
    public List<BossLeaderboardEntry> validLeaderboardEntries = new ArrayList<BossLeaderboardEntry>();
}
