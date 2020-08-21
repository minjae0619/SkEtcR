package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.block.Block;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpCrops_Age extends SimpleExpression<Number> {
	
	private Expression<Block> b;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.b = (Expression<Block>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "crops age %block%";
    }
    @Override
    @Nullable
    protected Number[] get(Event event) {
    	try {
        	String s = b.getSingle(event).getState().getData().toString();
        	String result = s.substring(s.length()-2, s.length());
        	String str = result.replace(")", "");
        	String[] rb = str.split(" ");
       		return new Integer[] {Integer.parseInt(rb[0])};
		} catch (Exception e) {
			return new Integer[] {-1};
		}
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Number> getReturnType(){
    	return Number.class;
    }
    @SuppressWarnings("deprecation")
	@Override
    public void change(Event event, Object[] d, ChangeMode mode){
		if (mode == ChangeMode.SET) {
			try {
				Block block = b.getSingle(event);
				int i = Integer.parseInt(d[0] + "");
				block.setData((byte) i);		
			} catch (Exception e) {
				// TODO: handle exception
			}
			}	
    }
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	if(mode == ChangeMode.SET) {
    		return (Class[])CollectionUtils.array(new Class[] { Number.class });
    	}
    	return null;
    }
 
}
