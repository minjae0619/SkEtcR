package kor.riga.sketcr.Effect;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffColorGlow extends Effect {
	private Expression<Entity> entity;
	private Expression<String> str;

	@Override
	public String toString(Event event, boolean b) {
		return "color glow %string% to %entity%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.str = (Expression<String>) expressions[0];
		this.entity = (Expression<Entity>) expressions[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		Entity entity = this.entity.getSingle(event);
		String str = this.str.getSingle(event);
		entity.setGlowing(true);
		addScoreBoard(entity, str.toCharArray()[0]);
	}

	@SuppressWarnings("deprecation")
	private void addScoreBoard(Entity entity, char code) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team team = null;
		team = board.getTeam(code + "SkEtcRGlow");
		if(entity instanceof Player)
			team.addPlayer((Player)entity);
		else
			team.addEntry(entity.getUniqueId().toString());
	}
}
