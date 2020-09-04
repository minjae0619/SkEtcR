package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerDeathEvent;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpKeepInventory extends SimpleExpression<Boolean> {
	
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "keep inventory";
    }
	@Override
    @Nullable
    protected Boolean[] get(Event event) {
    	if(!(event instanceof PlayerDeathEvent))
    		return new Boolean[]{false};
    	PlayerDeathEvent e = (PlayerDeathEvent)event;
    	return new Boolean[]{e.getKeepInventory()};
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Boolean> getReturnType(){
    	return Boolean.class;
    }
	@Override
    public void change(Event event, Object[] d, ChangeMode mode){
    	if(!(event instanceof PlayerDeathEvent))
    		return;
    	PlayerDeathEvent e = ((PlayerDeathEvent)event);
		if (mode == ChangeMode.SET) {
			boolean check = (boolean)d[0];
			e.setKeepInventory(check);
			if(check){
				e.getDrops().removeAll(e.getDrops());

			}
		}
    }
  
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	if(mode == ChangeMode.SET) {
    		return (Class[])CollectionUtils.array(new Class[] { Boolean.class });
    	}
    	return null;
    }
 
}
