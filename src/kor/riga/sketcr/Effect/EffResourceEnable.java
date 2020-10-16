package kor.riga.sketcr.Effect;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.Util.Variables;

public class EffResourceEnable extends Effect {
	private Expression<String> kickMessage;
	@Override
	public String toString(Event event, boolean b) {
		return "resource[ ][pack] enable[ kick message %string%]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		kickMessage = (Expression<String>) expressions[0];
		return true;
	}

	@Override
	protected void execute(Event event) {
		Variables.getInstance().resource = true;
		if(this.kickMessage.getSingle(event) != null) 
			Variables.getInstance().resourcePackKickMessage = this.kickMessage.getSingle(event);
		
	}

}
