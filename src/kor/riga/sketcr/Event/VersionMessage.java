package kor.riga.sketcr.Event;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import kor.riga.sketcr.Main;
import kor.riga.sketcr.etc.Variables;

public class VersionMessage implements Listener {

	@EventHandler
	public void onjoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (player.getUniqueId().toString().equals("cd81919e-adb2-4ec4-bf9e-37ef1be0c311")) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {

				@Override
				public void run() {
					player.sendMessage("§a[SkEtcR] SkEtcR이 적용된 서버입니다");
					player.sendMessage("§a[SkEtcR] SkEtcR이 적용된 서버입니다");
					player.sendMessage("§a[SkEtcR] SkEtcR이 적용된 서버입니다");
					player.sendMessage("§a[SkEtcR] SkEtcR이 적용된 서버입니다");
					player.sendMessage("§a[SkEtcR] SkEtcR이 적용된 서버입니다");
				}
			}, 10L);
		}
		if (player.isOp()) {
			System.out.println(Variables.getInstance().check);
			if (Variables.getInstance().check) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					@Override
					public void run() {
						player.sendMessage("§a[ SkEtcR ] 최신버전이 존재합니다 ( 문의 : rr#3274 )");
						player.sendMessage("§a[ SkEtcR ] 블로그 : https://blog.naver.com/pseongsil/222042861602");
					}
				}, 10L);
			}
		}
	}

}
