package com.ggollmer.wardedman.network.packet;

import java.util.UUID;

import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;

import io.netty.buffer.ByteBuf;

public class PacketTattooCharge extends PacketWardedManUser
{
	public int charge;
	
	public PacketTattooCharge()
	{
	}
	
	public PacketTattooCharge(UUID userId, int charge) {
		super(userId);
		this.charge = charge;
	}
	
	@Override
	public void fromBytes(ByteBuf data) {
		super.fromBytes(data);
		charge = data.readInt();
    }

	@Override
	public void toBytes(ByteBuf data) {
		super.toBytes(data);
		data.writeInt(charge);
    }

	@Override
	public void handle(MessageContext ctx) {
		LogHelper.debugLog("TattooChargePacket -- Handling");
		WardedMan.proxy.handleTattooChargePacket(userId, charge);
    }

	public static class Handler extends PacketWardedMan.Handler<PacketTattooCharge> {}
}
