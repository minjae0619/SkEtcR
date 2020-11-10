package kor.riga.sketcr.Util.CustomEvent;

public class RidingPacket extends net.minecraft.server.v1_12_R1.PlayerConnection {

	public RidingPacket(net.minecraft.server.v1_12_R1.EntityPlayer entityplayer) {
		super(entityplayer.server, entityplayer.playerConnection.networkManager, entityplayer);
	}

	
	@Override
	public void a(net.minecraft.server.v1_12_R1.PacketPlayInSteerVehicle packetplayinsteervehicle){
		
	}
	
}
