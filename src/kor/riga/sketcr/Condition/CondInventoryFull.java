package kor.riga.sketcr.Condition;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondInventoryFull extends Condition{
	private Expression<Inventory> inventory;
    @SuppressWarnings("unchecked")
	@Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
    	this.inventory = (Expression<Inventory>) expressions[0];
    	setNegated(parser.mark == 1);
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "error";
    }
 
    @Override
    public boolean check(Event event) {
    	Inventory inventory = this.inventory.getSingle(event);
    	int amount = 0;
    	for(ItemStack item : inventory.getStorageContents()) {
    		if(item == null)
    			continue;
    		amount++;
    	}
    	int size = inventory.getSize();
    	if(inventory.getType() == InventoryType.PLAYER)
    		size -= 5;
    	return (size <= amount) ? isNegated() : (!(isNegated()));
    }
}
