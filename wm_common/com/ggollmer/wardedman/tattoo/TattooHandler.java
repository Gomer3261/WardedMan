package com.ggollmer.wardedman.tattoo;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class TattooHandler
{
	public static HashMap<String, Integer> NameToID = new HashMap<String, Integer>();
	public static HashMap<Integer, String> IDToName = new HashMap<Integer, String>();
	public static Tattoo[] tattoos = new Tattoo[TattooConstants.ID_COUNT];
	public static List<List<Integer>> validTattoos = new ArrayList<List<Integer>>(TattooConstants.LOCATION_COUNT);
	
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
		
		NameToID.put(TattooConstants.FACE_LOCATION_NAME, TattooConstants.FACE_LOCATION_ID);
		IDToName.put(TattooConstants.FACE_LOCATION_ID, TattooConstants.FACE_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_CHEST_LOCATION_NAME, TattooConstants.RIGHT_CHEST_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_CHEST_LOCATION_ID, TattooConstants.RIGHT_CHEST_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_CHEST_LOCATION_NAME, TattooConstants.LEFT_CHEST_LOCATION_ID);
		IDToName.put(TattooConstants.LEFT_CHEST_LOCATION_ID, TattooConstants.LEFT_CHEST_LOCATION_NAME);
		NameToID.put(TattooConstants.AB_LOCATION_NAME, TattooConstants.AB_LOCATION_ID);
		IDToName.put(TattooConstants.AB_LOCATION_ID, TattooConstants.AB_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_PALM_LOCATION_NAME, TattooConstants.RIGHT_PALM_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_PALM_LOCATION_ID, TattooConstants.RIGHT_PALM_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_PALM_LOCATION_NAME, TattooConstants.LEFT_PALM_LOCATION_ID);
		IDToName.put(TattooConstants.LEFT_PALM_LOCATION_ID, TattooConstants.LEFT_PALM_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_THIGH_LOCATION_NAME, TattooConstants.RIGHT_THIGH_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_THIGH_LOCATION_ID, TattooConstants.RIGHT_THIGH_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_THIGH_LOCATION_NAME, TattooConstants.LEFT_THIGH_LOCATION_ID);
		IDToName.put(TattooConstants.LEFT_THIGH_LOCATION_ID, TattooConstants.LEFT_THIGH_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_FOOT_LOCATION_NAME, TattooConstants.RIGHT_FOOT_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_FOOT_LOCATION_ID, TattooConstants.RIGHT_FOOT_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_FOOT_LOCATION_NAME, TattooConstants.LEFT_FOOT_LOCATION_ID);
		IDToName.put(TattooConstants.LEFT_FOOT_LOCATION_ID, TattooConstants.LEFT_FOOT_LOCATION_NAME);
		NameToID.put(TattooConstants.HEAD_LOCATION_NAME, TattooConstants.HEAD_LOCATION_ID);
		IDToName.put(TattooConstants.HEAD_LOCATION_ID, TattooConstants.HEAD_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_SHOULDER_LOCATION_NAME, TattooConstants.RIGHT_SHOULDER_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_SHOULDER_LOCATION_ID, TattooConstants.RIGHT_SHOULDER_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_SHOULDER_LOCATION_NAME, TattooConstants.LEFT_SHOULDER_LOCATION_ID);
		IDToName.put(TattooConstants.LEFT_SHOULDER_LOCATION_ID, TattooConstants.LEFT_SHOULDER_LOCATION_NAME);
		NameToID.put(TattooConstants.BACK_LOCATION_NAME, TattooConstants.HEAD_LOCATION_ID);
		IDToName.put(TattooConstants.BACK_LOCATION_ID, TattooConstants.HEAD_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_GLUTEAL_LOCATION_NAME, TattooConstants.RIGHT_GLUTEAL_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_GLUTEAL_LOCATION_ID, TattooConstants.RIGHT_GLUTEAL_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_GLUTEAL_LOCATION_NAME, TattooConstants.LEFT_GLUTEAL_LOCATION_ID);
		IDToName.put(TattooConstants.LEFT_GLUTEAL_LOCATION_ID, TattooConstants.LEFT_GLUTEAL_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_HAND_LOCATION_NAME, TattooConstants.RIGHT_HAND_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_HAND_LOCATION_ID, TattooConstants.RIGHT_HAND_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_HAND_LOCATION_NAME, TattooConstants.LEFT_HAND_LOCATION_ID);
		IDToName.put(TattooConstants.LEFT_HAND_LOCATION_ID, TattooConstants.LEFT_HAND_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_HAM_LOCATION_NAME, TattooConstants.RIGHT_HAM_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_HAM_LOCATION_ID, TattooConstants.RIGHT_HAM_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_HAM_LOCATION_NAME, TattooConstants.LEFT_HAM_LOCATION_ID);
		IDToName.put(TattooConstants.LEFT_HAM_LOCATION_ID, TattooConstants.LEFT_HAM_LOCATION_NAME);
		NameToID.put(TattooConstants.RIGHT_CALF_LOCATION_NAME, TattooConstants.RIGHT_CALF_LOCATION_ID);
		IDToName.put(TattooConstants.RIGHT_CALF_LOCATION_ID, TattooConstants.RIGHT_CALF_LOCATION_NAME);
		NameToID.put(TattooConstants.LEFT_CALF_LOCATION_NAME, TattooConstants.LEFT_CALF_LOCATION_ID);
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
		if(!validTattoos.get(location).contains(tattooId)) {
			validTattoos.get(location).add(tattooId);
		}
	}
	
	public static int getPlayerTattooAmount(EntityPlayer player, int id)
	{
		return WardedMan.tattooTracker.getPlayerTattooStats(player.username).getTattooAmount(id);
	}
}
