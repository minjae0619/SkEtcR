package kor.riga.sketcr.Effect;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffCallDeath extends Effect {
	private Expression<Player> player;
	
	@Override
	public String toString(Event event, boolean b) {
		return "call[ ]event death %player%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.player = (Expression<Player>) expressions[0];
		
		return true;
	}

	@Override
	protected void execute(Event event) {
		Player player = this.player.getSingle(event);
		Bukkit.getServer().getPluginManager().callEvent(new PlayerDeathEvent(player, null , 0, null));
	}

}
