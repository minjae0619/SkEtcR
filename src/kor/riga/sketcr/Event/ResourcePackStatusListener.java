package kor.riga.sketcr.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import kor.riga.sketcr.Util.Variables;

public class ResourcePackStatusListener implements Listener{
	
	
	@EventHandler
	public void onResourcePack(PlayerResourcePackStatusEvent event) {
		Player player = event.getPlayer();
		if(!Variables.getInstance().resource) {
			return;
		}
		if(event.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED || event.getStatus() == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD) 
			player.kickPlayer(Variables.getInstance().resourcePackKickMessage);
	}
}
