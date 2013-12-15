package com.ggollmer.wardedman.network.packet;

import com.ggollmer.wardedman.network.PacketTypeHandler;

public class PacketTattooRequest extends PacketWardedMan
{
	public PacketTattooRequest(PacketTypeHandler handler, boolean isChunkData)
	{
		super(handler, isChunkData);
	}

}
