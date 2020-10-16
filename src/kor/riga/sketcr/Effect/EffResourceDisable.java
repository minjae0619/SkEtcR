package kor.riga.sketcr.Effect;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.Util.Variables;

public class EffResourceDisable extends Effect {
	@Override
	public String toString(Event event, boolean b) {
		return "resource[ ][pack] disable";
	}

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		return true;
	}

	@Override
	protected void execute(Event event) {
		Variables.getInstance().resource = false;
		Variables.getInstance().resourcePackKickMessage = null;
		
	}

}
