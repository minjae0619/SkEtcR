package kor.riga.sketcr.Effect;



import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
public class EffPlayerResourcePack extends Effect {
	private Expression<Player> player;
	private Expression<String> str;
	
	@Override
	public String toString(Event event, boolean b) {
		return "load resource[ ][pack] %player% at %string%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.player = (Expression<Player>) expressions[0];
		this.str = (Expression<String>) expressions[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		String str = this.str.getSingle(event);
		Player player = this.player.getSingle(event);
		player.setResourcePack(str);
	}

}
