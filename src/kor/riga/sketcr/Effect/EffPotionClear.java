package kor.riga.sketcr.Effect;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffectType;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffPotionClear extends Effect {
	private Expression<Player> player;

	@Override
	public String toString(Event event, boolean b) {
		return "clear %player%'s potion[s]";
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
		PotionEffectType[] pa = PotionEffectType.values();
		for (PotionEffectType p : pa) {
			try {
				player.removePotionEffect(p);
			} catch (Exception e) {
			}
		}
	}

}
