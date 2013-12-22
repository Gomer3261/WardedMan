package com.ggollmer.wardedman.core.proxy;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.item.WardedManItems;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.player.TattooStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.server.MinecraftServer;

public class CommonProxy
{
	public void registerSoundHandler() {}

    public void initRenderingAndTextures() {}

    public void registerTileEntities() {}
    
    public void registerVillagerSkins() {}
	
	/* Packet handling helpers */
    
	public void handleTattooUpdatePacket(String username, int location, int id, int colour) {
	}
	
	public void handleTattooDataPacket(PacketTattooData packet) {}

	public void handleTattooRequestPacket(String username, int location, int id,
			int colour)
	{
		TattooStats stats = WardedMan.tattooTracker.getPlayerTattooStats(username);
		if(stats != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if(player != null) {
				if(!player.worldObj.isRemote) {
					boolean canTattooThemselves = false;
					if(player.inventory.getStackInSlot(player.inventory.currentItem).getItem() == WardedManItems.tattooNeedle) {
						for(int i=0; i<player.inventory.mainInventory.length; i++) {
							if(player.inventory.mainInventory[i] != null) {
								if(player.inventory.mainInventory[i].getItem() == Item.dyePowder) {
									if(player.inventory.mainInventory[i].getItemDamage() == colour) {
										canTattooThemselves = true;
										player.inventory.decrStackSize(i, 1);
										break;
									}
								}
							}
						}
						if(canTattooThemselves) {
							player.inventory.decrStackSize(player.inventory.currentItem, 1);
							stats.setTattoo(location, id, ItemDye.dyeColors[colour]);
						}
					}
				}
			}
		}
	}

	public void handleDyePickupPacket(String username, int damage) {}

	public void handleTattooRemovePacket(String username, int location)
	{
		TattooStats stats = WardedMan.tattooTracker.getPlayerTattooStats(username);
		if(stats != null) {
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if(player != null) {
				if(!player.worldObj.isRemote) {
					if(player.inventory.getStackInSlot(player.inventory.currentItem).getItem() == WardedManItems.tattooRemover) {
						player.inventory.decrStackSize(player.inventory.currentItem, 1);
						stats.removeTattoo(location);
					}
				}
			}
		}
	}

	public void handleTattooChargePacket(String username, int charge) {}
}
