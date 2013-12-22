package com.ggollmer.wardedman.player;

import com.ggollmer.wardedman.item.WardedManItems;
import com.ggollmer.wardedman.network.PacketTypeHandler;
import com.ggollmer.wardedman.network.packet.PacketDyePickup;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import net.minecraft.item.Item;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

/**
 * Very simple class used to inform that player that they have picked up a dye object when using the tattoo needle (Refreshes GUI).
 * @author Gomer3261
 *
 */
public class DropMonitor
{
	@ForgeSubscribe
	public void checkDyePickup(EntityItemPickupEvent event) {
		if(event.item.getEntityItem().getItem() == Item.dyePowder) {
			if(event.item.getEntityItem().getItemDamage() < 16) {
				if(event.entityPlayer.inventory.mainInventory[event.entityPlayer.inventory.currentItem].itemID == WardedManItems.tattooNeedle.itemID) {
					PacketDispatcher.sendPacketToPlayer(PacketTypeHandler.populatePacket(new PacketDyePickup(event.entityPlayer.username, event.item.getEntityItem().getItemDamage())), (Player)event.entityPlayer);
				}
			}
		}
	}
}
