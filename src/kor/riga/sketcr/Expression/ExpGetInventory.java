package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpGetInventory extends SimpleExpression<String> {
	private Expression<Player> player;
    @SuppressWarnings("unchecked")
	@Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	this.player = (Expression<Player>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "[%player%['s]][ ]inv[entory][ ]name";
    }
	@Override
    @Nullable
    protected String[] get(Event event) {
    	if(event instanceof InventoryClickEvent){
    		InventoryClickEvent e = (InventoryClickEvent)event;
    		return new String[]{e.getView().getTitle()};
    	}else if(event instanceof InventoryCloseEvent){
    		InventoryCloseEvent e = (InventoryCloseEvent)event;
    		return new String[]{e.getView().getTitle()};
    	}else if(event instanceof InventoryOpenEvent){
    		InventoryOpenEvent e = (InventoryOpenEvent)event;
    		return new String[]{e.getView().getTitle()};
    	}else if(this.player.getSingle(event) != null) {
    		Player p = this.player.getSingle(event);
    		return new String[] {p.getOpenInventory().getTitle()};
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
