package kor.riga.sketcr.Expression;



import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.nisovin.magicspells.MagicSpells;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExpMagicCooldown extends SimpleExpression<Float> {
	private Expression<Player> player;
	private Expression<String> str;
	
	@Override
	public String toString(Event event, boolean b) {
		return " %player%['s] m[agic[ ]]cooldown of %string%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.str = (Expression<String>) expressions[0];
		this.player = (Expression<Player>) expressions[1];
		return true;
	}

    @Override
    protected Float[] get(Event event) {
		String str = this.str.getSingle(event);
		Player player = this.player.getSingle(event);
		float r = -1;
		try {
			r = MagicSpells.getSpellByInGameName(str).getCooldown(player);
		} catch (Exception e) {
			System.out.println(str + "은 존재하지 않는 스펠명입니다!");
			System.out.println(str + "은 존재하지 않는 스펠명입니다!");
			System.out.println(str + "은 존재하지 않는 스펠명입니다!");
		}
		return new Float[]{ r };
    	
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends Float> getReturnType(){
    	return Float.class;
    }
    @Override
    public void change(Event event, Object[] d, ChangeMode mode){
		if (mode == ChangeMode.SET) {
			float i = (float) d[0];
			String str = this.str.getSingle(event);
			Player player = this.player.getSingle(event);
			try {
				MagicSpells.getSpellByInGameName(str).setCooldown(player, i);
			} catch (Exception e) {
				System.out.println(str + "은 존재하지 않는 스펠명입니다!");
				System.out.println(str + "은 존재하지 않는 스펠명입니다!");
				System.out.println(str + "은 존재하지 않는 스펠명입니다!");
			}
		}	
    }
	@Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
    	if(mode == ChangeMode.SET) {
    		return (Class[])CollectionUtils.array(new Class[] { String.class });
    	}
    	return null;
    }
}
