package com.ggollmer.wardedman.core.proxy;

import net.minecraft.client.Minecraft;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.client.gui.GuiTattooNeedle;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.player.TattooStats;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
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
				stats.updateTattoo(location, id, colour);
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
}
