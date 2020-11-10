package kor.riga.sketcr.Util.CustomEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class AdvanOpenEvent extends PlayerEvent implements Cancellable{
	private static final HandlerList handlers = new HandlerList();
	
	private boolean cancel;
	

	public AdvanOpenEvent(Player player) {
		super(player);
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
