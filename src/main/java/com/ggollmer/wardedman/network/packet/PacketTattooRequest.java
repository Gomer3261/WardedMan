package com.ggollmer.wardedman.network.packet;

import java.util.UUID;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketTattooRequest extends PacketWardedManUser
{
	public int id, location, colour;
	
	public PacketTattooRequest()
	{
	}
	
	public PacketTattooRequest(UUID userId, int location, int id, int colour) {
		super(userId);
		this.location = location;
		this.id = id;
		this.colour = colour;
	}
	
	@Override
	public void fromBytes(ByteBuf data) {
		super.fromBytes(data);
		location = data.readInt();
		id = data.readInt();
		colour = data.readInt();
    }

	@Override
	public void toBytes(ByteBuf data) {
		super.toBytes(data);
		data.writeInt(location);
		data.writeInt(id);
		data.writeInt(colour);
    }

	@Override
	public void handle(MessageContext ctx) {
		LogHelper.debugLog("TattooRequestPacket -- Handling");
		WardedMan.proxy.handleTattooRequestPacket(userId, location, id, colour);
    }
	
	public static class Handler extends PacketWardedMan.Handler<PacketTattooRequest> {}
}
