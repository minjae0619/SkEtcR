package kor.riga.sketcr.API.ProtocolLib.Expression;


import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.Util.CustomEvent.PlayerRidingKeyPressEvent;

public class ExpRidingKey extends SimpleExpression<String> {
	
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "event-press";
    }
    @Override
    protected String[] get(Event event) {
    	if(!(event instanceof PlayerRidingKeyPressEvent))
    		return new String[] { "null" };
    	PlayerRidingKeyPressEvent e = (PlayerRidingKeyPressEvent)event;
    	return new String[]{e.getPress()};
    	
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
