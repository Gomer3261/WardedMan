package com.ggollmer.wardedman.network.packet;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import io.netty.buffer.ByteBuf;

public abstract class PacketWardedMan implements IMessage
{
	
	@Override
	public void fromBytes(ByteBuf data) {
		
	}

	@Override
	public void toBytes(ByteBuf data) {
		
	}
	
	protected void handle(MessageContext ctx) {
		
	}
	
	public static class Handler<T extends PacketWardedMan> implements IMessageHandler<T, IMessage> {
		
		@Override
		public IMessage onMessage(T message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handleInternal(message, ctx));
			return null;
		}
		
	    private void handleInternal(T message, MessageContext ctx) {
			message.handle(ctx);
	    }
	}
	
}
