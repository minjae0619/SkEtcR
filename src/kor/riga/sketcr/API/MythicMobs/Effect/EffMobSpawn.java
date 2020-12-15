package kor.riga.sketcr.API.MythicMobs.Effect;



import org.bukkit.Location;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
public class EffMobSpawn extends Effect {
	private Expression<String> name;
	private Expression<Location> loc;
//	private Expression<ActiveMob> mob;
	
	@Override
	public String toString(Event event, boolean b) {
		return "spawn %string% at %location%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.name = (Expression<String>) expressions[0];
		this.loc = (Expression<Location>) expressions[1];
//		this.mob = (Expression<ActiveMob>) expressions[0];
		return true;
	}

	@Override
	protected void execute(Event event) {
		try {
			MythicMobs.inst().getAPIHelper().spawnMythicMob(name.getSingle(event), loc.getSingle(event));
		} catch (InvalidMobTypeException e) {}
	}

}
