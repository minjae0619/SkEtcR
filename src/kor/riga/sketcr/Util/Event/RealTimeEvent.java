package kor.riga.sketcr.Util.Event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RealTimeEvent extends Event{
	private static final HandlerList handlers = new HandlerList();
	
	private String time;
	//private int day;
	private int hour;
	private int minute;
	private int second;
	

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public String getTime() {
		return time;
	}

	public RealTimeEvent(String time) {
		this.time = time;
		String[] str = time.split(":");
		this.hour = Integer.parseInt(str[0]);
		this.minute = Integer.parseInt(str[1]);
		this.second = Integer.parseInt(str[2]);
	}


	public final HandlerList getHandlers() {
		return handlers;
	}
	public static final  HandlerList getHandlerList() {
		return handlers;
	}



}
