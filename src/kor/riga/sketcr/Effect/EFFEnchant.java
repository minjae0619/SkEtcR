package kor.riga.sketcr.Effect;


import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;



public class EFFEnchant extends Effect{
	private Expression<ItemStack> item;
	@Override
	public String toString(Event event, boolean b)
	{
	  return "clear enchant of %itemstack%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.item = (Expression<ItemStack>) expressions[0];
		return true;
	}
	@Override
	protected void execute(Event event) {
		ItemStack i = (ItemStack) item.getSingle(event);
		Map<Enchantment, Integer> l = i.getEnchantments();
		for(Enchantment e : l.keySet()) {
			i.removeEnchantment(e);
		}
	}

}

