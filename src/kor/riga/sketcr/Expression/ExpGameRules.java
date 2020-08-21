package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpGameRules extends SimpleExpression<String> {
	
	private Expression<String> name;
	private Expression<World> world;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.name = (Expression<String>) expressions[0];
		this.world = (Expression<World>) expressions[1];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "gamerule %string% at %world%";
    }
    @Override
    @Nullable
    protected String[] get(Event event) {
    	World w = world.getSingle(event);
    	if(w != null) {
    		return new String[] { w.getGameRuleValue(name.getSingle(event)) };
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
    @Override
    public void change(Event event, Object[] d, ChangeMode mode){
		if (mode == ChangeMode.SET) {
			String b = (String) d[0];
			world.getSingle(event).setGameRuleValue(name.getSingle(event), b);
		}	
    }
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	if(mode == ChangeMode.SET) {
    		return (Class[])CollectionUtils.array(new Class[] { String.class });
    	}
    	return null;
    }
 
}
