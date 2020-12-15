package kor.riga.sketcr.API.MagicSpell.Expression;


import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.nisovin.magicspells.events.SpellApplyDamageEvent;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpMagicDamage extends SimpleExpression<Number> {
	
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "magic[-]damage";
    }
    @Override
    protected Number[] get(Event event) {
    	if(!(event instanceof SpellApplyDamageEvent)) {
    		return new Number[]{ -1 };
    	}
    	SpellApplyDamageEvent e = ((SpellApplyDamageEvent)event);
		return new Number[]{ e.getFinalDamage() };
    	
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Number> getReturnType(){
    	return Number.class;
    }
 //   @SuppressWarnings("deprecation")
	@Override
    public void change(Event event, Object[] d, ChangeMode mode){
    	if(event instanceof SpellApplyDamageEvent) {
    		SpellApplyDamageEvent e = ((SpellApplyDamageEvent)event);
			if (mode == ChangeMode.SET) {
				e.applyDamageModifier((float) (Float.parseFloat(d[0] + "")/e.getDamage()));
			}	
    	}
    }
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	if(mode == ChangeMode.SET) {
    		return (Class[])CollectionUtils.array(new Class[] { Number.class });
    	}
    	return null;
    }
 
}
