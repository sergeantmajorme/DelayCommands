package me.SgtMjrME.DQS;

import java.util.Timer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListener implements Listener{
	private DQS plugin;
	
	PlayerListener(DQS dqs)
	{
		plugin = dqs;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void PlayerEvent(PlayerCommandPreprocessEvent e)
	{
		if (e.getPlayer().isOp())
		{
			return;
		}
		if (plugin.checkDelay(e.getPlayer().getName()) && e.getMessage().split(" ")[0].equalsIgnoreCase("/quests"))
		{
			e.setCancelled(true);
			return;
		}
		plugin.addDelay(e.getPlayer().getName());
		Timer t = new Timer();
		t.schedule(new DelayTask(e.getPlayer().getName(), plugin), 5000);
	}
}
