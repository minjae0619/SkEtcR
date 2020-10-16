package kor.riga.sketcr.Expression;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpTime extends SimpleExpression<String> {
	
	private Expression<String> name;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.name = (Expression<String>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "time %string%";
    }
    @Override
    protected String[] get(Event event) {
    	String s = name.getSingle(event);
		Calendar now = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat(s);
    	return new String[] { format.format(now.getTime()) };
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
