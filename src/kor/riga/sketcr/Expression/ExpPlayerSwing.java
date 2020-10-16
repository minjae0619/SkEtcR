package kor.riga.sketcr.Expression;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import kor.riga.sketcr.Util.Variables;

public class ExpPlayerSwing extends SimpleExpression<Boolean> {
	
    private Expression<Player> player;
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
    	this.player = (Expression<Player>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "%player%'s swing";
    }
	@Override
    @Nullable
    protected Boolean[] get(Event event) {
    	Player p = player.getSingle(event);
    	return new Boolean[] {Variables.getInstance().playerHandMove.contains(p.getName())};
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Boolean> getReturnType(){
    	return Boolean.class;
    }
	@Override
    public void change(Event event, Object[] d, ChangeMode mode){
    	Player player = this.player.getSingle(event);
		if (player != null) {
			if (mode == ChangeMode.SET) {
				if((boolean)d[0])
					Variables.getInstance().playerHandMove.add(player.getName());
				else
					Variables.getInstance().playerHandMove.remove(player.getName());
			}else if (mode == ChangeMode.DELETE) {
				Variables.getInstance().playerHandMove.remove(player.getName());
			}
		}
    }
  
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	if(mode == ChangeMode.SET || mode == ChangeMode.DELETE) {
    		return (Class[])CollectionUtils.array(new Class[] { Boolean.class });
    	}
    	return null;
    }
 
}
