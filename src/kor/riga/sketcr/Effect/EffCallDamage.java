package kor.riga.sketcr.Effect;



import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffCallDamage extends Effect {
	private Expression<Entity> damager;
	private Expression<Entity> damagee;
	private Expression<String> cause;
	private Expression<Double> damage;
	
	@Override
	public String toString(Event event, boolean b) {
		return "call[ ]event damage %entity% by %entity% cause %string% damage %double%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.damager = (Expression<Entity>) expressions[1];
		this.damagee = (Expression<Entity>) expressions[0];
		this.cause = (Expression<String>) expressions[2];
		this.damage = (Expression<Double>) expressions[3];
		
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event event) {
		Entity damager = this.damager.getSingle(event);
		Entity damagee = this.damagee.getSingle(event);
		DamageCause cause = DamageCause.valueOf(this.cause.getSingle(event).toUpperCase());
		double damage = this.damage.getSingle(event);
		System.out.println(1);
		Bukkit.getServer().getPluginManager().callEvent(new EntityDamageByEntityEvent(damager, damagee, cause, damage));
	}

}
