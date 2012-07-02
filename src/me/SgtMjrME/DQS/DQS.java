package me.SgtMjrME.DQS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DQS extends JavaPlugin{

	private static Logger log;
	private static PluginManager pm;
	private PlayerListener playerListener;
	private ArrayList<String> delay = new ArrayList<String>();
	private HashMap<String, Integer> commands = new HashMap<String, Integer>();
	private String directory;
	
	@Override
	public void onEnable()
	{
		log = getServer().getLogger();
		pm = getServer().getPluginManager();
		playerListener = new PlayerListener(this);
		pm.registerEvents(playerListener, this);
		try{
			File duck = new File("plugins/DQSData");
			if (!duck.exists())
			{
				duck.mkdir();
			}
			directory = duck + "/";
		}
		catch(Exception e)
		{
			log.info("Could not create folder");
			pm.disablePlugin(this);
		}
		try{
			File f = new File(directory + "config.txt");
			if (!f.exists())
			{
				f.createNewFile();
			}
			FileReader p = new FileReader(f);
			BufferedReader out = new BufferedReader(p);
			String command = out.readLine();
			while (command != null)
			{
				String[] split = command.split(" ");
				if (split.length != 2)
				{
					command = out.readLine();
					continue;
				}
				command = "/" + split[0];
				int time = Integer.parseInt(split[1]);
				commands.put(command, time);
				command = out.readLine();
			}
			out.close();
			p.close();
		}
		catch(Exception e)
		{
			log.info("Could not read config file");
		}
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
	
	public int giveTime(String command)
	{
		return commands.get(command);
	}

	public boolean checkCommand(String string) {
		Iterator<String> i = commands.keySet().iterator();
		while (i.hasNext())
		{
			if (i.next().equalsIgnoreCase(string))
				return true;
		}
		return false;
	}
}
