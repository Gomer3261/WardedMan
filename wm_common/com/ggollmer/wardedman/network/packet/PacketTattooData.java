package com.ggollmer.wardedman.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.wardedman.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketTattooData extends PacketWardedMan
{
	public String username;
	
	public PacketTattooData() {
		super(PacketTypeHandler.TATTOODAT, false);
	}
	
	public PacketTattooData(String username) {
		super(PacketTypeHandler.TATTOODAT, false);
		this.username = username;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		username = data.readUTF();
    }

	@Override
    public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(username);
    }

	@Override
    public void execute(INetworkManager network, Player player) {
		
    }
}
