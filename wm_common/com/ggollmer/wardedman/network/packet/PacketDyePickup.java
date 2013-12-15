package com.ggollmer.wardedman.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketDyePickup extends PacketWardedMan
{
	public String username;
	public int damage;
	
	public PacketDyePickup() {
		super(PacketTypeHandler.DYEPICKUP, false);
	}
	
	public PacketDyePickup(String username, int damage) {
		super(PacketTypeHandler.DYEPICKUP, false);
		this.username = username;
		this.damage = damage;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		username = data.readUTF();
		damage = data.readInt();
    }

	@Override
    public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(username);
		data.writeInt(damage);
    }

	@Override
    public void execute(INetworkManager network, Player player) {
		LogHelper.debugLog("PacketDyePickup -- received");
		WardedMan.proxy.handleDyePickupPacket(username, damage);
    }
}
