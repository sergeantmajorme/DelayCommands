package me.SgtMjrME.DQS;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DQS extends JavaPlugin{

	private static Logger log;
	private static PluginManager pm;
	private PlayerListener playerListener;
	private ArrayList<String> delay = new ArrayList<String>();
	
	@Override
	public void onEnable()
	{
		log = getServer().getLogger();
		pm = getServer().getPluginManager();
		playerListener = new PlayerListener(this);
		pm.registerEvents(playerListener, this);
		log.info("[DQS] Loaded");
	}
	
	public void sendLog(String string)
	{
		log.info(string);
	}
	
	public boolean addDelay(String player)
	{
		return delay.add(player);
	}
	
	public boolean checkDelay(String player)
	{
		return delay.contains(player);
	}
	
	public boolean removeDelay(String player)
	{
		return delay.remove(player);
	}
}
