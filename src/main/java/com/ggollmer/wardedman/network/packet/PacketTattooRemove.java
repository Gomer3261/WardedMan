package com.ggollmer.wardedman.network.packet;

import java.util.UUID;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTattooRemove extends PacketWardedManUser
{
	public int location;
	
	public PacketTattooRemove()
	{
	}
	
	public PacketTattooRemove(UUID userId, int location) {
		super(userId);
		this.location = location;
	}
	
	@Override
	public void fromBytes(ByteBuf data) {
		super.fromBytes(data);
		location = data.readInt();
    }

	@Override
	public void toBytes(ByteBuf data) {
		super.toBytes(data);
		data.writeInt(location);
    }
	
	@Override
	public void handle(MessageContext ctx) {
		LogHelper.debugLog("TattooUpdatedPacket -- Handling");
		WardedMan.proxy.handleTattooRemovePacket(userId, location);
	}
	
	public static class Handler extends PacketWardedMan.Handler<PacketTattooRemove> {}
}
