
package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpEnchant extends SimpleExpression<Number> {
	
    private Expression<ItemStack> item;
    private Expression<Number> cn;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	this.item = (Expression<ItemStack>) expressions[1];
    	this.cn = (Expression<Number>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "enchant of %number% in %itemstack%";
    }
	@Override
    @Nullable
    protected Number[] get(Event event) {
    	if(item.getSingle(event) != null || cn.getSingle(event) != null) {
        	ItemStack i = item.getSingle(event);
        	Number s = cn.getSingle(event);
    		@SuppressWarnings("deprecation")
			Enchantment en = Enchantment.getById(Integer.parseInt(s + ""));
    		return new Number[] { i.getEnchantmentLevel(en) };
    	}
		return null;
    	
    }
	@Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Number> getReturnType(){
    	return Number.class;
    }
	@Override
    public void change(Event event, Object[] d, ChangeMode mode){
    	if(item.getSingle(event) != null || cn.getSingle(event) != null) {
    		ItemStack i = item.getSingle(event);
			Number s = cn.getSingle(event);
			@SuppressWarnings("deprecation")
			Enchantment en = Enchantment.getById(Integer.parseInt(s + ""));
			ItemMeta im = i.getItemMeta();
			if (mode == ChangeMode.SET) {
				im.addEnchant(en, Integer.parseInt(d[0] + ""), true);
				i.setItemMeta(im);
			}else if (mode == ChangeMode.DELETE) {
				im.removeEnchant(en);
				i.setItemMeta(im);
			}
		}
    }
  
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	if(mode == ChangeMode.SET || mode == ChangeMode.DELETE) {
    		return (Class[])CollectionUtils.array(new Class[] { Number.class });
    	}
    	return null;
    }
 
}
