package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.Util.Variables;

public class ExpMs extends SimpleExpression<String> {
	
	private Expression<String> key;
    @SuppressWarnings("unchecked")
	@Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	key = (Expression<String>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "ms of %string%";
    }
	@Override
    @Nullable
    protected String[] get(Event event) {
		String key = this.key.getSingle(event);
		if(Variables.getInstance().ms.containsKey(key)) {
			long min = System.currentTimeMillis()-Variables.getInstance().ms.get(key);
			double value = (double)min/1000; 
			return new String[] {value + "" };
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
