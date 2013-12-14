package com.ggollmer.wardedman.tattoo;

import java.util.List;
import java.util.ArrayList;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class TattooHandler
{
	public static Tattoo[] tattoos = new Tattoo[TattooConstants.ID_COUNT];
	public static List<List<Integer>> validTattoos = new ArrayList<List<Integer>>(TattooConstants.LOCATION_COUNT);
	
	public static Tattoo tattooDamageReduction;
	
	public static void init() {
		for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
			validTattoos.set(i, new ArrayList<Integer>());
		}
		
		tattooDamageReduction = new TattooDamageReduction(TattooConstants.DAMAGE_REDUCTION_ID, TattooConstants.DAMAGE_REDUCTION_NAME);
		
		MinecraftForge.EVENT_BUS.register(tattooDamageReduction);
	}
	
	public static List<Integer> getValidTattoosForLocation(int location) {
		return validTattoos.get(location);
	}
	
	public static void validateTattooForLocation(int location, int tattooId) {
		if(!validTattoos.contains(tattooId)) {
			validTattoos.get(location).add(tattooId);
		}
	}
	
	public static int getPlayerTattooAmount(EntityPlayer player, int id)
	{
		return WardedMan.tattooTracker.getPlayerTattooStats(player.username).getTattooAmount(id);
	}
}
