package kor.riga.sketcr.Effect;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffCmdOp extends Effect {
	private Expression<Player> player;
	private Expression<String> cmd;
	
	@Override
	public String toString(Event event, boolean b) {
		return "%player% op c[om]m[an]d %string%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.player = (Expression<Player>) expressions[0];
		this.cmd = (Expression<String>) expressions[1];
		return true;
	}

	@Override
	protected void execute(Event event) {
		Player player = this.player.getSingle(event);
		String cmd = this.cmd.getSingle(event);
		assert cmd != null;
		if(cmd.startsWith("/"))
			cmd = cmd.substring(1);
		if(player.isOp())
			Bukkit.getServer().dispatchCommand(player, cmd);
		else {
			player.setOp(true);
			Bukkit.getServer().dispatchCommand(player, cmd);
			player.setOp(false);
		}
			
	}

}
