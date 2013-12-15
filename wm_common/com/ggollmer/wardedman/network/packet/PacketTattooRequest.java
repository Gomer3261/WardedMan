package com.ggollmer.wardedman.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketTattooRequest extends PacketWardedMan
{
	public int id, location, colour;
	public String username;
	
	public PacketTattooRequest()
	{
		super(PacketTypeHandler.TATTOOREQ, false);
	}
	
	public PacketTattooRequest(String username, int id, int location, int colour) {
		super(PacketTypeHandler.TATTOOREQ, false);
		this.username = username;
		this.id = id;
		this.location = location;
		this.colour = colour;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		username = data.readUTF();
		id = data.readInt();
		location = data.readInt();
		colour = data.readInt();
    }

	@Override
    public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(username);
		data.writeInt(id);
		data.writeInt(location);
		data.writeInt(colour);
    }

	@Override
    public void execute(INetworkManager network, Player player) {
		LogHelper.debugLog("Received a tattoo request packet!");
		WardedMan.proxy.handleTattooRequestPacket(username, id, location, colour);
    }
}
