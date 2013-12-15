package com.ggollmer.wardedman.player;

import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.network.PacketTypeHandler;
import com.ggollmer.wardedman.network.packet.PacketDyePickup;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class DropMonitor
{
	@ForgeSubscribe
	public void checkDyePickup(EntityItemPickupEvent event) {
		if(event.item.getEntityItem().getItem() == Item.dyePowder) {
			if(event.item.getEntityItem().getItemDamage() < 16) {
				LogHelper.debugLog("World is remote? :" + event.entityPlayer.worldObj.isRemote);
				PacketDispatcher.sendPacketToPlayer(PacketTypeHandler.populatePacket(new PacketDyePickup(event.entityPlayer.username, event.item.getEntityItem().getItemDamage())), (Player)event.entityPlayer);
			}
		}
	}
}
