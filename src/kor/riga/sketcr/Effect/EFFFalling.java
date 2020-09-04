package kor.riga.sketcr.Effect;



import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;



public class EFFFalling extends Effect{
	private Expression<String> type;
	private Expression<Byte> b;
	private Expression<Location> loc;
	@Override
	public String toString(Event event, boolean b)
	{
	  return "falling block %string% with %byte% at %location%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.type = (Expression<String>) expressions[0];
		this.b = (Expression<Byte>) expressions[1];
		this.loc = (Expression<Location>) expressions[2];
		return true;
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event event) {
		Location loc = this.loc.getSingle(event);
		Material type = Material.valueOf(this.type.getSingle(event).toUpperCase());
		loc.getWorld().spawnFallingBlock(loc, type, this.b.getSingle(event));
	}
}

