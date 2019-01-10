package com.ggollmer.wardedman.core.proxy;

import java.util.UUID;

import com.ggollmer.wardedman.item.WardedManItems;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.network.PacketHandler;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.player.TattooStats;
import com.ggollmer.wardedman.player.TattooTracker;
import com.ggollmer.wardedman.village.VillageHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

public class CommonProxy
{
	@ObjectHolder("minecraft:dye")
	public static final Item dye = null;
	
	public void preInit(FMLPreInitializationEvent event) {
		PacketHandler.registerMessages(Reference.MOD_ID);
	}

	public void init(FMLInitializationEvent event) {
		VillageHandler.registerCareersAndTrades();
	}

	public void postInit(FMLPostInitializationEvent event) {
	}
	
	/* Packet handling helpers */
    
	public void handleTattooUpdatePacket(UUID userId, int location, int id, int colour) {
	}
	
	public void handleTattooDataPacket(PacketTattooData packet) {}

	public void handleTattooRequestPacket(UUID userId, int location, int id,
			int colour)
	{
		TattooStats stats = TattooTracker.getPlayerTattooStats(userId);
		if(stats != null) {
			EntityPlayer player = (EntityPlayer) FMLCommonHandler.instance().getMinecraftServerInstance().getEntityFromUuid(userId);
			if(player != null) {
				if(!player.getEntityWorld().isRemote) {
					boolean canTattooThemselves = false;
					if(player.inventory.getStackInSlot(player.inventory.currentItem).getItem() == WardedManItems.tattooNeedle) {
						for(int i=0; i<player.inventory.mainInventory.size(); i++) {
							if(player.inventory.mainInventory.get(i) != null) {
								if(player.inventory.mainInventory.get(i).getItem() == dye) {
									if(player.inventory.mainInventory.get(i).getItemDamage() == colour) {
										canTattooThemselves = true;
										player.inventory.decrStackSize(i, 1);
										break;
									}
								}
							}
						}
						if(canTattooThemselves) {
							player.inventory.decrStackSize(player.inventory.currentItem, 1);
							stats.setTattoo(location, id, ItemDye.DYE_COLORS[colour]);
						}
					}
				}
			}
		}
	}

	public void handleDyePickupPacket(UUID userId, int damage) {}

	public void handleTattooRemovePacket(UUID userId, int location)
	{
		TattooStats stats = TattooTracker.getPlayerTattooStats(userId);
		if(stats != null) {
			EntityPlayer player = (EntityPlayer) FMLCommonHandler.instance().getMinecraftServerInstance().getEntityFromUuid(userId);
			if(player != null) {
				if(!player.getEntityWorld().isRemote) {
					if(player.inventory.getStackInSlot(player.inventory.currentItem).getItem() == WardedManItems.tattooRemover) {
						player.inventory.decrStackSize(player.inventory.currentItem, 1);
						stats.removeTattoo(location);
					}
				}
			}
		}
	}

	public void handleTattooChargePacket(UUID userId, int charge) {}
}
