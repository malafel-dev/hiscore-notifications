package com.malafel.leaderboard;

import com.malafel.hiscore.HiscoreNotificationsPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class HiscoreNotificationsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(HiscoreNotificationsPlugin.class);
		RuneLite.main(args);
	}
}