package kor.riga.sketcr.Expression;


import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpAddString extends SimpleExpression<String> {
	
	private Expression<String> str1;
	private Expression<String> str2;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.str1 = (Expression<String>) expressions[0];
		this.str2 = (Expression<String>) expressions[1];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "combine %string%[ ]+[ ]%string%";
    }
    @Override
    protected String[] get(Event event) {
    	String str = this.str1.getSingle(event) + this.str2.getSingle(event);
    	return new String[] {str};
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
