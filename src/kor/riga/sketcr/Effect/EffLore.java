package kor.riga.sketcr.Effect;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffLore extends Effect {
	private Expression<ItemStack> item;
	private Expression<Number> amount;
	private Expression<String> acc;

	@Override
	public String toString(Event event, boolean b) {
		return "Lore{%itemstack%, %number%, %string%}";
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.item = (Expression<ItemStack>) expressions[0];
		this.amount = (Expression<Number>) expressions[1];
		this.acc = (Expression<String>) expressions[2];
		return true;
	}

	@Override
	protected void execute(Event event) {
		ItemStack i = item.getSingle(event);
		String ac = ChatColor.translateAlternateColorCodes('&', acc.getSingle(event));
		int t = Integer.parseInt(amount.getSingle(event) + "");
		ItemMeta im = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		if (im.getLore() != null) {
			l.addAll(im.getLore());
		}
		//String[] array = new String[] {};
		/*if (ac.contains("||")) {
			array = ac.split("||");
		}
		if (array == null) {*/
			for (int c = l.size() - 1; c <= t; c++) {
				try {
					l.set(t, ac);
				} catch (Exception e) {
					l.add("");
				}
			}
		/*} else {
			int a = t;
			try {
				for (String s : array) {
					l.set(a, s);
					a++;
				}
			} catch (Exception e) {
				l.add("");
			}
		}*/
		im.setLore(l);
		i.setItemMeta(im);
	}

}
