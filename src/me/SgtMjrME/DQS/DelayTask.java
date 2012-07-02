package me.SgtMjrME.DQS;

import java.util.TimerTask;

public class DelayTask extends TimerTask{

	private String player;
	private DQS plugin;
	
	DelayTask(String player, DQS plugin)
	{
		this.player = player;
		this.plugin = plugin;
	}
	
	public void run()
	{
		plugin.removeDelay(player);
	}
}
