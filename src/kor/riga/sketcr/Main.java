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
import kor.riga.sketcr.Effect.EffCmdOp;
import kor.riga.sketcr.Effect.EffDisableDamageParticle;
import kor.riga.sketcr.Effect.EffEnableDamageParticle;
import kor.riga.sketcr.Effect.EffLore;
import kor.riga.sketcr.Effect.EffMagicCast;
import kor.riga.sketcr.Effect.EffMagicTeach;
import kor.riga.sketcr.Effect.EffOpenInv;
import kor.riga.sketcr.Effect.EffParticle;
import kor.riga.sketcr.Effect.EffParticle2;
import kor.riga.sketcr.Effect.EffParticle3;
import kor.riga.sketcr.Effect.EffPotionClear;
import kor.riga.sketcr.Effect.EffSort;
import kor.riga.sketcr.Effect.LoreClear;
import kor.riga.sketcr.Effect.Memory;
import kor.riga.sketcr.Event.EvtBlockGrow;
import kor.riga.sketcr.Event.EvtInventoryPickup;
import kor.riga.sketcr.Event.EvtItemDamage;
import kor.riga.sketcr.Event.EvtItemMergeEvent;
import kor.riga.sketcr.Event.EvtLocaleChange;
import kor.riga.sketcr.Event.EvtMagicCast;
import kor.riga.sketcr.Event.EvtMagicDamage;
import kor.riga.sketcr.Event.EvtNotePlay;
import kor.riga.sketcr.Event.EvtPlayerMove;
import kor.riga.sketcr.Event.EvtRidingKeyPress;
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
import kor.riga.sketcr.Expression.ExpMagicCooldown;
import kor.riga.sketcr.Expression.ExpMagicDamage;
import kor.riga.sketcr.Expression.ExpMagicID;
import kor.riga.sketcr.Expression.ExpMagicVictim;
import kor.riga.sketcr.Expression.ExpPotion;
import kor.riga.sketcr.Expression.ExpRidingKey;
import kor.riga.sketcr.Expression.ExpSort;
import kor.riga.sketcr.Expression.File_List;
import kor.riga.sketcr.Expression.File_List_Name;
import kor.riga.sketcr.Expression.Time;
import kor.riga.sketcr.Util.Packet;
import kor.riga.sketcr.Util.Event.PlayerRidingKeyPressEvent;
import kor.riga.sketcr.etc.VersionCheck;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
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
		//apiList = new ArrayList<String>();
		this.saveDefaultConfig();
		if(!getConfig().isSet("Packet")) {
			new File("plugins//SkEtcR//config.yml").delete();
			this.saveDefaultConfig();
		}
		if(getConfig().getBoolean("Packet"))
			Packet.start();
		else {
			System.out.println("[SkEtcR] - 패킷을 사용하지 않습니다");
			System.out.println("[SkEtcR] - 패킷을 사용하지 않습니다");
		}
		// System.out.println(getDescription().getVersion());
		register();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new VersionMessage(), this);
		File file = new File("plugins\\SkEtcR\\Example.txt");
		file.delete();
		saveResource("Example.txt", false);
		new VersionCheck().start();

	}

	@Override
	public void onDisable() {
		//unloadAPI();
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
					new String[] { "enchant of %number% in %itemstack%" });
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
			Skript.registerEffect(EffCmdOp.class, new String[] { "%player% op c[om]m[an]d %string%" });
			Skript.registerEffect(EffSort.class, new String[] { "sort index %objects% value %numbers% in %string%" });
			Skript.registerEffect(EffCallDamage.class,
					new String[] { "call[ ]event damage %entity% by %entity% cause %string% damage %double%" });
			Skript.registerEffect(EffCallChat.class, new String[] { "call[ ]event chat %player%" });
			Skript.registerEffect(EffCallJoin.class, new String[] { "call[ ]event join %player%" });
			Skript.registerEffect(EffCallQuit.class, new String[] { "call[ ]event quit %player%" });
			Skript.registerEffect(EffCallDeath.class, new String[] { "call[ ]event death %player%" });
			Skript.registerEffect(EffOpenInv.class, new String[] { "open[ ]inv %number% and %string% to %player%" });
			Skript.registerEffect(EFFBossbar.class, new String[] {
					"send bossbar %string% with style %string% and color %string% of id %string% to %player% for %number% seconds" });
			Skript.registerEffect(EFFStopBossbar.class, new String[] { "stop[ ]b[oss]b[ar] id %string%" });
			Skript.registerEffect(EFFBroadcastBossbar.class, new String[] {
					"broadcast bossbar %string% with style %string% and color %string% of id %string% for %number% seconds" });
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
			Skript.registerEvent("itemDamage", EvtItemDamage.class, PlayerRidingKeyPressEvent.class,
					"player i[tem][ ]damage");
			Skript.registerEvent("inv pickup", EvtInventoryPickup.class, InventoryPickupItemEvent.class,
					"[inventory] pickup item");
			Skript.registerCondition(CommandAynchronous.class, "command (1¦is|2¦is(n't| not)) exist");
			Skript.registerCondition(CondEven.class, "%number% (1¦is|2¦is(n't| not)) even");
			// Skript.registerCondition(CondKeepInventory.class, "inventory (1¦is|2¦is(n't|
			// not)) keep");
			// MagicSpells
			if (Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) {
				Skript.registerEvent("ride", EvtRidingKeyPress.class, PlayerRidingKeyPressEvent.class, "riding key press");
				Skript.registerExpression(ExpRidingKey.class, String.class, ExpressionType.PROPERTY,
						new String[] { "event-press" });
				Skript.registerEffect(EffEnableDamageParticle.class, new String[] { "enable damage particle" });
				Skript.registerEffect(EffDisableDamageParticle.class, new String[] { "disable damage particle" });
			}
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
				Skript.registerExpression(ExpMagicCooldown.class, Float.class, ExpressionType.PROPERTY,
						new String[] { "%player%['s] m[agic[ ]]cooldown of %string%" });
				Skript.registerEvent("damage", EvtMagicDamage.class, SpellApplyDamageEvent.class, "m[agic][ ]damage");
				Skript.registerEvent("cast", EvtMagicCast.class, SpellCastEvent.class, "m[agic][ ]cast");
			}
			return;
		}

		Bukkit.getPluginManager().disablePlugin(this);
	}

/*	private File tempFile;
	private ArrayList<String> apiList;

	private void unloadAPI() {
		for (String s : apiList) {
			File[] lf = tempFile.getParentFile().listFiles();
			if (lf != null) {
				for (File f : lf) {
					if (f.getName().startsWith(s)) {
						f.delete();
					}
				}
			}
		}
	}

	private void loadAPI(String str, String str2, String url) {
		try {
			tempFile = File.createTempFile(str, str2);
			InputStream rin = getResource(url);
			FileOutputStream fo = new FileOutputStream(tempFile);

			int b;
			while ((b = rin.read()) != -1) {
				fo.write(b);
			}
			rin.close();
			fo.close();

			JarUtils.extractFromJar(tempFile.getName(), tempFile.getAbsolutePath());
			addClassPath(JarUtils.getJarUrl(tempFile));
			apiList.add(str);
			System.out.println("완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addClassPath(final URL url) throws IOException {
		final URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		final Class<URLClassLoader> sysclass = URLClassLoader.class;
		try {
			final Method method = sysclass.getDeclaredMethod("addURL", new Class[] { URL.class });
			method.setAccessible(true);
			method.invoke(sysloader, new Object[] { url });
		} catch (final Throwable t) {
			t.printStackTrace();
			throw new IOException("Error adding " + url + " to system classloader");
		}
	}*/
}
