package kor.riga.sketcr.Effect;



import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.etc.Variables;



public class EFFStopBossbar extends Effect{
	private Expression<String> id;
	@Override
	public String toString(Event event, boolean b)
	{
	  return "stop[]bossbar id %string%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.id = (Expression<String>) expressions[0];
		return true;
	}
	@Override
	protected void execute(Event event) {
		String id = this.id.getSingle(event);
		if(Variables.getInstance().bossbarList.containsKey(id)){
			Variables.getInstance().bossbarList.get(id).removeAll();
			Variables.getInstance().bossbarList.remove(id);
			
		}
	}
}

