package com.ggollmer.wardedman.network.packet;

import java.util.UUID;

import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;

import io.netty.buffer.ByteBuf;

public class PacketTattooData extends PacketWardedManUser
{
	public int tattooCharge;
	public int[] tattooValues;
	public int[] tattooColours;
	
	
	public PacketTattooData() {
	}
	
	public PacketTattooData(UUID userId, int charge, int[] values, int[] colours) {
		super(userId);
		this.tattooCharge = charge;
		this.tattooValues = values;
		this.tattooColours = colours;
	}
	
	@Override
	public void fromBytes(ByteBuf data) {
		super.fromBytes(data);
		tattooCharge = data.readInt();
		int count;
		
		count = data.readInt();
		tattooValues = new int[count];
		for(int i=0; i<count; i++) {
			tattooValues[i] = data.readInt();
		}
		
		count = data.readInt();
		tattooColours = new int[count];
		for(int i=0; i<count; i++) {
			tattooColours[i] = data.readInt();
		}
    }

	@Override
	public void toBytes(ByteBuf data) {
		super.toBytes(data);
		data.writeInt(tattooCharge);
		
		data.writeInt(tattooValues.length);
		for(int i=0; i<tattooValues.length; i++) {
			data.writeInt(tattooValues[i]);
		}
		
		data.writeInt(tattooColours.length);
		for(int i=0; i<tattooColours.length; i++) {
			data.writeInt(tattooColours[i]);
		}
    }

	@Override
	public void handle(MessageContext ctx) {
		LogHelper.debugLog("PacketTattooData -- received");
		WardedMan.proxy.handleTattooDataPacket(this);
    }
	
	public static class Handler extends PacketWardedMan.Handler<PacketTattooData> {}
}
