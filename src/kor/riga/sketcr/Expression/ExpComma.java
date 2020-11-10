package kor.riga.sketcr.Expression;

import java.text.DecimalFormat;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpComma extends SimpleExpression<String> {
	private Expression<Number> num;
	
	@Override
	public String toString(Event event, boolean b) {
		return "%number% apply comma";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.num =  (Expression<Number>) expressions[0];
		return true;
	}


    @Override
    protected String[] get(Event event) {
		Number num = this.num.getSingle(event);
		DecimalFormat df = new DecimalFormat("#,##0");
		return new String[] { df.format(num) };
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
