package kor.riga.sketcr.Effect;



import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.Util.Variables;



public class EFFNs extends Effect{
	private Expression<String> key;
	@Override
	public String toString(Event event, boolean b)
	{
	  return "start ns of %string%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.key = (Expression<String>) expressions[0];
		return true;
	}
	@Override
	protected void execute(Event event) {
		Variables.getInstance().ns.put(key.getSingle(event), System.nanoTime());
	}
}

