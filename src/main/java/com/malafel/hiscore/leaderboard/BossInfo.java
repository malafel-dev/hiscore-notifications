package com.malafel.hiscore.leaderboard;

public class BossInfo {
    public final String hiscoreSkillName;
    public final String chatCommandsLongName;
    public final int tableNumber;

    BossInfo(String hiscoreSkillName, String chatCommandsLongName, int tableNumber) {
        this.hiscoreSkillName = hiscoreSkillName;
        this.chatCommandsLongName = chatCommandsLongName;
        this.tableNumber = tableNumber;
    }

    public boolean isValid() {
        return hiscoreSkillName != null && !hiscoreSkillName.isEmpty() &&
               chatCommandsLongName != null && !chatCommandsLongName.isEmpty() &&
               tableNumber >= 0;
    }
}
