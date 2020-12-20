package kor.riga.sketcr.API.MythicMobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDespawnEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import kor.riga.sketcr.API.MythicMobs.Condition.CondMythicMob;
import kor.riga.sketcr.API.MythicMobs.Effect.EffMobSpawn;
import kor.riga.sketcr.API.MythicMobs.Event.EvtMobDeathEvent;
import kor.riga.sketcr.API.MythicMobs.Event.EvtMobDespawnEvent;
import kor.riga.sketcr.API.MythicMobs.Event.EvtMobSpawnEvent;
import kor.riga.sketcr.API.MythicMobs.Expression.ExpMobDamage;
import kor.riga.sketcr.API.MythicMobs.Expression.ExpMobLevel;
import kor.riga.sketcr.API.MythicMobs.Expression.ExpMobType;
import kor.riga.sketcr.Type.MythicMob.ActiveMobInfo;
public class MythicMobs {

	public static MythicMob getMythicMob(ActiveMob mob) {
		return mob.getType();
	}
	
	public static void register() {
		if (Bukkit.getPluginManager().getPlugin("MythicMobs") == null) 
			return;
		ActiveMobInfo.register();
		Skript.registerExpression(ExpMobType.class, String.class, ExpressionType.PROPERTY, new String[] { "(name of %mythicmob%|%mythicmob%'s name)" });
		Skript.registerExpression(ExpMobType.class, String.class, ExpressionType.PROPERTY, new String[] { "(type of %mythicmob%|%mythicmob%'s type)" });
		Skript.registerExpression(ExpMobDamage.class, Double.class, ExpressionType.PROPERTY, new String[] { "(damage of %mythicmob%|%mythicmob%'s damage)" });
		Skript.registerExpression(ExpMobLevel.class, Double.class, ExpressionType.PROPERTY, new String[] { "(level of %mythicmob%|%mythicmob%'s level)" });
		Skript.registerCondition(CondMythicMob.class, "%entity% (1¦is|2¦is(n't| not)) (mm|mythicmob[s])");
		Skript.registerEffect(EffMobSpawn.class, new String[] { "spawn %string% at %location%" });
		Skript.registerEvent("mm spawn", EvtMobSpawnEvent.class, MythicMobSpawnEvent.class, "(mm|mythicmob[s]) spawn");
//		Skript.\
		
		EventValues.registerEventValue(MythicMobSpawnEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicMobSpawnEvent>() {
		    @Override
		    public ActiveMob get(MythicMobSpawnEvent e) {
		        return e.getMob();
		    }
		}, 0);
		EventValues.registerEventValue(MythicMobSpawnEvent.class, Entity.class, new Getter<Entity, MythicMobSpawnEvent>() {
		    @Override
		    public Entity get(MythicMobSpawnEvent e) {
		        return e.getEntity();
		    }
		}, 0);
		
		
		Skript.registerEvent("mm despawn", EvtMobDespawnEvent.class, MythicMobDespawnEvent.class, "(mm|mythicmob[s]) despawn");
		EventValues.registerEventValue(MythicMobDespawnEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicMobDespawnEvent>() {
		    @Override
		    public ActiveMob get(MythicMobDespawnEvent e) {
		        return e.getMob();
		    }
		}, 0);
		EventValues.registerEventValue(MythicMobDespawnEvent.class, Entity.class, new Getter<Entity, MythicMobDespawnEvent>() {
		    @Override
		    public Entity get(MythicMobDespawnEvent e) {
		        return e.getEntity();
		    }
		}, 0);
		
		
		Skript.registerEvent("mm death", EvtMobDeathEvent.class, MythicMobDeathEvent.class, "(mm|mythicmob[s]) death");
		EventValues.registerEventValue(MythicMobDeathEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicMobDeathEvent>() {
		    @Override
		    public ActiveMob get(MythicMobDeathEvent e) {
		        return e.getMob();
		    }
		}, 0);
		EventValues.registerEventValue(MythicMobDeathEvent.class, Entity.class, new Getter<Entity, MythicMobDeathEvent>() {
		    @Override
		    public Entity get(MythicMobDeathEvent e) {
		        return e.getEntity();
		    }
		}, 0);
	}
	
}
