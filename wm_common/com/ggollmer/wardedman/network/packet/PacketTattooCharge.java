package com.ggollmer.wardedman.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketTattooCharge extends PacketWardedMan
{
	public String username;
	public int charge;
	
	public PacketTattooCharge()
	{
		super(PacketTypeHandler.TATTOOCHARGE, false);
	}
	
	public PacketTattooCharge(String username, int charge) {
		super(PacketTypeHandler.TATTOOCHARGE, false);
		this.username = username;
		this.charge = charge;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		username = data.readUTF();
		charge = data.readInt();
    }

	@Override
    public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(username);
		data.writeInt(charge);
    }

	@Override
    public void execute(INetworkManager network, Player player) {
		LogHelper.debugLog("TattooChargePacket -- Handling");
		WardedMan.proxy.handleTattooChargePacket(username, charge);
    }
}
