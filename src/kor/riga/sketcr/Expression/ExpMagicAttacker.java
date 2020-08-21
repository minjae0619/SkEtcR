package kor.riga.sketcr.Expression;


import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import com.nisovin.magicspells.events.SpellApplyDamageEvent;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpMagicAttacker extends SimpleExpression<Entity> {
	
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "m[agic][-]attacker";
    }
    @Override
    protected Entity[] get(Event event) {
    	if(!(event instanceof SpellApplyDamageEvent)) {
    		return null;
    	}
    	SpellApplyDamageEvent e = ((SpellApplyDamageEvent)event);
		return new Entity[]{ e.getCaster() };
    	
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Entity> getReturnType(){
    	return Entity.class;
    }
 
}
