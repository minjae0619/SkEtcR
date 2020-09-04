package kor.riga.sketcr.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;

import kor.riga.sketcr.Main;
import kor.riga.sketcr.Util.Event.PlayerRidingKeyPressEvent;

public class Packet {

	public static void start() {
		ProtocolManager manager = ProtocolLibrary.getProtocolManager();
		System.out.println("[SkEtcR] 패킷을 사용합니다");
		System.out.println("[SkEtcR] 패킷을 사용합니다");
		steerVehicle(manager);
		DamageParticleCancel(manager);
	}

	private static void DamageParticleCancel(ProtocolManager manager) {
		
		
		manager.addPacketListener(
				new PacketAdapter(Main.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Server.WORLD_PARTICLES) {

					@Override
					public void onPacketSending(PacketEvent event) {
						if (event.getPacketType() == PacketType.Play.Server.WORLD_PARTICLES) {
							for(EnumWrappers.Particle p : event.getPacket().getParticles().getValues()) {
								if(p == EnumWrappers.Particle.DAMAGE_INDICATOR)
									if(Variables.getInstance().damageParticle)
										event.setCancelled(true);
							}
						}
					}
					
			});
	}

	private static void steerVehicle(ProtocolManager manager) {
		manager.addPacketListener(
				new PacketAdapter(Main.getInstance(), ListenerPriority.NORMAL, PacketType.Play.Client.STEER_VEHICLE) {

					@Override
					public void onPacketReceiving(PacketEvent event) {
						final Player player = event.getPlayer();
						if (event.getPacketType() == PacketType.Play.Client.STEER_VEHICLE
								&& player.getVehicle() != null) {
							String press = "";
							final float right = event.getPacket().getFloat().readSafely(0);

							final float forward = event.getPacket().getFloat().readSafely(1);
							if (right > 0)
								press = "a";
							if (right < 0)
								press = "d";
							if (forward > 0)
								press = "w";
							if (forward < 0)
								press = "s";
							try {
								if (event.getPacket().getBooleans().readSafely(1)) {
									press = "shift";
								}
								if (event.getPacket().getBooleans().readSafely(0)) {
									press = "space";
								}
							} catch (Error | Exception e45) {
							}
							if (press != "") {
								PlayerRidingKeyPressEvent rideEvent = new PlayerRidingKeyPressEvent(player, press);
								try {
									Bukkit.getServer().getPluginManager().callEvent(rideEvent);
								} catch (Exception e) {
									Bukkit.getScheduler().runTask(Main.getInstance(),
											() -> Bukkit.getServer().getPluginManager().callEvent(rideEvent));
								}
								if (rideEvent.isCancelled())
									event.setCancelled(true);
							}

						}
					}

				});
	}
}
