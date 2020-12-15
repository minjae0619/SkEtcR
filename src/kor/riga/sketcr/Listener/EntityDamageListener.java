package kor.riga.sketcr.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.nisovin.magicspells.events.SpellApplyDamageEvent;

import kor.riga.sketcr.Main;

public class EntityDamageListener implements Listener{
	
	
	@EventHandler
	public void onMagicDamage(SpellApplyDamageEvent event) {
		Player player = event.getCaster();
		player.setMetadata("SkEtcRMagicSpell", new FixedMetadataValue(Main.getInstance(), event.getSpell().getName()));
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player))
			return;
		Player player = (Player) event.getDamager();
		if(!player.hasMetadata("SkEtcRMagicSpell"))
			return;
		player.removeMetadata("SkEtcRMagicSpell", Main.getInstance());
			
		
	}

}
