package kor.riga.sketcr.Effect;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kor.riga.sketcr.Util.Packet.NMS;

public class EffChangeYawPitch extends Effect {
	private Expression<Player> player;
	private Expression<Number> yaw;
	private Expression<Number> pitch;

	@Override
	public String toString(Event event, boolean b) {
		return "change yaw %number% pitch %number% to %player%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.yaw = (Expression<Number>) expressions[0];
		this.pitch = (Expression<Number>) expressions[1];
		this.player = (Expression<Player>) expressions[2];
		return true;
	}

	@Override
	protected void execute(Event event) {
		try {
			Player player = this.player.getSingle(event);
			float yaw = this.yaw.getSingle(event).floatValue();
			float pitch = this.pitch.getSingle(event).floatValue();
			NMS.changePositionPacket(player, yaw, pitch);
			//Packet.yawPitch((CraftPlayer)player, yaw, pitch);	
		} catch (Exception e) {
		}
	}

}
