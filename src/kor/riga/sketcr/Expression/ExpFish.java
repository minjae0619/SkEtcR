package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.entity.Item;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpFish extends SimpleExpression<ItemStack> {
	
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "event-fish";
    }
    @Override
    @Nullable
    protected ItemStack[] get(Event event) {
		PlayerFishEvent e =  (PlayerFishEvent)event;
		if(e.getState() == State.CAUGHT_FISH) {
			return new ItemStack[]{((Item)e.getCaught()).getItemStack()};
		}
    	return null;
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends ItemStack> getReturnType(){
    	return ItemStack.class;
    }
    @Override
    public void change(Event event, Object[] d, ChangeMode mode){
		if (mode == ChangeMode.SET) {
			PlayerFishEvent e =  (PlayerFishEvent)event;
			if(e.getState() == State.CAUGHT_FISH) {
				((Item)e.getCaught()).setItemStack((ItemStack) d[0]);
			}
		}	
    }
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	if(mode == ChangeMode.SET) {
    		return (Class[])CollectionUtils.array(new Class[] { ItemStack.class });
    	}
    	return null;
    }
 
}
