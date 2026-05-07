package com.malafel.hiscore.leaderboard;

import lombok.Getter;

@Getter
public enum ValidLeaderboard {
    NORMAL("Normal"),
    IRONMAN("Ironman"),
    HARDCORE_IRONMAN("Hardcore Ironman"),
    ULTIMATE_IRONMAN("Ultimate Ironman"),
    PURE("1 Defence Pure"),
    LEVEL_3_SKILLER("Level 3 Skiller");

    private final String name;

    ValidLeaderboard(String name) {
        this.name = name;
    }
}
