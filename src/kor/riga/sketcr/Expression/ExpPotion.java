package kor.riga.sketcr.Expression;

import java.util.Collection;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExpPotion extends SimpleExpression<String> {
	
	private Expression<Player> player;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.player = (Expression<Player>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "%player%'s potion[s]";
    }
    @Override
    protected String[] get(Event event) {
    	Player player = this.player.getSingle(event);
    	Collection<PotionEffect> p = player.getActivePotionEffects();
    	String[] s = new String[p.size()];
    	int amount = 0;
    	for(PotionEffect pe : p) {
    		s[amount] = pe.toString();
    		amount++;
    	}
    	return s;
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends String> getReturnType(){
    	return String.class;
    }
 
}
