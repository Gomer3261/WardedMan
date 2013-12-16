package com.ggollmer.wardedman.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketTattooRemove extends PacketWardedMan
{
	public int location;
	public String username;
	
	public PacketTattooRemove()
	{
		super(PacketTypeHandler.TATTOOREMOVE, false);
	}
	
	public PacketTattooRemove(String username, int location) {
		super(PacketTypeHandler.TATTOOREMOVE, false);
		this.username = username;
		this.location = location;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		username = data.readUTF();
		location = data.readInt();
    }

	@Override
    public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(username);
		data.writeInt(location);
    }

	@Override
    public void execute(INetworkManager network, Player player) {
		LogHelper.debugLog("TattooUpdatedPacket -- Handling");
		WardedMan.proxy.handleTattooRemovePacket(username, location);
    }
}
