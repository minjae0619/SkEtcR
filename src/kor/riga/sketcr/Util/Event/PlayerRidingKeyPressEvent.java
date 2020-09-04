package kor.riga.sketcr.Util.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerRidingKeyPressEvent extends PlayerEvent implements Cancellable{
	private static final HandlerList handlers = new HandlerList();
	
	private String press;
	private boolean cancel;
	

	public String getPress() {
		return press;
	}



	public void setPress(String press) {
		this.press = press;
	}



	public PlayerRidingKeyPressEvent(Player player, String press) {
		super(player);
		this.press = press;
	}


	public final HandlerList getHandlers() {
		return handlers;
	}
	public static final  HandlerList getHandlerList() {
		return handlers;
	}



	public final boolean isCancelled() {
		return cancel;
	}

	public final void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
}
