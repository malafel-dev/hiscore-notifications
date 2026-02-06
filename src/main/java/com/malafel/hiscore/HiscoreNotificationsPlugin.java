package com.malafel.hiscore;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.malafel.hiscore.leaderboard.LeaderboardEntry;
import com.malafel.hiscore.leaderboard.LeaderboardManager;
import com.malafel.hiscore.leaderboard.ValidLeaderboard;
import com.malafel.hiscore.notifications.NotificationManager;
import com.malafel.hiscore.util.Util;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Skill;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.StatChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.JagexColors;
import net.runelite.client.util.QuantityFormatter;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Slf4j
@PluginDescriptor(
	name = "Hiscore Notifications"
)
public class HiscoreNotificationsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private HiscoreNotificationsConfig config;

	@Inject
	private NotificationManager notifications;

	@Inject
	private LeaderboardManager leaderboardManager;

	private final Map<Skill, Integer> previousXpMap = new EnumMap<>(Skill.class);
	private ValidLeaderboard previousChosenLeaderboard = ValidLeaderboard.NORMAL;

	private int lastHundredsInterval = 1;
	private int lastThousandsInterval = 10;
	private int lastTenThousandsInterval = 100;
	private int lastHundredThousandsInterval = 1000;

	@Provides
	HiscoreNotificationsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HiscoreNotificationsConfig.class);
	}

	@Override
	protected void startUp()
	{
		clientThread.invoke(this::initializePreviousXpMap);

		lastHundredsInterval = config.hundredsInterval();
		lastThousandsInterval = config.thousandsInterval();
		lastTenThousandsInterval = config.tenThousandsInterval();
		lastHundredThousandsInterval = config.hundredThousandsInterval();

		previousChosenLeaderboard = config.chosenLeaderboard();
		notifications.startUp();
		leaderboardManager.reset();
	}

	@Override
	protected void shutDown()
	{
		previousXpMap.clear();
		notifications.shutDown();
		leaderboardManager.reset();
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		// Clear previous XP when not logged in
		switch (gameStateChanged.getGameState())
		{
			case HOPPING:
			case LOGGING_IN:
			case LOGIN_SCREEN:
			case LOGIN_SCREEN_AUTHENTICATOR:
			case CONNECTION_LOST:
				previousXpMap.clear();
				leaderboardManager.reset();
				break;
		}

	}

	@Subscribe
	public void onGameTick(GameTick event) {
		leaderboardManager.process(event);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (previousChosenLeaderboard != config.chosenLeaderboard()) {
			leaderboardManager.reset();
			previousChosenLeaderboard = config.chosenLeaderboard();
		}

		if (lastHundredsInterval != config.hundredsInterval() ||
			lastThousandsInterval != config.thousandsInterval() ||
			lastTenThousandsInterval != config.tenThousandsInterval() ||
			lastHundredThousandsInterval != config.hundredThousandsInterval()) {
			lastHundredsInterval = config.hundredsInterval();
			lastThousandsInterval = config.thousandsInterval();
			lastTenThousandsInterval = config.tenThousandsInterval();
			lastHundredThousandsInterval = config.hundredThousandsInterval();
			leaderboardManager.reset();
		}
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged)
	{
		final Skill skill = statChanged.getSkill();

		final int currentXp = statChanged.getXp();
		final int previousXp = previousXpMap.getOrDefault(skill, -1);

		previousXpMap.put(skill, currentXp);

		// Previous xp has to be set, and our current xp has to be higher or equal to the previous xp
		if (previousXp == -1 || previousXp >= currentXp)
		{
			return;
		}

		// Only standard worlds are allowed, and if a player is in LMS, we should abort.
		if (!Util.isStandardWorld(client) || Util.isInLMS(client))
		{
			log.debug("Not on a standard world nor in LMS.");
			return;
		}

		final List<LeaderboardEntry> milestoneLeaderboardEntries = getMilestoneLeaderboardEntries(skill, previousXp, currentXp);
		if (shouldNotifyForSkill(skill) && !milestoneLeaderboardEntries.isEmpty())
		{
			log.debug("Milestone leaderboard rank to notify for {}", skill.getName());

			for (LeaderboardEntry entry: milestoneLeaderboardEntries) {
				notifyLeaderboard(skill, entry);
			}
		}
	}


	/**
	 * Gets the list of milestone xp values between two numbers from values that were fetched from the OSRS hiscores
	 *
	 * @param skill Skill
	 * @param previousXp int
	 * @param currentXp int
	 * @return List<LeaderboardEntry>
	 */
	private List<LeaderboardEntry> getMilestoneLeaderboardEntries(Skill skill, int previousXp, int currentXp) {
		return leaderboardManager.getMilestoneLeaderboardEntries(skill, previousXp, currentXp);
	}

	/**
	 * Populate initial xp per skill.
	 */
	private void initializePreviousXpMap()
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			previousXpMap.clear();
		}
		else
		{
			for (final Skill skill : Skill.values())
			{
				previousXpMap.put(skill, client.getSkillExperience(skill));
			}
		}
	}

	/**
	 * Adds a leaderboard rank notification to the queue if certain requirements are met.
	 *
	 * @param skill Skill
	 * @param leaderboardEntry LeaderboardEntry
	 */
	private void notifyLeaderboard(Skill skill, LeaderboardEntry leaderboardEntry)
	{
		String title = Util.replaceLeaderboardValues(config.notificationLeaderboardRankTitle(), skill, leaderboardEntry);
		String text = Util.replaceLeaderboardValues(config.notificationLeaderboardRankText(), skill, leaderboardEntry);
		int color = Util.getIntValue(JagexColors.DARK_ORANGE_INTERFACE_TEXT);

		log.debug("Notify leaderboard milestone reached for {} to rank {} (xp {})",
				skill.getName(),
				QuantityFormatter.formatNumber(leaderboardEntry.rank),
				QuantityFormatter.formatNumber(leaderboardEntry.xp));
		notifications.addNotification(title, text, color);
	}

	/**
	 * Check if we should notify for the given skill based off of our config settings.
	 *
	 * @param skill Skill
	 * @return boolean
	 */
	private boolean shouldNotifyForSkill(Skill skill)
	{
		return Util.skillEnabledInConfig(config, skill);
	}
}
