package com.malafel.hiscore.util;

import java.awt.Color;

import com.malafel.hiscore.HiscoreNotificationsConfig;
import com.malafel.hiscore.leaderboard.BossInfo;
import com.malafel.hiscore.leaderboard.BossLeaderboardEntry;
import com.malafel.hiscore.leaderboard.SkillLeaderboardEntry;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.api.Skill;
import net.runelite.client.config.RuneScapeProfileType;
import net.runelite.client.util.QuantityFormatter;
import net.runelite.client.util.Text;

import static java.lang.Math.min;

@Slf4j
public class Util
{
	private static final int IN_LMS_VARBIT = 5314;

	/**
	 * Checks if a level is a valid real level (>= 1 and <= 99)
	 *
	 * @param level int
	 * @return boolean
	 */
	public static boolean isValidRealLevel(int level)
	{
		return level >= 1 && level <= Experience.MAX_REAL_LEVEL;
	}


	/**
	 * Checks if a number is a valid XP target (>= 1 and <= 200M)
	 *
	 * @param xp int
	 * @return boolean
	 */
	public static boolean isValidExperience(int xp)
	{
		return xp > 0 && xp <= Experience.MAX_SKILL_XP;
	}


	/**
	 * @param string String
	 * @return boolean
	 */
	public static boolean isInteger(String string)
	{
		try
		{
			Integer.parseInt(string);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * Gets the int value for a color.
	 *
	 * @param color color
	 * @return int
	 */
	public static int getIntValue(Color color)
	{
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();

		// Combine RGB values into a single integer
		return (red << 16) | (green << 8) | blue;
	}


	/**
	 * Replaces the words $skill and $level from the text to the passed skill and level respectively
	 *
	 * @param text  String
	 * @param skill Skill
	 * @param level int
	 * @return String
	 */
	public static String replaceSkillAndLevel(String text, Skill skill, int level)
	{
		return Text.escapeJagex(text
			.replaceAll("\\$skill", skill.getName())
			.replaceAll("\\$level", Integer.toString(level)));
	}

	/**
	 * Replaces the words $skill and $xp from the text to the passed skill and level respectively
	 *
	 * @param text  String
	 * @param skill Skill
	 * @param xp    int
	 * @return String
	 */
	public static String replaceSkillAndExperience(String text, Skill skill, int xp)
	{
		return Text.escapeJagex(text
			.replaceAll("\\$skill", skill.getName())
			.replaceAll("\\$xp", QuantityFormatter.formatNumber(xp)));
	}


	/** Replaces the words $skill, $xp, $rank, and $player from the text to the passed skill and data from
	 * leaderboardEntry
	 *
	 * @param text  String
	 * @param skill Skill
	 * @param leaderboardEntry LeaderboardEntry
	 * @return String
	 */
	public static String replaceSkillLeaderboardValues(String text, Skill skill, SkillLeaderboardEntry leaderboardEntry)
	{
		return Text.escapeJagex(text
				.replaceAll("\\$skill", skill.getName())
				.replaceAll("\\$xp", QuantityFormatter.formatNumber(leaderboardEntry.xp))
				.replaceAll("\\$rank", QuantityFormatter.formatNumber(leaderboardEntry.rank))
				.replaceAll("\\$player", leaderboardEntry.name)
				.replaceAll("\\$name", leaderboardEntry.name));
	}

	/** Replaces the words $skill, $xp, $rank, and $player from the text to the passed skill and data from
	 * leaderboardEntry
	 *
	 * @param text  String
	 * @param boss BossInfo
	 * @param leaderboardEntry LeaderboardEntry
	 * @return String
	 */
	public static String replaceBossLeaderboardValues(String text, BossInfo boss, BossLeaderboardEntry leaderboardEntry)
	{
		return Text.escapeJagex(text
				.replaceAll("\\$boss", boss.chatCommandsLongName)
				.replaceAll("\\$kc", QuantityFormatter.formatNumber(leaderboardEntry.score))
				.replaceAll("\\$score", QuantityFormatter.formatNumber(leaderboardEntry.score))
				.replaceAll("\\$rank", QuantityFormatter.formatNumber(leaderboardEntry.rank))
				.replaceAll("\\$player", leaderboardEntry.name)
				.replaceAll("\\$name", leaderboardEntry.name));
	}

	/**
	 * Check if notification for a skill is enabled in the config.
	 *
	 * @param config MilestoneLevelsConfig
	 * @param skill Skill
	 * @return boolean
	 */
	public static boolean skillEnabledInConfig(HiscoreNotificationsConfig config, Skill skill)
	{
		switch (skill)
		{
			case ATTACK:
				return config.showAttackNotifications();
			case DEFENCE:
				return config.showDefenceNotifications();
			case STRENGTH:
				return config.showStrengthNotifications();
			case HITPOINTS:
				return config.showHitpointsNotifications();
			case RANGED:
				return config.showRangedNotifications();
			case PRAYER:
				return config.showPrayerNotifications();
			case MAGIC:
				return config.showMagicNotifications();
			case COOKING:
				return config.showCookingNotifications();
			case WOODCUTTING:
				return config.showWoodcuttingNotifications();
			case FLETCHING:
				return config.showFletchingNotifications();
			case FISHING:
				return config.showFishingNotifications();
			case FIREMAKING:
				return config.showFiremakingNotifications();
			case CRAFTING:
				return config.showCraftingNotifications();
			case SMITHING:
				return config.showSmithingNotifications();
			case MINING:
				return config.showMiningNotifications();
			case HERBLORE:
				return config.showHerbloreNotifications();
			case AGILITY:
				return config.showAgilityNotifications();
			case THIEVING:
				return config.showThievingNotifications();
			case SLAYER:
				return config.showSlayerNotifications();
			case FARMING:
				return config.showFarmingNotifications();
			case RUNECRAFT:
				return config.showRunecraftNotifications();
			case HUNTER:
				return config.showHunterNotifications();
			case CONSTRUCTION:
				return config.showConstructionNotifications();
			case SAILING:
				return config.showSailingNotifications();
		}

		return true;
	}

	public static boolean isStandardWorld(Client client)
	{
		return RuneScapeProfileType.getCurrent(client) == RuneScapeProfileType.STANDARD;
	}

	public static boolean isInLMS(Client client)
	{
		return client.getVarbitValue(IN_LMS_VARBIT) == 1;
	}

	public static int nextRankInInterval(int rank, int interval) {
		if (rank % interval == 0) {
			return rank - interval;
		}
		return (rank / interval) * interval;
	}

	public static class IntervalConfig {
		public int tensInterval;
		public int hundredsInterval;
		public int thousandsInterval;
		public int tenThousandsInterval;
		public int hundredThousandsInterval;
		public int firstRankToConsider;
	}
	public static IntervalConfig cleanupIntervalData(IntervalConfig in) {
		IntervalConfig out = new IntervalConfig();

		// Arbitrary: only start considering ranks 1 million or lower.
		out.firstRankToConsider = 1_000_000;
		// This chain does 2 things:
		//  1. ensure the lower rank ranges have lower intervals than higher rank ranges (preventing potential bugs).
		//  2. if a rank-range's interval is set to 0, don't consider any ranks above that range.
		out.hundredThousandsInterval = in.hundredThousandsInterval;
		if (out.hundredThousandsInterval <= 0) {
			out.firstRankToConsider = 99_999;
			// Set this to an arbitrary high number to make the calls to `min` work.
			out.hundredThousandsInterval = 2_000_000;
		}
		out.tenThousandsInterval = min(in.tenThousandsInterval, out.hundredThousandsInterval);
		if (out.tenThousandsInterval <= 0) {
			out.firstRankToConsider = 9999;
			out.tenThousandsInterval = 2_000_000;
		}
		out.thousandsInterval = min(in.thousandsInterval, out.tenThousandsInterval);
		if (out.thousandsInterval <= 0) {
			out.firstRankToConsider = 999;
			out.thousandsInterval = 2_000_000;
		}
		out.hundredsInterval = min(in.hundredsInterval, out.thousandsInterval);
		if (out.hundredsInterval <= 0) {
			out.firstRankToConsider = 99;
			out.hundredsInterval = 2_000_000;
		}
		out.tensInterval = min(in.tensInterval, out.hundredsInterval);
		if (out.tensInterval <= 0) {
			out.firstRankToConsider = 1;
			out.hundredsInterval = 2_000_000;
		}


		return out;
	}
}
