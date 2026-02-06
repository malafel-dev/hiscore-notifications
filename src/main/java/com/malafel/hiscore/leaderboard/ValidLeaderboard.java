package com.malafel.hiscore.leaderboard;

import lombok.Getter;

@Getter
public enum ValidLeaderboard {
    NORMAL("Normal"),
    IRONMAN("Ironman"),
    HARDCORE_IRONMAN("Hardcore Ironman"),
    ULTIMATE_IRONMAN("Ultimate Ironman");

    private final String name;

    ValidLeaderboard(String name) {
        this.name = name;
    }
}
