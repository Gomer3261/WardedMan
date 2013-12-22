package com.ggollmer.wardedman.core.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.client.gui.GuiTattooNeedle;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.VillageConstants;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.player.TattooStats;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerVillagerSkins()
	{
		VillagerRegistry.instance().registerVillagerSkin(VillageConstants.TATTOO_ARTIST_ID, new ResourceLocation(Reference.MOD_ID, VillageConstants.TATTOO_ARTIST_TEXTURE_LOCATION));
	}
	
	@Override
	public void handleTattooDataPacket(PacketTattooData packet) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			WardedMan.tattooTracker.handleTattooDataPacket(packet);
		}
	}
	
	@Override
	public void handleTattooUpdatePacket(String username, int location, int id, int colour) {
		if(Minecraft.getMinecraft().thePlayer.worldObj.isRemote) {
			TattooStats stats = WardedMan.tattooTracker.getPlayerTattooStats(username);
			if(stats != null) {
				stats.setTattoo(location, id, colour);
			}
		}
	}
	
	@Override
	public void handleDyePickupPacket(String username, int damage) {
		if(Minecraft.getMinecraft().thePlayer.username.equals(username)) {
			if(Minecraft.getMinecraft().currentScreen != null) {
				if(Minecraft.getMinecraft().currentScreen instanceof GuiTattooNeedle) {
					((GuiTattooNeedle)Minecraft.getMinecraft().currentScreen).notifyPickup(damage);
				}
			}
		}
	}
	
	@Override
	public void handleTattooChargePacket(String username, int charge) {
		if(Minecraft.getMinecraft().thePlayer.worldObj.isRemote) {
			TattooStats stats = WardedMan.tattooTracker.getPlayerTattooStats(username);
			if(stats != null) {
				stats.updateCharge(charge);
			}
		}
	}
}
