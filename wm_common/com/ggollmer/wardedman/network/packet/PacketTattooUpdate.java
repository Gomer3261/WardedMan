package com.ggollmer.wardedman.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketTattooUpdate extends PacketWardedMan
{
	public int id, location, colour;
	public String username;
	
	public PacketTattooUpdate()
	{
		super(PacketTypeHandler.TATTOOUPDATE, false);
	}
	
	public PacketTattooUpdate(String username, int location, int id, int colour) {
		super(PacketTypeHandler.TATTOOUPDATE, false);
		this.username = username;
		this.location = location;
		this.id = id;
		this.colour = colour;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		username = data.readUTF();
		location = data.readInt();
		id = data.readInt();
		colour = data.readInt();
    }

	@Override
    public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(username);
		data.writeInt(location);
		data.writeInt(id);
		data.writeInt(colour);
    }

	@Override
    public void execute(INetworkManager network, Player player) {
		LogHelper.debugLog("TattooUpdatedPacket -- Handling");
		WardedMan.proxy.handleTattooUpdatePacket(username, location, id, colour);
    }
}
