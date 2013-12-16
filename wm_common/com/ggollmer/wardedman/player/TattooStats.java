package com.ggollmer.wardedman.player;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.PlayerNBTNames;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.tattoo.TattooHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class TattooStats
{
	public String username;
	
	public int[] tattooValues;
	public int[] tattooCounts;
	public int[] tattooColours;
	
	public TattooStats() {
		tattooValues = new int[TattooConstants.LOCATION_COUNT];
		for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
			tattooValues[i] = -1;
		}
		tattooCounts = new int[TattooConstants.ID_COUNT];
		tattooColours = new int[TattooConstants.LOCATION_COUNT];
	}
	
	public TattooStats(PacketTattooData packet) {
		this.loadFromPacket(packet);
	}

	public boolean setTattoo(int tattooLocation, int tattooId, int tattooColour) {
		if(tattooValues[tattooLocation] != -1) return false;
		if(tattooId != -1 && TattooHandler.tattoos[tattooId] == null) return false;
		
		if(tattooId >= 0) {
			tattooCounts[tattooId] ++;
		}
		tattooValues[tattooLocation] = tattooId;
		tattooColours[tattooLocation] = tattooColour;
		
		WardedMan.tattooTracker.sendTattooUpdate(this.username, tattooLocation, tattooId, tattooColour);
		
		return true;
	}
	
	public boolean removeTattoo(int tattooLocation) {
		if(tattooValues[tattooLocation] == -1) return false;
		
		tattooCounts[tattooValues[tattooLocation]] --;
		tattooValues[tattooLocation] = -1;
		tattooColours[tattooLocation] = 0;
		
		WardedMan.tattooTracker.sendTattooUpdate(username, tattooLocation, -1, 0);
		
		return true;
	}
	
	public int getTattooAmount(int id) {
		return tattooCounts[id];
	}
	
	public int getTattooId(int tattooLocation) {
		return tattooValues[tattooLocation];
	}
	
	public int getTattooColour(int tattooLocation) {
		return tattooColours[tattooLocation];
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
		wmTags.setIntArray(PlayerNBTNames.TATTOO_COLOUR_LIST_NAME, tattooColours);
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
		tattooColours = wmTags.getIntArray(PlayerNBTNames.TATTOO_COLOUR_LIST_NAME);
		
		/* Ensuring Data is Correct */
		
		if(tattooValues.length != TattooConstants.LOCATION_COUNT) {
			int[] oldData = tattooValues.clone();
			tattooValues = new int[TattooConstants.LOCATION_COUNT];
			for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
				tattooValues[i] = -1;
			}
			for(int i=0; i<tattooValues.length && i<oldData.length; i++) {
				tattooValues[i] = oldData[i];
			}
		}
		
		if(tattooCounts.length != TattooConstants.ID_COUNT) {
			int[] oldData = tattooCounts.clone();
			tattooCounts = new int[TattooConstants.ID_COUNT];
			for(int i=0; i<tattooCounts.length && i<oldData.length; i++) {
				tattooCounts[i] = oldData[i];
			}
		}
		
		if(tattooColours.length != TattooConstants.LOCATION_COUNT) {
			int[] oldData = tattooColours.clone();
			tattooColours = new int[TattooConstants.LOCATION_COUNT];
			for(int i=0; i<tattooColours.length && i<oldData.length; i++) {
				tattooColours[i] = oldData[i];
			}
		}
		
		clearTattooData();
	}
	
	public void clearTattooData() {
		tattooValues = new int[TattooConstants.LOCATION_COUNT];
		for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
			tattooValues[i] = -1;
		}
		tattooCounts = new int[TattooConstants.ID_COUNT];
		tattooColours = new int[TattooConstants.LOCATION_COUNT];
		
	}
	
	public PacketTattooData assemblePacket() {
		return new PacketTattooData(this.username, this.tattooValues, this.tattooColours);
	}
	
	public void loadFromPacket(PacketTattooData packet)
	{
		this.username = packet.username;
		this.tattooValues = packet.tattooValues.clone();
		this.tattooColours = packet.tattooColours.clone();
		
		tattooCounts = new int[TattooConstants.ID_COUNT];
		for(int i=0; i<tattooValues.length; i++) {
			if(tattooValues[i] >= 0) {
				tattooCounts[tattooValues[i]]++;
			}
		}
	}
}
