package kor.riga.sketcr.Expression;


import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpAccess extends SimpleExpression<Boolean> {
	
	private Expression<Player> player;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.player = (Expression<Player>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "%player%'s access";
    }
    @Override
    protected Boolean[] get(Event event) {
    	try {
    		return new Boolean[]{this.player.getSingle(event).isOnline()};	
		} catch (Exception e) {
		}
    	return new Boolean[]{false};
    	
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Boolean> getReturnType(){
    	return Boolean.class;
    }
 
}
