package com.ggollmer.wardedman.client.proxy;

import net.minecraft.client.Minecraft;

import java.util.UUID;

import com.ggollmer.wardedman.core.proxy.CommonProxy;
import com.ggollmer.wardedman.client.gui.GuiTattooNeedle;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.player.TattooStats;
import com.ggollmer.wardedman.player.TattooTracker;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
	
	// Old
	/*@Override
	public void registerVillagerSkins()
	{
		VillagerRegistry.instance().registerVillagerSkin(VillageConstants.TATTOO_ARTIST_ID, new ResourceLocation(Reference.MOD_ID, VillageConstants.TATTOO_ARTIST_TEXTURE_LOCATION));
	}*/
	
	@Override
	public void handleTattooDataPacket(PacketTattooData packet) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			TattooTracker.handleTattooDataPacket(packet);
		}
	}
	
	@Override
	public void handleTattooUpdatePacket(UUID userId, int location, int id, int colour) {
		if(Minecraft.getMinecraft().player.getEntityWorld().isRemote) {
			TattooStats stats = TattooTracker.getPlayerTattooStats(userId);
			if(stats != null) {
				stats.setTattoo(location, id, colour);
			}
		}
	}
	
	@Override
	public void handleDyePickupPacket(UUID userId, int damage) {
		if(Minecraft.getMinecraft().player.getPersistentID().equals(userId)) {
			if(Minecraft.getMinecraft().currentScreen != null) {
				if(Minecraft.getMinecraft().currentScreen instanceof GuiTattooNeedle) {
					((GuiTattooNeedle)Minecraft.getMinecraft().currentScreen).notifyPickup(damage);
				}
			}
		}
	}
	
	@Override
	public void handleTattooChargePacket(UUID userId, int charge) {
		if(Minecraft.getMinecraft().player.getEntityWorld().isRemote) {
			TattooStats stats = TattooTracker.getPlayerTattooStats(userId);
			if(stats != null) {
				stats.updateCharge(charge);
			}
		}
	}
}
