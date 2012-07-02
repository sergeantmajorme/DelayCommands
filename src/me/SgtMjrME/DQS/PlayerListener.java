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
//		if (e.getPlayer().isOp())
//		{
//			return;
//		}
		if (plugin.checkDelay(e.getPlayer().getName()))
		{
			e.setCancelled(true);
			return;
		}
		else if (plugin.checkCommand(e.getMessage().split(" ")[0])){
			plugin.addDelay(e.getPlayer().getName());
			Timer t = new Timer();
			t.schedule(new DelayTask(e.getPlayer().getName(), plugin), plugin.giveTime(e.getMessage().split(" ")[0]));
		}
	}
}
