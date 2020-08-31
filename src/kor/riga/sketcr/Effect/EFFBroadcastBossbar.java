package kor.riga.sketcr.Effect;



import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.Main;
import kor.riga.sketcr.Util.Variables;



public class EFFBroadcastBossbar extends Effect{
	private Expression<String> message;
	private Expression<String> style;
	private Expression<String> color;
	private Expression<String> bbId;
	private Expression<Number> time;
	private int id;
	@Override
	public String toString(Event event, boolean b)
	{
		return "broadcast bossbar %string% with style %string% and color %string% of id %string% for %number% seconds";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.message = (Expression<String>) expressions[0];
		this.style = (Expression<String>) expressions[1];
		this.color = (Expression<String>) expressions[2];
		this.bbId = (Expression<String>) expressions[3];
		this.time = (Expression<Number>) expressions[4];
		return true;
	}
	@Override
	protected void execute(Event event) {
		String style = this.style.getSingle(event);
		String color = this.color.getSingle(event);
		String message = this.message.getSingle(event);
		Number time = this.time.getSingle(event);
		BossBar bossbar = Bukkit.createBossBar(message, BarColor.valueOf(color.toUpperCase()), BarStyle.valueOf(style.toUpperCase().replaceAll(" ", "_")), BarFlag.PLAY_BOSS_MUSIC);
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			bossbar.addPlayer(player);
		}
		bossbar.setProgress(1);
		String bbId = this.bbId.getSingle(event);
		int tick = (int) time.floatValue()*20;
		Variables.getInstance().bossbarList.put(bbId, bossbar);
		id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			private int amount = tick;
			@Override
			public void run() {
				try {
					bossbar.setProgress((float)amount/tick);
					amount--;
					if(amount < 0)
						bossbar.setProgress(0);
				} catch (Exception e) {
				}
					
			}
		}, 0L, 1L);
		final int i = id;
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				try {
					bossbar.removeAll();
					Variables.getInstance().bossbarList.remove(bbId, bossbar);
				} catch (Exception e) {
					// TODO: handle exception
				}
				Bukkit.getServer().getScheduler().cancelTask(i);
			}
		}, tick+5);
	}

}

