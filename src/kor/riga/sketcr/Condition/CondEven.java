package kor.riga.sketcr.Condition;


import javax.annotation.Nullable;

import org.bukkit.event.Event;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
 
public class CondEven extends Condition {
 
	Expression<Number> num;
    @SuppressWarnings("unchecked")
	@Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
    	this.num = (Expression<Number>) expressions[0];
    	setNegated(parser.mark == 1);
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "error";
    }
 
    @Override
    public boolean check(Event event) {
    	Number num = this.num.getSingle(event);
    	return (num.doubleValue()%2 == 0) ? isNegated() : (!(isNegated()));
    }
 
}