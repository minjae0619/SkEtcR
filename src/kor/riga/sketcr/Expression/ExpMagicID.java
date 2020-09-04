package kor.riga.sketcr.Expression;


import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.nisovin.magicspells.events.SpellApplyDamageEvent;
import com.nisovin.magicspells.events.SpellCastEvent;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpMagicID extends SimpleExpression<String> {
	
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "m[agic][-]id";
    }
    @Override
    protected String[] get(Event event) {
    	if(event instanceof SpellApplyDamageEvent) {
        	SpellApplyDamageEvent e = ((SpellApplyDamageEvent)event);
    		return new String[]{ e.getSpell().getName() };
    	}
    	else if( event instanceof SpellCastEvent){
    		SpellCastEvent e = ((SpellCastEvent)event);
    		return new String[]{ e.getSpell().getName() };
    	}
    	return null;
    	
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends String> getReturnType(){
    	return String.class;
    }
 
}
