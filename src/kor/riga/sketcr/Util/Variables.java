package kor.riga.sketcr.Util;

import java.util.HashMap;

import org.bukkit.boss.BossBar;

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
	public HashMap<String, Long> ms;
	public HashMap<String, Long> ns;
	public HashMap<String, BossBar> bossbarList;
	
	private Variables() {
		
		bossbarList = new HashMap<String, BossBar>();
		stop  = true;
		check  = false;
		damageParticle = false;
		ms = new HashMap<String, Long>();
		ns = new HashMap<String, Long>();
		
	}
	
}


