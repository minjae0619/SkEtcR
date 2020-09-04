package kor.riga.sketcr.Effect;




import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.Util.Variables;

public class EffDisableDamageParticle extends Effect {
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "disable damage particle";
    }

	@Override
	protected void execute(Event event) {
		Variables.getInstance().damageParticle = true;
	}

}
