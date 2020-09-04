package kor.riga.sketcr.Expression;


import java.util.Arrays;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpSort extends SimpleExpression<Number> {
	
	private Expression<Object> num;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.num = (Expression<Object>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "sort in %object%";
    }
    @Override
    protected Number[] get(Event event) {
    	Number[] num = (Number[]) this.num.getAll(event);
    	Arrays.sort(num);
    	return num;
    	
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Number> getReturnType(){
    	return Number.class;
    }
 
}
