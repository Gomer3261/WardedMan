package com.ggollmer.wardedman.network.packet;

import java.util.UUID;

import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;

import io.netty.buffer.ByteBuf;

public class PacketDyePickup extends PacketWardedManUser
{
	public int damage;
	
	public PacketDyePickup() {
	}
	
	public PacketDyePickup(UUID userId, int damage) {
		super(userId);
		this.damage = damage;
	}
	
	@Override
	public void fromBytes(ByteBuf data) {
		super.fromBytes(data);
		damage = data.readInt();
	}

	@Override
	public void toBytes(ByteBuf data) {
		super.toBytes(data);
		data.writeInt(damage);
	}
	
	@Override
	public void handle(MessageContext ctx) {
		LogHelper.debugLog("PacketDyePickup -- received");
		WardedMan.proxy.handleDyePickupPacket(userId, damage);
	}
	
	public static class Handler extends PacketWardedMan.Handler<PacketDyePickup> {}
}
