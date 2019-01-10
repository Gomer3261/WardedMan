package com.ggollmer.wardedman.network.packet;

import java.util.UUID;

import com.ggollmer.wardedman.network.packet.PacketWardedMan;

import io.netty.buffer.ByteBuf;

public abstract class PacketWardedManUser extends PacketWardedMan {
	public UUID userId;
	
	public PacketWardedManUser()
	{
	}
	
	public PacketWardedManUser(UUID userId) {
		this.userId = userId;
	}
	
	@Override
	public void fromBytes(ByteBuf data) {
		userId = new UUID(data.readLong(), data.readLong());
    }

	@Override
	public void toBytes(ByteBuf data) {
		data.writeLong(userId.getMostSignificantBits());
		data.writeLong(userId.getLeastSignificantBits());
    }
}
