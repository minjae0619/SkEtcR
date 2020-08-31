package kor.riga.sketcr.Effect;



import java.util.HashMap;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;

public class EffSort extends Effect {
	private Expression<Object> index;
	private Expression<Object> value;
	private Expression<String> name;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.index = (Expression<Object>) expressions[0];
		this.value = (Expression<Object>) expressions[1];
		this.name = (Expression<String>) expressions[2];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "sort index %objects% value %numbers% in %string%";
    }

	@Override
	protected void execute(Event event) {
		Object[] index = this.index.getAll(event);
		Number[]  value = (Number[]) this.value.getAll(event);
    	String name = this.name.getSingle(event);
		int[] rank = new int[value.length];
		HashMap<Integer, String> ind = new HashMap<Integer,String>();
		HashMap<Integer, Long> val = new HashMap<Integer,Long>();
		for(int i=0; i<value.length; ++i) {
			rank[i] = 1;
			for(int j=0; j<value.length; ++j) {
				if (value[i].longValue() < value[j].longValue()) 
					rank[i]++;
			}
			ind.put(rank[i], index[i] + "");
			val.put(rank[i], value[i].longValue());
		}
		for(int i = val.size(); i >= 1; i--) {
			Variables.setVariable(name + "index::" + i, ind.get(i), null, false);
			Variables.setVariable(name + "value::" + i, val.get(i), null, false);
		}
	}

}
