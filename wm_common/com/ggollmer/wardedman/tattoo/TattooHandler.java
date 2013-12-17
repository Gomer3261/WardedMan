package com.ggollmer.wardedman.tattoo;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.player.TattooStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class TattooHandler
{
	public static HashMap<Integer, Integer> IDToSlot = new HashMap<Integer, Integer>();
	public static HashMap<Integer, String> IDToName = new HashMap<Integer, String>();
	public static Tattoo[] tattoos = new Tattoo[TattooConstants.ID_COUNT];
	public static List<List<Integer>> validTattoos = new ArrayList<List<Integer>>(TattooConstants.LOCATION_COUNT);
	public static List<List<Integer>> validLocations = new ArrayList<List<Integer>>(TattooConstants.ID_COUNT);
	
	public static Tattoo tattooDamageReduction;
	public static Tattoo tattooFireResistance;
	public static Tattoo tattooFeatherFalling;
	public static Tattoo tattooIronFist;
	public static Tattoo tattooFireFist;
	public static Tattoo tattooStoneFist;
	public static Tattoo tattooChiseledFist;
	public static Tattoo tattooThorns;
	public static Tattoo tattooMagicResist;
	public static Tattoo tattooProjectileProtection;
	
	public static void init() {
		for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
			validTattoos.add(i, new ArrayList<Integer>());
		}
		for(int i=0; i<TattooConstants.ID_COUNT; i++) {
			validLocations.add(i, new ArrayList<Integer>());
		}
		
		IDToSlot.put(TattooConstants.FACE_LOCATION_ID, TattooConstants.FACE_LOCATION_SLOT);
		IDToName.put(TattooConstants.FACE_LOCATION_ID, TattooConstants.FACE_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_CHEST_LOCATION_ID, TattooConstants.RIGHT_CHEST_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_CHEST_LOCATION_ID, TattooConstants.RIGHT_CHEST_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_CHEST_LOCATION_ID, TattooConstants.LEFT_CHEST_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_CHEST_LOCATION_ID, TattooConstants.LEFT_CHEST_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.AB_LOCATION_ID, TattooConstants.AB_LOCATION_SLOT);
		IDToName.put(TattooConstants.AB_LOCATION_ID, TattooConstants.AB_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_PALM_LOCATION_ID, TattooConstants.RIGHT_PALM_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_PALM_LOCATION_ID, TattooConstants.RIGHT_PALM_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_PALM_LOCATION_ID, TattooConstants.LEFT_PALM_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_PALM_LOCATION_ID, TattooConstants.LEFT_PALM_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_THIGH_LOCATION_ID, TattooConstants.RIGHT_THIGH_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_THIGH_LOCATION_ID, TattooConstants.RIGHT_THIGH_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_THIGH_LOCATION_ID, TattooConstants.LEFT_THIGH_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_THIGH_LOCATION_ID, TattooConstants.LEFT_THIGH_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_FOOT_LOCATION_ID, TattooConstants.RIGHT_FOOT_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_FOOT_LOCATION_ID, TattooConstants.RIGHT_FOOT_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_FOOT_LOCATION_ID, TattooConstants.LEFT_FOOT_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_FOOT_LOCATION_ID, TattooConstants.LEFT_FOOT_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.HEAD_LOCATION_ID, TattooConstants.HEAD_LOCATION_SLOT);
		IDToName.put(TattooConstants.HEAD_LOCATION_ID, TattooConstants.HEAD_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_SHOULDER_LOCATION_ID, TattooConstants.RIGHT_SHOULDER_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_SHOULDER_LOCATION_ID, TattooConstants.RIGHT_SHOULDER_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_SHOULDER_LOCATION_ID, TattooConstants.LEFT_SHOULDER_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_SHOULDER_LOCATION_ID, TattooConstants.LEFT_SHOULDER_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.BACK_LOCATION_ID, TattooConstants.BACK_LOCATION_SLOT);
		IDToName.put(TattooConstants.BACK_LOCATION_ID, TattooConstants.BACK_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_GLUTEAL_LOCATION_ID, TattooConstants.RIGHT_GLUTEAL_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_GLUTEAL_LOCATION_ID, TattooConstants.RIGHT_GLUTEAL_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_GLUTEAL_LOCATION_ID, TattooConstants.LEFT_GLUTEAL_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_GLUTEAL_LOCATION_ID, TattooConstants.LEFT_GLUTEAL_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_HAND_LOCATION_ID, TattooConstants.RIGHT_HAND_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_HAND_LOCATION_ID, TattooConstants.RIGHT_HAND_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_HAND_LOCATION_ID, TattooConstants.LEFT_HAND_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_HAND_LOCATION_ID, TattooConstants.LEFT_HAND_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_HAM_LOCATION_ID, TattooConstants.RIGHT_HAM_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_HAM_LOCATION_ID, TattooConstants.RIGHT_HAM_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_HAM_LOCATION_ID, TattooConstants.LEFT_HAM_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_HAM_LOCATION_ID, TattooConstants.LEFT_HAM_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.RIGHT_CALF_LOCATION_ID, TattooConstants.RIGHT_CALF_LOCATION_SLOT);
		IDToName.put(TattooConstants.RIGHT_CALF_LOCATION_ID, TattooConstants.RIGHT_CALF_LOCATION_NAME);
		
		IDToSlot.put(TattooConstants.LEFT_CALF_LOCATION_ID, TattooConstants.LEFT_CALF_LOCATION_SLOT);
		IDToName.put(TattooConstants.LEFT_CALF_LOCATION_ID, TattooConstants.LEFT_CALF_LOCATION_NAME);
		
		tattooDamageReduction = new TattooDamageReduction(TattooConstants.DAMAGE_REDUCTION_ID, TattooConstants.DAMAGE_REDUCTION_NAME);
		tattooFireResistance = new TattooFireResistance(TattooConstants.FIRE_RESISTANCE_ID,	TattooConstants.FIRE_RESISTANCE_NAME);
		tattooFeatherFalling = new TattooFeatherFalling(TattooConstants.FEATHER_FALLING_ID, TattooConstants.FEATHER_FALLING_NAME);
		tattooIronFist = new TattooIronFist(TattooConstants.IRON_FIST_ID, TattooConstants.IRON_FIST_NAME);
		tattooFireFist = new TattooFireFist(TattooConstants.FIRE_FIST_ID, TattooConstants.FIRE_FIST_NAME);
		tattooStoneFist = new TattooStoneFist(TattooConstants.STONE_FIST_ID, TattooConstants.STONE_FIST_NAME);
		tattooChiseledFist = new TattooChiseledFist(TattooConstants.CHISELED_FIST_ID, TattooConstants.CHISELED_FIST_NAME);
		tattooThorns = new TattooThorns(TattooConstants.THORNS_ID, TattooConstants.THORNS_NAME);
		tattooMagicResist = new TattooMagicResist(TattooConstants.MAGIC_RESIST_ID, TattooConstants.MAGIC_RESIST_NAME);
		tattooProjectileProtection = new TattooProjectileProtection(TattooConstants.PROJECTILE_PROTECTION_ID, TattooConstants.PROJECTILE_PROTECTION_NAME);
		
		MinecraftForge.EVENT_BUS.register(tattooDamageReduction);
		MinecraftForge.EVENT_BUS.register(tattooFireResistance);
		MinecraftForge.EVENT_BUS.register(tattooFeatherFalling);
		MinecraftForge.EVENT_BUS.register(tattooIronFist);
		MinecraftForge.EVENT_BUS.register(tattooFireFist);
		MinecraftForge.EVENT_BUS.register(tattooStoneFist);
		MinecraftForge.EVENT_BUS.register(tattooChiseledFist);
		MinecraftForge.EVENT_BUS.register(tattooThorns);
		MinecraftForge.EVENT_BUS.register(tattooMagicResist);
		MinecraftForge.EVENT_BUS.register(tattooProjectileProtection);
	}
	
	public static List<Integer> getValidTattoosForLocation(int location) {
		return validTattoos.get(location);
	}
	
	public static void validateTattooForLocation(int location, int tattooId) {
		validTattoos.get(location).add(tattooId);
		validLocations.get(tattooId).add(location);
	}
	
	public static int getPlayerTattooAmount(EntityPlayer player, int id)
	{
		int workingTattoos = 0;
		TattooStats stats = WardedMan.tattooTracker.getPlayerTattooStats(player.username);
		for(int location : validLocations.get(id)) {
			if(stats.getTattooId(location) != id) continue;
			if(IDToSlot.get(location) == -1) {
				if(player.inventory.mainInventory[player.inventory.currentItem] != null) continue;
			} else {
				if(player.inventory.armorInventory[IDToSlot.get(location)] != null) continue;
			}
			workingTattoos++;
		}
		return workingTattoos;
	}
}
