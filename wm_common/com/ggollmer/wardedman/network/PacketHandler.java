package com.ggollmer.wardedman.network;

import com.ggollmer.wardedman.network.packet.PacketWardedMan;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	/***
     * Handles Packet250CustomPayload packets that are registered to an
     * IneveraCraft network channel
     * 
     * @param manager
     *            The NetworkManager associated with the current platform
     *            (client/server)
     * @param packet
     *            The Packet250CustomPayload that was received
     * @param player
     *            The Player associated with the packet
     */
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player)
	{
		PacketWardedMan packetWardedMan = PacketTypeHandler.buildPacket(packet.data);
		
		packetWardedMan.execute(manager, player);
	}
}
