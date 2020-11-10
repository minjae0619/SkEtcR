package kor.riga.sketcr.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;

import kor.riga.sketcr.Main;
import kor.riga.sketcr.Util.CustomEvent.RealTimeEvent;

public class Repeat{

	
	
	public static void callTimeEvent() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance() , new Runnable() {
			@Override
			public void run() {
				Calendar now = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				String time = format.format(now.getTime());
		    	Bukkit.getServer().getPluginManager().callEvent(new RealTimeEvent(time));
			}
		}, 0 , 20L);
	}
}
