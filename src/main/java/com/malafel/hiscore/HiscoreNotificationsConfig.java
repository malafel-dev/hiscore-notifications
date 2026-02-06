package com.malafel.hiscore;

import com.malafel.hiscore.leaderboard.ValidLeaderboard;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("hiscorenotifications")
public interface HiscoreNotificationsConfig extends Config
{
	@ConfigSection(
			name = "Hiscore Ranks",
			description = "All hiscore rank notification settings",
			position = 100
	)
	String SECTION_LEADERBOARD = "leaderboardRanks";

	@ConfigItem(
			keyName = "chosenLeaderboard",
			name = "Leaderboard",
			description = "Configures which leaderboard to use for rank notifications.",
			section = SECTION_LEADERBOARD,
			position = 1
	)
	default ValidLeaderboard chosenLeaderboard()
	{
		return ValidLeaderboard.NORMAL;
	}

	@ConfigItem(
			keyName = "hundredsInterval",
			name = "1-999 Interval",
			description = "Configures gaps between notifications when your rank is between 1 and 999. Should be less than or equal to 1000s Interval.",
			section = SECTION_LEADERBOARD,
			position = 2
	)
	default int hundredsInterval()
	{
		return 1;
	}

	@ConfigItem(
			keyName = "thousandsInterval",
			name = "1000s Interval",
			description = "Configures gaps between notifications when your rank is between 1000 and 9999. Should be less than or equal to 10,000s Interval.",
			section = SECTION_LEADERBOARD,
			position = 3
	)
	default int thousandsInterval()
	{
		return 10;
	}

	@ConfigItem(
			keyName = "tenThousandsInterval",
			name = "10,000s Interval",
			description = "Configures gaps between notifications when your rank is between 10,000 and 99,999. Should be less than or equal to 100,000+ Interval.",
			section = SECTION_LEADERBOARD,
			position = 4
	)
	default int tenThousandsInterval()
	{
		return 100;
	}

	@ConfigItem(
			keyName = "hundredThousandsInterval",
			name = "100,000+ Interval",
			description = "Configures gaps between notifications when your rank is 100,000 or higher.",
			section = SECTION_LEADERBOARD,
			position = 5
	)
	default int hundredThousandsInterval()
	{
		return 1000;
	}

	@ConfigSection(
			name = "Skills",
			description = "Settings for what skills we want to display notifications on",
			position = 200
	)
	String SECTION_SKILLS = "skills";

	@ConfigItem(
			keyName = "showAttackNotifications",
			name = "Attack",
			description = "Should we show Attack notifications?",
			section = SECTION_SKILLS
	)
	default boolean showAttackNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showDefenceNotifications",
			name = "Defence",
			description = "Should we show Defence notifications?",
			section = SECTION_SKILLS
	)
	default boolean showDefenceNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showStrengthNotifications",
			name = "Strength",
			description = "Should we show Strength notifications?",
			section = SECTION_SKILLS
	)
	default boolean showStrengthNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showHitpointsNotifications",
			name = "Hitpoints",
			description = "Should we show Hitpoints notifications?",
			section = SECTION_SKILLS
	)
	default boolean showHitpointsNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showRangedNotifications",
			name = "Ranged",
			description = "Should we show Ranged notifications?",
			section = SECTION_SKILLS
	)
	default boolean showRangedNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showPrayerNotifications",
			name = "Prayer",
			description = "Should we show Prayer notifications?",
			section = SECTION_SKILLS
	)
	default boolean showPrayerNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showMagicNotifications",
			name = "Magic",
			description = "Should we show Magic notifications?",
			section = SECTION_SKILLS
	)
	default boolean showMagicNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showCookingNotifications",
			name = "Cooking",
			description = "Should we show Cooking notifications?",
			section = SECTION_SKILLS
	)
	default boolean showCookingNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showWoodcuttingNotifications",
			name = "Woodcutting",
			description = "Should we show Woodcutting notifications?",
			section = SECTION_SKILLS
	)
	default boolean showWoodcuttingNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showFletchingNotifications",
			name = "Fletching",
			description = "Should we show Fletching notifications?",
			section = SECTION_SKILLS
	)
	default boolean showFletchingNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showFishingNotifications",
			name = "Fishing",
			description = "Should we show Fishing notifications?",
			section = SECTION_SKILLS
	)
	default boolean showFishingNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showFiremakingNotifications",
			name = "Firemaking",
			description = "Should we show Firemaking notifications?",
			section = SECTION_SKILLS
	)
	default boolean showFiremakingNotifications()
	{
		return true;
	}


	@ConfigItem(
			keyName = "showCraftingNotifications",
			name = "Crafting",
			description = "Should we show Crafting notifications?",
			section = SECTION_SKILLS
	)
	default boolean showCraftingNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showSmithingNotifications",
			name = "Smithing",
			description = "Should we show Smithing notifications?",
			section = SECTION_SKILLS
	)
	default boolean showSmithingNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showMiningNotifications",
			name = "Mining",
			description = "Should we show Mining notifications?",
			section = SECTION_SKILLS
	)
	default boolean showMiningNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showHerbloreNotifications",
			name = "Herblore",
			description = "Should we show Herblore notifications?",
			section = SECTION_SKILLS
	)
	default boolean showHerbloreNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showAgilityNotifications",
			name = "Agility",
			description = "Should we show Agility notifications?",
			section = SECTION_SKILLS
	)
	default boolean showAgilityNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showThievingNotifications",
			name = "Thieving",
			description = "Should we show Thieving notifications?",
			section = SECTION_SKILLS
	)
	default boolean showThievingNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showSlayerNotifications",
			name = "Slayer",
			description = "Should we show Slayer notifications?",
			section = SECTION_SKILLS
	)
	default boolean showSlayerNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showFarmingNotifications",
			name = "Farming",
			description = "Should we show Farming notifications?",
			section = SECTION_SKILLS
	)
	default boolean showFarmingNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showRunecraftNotifications",
			name = "Runecraft",
			description = "Should we show Runecraft notifications?",
			section = SECTION_SKILLS
	)
	default boolean showRunecraftNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showHunterNotifications",
			name = "Hunter",
			description = "Should we show Hunter notifications?",
			section = SECTION_SKILLS
	)
	default boolean showHunterNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showConstructionNotifications",
			name = "Construction",
			description = "Should we show Construction notifications?",
			section = SECTION_SKILLS
	)
	default boolean showConstructionNotifications()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showSailingNotifications",
			name = "Sailing",
			description = "Should we show Sailing notifications?",
			section = SECTION_SKILLS
	)
	default boolean showSailingNotifications()
	{
		return true;
	}

	@ConfigSection(
			name = "Notifications",
			description = "Settings for the notification boxes",
			position = 300
	)
	String SECTION_NOTIFICATION = "notifications";
	@ConfigItem(
			keyName = "notificationLeaderboardRankTitle",
			name = "Title",
			description = "Can include $rank, $xp, $player, and $skill variables.",
			section = SECTION_NOTIFICATION,
			position = 1
	)
	default String notificationLeaderboardRankTitle()
	{
		return "Hiscore rank milestone";
	}
	@ConfigItem(
			keyName = "notificationLeaderboardRankText",
			name = "Text",
			description = "Can include $rank, $xp, $player, and $skill variables.",
			section = SECTION_NOTIFICATION,
			position = 2
	)
	default String notificationLeaderboardRankText()
	{
		return "Achieved rank $rank in $skill,\nsurpassing $name!";
	}
}
