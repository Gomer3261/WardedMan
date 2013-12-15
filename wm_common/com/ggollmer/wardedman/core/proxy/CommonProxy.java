package com.ggollmer.wardedman.core.proxy;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.item.WardedManItems;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.player.TattooStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.server.MinecraftServer;

public class CommonProxy
{
	public void registerSoundHandler()
	{
    }

    public void initRenderingAndTextures()
    {
    }

    public void registerTileEntities()
    {
    }
	
	/* Packet handling helpers! */

	public void handleTattooUpdatePacket(String username, int location, int id, int colour) {
	}
	
	public void handleTattooDataPacket(PacketTattooData packet) {}

	public void handleTattooRequestPack(String username, int location, int id,
			int colour)
	{
		TattooStats stats = WardedMan.tattooTracker.getPlayerTattooStats(username);
		if(stats != null) {
			LogHelper.debugLog("Tattoo Stats exist, moving on.");
			EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(username);
			if(player != null) {
				LogHelper.debugLog("Found the player, this is cool.");
				boolean canTattooThemselves = false;
				if(player.inventory.getStackInSlot(player.inventory.currentItem).getItem() == WardedManItems.tattooNeedle) {
					LogHelper.debugLog("Player is holding a needle that's cool.");
					for(int i=0; i<player.inventory.mainInventory.length; i++) {
						if(player.inventory.mainInventory[i] != null) {
							if(player.inventory.mainInventory[i].getItem() == Item.dyePowder) {
								if(player.inventory.mainInventory[i].getItemDamage() == colour) {
									LogHelper.debugLog("Found the dye that the player used!");
									canTattooThemselves = true;
									player.inventory.decrStackSize(i, 1);
									break;
								}
							}
						}
					}
					if(canTattooThemselves) {
						LogHelper.debugLog("Tattooing time!");
						player.inventory.decrStackSize(player.inventory.currentItem, 1);
						stats.updateTattoo(location, id, ItemDye.dyeColors[colour]);
					}
				}
			}
		}
	}

	public void handleDyePickupPacket(String username, int damage) {}
}
