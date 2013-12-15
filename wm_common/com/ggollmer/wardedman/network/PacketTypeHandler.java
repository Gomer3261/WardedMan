package com.ggollmer.wardedman.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.network.packet.PacketTattooRequest;
import com.ggollmer.wardedman.network.packet.PacketWardedMan;

public enum PacketTypeHandler
{
	TATTOOREQ(PacketTattooRequest.class),
	TATTOODAT(PacketTattooData.class);
	
	private Class<? extends PacketWardedMan> clazz;
	
	PacketTypeHandler(Class<? extends PacketWardedMan> clazz) {
		this.clazz = clazz;
	}
	
	public static PacketWardedMan buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);
		
		PacketWardedMan packet = null;
		
		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		packet.readPopulate(dis);
		
		return packet;
	}
	
	public static PacketWardedMan buildPacket(PacketTypeHandler type) {
		PacketWardedMan packet = null;
		
		try {
			packet = values()[type.ordinal()].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		return packet;
	}
	
	public static Packet populatePacket(PacketWardedMan packetWardedMan) {
		byte[] data = packetWardedMan.populate();
		
		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetWardedMan.isChunkDataPacket;
		
		return packet250;
	}
}
