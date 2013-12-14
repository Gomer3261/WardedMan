package com.ggollmer.wardedman.player;

import java.lang.ref.WeakReference;

import com.ggollmer.wardedman.lib.PlayerNBTNames;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.tattoo.TattooHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerTattooStats
{
	public WeakReference<EntityPlayer> player;
	
	public int[] tattooValues;
	public int[] tattooCounts;
	
	public PlayerTattooStats() {
		tattooValues = new int[TattooConstants.TATTOO_LOCATION_COUNT];
		for(int i=0; i<TattooConstants.TATTOO_LOCATION_COUNT; i++) {
			tattooValues[i] = -1;
		}
		tattooCounts = new int[TattooConstants.MAX_TATTOO_ID];
	}
	
	public boolean applyTattoo(int tattooLocation, int tattooId) {
		if(tattooValues[tattooLocation] != -1) return false;
		if(TattooHandler.tattoos[tattooId] == null) return false;
		
		tattooCounts[tattooId] ++;
		tattooValues[tattooLocation] = tattooId;
		
		if(TattooHandler.tattoos[tattooId].tattooCanBeActivated()) TattooHandler.tattoos[tattooId].onTattooActivation(player.get(), tattooCounts[tattooId]);
		
		return true;
	}
	
	public int getTattooAmount(int id) {
		return tattooCounts[id];
	}
	
	public int getTattooAt(int tattooLocation) {
		return tattooValues[tattooLocation];
	}
	
	public void saveToNBT(EntityPlayer ePlayer) {
		
		NBTTagCompound playerTags = ePlayer.getEntityData();
		if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME))
        {
            playerTags.setCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
        }
		NBTTagCompound wmTags = playerTags.getCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME);
		wmTags.setIntArray(PlayerNBTNames.TATTOO_LOCATION_LIST_NAME, tattooValues);
		wmTags.setIntArray(PlayerNBTNames.TATTOO_AMOUNT_LIST_NAME, tattooCounts);
	}
	
	public void loadFromNBT(EntityPlayer ePlayer) {
		
		NBTTagCompound playerTags = ePlayer.getEntityData();
		if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME))
        {
            playerTags.setCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
        }
		NBTTagCompound wmTags = playerTags.getCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME);
		tattooValues = wmTags.getIntArray(PlayerNBTNames.TATTOO_LOCATION_LIST_NAME);
		tattooCounts = wmTags.getIntArray(PlayerNBTNames.TATTOO_AMOUNT_LIST_NAME);
	}
}
