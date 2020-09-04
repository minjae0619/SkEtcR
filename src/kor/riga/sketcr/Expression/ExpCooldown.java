package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpCooldown extends SimpleExpression<Number> {
	
    private Expression<Player> player;
    private Expression<String> type;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	this.type = (Expression<String>) expressions[1];
    	this.player = (Expression<Player>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "%player%'s cooldown of %itemtype%";
    }
	@Override
    @Nullable
    protected Number[] get(Event event) {
    	Player p = player.getSingle(event); 
    	String t = this.type.getSingle(event).replaceAll(" ", "_").toUpperCase();
    	Material type = Material.valueOf(t);
    	if(p != null) {
    		return new Integer[] { (Integer) p.getCooldown(type) };
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
    	Player p = player.getSingle(event);
    	String t = this.type.getSingle(event).replaceAll(" ", "_").toUpperCase();
    	Material type = Material.valueOf(t);
		if (p != null) {
			if (mode == ChangeMode.SET) {
				int i = Integer.parseInt(d[0] +  "");
				p.setCooldown(type, i);
			}else if (mode == ChangeMode.DELETE) {
				p.setCooldown(type, 0);
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
