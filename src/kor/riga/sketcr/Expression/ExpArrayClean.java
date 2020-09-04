package kor.riga.sketcr.Expression;


import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpArrayClean extends SimpleExpression<Object> {
	private Expression<Object> object;
	
	@Override
	public String toString(Event event, boolean b) {
		return "clean array %object%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.object =  (Expression<Object>) expressions[0];
		return true;
	}


    @Override
    protected Object[] get(Event event) {
		Object[] object = this.object.getAll(event);
		return object;
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Object> getReturnType(){
    	return Object.class;
    }
 
}
