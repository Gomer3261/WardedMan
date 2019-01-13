package com.ggollmer.wardedman.network;

import com.ggollmer.wardedman.network.packet.PacketDyePickup;
import com.ggollmer.wardedman.network.packet.PacketTattooCharge;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.network.packet.PacketTattooRemove;
import com.ggollmer.wardedman.network.packet.PacketTattooRequest;
import com.ggollmer.wardedman.network.packet.PacketTattooUpdate;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
	private static int packetId = 0;
	
	public static SimpleNetworkWrapper INSTANCE = null;
	
	public PacketHandler() {
	}
	
	public static int nextID() {
		return packetId++;
	}
	
	public static void registerMessages(String channelName) {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		registerMessages();
	}
	
	public static void registerMessages() {
		// Packets sent from server to client
		INSTANCE.registerMessage(PacketDyePickup.Handler.class, PacketDyePickup.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketTattooCharge.Handler.class, PacketTattooCharge.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketTattooData.Handler.class, PacketTattooData.class, nextID(), Side.CLIENT);
		INSTANCE.registerMessage(PacketTattooUpdate.Handler.class, PacketTattooUpdate.class, nextID(), Side.CLIENT);
		
		// Packets sent from client to server
		INSTANCE.registerMessage(PacketTattooRemove.Handler.class, PacketTattooRemove.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(PacketTattooRequest.Handler.class, PacketTattooRequest.class, nextID(), Side.SERVER);
	}
}
