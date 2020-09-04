package kor.riga.sketcr.Effect;


import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;



public class EffParticle2 extends Effect{
	private Expression<String> particleName;
	private Expression<Location> loc;
	private Expression<Double> height;
	private Expression<Double> width;
	private Expression<Double> offsetX;
	private Expression<Double> offsetY;
	private Expression<Double> offsetZ;
	
	@Override
	public String toString(Event event, boolean b)
	{
	  return "particle spring %string% at %location% height %double% width %double% RGB %double%[,] %double%[,] %double%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.particleName = (Expression<String>) expressions[0];
		this.loc = (Expression<Location>) expressions[1];
		this.height = (Expression<Double>) expressions[2];
		this.width = (Expression<Double>) expressions[3];
		this.offsetX = (Expression<Double>) expressions[4];
		this.offsetY = (Expression<Double>) expressions[5];
		this.offsetZ = (Expression<Double>) expressions[6];
		return true;
	}
	@Override
	protected void execute(Event event) {
		String ptc = this.particleName.getSingle(event).toUpperCase();
		Location loc = this.loc.getSingle(event);
		double height = this.height.getSingle(event);
		double width = this.width.getSingle(event);
		double offsetX = this.offsetX.getSingle(event);
		double offsetY = this.offsetY.getSingle(event);
		double offsetZ = this.offsetZ.getSingle(event);
		World world = loc.getWorld();
		double t = 0;
		double r = width;
		for(int i = 0; i < height; i++) {
			t += Math.PI/16;
			double x = r * Math.cos(t);
			double y = t-(i*0.15);
			double z = r * Math.sin(t);
			loc.add(x,y,z);
			try {
				world.spawnParticle(Particle.valueOf(ptc), loc, 0, offsetX/255, offsetY/255, offsetZ/255, 1);
			} catch (Exception e) {
				world.spawnParticle(Particle.valueOf(ptc), loc, 0, 0, 0, 0, 1);
			}
			loc.subtract(x,y,z);
		}
	}


}

