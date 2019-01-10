package com.ggollmer.wardedman.player;

import com.ggollmer.wardedman.item.WardedManItems;
import com.ggollmer.wardedman.network.PacketHandler;
import com.ggollmer.wardedman.network.packet.PacketDyePickup;

import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

/**
 * Very simple class used to inform that player that they have picked up a dye object when using the tattoo needle (Refreshes GUI).
 * @author Gomer3261
 *
 */

@EventBusSubscriber
public class DropMonitor
{
	@ObjectHolder("minecraft:dye")
	public static final Item dye = null;
	
	@SubscribeEvent
	@SideOnly(Side.SERVER)
	public static void checkDyePickup(EntityItemPickupEvent event) {
		if(event.getItem().getItem().getItem() == dye) {
			if(event.getItem().getItem().getItemDamage() < 16) {
				if(event.getEntityPlayer().inventory.mainInventory.get(event.getEntityPlayer().inventory.currentItem).getItem() == WardedManItems.tattooNeedle) {
					PacketHandler.INSTANCE.sendTo(new PacketDyePickup(event.getEntityPlayer().getPersistentID(), event.getItem().getItem().getItemDamage()), (EntityPlayerMP)event.getEntityPlayer());
				}
			}
		}
	}
}
