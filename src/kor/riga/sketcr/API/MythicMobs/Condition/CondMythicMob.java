package kor.riga.sketcr.API.MythicMobs.Condition;


import javax.annotation.Nullable;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
 
public class CondMythicMob extends Condition {
 
	Expression<Entity> entity;
    @SuppressWarnings("unchecked")
	@Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
    	this.entity = (Expression<Entity>) expressions[0];
    	setNegated(parser.mark == 1);
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "error";
    }
 
    @Override
    public boolean check(Event event) {
    	return MythicMobs.inst().getAPIHelper().isMythicMob(entity.getSingle(event)) ? isNegated() : (!isNegated());
    }
 
}