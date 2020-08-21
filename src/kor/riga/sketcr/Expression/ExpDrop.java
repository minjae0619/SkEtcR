package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpDrop extends SimpleExpression<ItemStack> {

	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		return true;
	}

	@Override
	public String toString(@Nullable Event event, boolean b) {
		return "event-drop[s]";
	}

	@SuppressWarnings("deprecation")
	@Override
	@Nullable
	protected ItemStack[] get(Event event) {
		if (event instanceof BlockBreakEvent) {
			BlockBreakEvent e = (BlockBreakEvent) event;
			ItemStack[] al = new ItemStack[e.getBlock().getDrops(e.getPlayer().getItemInHand()).size()];
			int amount = 0;
			for (ItemStack item : e.getBlock().getDrops(e.getPlayer().getItemInHand())) {
				al[amount] = item;
				amount++;
				return al;
			}
		} else if (event instanceof EntityDeathEvent) {
			EntityDeathEvent e = (EntityDeathEvent) event;
			return (ItemStack[]) e.getDrops().toArray();
		}
		return null;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public void change(Event event, Object[] d, ChangeMode mode) {
		if (mode == ChangeMode.SET) {
			if (event instanceof BlockBreakEvent) {
				BlockBreakEvent e = (BlockBreakEvent) event;
				e.setDropItems(false);
				try {
					ItemStack[] items = (ItemStack[]) d;
					for (ItemStack item : items) {
						e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
					}
				} catch (Exception e2) {
				}
			} else if (event instanceof EntityDeathEvent) {
				EntityDeathEvent e = (EntityDeathEvent) event;
				e.getDrops().clear();
				try {
					ItemStack[] items = (ItemStack[]) d;
					for (ItemStack item : items) {
						e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
					}
				} catch (Exception e2) {
				}
			}
		}
	}

	@Override
	public Class<?>[] acceptChange(final ChangeMode mode) {
		if (mode == ChangeMode.SET) {
			return (Class[]) CollectionUtils.array(new Class[] { ItemStack[].class });
		}
		return null;
	}

}
