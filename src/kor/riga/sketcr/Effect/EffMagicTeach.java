package kor.riga.sketcr.Effect;



import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.nisovin.magicspells.MagicSpells;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
public class EffMagicTeach extends Effect {
	private Expression<Player> player;
	private Expression<String> str;
	
	@Override
	public String toString(Event event, boolean b) {
		return "[magic[ ]]teach %string% to %player%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.str = (Expression<String>) expressions[0];
		this.player = (Expression<Player>) expressions[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		String str = this.str.getSingle(event);
		Player player = this.player.getSingle(event);
		try {
			MagicSpells.teachSpell(player, str);
		} catch (Exception e) {
			System.out.println(str + "은 존재하지 않는 스펠명입니다!");
			System.out.println(str + "은 존재하지 않는 스펠명입니다!");
			System.out.println(str + "은 존재하지 않는 스펠명입니다!");
		}
	}

}
