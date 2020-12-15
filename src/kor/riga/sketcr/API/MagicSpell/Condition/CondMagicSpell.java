package kor.riga.sketcr.API.MagicSpell.Condition;


import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
 
public class CondMagicSpell extends Condition {
 
	@Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
    	setNegated(parser.mark == 1);
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "error";
    }
 
    @Override
    public boolean check(Event event) {
    	return CondMagicSpell.isMagic(event) ? isNegated() : (!isNegated());
    }
    
    
    
    public static boolean isMagic(Event event) {
    	if(!(event instanceof EntityDamageByEntityEvent))
    		return false;
    	EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
    	if(!(e.getDamager() instanceof Player))
    		return false;
    	Player player = (Player) e.getDamager();
    	return player.hasMetadata("SkEtcRMagicSpell");
    }
 
}