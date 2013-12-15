package com.ggollmer.wardedman.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.wardedman.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketWardedMan
{
	public PacketTypeHandler packetHandler;
	public boolean isChunkDataPacket;
	
	public PacketWardedMan(PacketTypeHandler handler, boolean isChunkData) {
		packetHandler = handler;
		isChunkDataPacket = isChunkData;
	}
	
	public byte[] populate() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            dos.writeByte(packetHandler.ordinal());
            this.writeData(dos);
        }
        catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return bos.toByteArray();
	}
	
	public void readPopulate(DataInputStream input) {
		try {
            this.readData(input);
        }
        catch (IOException e) {
            e.printStackTrace(System.err);
        }
	}
	
	public void readData(DataInputStream data) throws IOException {

    }

    public void writeData(DataOutputStream dos) throws IOException {

    }

    public void execute(INetworkManager network, Player player) {

    }

    public void setKey(int key) {

    }
}
