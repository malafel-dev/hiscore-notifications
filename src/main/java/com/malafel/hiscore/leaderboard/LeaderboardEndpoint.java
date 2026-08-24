package com.malafel.hiscore.leaderboard;

import lombok.Getter;
import net.runelite.client.hiscore.HiscoreEndpoint;
import okhttp3.HttpUrl;

/**
 * Maps the various game modes to leaderboard URLs in the OSRS hiscores API. This parallels `HiscoreEndpoint` in
 * the RuneLite code base.
 */
@Getter
public enum LeaderboardEndpoint {
    NORMAL("Normal", "https://secure.runescape.com/m=hiscore_oldschool/ranking.json"),
    IRONMAN("Ironman", "https://secure.runescape.com/m=hiscore_oldschool_ironman/ranking.json"),
    HARDCORE_IRONMAN("Hardcore Ironman", "https://secure.runescape.com/m=hiscore_oldschool_hardcore_ironman/ranking.json"),
    ULTIMATE_IRONMAN("Ultimate Ironman", "https://secure.runescape.com/m=hiscore_oldschool_ultimate/ranking.json"),
    DEADMAN("Deadman", "https://secure.runescape.com/m=hiscore_oldschool_deadman/ranking.json"),
    SEASONAL("Leagues", "https://secure.runescape.com/m=hiscore_oldschool_seasonal/ranking.json"),
    TOURNAMENT("Tournament", "https://secure.runescape.com/m=hiscore_oldschool_tournament/ranking.json"),
    FRESH_START_WORLD("Fresh Start", "https://secure.runescape.com/m=hiscore_oldschool_fresh_start/ranking.json"),
    PURE("1 Defence Pure", "https://secure.runescape.com/m=hiscore_oldschool_skiller_defence/ranking.json"),
    LEVEL_3_SKILLER("Level 3 Skiller", "https://secure.runescape.com/m=hiscore_oldschool_skiller/ranking.json");

    private final String name;
    private final HttpUrl leaderboardURL;

    LeaderboardEndpoint(String name, String leaderboardURL) {
        this.name = name;
        this.leaderboardURL = HttpUrl.get(leaderboardURL);
    }
}