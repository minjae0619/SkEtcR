package kor.riga.sketcr.Event;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import kor.riga.sketcr.Main;
import kor.riga.sketcr.Util.Variables;

public class VersionMessage implements Listener {

	@EventHandler
	public void onjoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.getUniqueId().toString().equals("cd81919e-adb2-4ec4-bf9e-37ef1be0c311")) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

				@Override
				public void run() {
					player.sendMessage("§a[SkEtcR] SkEtcR이 적용된 서버입니다  ( SkEtcR v" + Main .getInstance().getDescription().getVersion() + " )");
					player.sendMessage("§a[SkEtcR] SkEtcR이 적용된 서버입니다  ( SkEtcR v" + Main .getInstance().getDescription().getVersion() + " )");
					player.sendMessage("§a[SkEtcR] SkEtcR이 적용된 서버입니다  ( SkEtcR v" + Main .getInstance().getDescription().getVersion() + " )");
				}
			}, 20L);
		}
		if (player.isOp()) {
			if (Variables.getInstance().check) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					@Override
					public void run() {
						player.sendMessage("§a[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전이 존재합니다 ( 문의 : _R#8668 )");
						player.sendMessage("§a[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전이 존재합니다 ( 문의 : _R#8668 )");
						player.sendMessage("§a[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전이 존재합니다 ( 문의 : _R#8668 )");
						player.sendMessage("§a[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 애드온 다운로드 블로그 : https://blog.naver.com/pseongsil/222042861602");
					}
				}, 15L);
			}
		}
	}

}
