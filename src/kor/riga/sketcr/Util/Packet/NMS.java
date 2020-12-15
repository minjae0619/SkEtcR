package kor.riga.sketcr.Util.Packet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

import kor.riga.sketcr.Main;

public class NMS {

	private static String nmsVersion;

	public static String getNmsVersion() {
		if (nmsVersion == null) {
			String version = Main.getVersion().replace(".", "_");
			String nms = "v";
			int i = 0;
			for (byte b : version.getBytes()) {
				char c = (char) b;
				if (c == '_')
					i++;
				nms += c;
				if (i == 2)
					break;
			}
			for (int a = 1; a <= 5; a++) {
				try {
					nmsVersion = nms + "R"+a;
					Class.forName("org.bukkit.craftbukkit." + nmsVersion + ".entity.CraftPlayer");
					break;
				} catch (Exception e3) {
					nmsVersion = nms + "R"+(a+1);
				}

			}
		}
		return nmsVersion;
	}

	static Class<?> getCraftPlayerClass() {
		try {
			return Class.forName("org.bukkit.craftbukkit." + getNmsVersion() + ".entity.CraftPlayer");
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	static Object getCraftPlayer(Player player) {
		return getCraftPlayerClass().cast(player);
	}

	static Class<?> getNMSClass(String name) {
		try {
			return Class.forName("net.minecraft.server." + getNmsVersion() + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	static Class<?> getCraftBukkitClass(String name) {
		try {
			return Class.forName("org.bukkit.craftbukkit." + getNmsVersion() + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void changePositionPacket(Player player, float yaw, float pitch) {
		//PacketPlayOutPosition packet = new PacketPlayOutPosition(1, 1, 1, 1, 1, null, 1);
		//((CraftPlayer)player).getHandle().playerConnection.sendPacket("");
		
		try {
			Object packet = null;
			Class<?> craftPlayerClass = getCraftPlayerClass();
			Object craftPlayer = getCraftPlayer(player);
			Class<?> packetPlayOutClass = getNMSClass("PacketPlayOutPosition");
			Class<?> packetClass = getNMSClass("Packet");
			Class<?> teleportFlagsClass = getNMSClass("PacketPlayOutPosition$EnumPlayerTeleportFlags");
			Object[] enums = teleportFlagsClass.getEnumConstants();
			Set<Object> teleportFlags = new HashSet<>(Arrays.asList(enums[0], enums[1], enums[2]));
			packet = packetPlayOutClass.getConstructor(new Class[] { double.class, double.class, double.class,
					float.class, float.class, Set.class, int.class })
					.newInstance(new Object[] { 0, 0, 0, yaw, pitch, teleportFlags, 0 });
			Method craftPlayerHandleMethod = craftPlayerClass.getDeclaredMethod("getHandle", new Class[0]);
			Object craftPlayerHandle = craftPlayerHandleMethod.invoke(craftPlayer, new Object[0]);
			Field playerConnectionField = craftPlayerHandle.getClass().getDeclaredField("playerConnection");
			Object playerConnection = playerConnectionField.get(craftPlayerHandle);
			Method sendPacketMethod = playerConnection.getClass().getDeclaredMethod("sendPacket",
					new Class[] { packetClass });
			sendPacketMethod.invoke(playerConnection, new Object[] { packet });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
