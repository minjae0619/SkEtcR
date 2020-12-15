package kor.riga.sketcr.API.MythicMobs.Expression;


import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExpMobLevel extends SimpleExpression<Double> {
	
	private Expression<ActiveMob> mob;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.mob = (Expression<ActiveMob>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "(level of %mythicmob%|%mythicmob%'s level)";
    }
    @Override
    protected Double[] get(Event event) {
    	return new Double[]{mob.getSingle(event).getLevel()};
    	
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Double> getReturnType(){
    	return Double.class;
    }
	@Override
    public void change(Event event, Object[] d, ChangeMode mode){
    	ActiveMob mob = this.mob.getSingle(event);
    	int value = (int)d[0];
    	switch (mode) {
		case ADD:
			mob.setLevel(mob.getLevel()+value);
			break;
		case REMOVE:
			mob.setLevel(mob.getLevel()-value);
			break;
		case SET:
			mob.setLevel(value);
			break;
		default:
			break;
		}
    }
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	return (Class[])CollectionUtils.array(new Class[] { Number.class });
    }
 
}
