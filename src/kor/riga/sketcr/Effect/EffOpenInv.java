package kor.riga.sketcr.Effect;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffOpenInv extends Effect {
	private Expression<Player> player;
	private Expression<Number> row;
	private Expression<String> title;
	
	@Override
	public String toString(Event event, boolean b) {
		return "open[ ]inv %number% and %string% to %player%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.row = (Expression<Number>) expressions[0];
		this.title = (Expression<String>) expressions[1];
		this.player = (Expression<Player>) expressions[2];
		return true;
	}

	@Override
	protected void execute(Event event) {
		Integer row = this.row.getSingle(event).intValue();
		String title = this.title.getSingle(event);
		Player player = this.player.getSingle(event);
		Inventory i = Bukkit.getServer().createInventory(null, row*9, title);
		player.openInventory(i);
	}

}
