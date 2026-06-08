# Hiscore Notifications
Gives a leagues style notification to the player when they achieve certain milestones on the OSRS hiscores.

## Credit:
Huge credit to Antimated for their original plugin [Milestone Levels](https://github.com/Antimated/milestone-levels)!
Hiscore Notifications builds off of the great work that was done in Milestone Levels.
For customizable notifications at XP, Level, and Virtual Level intervals, check out that plugin instead of this one.

## Features:
* Get notified when ranking up on the OSRS leaderboards.
* Choose which leaderboard to use (Normal, Ironman, Hardcore Ironman, Ultimate Ironman).
* Customize how often you should be notified about leaderboard ranks by setting rank intervals.
* Enable or disable notifications for any skills.
* Customize the notification message when gaining hiscore ranks.

## Notes:
This plugin is not enabled on a skill until you reach level 60. The leaderboards at low levels are too densely 
populated. This would result in notification spam, and spammed requests to Jagex's hiscores page, which should be 
avoided.

This plugin works by making requests to the OSRS hiscores website. That website can rate limit you if the plugin makes 
too many requests for data. This can be caused by frequent world hopping, frequent changes to the plugin's settings,
and enabling tracking of boss hiscores. The plugin employs several mechanisms to limit outgoing requests, but multiple
people on the same network, or the above causes can still get you rate limited. If that happens, please disable the
plugin and wait a while before enabling it again.

Due to the rate limiting, the hiscore data can take a few minutes to initialize. If you're not getting notified about 
ranks right after logging in, that is expected behavior.

## Screenshot:
![screenshot showing a rank up notification](screenshot.png)
