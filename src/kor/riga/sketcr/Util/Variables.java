package kor.riga.sketcr.Util;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.boss.BossBar;

import kor.riga.sketcr.Main;

public class Variables{
//========================================================================
	private static Variables instance = null;
	public static Variables getInstance(){
		if(instance == null) {
			instance = new Variables();
		}
	   return instance;
	}
	
	public boolean stop; 
	public boolean check; 
	public boolean damageParticle;
	public boolean resource;
	public String version;
	public String resourcePackKickMessage;
	public ArrayList<String> playerHandMove; // player
	public HashMap<String, Long> ms;
	public HashMap<String, Long> ns;
	public HashMap<String, BossBar> bossbarList;
	
	private Variables() {
		
		bossbarList = new HashMap<String, BossBar>();
		stop  = true;
		check  = false;
		resource = false;
		version = Main.getInstance().getDescription().getVersion();
		resourcePackKickMessage = "";
		playerHandMove = new ArrayList<String>();
		damageParticle = false;
		ms = new HashMap<String, Long>();
		ns = new HashMap<String, Long>();
		
	}
	
}


