package kor.riga.sketcr.API.ProtocolLib;

import org.bukkit.Bukkit;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import kor.riga.sketcr.API.ProtocolLib.Effect.EffDisableDamageParticle;
import kor.riga.sketcr.API.ProtocolLib.Effect.EffEnableDamageParticle;
import kor.riga.sketcr.API.ProtocolLib.Event.EvtAdvanOpen;
import kor.riga.sketcr.API.ProtocolLib.Event.EvtRidingKeyPress;
import kor.riga.sketcr.API.ProtocolLib.Expression.ExpPlayerSwing;
import kor.riga.sketcr.API.ProtocolLib.Expression.ExpRidingKey;
import kor.riga.sketcr.Util.CustomEvent.AdvanOpenEvent;
import kor.riga.sketcr.Util.CustomEvent.PlayerRidingKeyPressEvent;

public class ProtocolLib {

	public static void register() {
		if (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null)
			return;
		Skript.registerExpression(ExpPlayerSwing.class, Boolean.class, ExpressionType.PROPERTY,
				new String[] { "%player%'s swing" });
		Skript.registerEvent("ride", EvtRidingKeyPress.class, PlayerRidingKeyPressEvent.class, "riding key press");
		Skript.registerEvent("advan", EvtAdvanOpen.class, AdvanOpenEvent.class, "advan open");
		Skript.registerExpression(ExpRidingKey.class, String.class, ExpressionType.PROPERTY,
				new String[] { "event-press" });
		Skript.registerEffect(EffEnableDamageParticle.class, new String[] { "enable damage particle" });
		Skript.registerEffect(EffDisableDamageParticle.class, new String[] { "disable damage particle" });
	}
}
  