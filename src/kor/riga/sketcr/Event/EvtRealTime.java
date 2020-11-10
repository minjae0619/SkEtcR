package kor.riga.sketcr.Event;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import kor.riga.sketcr.Util.CustomEvent.RealTimeEvent;

public class EvtRealTime extends SkriptEvent {

	// player move [of] [%string%][,] [%string%][,] [%string%]
	Literal<String> time;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		time = (Literal<String>) args[0];
		return true;
	}

	@Override
	public boolean check(Event e) {
		String time = this.time.getSingle(e);
		if(((RealTimeEvent)e).getTime().equalsIgnoreCase(time))
				return true;
		return false;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "";
	}
	
}
