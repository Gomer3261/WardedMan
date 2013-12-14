package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class TattooHandler
{
	public static Tattoo[] tattoos = new Tattoo[TattooConstants.MAX_TATTOO_ID+1];
	
	public static Tattoo tattooDamageReduction;
	
	public static void init() {
		
		tattooDamageReduction = new TattooDamageReduction(TattooConstants.DAMAGE_REDUCTION_ID, TattooConstants.DAMAGE_REDUCTION_NAME);
		
		MinecraftForge.EVENT_BUS.register(tattooDamageReduction);
	}
	
	public static int getPlayerTattooAmount(EntityPlayer player, int id)
	{
		return WardedMan.tattooTracker.getPlayerTattooStats(player.username).getTattooAmount(id);
	}
}
