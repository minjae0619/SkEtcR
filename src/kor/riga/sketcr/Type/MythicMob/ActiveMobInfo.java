package kor.riga.sketcr.Type.MythicMob;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ActiveMobInfo {

	public static void register() {
		Classes.registerClass(new ClassInfo<>(ActiveMob.class, "mythicmob")
				.user("mythicmobs?")
				.name("MythicMob")
				.defaultExpression(new EventValueExpression<>(ActiveMob.class))
				.parser(new Parser<ActiveMob>() {
					public ActiveMob parse(String s, ParseContext context) {
						try {
							UUID uuid = UUID.fromString(s);
							Entity entity = Bukkit.getEntity(uuid);
							return MythicMobs.inst().getAPIHelper().getMythicMobInstance(entity);
						} catch (Exception e) {
						}
						return null;
					}

					public boolean canParse(ParseContext context) {
						return context == ParseContext.COMMAND;
					}

					public String toCommandString(ActiveMob o) {
						return null;
					}

					public String getDebugMessage(ActiveMob o) {
						return null;
					}

					public String toString(ActiveMob mob, int i) {
						return toVariableNameString(mob);
					}

					public String toVariableNameString(ActiveMob mob) {
						return mob.getType().getInternalName();
					}

					public String getVariableNamePattern() {
						return "mob:[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
					}
				}).serializer(new Serializer<ActiveMob>() {
					public Fields serialize(ActiveMob mob) throws NotSerializableException {
						return null;
					}

					public void deserialize(ActiveMob mob, Fields fields)
							throws StreamCorruptedException, NotSerializableException {
						assert false;
					}

					public boolean mustSyncDeserialization() {
						return false;
					}

					protected boolean canBeInstantiated() {
						return false;
					}
				}));
	}
}
