package kor.riga.sketcr.Effect;


import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;



public class EffParticle extends Effect{
	private Expression<String> particleName;
	private Expression<Location> loc;
	private Expression<Integer> amount;
	private Expression<Double> offsetX;
	private Expression<Double> offsetY;
	private Expression<Double> offsetZ;
	
	@Override
	public String toString(Event event, boolean b)
	{
	  return "particle %string% of %integer% at %location% ([offset]XYZ|RGB) %double%, %double%, %double%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.particleName = (Expression<String>) expressions[0];
		this.amount = (Expression<Integer>) expressions[1];
		this.loc = (Expression<Location>) expressions[2];
		this.offsetX = (Expression<Double>) expressions[3];
		this.offsetY = (Expression<Double>) expressions[4];
		this.offsetZ = (Expression<Double>) expressions[5];
		return true;
	}
	@Override
	protected void execute(Event event) {
		String ptc = this.particleName.getSingle(event).toUpperCase();
		Location loc = this.loc.getSingle(event);
		int amount = this.amount.getSingle(event);
		double offsetX = this.offsetX.getSingle(event);
		double offsetY = this.offsetY.getSingle(event);
		double offsetZ = this.offsetZ.getSingle(event);
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		if(amount == 0)
			loc.getWorld().spawnParticle(Particle.valueOf(ptc), x, y, z, amount, offsetX/255, offsetY/255, offsetZ/255, 1);
		else
			loc.getWorld().spawnParticle(Particle.valueOf(ptc), x, y, z, amount, offsetX, offsetY, offsetZ, 1);
	}

}

