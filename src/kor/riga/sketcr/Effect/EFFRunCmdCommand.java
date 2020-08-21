package kor.riga.sketcr.Effect;



import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;



public class EFFRunCmdCommand extends Effect{
	private Expression<String> cmd;
	@Override
	public String toString(Event event, boolean b)
	{
	  return "run cmd command %string%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.cmd = (Expression<String>) expressions[0];
		return true;
	}
	@Override
	protected void execute(Event event) {
		String str = cmd.getSingle(event);
		try {
			Runtime.getRuntime().exec("cmd /c " + str);
		}catch (Exception e) {
		}
	}
}

