package kor.riga.sketcr.Event;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

public class EvtToggleGlide extends SkriptEvent {
	
	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {

		return true;
	}

	@Override
	public boolean check(Event e) {
		return true;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "";
	}
}
