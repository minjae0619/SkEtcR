package kor.riga.sketcr.Effect;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;



public class EffParticleBeam extends Effect{
	private Expression<String> particleName;
	private Expression<Double> damage;
	private Expression<Location> loc;
	private Expression<Integer> length;
	private Expression<Double> gap;
	private Expression<Double> offsetX;
	private Expression<Double> offsetY;
	private Expression<Double> offsetZ;
	
	@Override
	public String toString(Event event, boolean b)
	{
	  return "particle beam %string% at %location% damage %double% length %integer% gap %double% RGB %double%[,] %double%[,] %double%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.particleName = (Expression<String>) expressions[0];
		this.loc = (Expression<Location>) expressions[1];
		this.damage = (Expression<Double>) expressions[2];
		this.length = (Expression<Integer>) expressions[3];
		this.gap = (Expression<Double>) expressions[4];
		this.offsetX = (Expression<Double>) expressions[5];
		this.offsetY = (Expression<Double>) expressions[6];
		this.offsetZ = (Expression<Double>) expressions[7];
		return true;
	}
	@Override
	protected void execute(Event event) {
		String ptc = this.particleName.getSingle(event).toUpperCase();
		Location loc = this.loc.getSingle(event);
		int length = this.length.getSingle(event);
		double damage = this.damage.getSingle(event);
		double gap = this.gap.getSingle(event);
		double offsetX = this.offsetX.getSingle(event);
		double offsetY = this.offsetY.getSingle(event);
		double offsetZ = this.offsetZ.getSingle(event);
		World world = loc.getWorld();
		double t = 0;
		for(int i = 0; i < length; i++) {
			t += gap;
			Vector direction = loc.getDirection().normalize();
			double x = direction.getX() * t;
			double y = direction.getY() * t;
			double z = direction.getZ() * t;
			loc.add(x,y,z);
			try {
				if(loc.getBlock().getType() != Material.AIR)
					return;
				for(Entity entity : loc.getChunk().getEntities()) {
					if(entity.getLocation().distance(loc) < 1.0)
						entity.setFireTicks((int)damage);
					else if(entity instanceof Player) 
						if(((Player)entity).getEyeLocation().distance(loc) < 0.5)
							entity.setFireTicks((int)damage);
					
				}
				world.spawnParticle(Particle.valueOf(ptc), loc, 0, offsetX/255, offsetY/255, offsetZ/255, 1);
			} catch (Exception e) {
				world.spawnParticle(Particle.valueOf(ptc), loc, 0, 0, 0, 0, 1);
			}
			loc.subtract(x,y,z);
		}
	}


}

