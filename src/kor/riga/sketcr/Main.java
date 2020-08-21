package kor.riga.sketcr;

import kor.riga.sketcr.Condition.CommandAynchronous;
import kor.riga.sketcr.Condition.CondEven;
import kor.riga.sketcr.Effect.EFFBossbar;
import kor.riga.sketcr.Effect.EFFBroadcastBossbar;
import kor.riga.sketcr.Effect.EFFEnchant;
import kor.riga.sketcr.Effect.EFFMessageBox;
import kor.riga.sketcr.Effect.EFFNoteBlockFlat;
import kor.riga.sketcr.Effect.EFFNoteBlockNatural;
import kor.riga.sketcr.Effect.EFFNoteBlockSharp;
import kor.riga.sketcr.Effect.EFFNotePlay;
import kor.riga.sketcr.Effect.EFFNotePlayPlayer;
import kor.riga.sketcr.Effect.EFFRunCmdCommand;
import kor.riga.sketcr.Effect.EFFStopBossbar;
import kor.riga.sketcr.Effect.EffCallChat;
import kor.riga.sketcr.Effect.EffCallDamage;
import kor.riga.sketcr.Effect.EffCallDeath;
import kor.riga.sketcr.Effect.EffCallJoin;
import kor.riga.sketcr.Effect.EffCallQuit;
import kor.riga.sketcr.Effect.EffLore;
import kor.riga.sketcr.Effect.EffMagicCast;
import kor.riga.sketcr.Effect.EffMagicTeach;
import kor.riga.sketcr.Effect.EffOpenInv;
import kor.riga.sketcr.Effect.EffParticle;
import kor.riga.sketcr.Effect.EffParticle2;
import kor.riga.sketcr.Effect.EffParticle3;
import kor.riga.sketcr.Effect.EffPotionClear;
import kor.riga.sketcr.Effect.LoreClear;
import kor.riga.sketcr.Effect.Memory;
import kor.riga.sketcr.Event.EvtBlockGrow;
import kor.riga.sketcr.Event.EvtInventoryPickup;
import kor.riga.sketcr.Event.EvtItemMergeEvent;
import kor.riga.sketcr.Event.EvtLocaleChange;
import kor.riga.sketcr.Event.EvtMagicDamage;
import kor.riga.sketcr.Event.EvtNotePlay;
import kor.riga.sketcr.Event.EvtPlayerMove;
import kor.riga.sketcr.Event.EvtSlimeSplitEvent;
import kor.riga.sketcr.Event.EvtToggleGlide;
import kor.riga.sketcr.Event.VersionMessage;
import kor.riga.sketcr.Expression.ExpAccess;
import kor.riga.sketcr.Expression.ExpAddString;
import kor.riga.sketcr.Expression.ExpArrayClean;
import kor.riga.sketcr.Expression.ExpComma;
import kor.riga.sketcr.Expression.ExpCooldown;
import kor.riga.sketcr.Expression.ExpCrops_Age;
import kor.riga.sketcr.Expression.ExpDrop;
import kor.riga.sketcr.Expression.ExpEnchant;
import kor.riga.sketcr.Expression.ExpFish;
import kor.riga.sketcr.Expression.ExpGameRules;
import kor.riga.sketcr.Expression.ExpGetInventory;
import kor.riga.sketcr.Expression.ExpKeepInventory;
import kor.riga.sketcr.Expression.ExpMagicAttacker;
import kor.riga.sketcr.Expression.ExpMagicCaster;
import kor.riga.sketcr.Expression.ExpMagicDamage;
import kor.riga.sketcr.Expression.ExpMagicID;
import kor.riga.sketcr.Expression.ExpMagicVictim;
import kor.riga.sketcr.Expression.ExpPotion;
import kor.riga.sketcr.Expression.ExpSort;
import kor.riga.sketcr.Expression.File_List;
import kor.riga.sketcr.Expression.File_List_Name;
import kor.riga.sketcr.Expression.Time;
import kor.riga.sketcr.etc.VersionCheck;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerLocaleChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.nisovin.magicspells.events.SpellApplyDamageEvent;
import com.nisovin.magicspells.events.SpellCastEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;

public class Main extends JavaPlugin implements Listener {
	private static Main instance = null;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		//System.out.println(getDescription().getVersion());
		register();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new VersionMessage(), this);
		File file = new File("plugins\\SkEtcR\\Example.txt");
		file.delete();
		saveResource("Example.txt", false);
		this.getConfig().addDefault("개발자.닉네임", "__Riga");
		this.getConfig().addDefault("개발자.디스코드", "rr#3274");
		this.getConfig().addDefault("제작에 도움을 주신 분", "디코 : 짖지리#6654, 블로그 : https://blog.naver.com/pseongsil");
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		new VersionCheck().start();

	}

	@Override
	public void onDisable() {
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	    public void onTabComplete(PlayerChatTabCompleteEvent e) {
			System.out.println(123);
	        e.getTabCompletions().clear();
	}

	private void register() {
		if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
			Skript.registerAddon(this);
			Skript.registerExpression(ExpCooldown.class, Number.class, ExpressionType.PROPERTY,
					new String[] { "%player%'s cooldown of %string%" });
			Skript.registerExpression(ExpFish.class, ItemStack.class, ExpressionType.PROPERTY,
					new String[] { "event-fish" });
			Skript.registerExpression(ExpDrop.class, ItemStack.class, ExpressionType.PROPERTY,
					new String[] { "event-drop[s]" });
			Skript.registerExpression(ExpGameRules.class, String.class, ExpressionType.PROPERTY,
					new String[] { "gamerule %string% at %world%" });
			Skript.registerExpression(ExpAddString.class, String.class, ExpressionType.PROPERTY,
					new String[] { "combine %string%[ ]+[ ]%string%" });
			Skript.registerExpression(ExpCrops_Age.class, Number.class, ExpressionType.PROPERTY,
					new String[] { "crops age %block%" });
			Skript.registerExpression(Time.class, String.class, ExpressionType.PROPERTY,
					new String[] { "time %string%" });
			Skript.registerExpression(File_List.class, String.class, ExpressionType.PROPERTY,
					new String[] { "file list %string%" });
			Skript.registerExpression(ExpPotion.class, String.class, ExpressionType.PROPERTY,
					new String[] { "%player%'s potion[s]" });
			Skript.registerExpression(File_List_Name.class, String.class, ExpressionType.PROPERTY,
					new String[] { "file list name %string%" });
			Skript.registerExpression(ExpGetInventory.class, String.class, ExpressionType.PROPERTY,
					new String[] { "[%player%['s]][ ]inv[entory][ ]name" });
			Skript.registerExpression(ExpEnchant.class, Number.class, ExpressionType.PROPERTY,
					new String[] { "enchant of %number% of %itemstack%" });
			Skript.registerExpression(ExpAccess.class, Boolean.class, ExpressionType.PROPERTY,
					new String[] { "%player%'s access" });
			Skript.registerExpression(ExpComma.class, String.class, ExpressionType.PROPERTY,
					new String[] { "%number% apply comma" });
			Skript.registerExpression(ExpKeepInventory.class, Boolean.class, ExpressionType.PROPERTY,
					new String[] { "keep inventory" });
			Skript.registerExpression(ExpArrayClean.class, Object.class, ExpressionType.PROPERTY,
					new String[] { "clean array %objects%" });
			Skript.registerExpression(ExpSort.class, Number.class, ExpressionType.PROPERTY,
					new String[] { "sort in %numbers%" });
			Skript.registerEffect(EFFEnchant.class, new String[] { "clear enchant of %itemstack%" });
			Skript.registerEffect(EffCallDamage.class, new String[] { "call[ ]event damage %entity% by %entity% cause %string% damage %double%" });
			Skript.registerEffect(EffCallChat.class, new String[] { "call[ ]event chat %player%" });
			Skript.registerEffect(EffCallJoin.class, new String[] { "call[ ]event join %player%" });
			Skript.registerEffect(EffCallQuit.class, new String[] { "call[ ]event quit %player%" });
			Skript.registerEffect(EffCallDeath.class, new String[] { "call[ ]event death %player%" });
			Skript.registerEffect(EffOpenInv.class, new String[] { "open[ ]inv %number% and %string% to %player%" });
			Skript.registerEffect(EFFBossbar.class, new String[] { "send bossbar %string% with style %string% and color %string% of id %string% to %player% for %number% seconds" });
			Skript.registerEffect(EFFStopBossbar.class, new String[] { "stop[ ]b[oss]b[ar] id %string%" });
			Skript.registerEffect(EFFBroadcastBossbar.class, new String[] { "broadcast bossbar %string% with style %string% and color %string% of id %string% for %number% seconds" });
			// Skript.registerEffect(EFFFalling.class, new String[] { "falling block
			// %string% with %byte% at %location%" });
			Skript.registerEffect(EFFNoteBlockFlat.class, new String[] {
					"play noteblock flat instrument %string% and tone %string% at %location% to %player%" });
			Skript.registerEffect(EFFNoteBlockNatural.class, new String[] {
					"play noteblock natural instrument %string% and tone %string% at %location% to %player%" });
			Skript.registerEffect(EFFNoteBlockSharp.class, new String[] {
					"play noteblock sharp instrument %string% and tone %string% at %location% to %player%" });
			Skript.registerEffect(EFFNotePlay.class,
					new String[] { "play noteblock %string% of %float% with volume %float% at %location%" });
			Skript.registerEffect(EFFNotePlayPlayer.class,
					new String[] { "play noteblock %string% of %float% with volume %float% for %player%" });
			Skript.registerEffect(EffPotionClear.class, new String[] { "clear %player%'s potion[s]" });
			Skript.registerEffect(EffLore.class, new String[] { "lore{%itemstack%, %number%, %string%}" });
			Skript.registerEffect(LoreClear.class, new String[] { "clear lore of %itemstack%" });
			Skript.registerEffect(EFFMessageBox.class, new String[] { "messagebox %string%" });
			Skript.registerEffect(EFFRunCmdCommand.class, new String[] { "run cmd command %string%" });
			Skript.registerEffect(EffParticle.class, new String[] {
					"particle %string% of %integer% at %location% ([offset]XYZ|RGB) %double%[,] %double%[,] %double%" });
			Skript.registerEffect(EffParticle2.class, new String[] {
					"particle spring %string% at %location% height %double% width %double% RGB %double%[,] %double%[,] %double%" });
			Skript.registerEffect(EffParticle3.class, new String[] {
					"particle beam %string% at %location% length %integer% gap %double% RGB %double%[,] %double%[,] %double%" });
			Skript.registerEffect(Memory.class, new String[] { "memory optimize" });
			Skript.registerEvent("Player Move", EvtPlayerMove.class, PlayerMoveEvent.class,
					"player move [of] [%string%][,] [%string%][,] [%string%]");
			Skript.registerEvent("block grow", EvtBlockGrow.class, BlockGrowEvent.class, "[block] grow");
			Skript.registerEvent("locale", EvtLocaleChange.class, PlayerLocaleChangeEvent.class, "locale [change]");
			Skript.registerEvent("glide", EvtToggleGlide.class, EntityToggleGlideEvent.class, "toggle glide");
			Skript.registerEvent("NotePlay", EvtNotePlay.class, NotePlayEvent.class, "note play");
			Skript.registerEvent("merge", EvtItemMergeEvent.class, ItemMergeEvent.class, "[item] merge");
			Skript.registerEvent("slime", EvtSlimeSplitEvent.class, SlimeSplitEvent.class, "slime split");
			Skript.registerEvent("inv pickup", EvtInventoryPickup.class, InventoryPickupItemEvent.class,
					"[inventory] pickup item");
			Skript.registerCondition(CommandAynchronous.class, "command (1¦is|2¦is(n't| not)) exist");
			Skript.registerCondition(CondEven.class, "%number% (1¦is|2¦is(n't| not)) even");
			// Skript.registerCondition(CondKeepInventory.class, "inventory (1¦is|2¦is(n't|
			// not)) keep");
			//MagicSpells
			if (Bukkit.getPluginManager().getPlugin("MagicSpells") != null) {
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
				Skript.registerExpression(ExpMagicCaster.class, Player.class, ExpressionType.PROPERTY,
						new String[] { "m[agic][-]cooldown" });
				Skript.registerEvent("damage", EvtMagicDamage.class, SpellApplyDamageEvent.class, "m[agic][ ]damage");
				Skript.registerEvent("cast", EvtMagicDamage.class, SpellCastEvent.class, "m[agic][ ]cast");
			}
			return;
		}

		Bukkit.getPluginManager().disablePlugin(this);
	}
}
