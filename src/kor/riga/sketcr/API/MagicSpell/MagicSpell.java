package kor.riga.sketcr.API.MagicSpell;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.nisovin.magicspells.events.SpellApplyDamageEvent;
import com.nisovin.magicspells.events.SpellCastEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import kor.riga.sketcr.API.MagicSpell.Condition.CondMagicSpell;
import kor.riga.sketcr.API.MagicSpell.Effect.EffMagicCast;
import kor.riga.sketcr.API.MagicSpell.Effect.EffMagicForget;
import kor.riga.sketcr.API.MagicSpell.Effect.EffMagicTeach;
import kor.riga.sketcr.API.MagicSpell.Event.EvtMagicCast;
import kor.riga.sketcr.API.MagicSpell.Event.EvtMagicDamage;
import kor.riga.sketcr.API.MagicSpell.Expression.ExpMagicAttacker;
import kor.riga.sketcr.API.MagicSpell.Expression.ExpMagicCaster;
import kor.riga.sketcr.API.MagicSpell.Expression.ExpMagicCooldown;
import kor.riga.sketcr.API.MagicSpell.Expression.ExpMagicDamage;
import kor.riga.sketcr.API.MagicSpell.Expression.ExpMagicID;
import kor.riga.sketcr.API.MagicSpell.Expression.ExpMagicVictim;

public class MagicSpell {

	public static void register() {
		if (Bukkit.getPluginManager().getPlugin("MagicSpells") == null)
			return;
		Skript.registerEffect(EffMagicForget.class, new String[] { "[magic[ ]]forget %string% to %player%" });
		Skript.registerEffect(EffMagicTeach.class, new String[] { "[magic[ ]]teach %string% to %player%" });
		Skript.registerEffect(EffMagicCast.class, new String[] { "[magic[ ]]cast %string% to %player%" });
		Skript.registerExpression(ExpMagicDamage.class, Number.class, ExpressionType.PROPERTY,
				new String[] { "m[agic][-]damage" });
		Skript.registerExpression(ExpMagicID.class, String.class, ExpressionType.PROPERTY,
				new String[] { "m[agic][-]id" });
		Skript.registerExpression(ExpMagicVictim.class, Entity.class, ExpressionType.PROPERTY,
				new String[] { "m[agic][-]victim" });
		Skript.registerExpression(ExpMagicAttacker.class, Entity.class, ExpressionType.PROPERTY,
				new String[] { "m[agic][-]attacker" });
		Skript.registerExpression(ExpMagicCaster.class, Player.class, ExpressionType.PROPERTY,
				new String[] { "m[agic][-]caster" });
		Skript.registerExpression(ExpMagicCooldown.class, Float.class, ExpressionType.PROPERTY,
				new String[] { "%player%['s] m[agic[ ]]cooldown of %string%" });
		Skript.registerEvent("damage", EvtMagicDamage.class, SpellApplyDamageEvent.class, "m[agic][ ]damage");
		Skript.registerEvent("cast", EvtMagicCast.class, SpellCastEvent.class, "m[agic][ ]cast");
		Skript.registerCondition(CondMagicSpell.class, "attack (1¦is|2¦is(n't| not)) magic");
	}
}
