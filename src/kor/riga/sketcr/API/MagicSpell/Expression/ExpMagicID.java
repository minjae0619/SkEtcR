package kor.riga.sketcr.API.MagicSpell.Expression;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.nisovin.magicspells.events.SpellApplyDamageEvent;
import com.nisovin.magicspells.events.SpellCastEvent;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.API.MagicSpell.Condition.CondMagicSpell;

public class ExpMagicID extends SimpleExpression<String> {

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean b) {
		return "m[agic][-]id";
	}

	@Override
	protected String[] get(Event event) {
		if (event instanceof SpellApplyDamageEvent) {
			SpellApplyDamageEvent e = ((SpellApplyDamageEvent) event);
			return new String[] { e.getSpell().getName() };
		} else if (event instanceof SpellCastEvent) {
			SpellCastEvent e = ((SpellCastEvent) event);
			return new String[] { e.getSpell().getName() };
		} else if (event instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
			if (!(e.getDamager() instanceof Player))
				return null;
			Player player = (Player) e.getDamager();
			CondMagicSpell.isMagic(event);
			return new String[] { player.getMetadata("SkEtcRMagicSpell").get(0).asString() };
		}
		return null;

	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

}
