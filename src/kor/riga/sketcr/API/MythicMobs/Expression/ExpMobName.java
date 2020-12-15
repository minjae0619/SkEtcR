package kor.riga.sketcr.API.MythicMobs.Expression;


import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExpMobName extends SimpleExpression<String> {
	
	private Expression<ActiveMob> mob;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.mob = (Expression<ActiveMob>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "(name of %mythicmob%|%mythicmob%'s name)";
    }
    @Override
    protected String[] get(Event event) {
    	return new String[]{mob.getSingle(event).getDisplayName()};
    	
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
