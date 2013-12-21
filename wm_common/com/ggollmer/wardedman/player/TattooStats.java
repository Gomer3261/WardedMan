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
		tattooCounts = new int[TattooHandler.tattoos.size()];
		tattooColours = new int[TattooConstants.LOCATION_COUNT];
	}
	
	public TattooStats(PacketTattooData packet) {
		this.loadFromPacket(packet);
	}

	public boolean setTattoo(int tattooLocation, int tattooId, int tattooColour) {
		if(tattooId >= 0) {
			tattooCounts[tattooId] ++;
		} else {
			if(tattooValues[tattooLocation] != -1) {
				tattooCounts[tattooValues[tattooLocation]]--;
			}
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
		saveTattooLocations(wmTags);
		wmTags.setIntArray(PlayerNBTNames.TATTOO_COLOUR_LIST_NAME, tattooColours);
	}
	
	public void loadFromNBT(EntityPlayer ePlayer) {
		
		NBTTagCompound playerTags = ePlayer.getEntityData();
		if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME))
        {
            playerTags.setCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
        }
		
		NBTTagCompound wmTags = playerTags.getCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME);
		
		// Load old data.
		if(wmTags.hasKey(PlayerNBTNames.OLD_TATTOO_AMOUNT_LIST_NAME) && wmTags.hasKey(PlayerNBTNames.OLD_TATTOO_LOCATION_LIST_NAME)) {
			int[] oldTattooCounts = wmTags.getIntArray(PlayerNBTNames.OLD_TATTOO_AMOUNT_LIST_NAME);
			wmTags.removeTag(PlayerNBTNames.OLD_TATTOO_AMOUNT_LIST_NAME);
			
			for(int i=0; i<oldTattooCounts.length && i<tattooCounts.length; i++) {
				tattooCounts[i] = oldTattooCounts[i];
			}
			
			int[] oldTattooValues = wmTags.getIntArray(PlayerNBTNames.OLD_TATTOO_LOCATION_LIST_NAME);
			wmTags.removeTag(PlayerNBTNames.OLD_TATTOO_LOCATION_LIST_NAME);
			
			for(int i=0; i<oldTattooValues.length && i<tattooValues.length; i++) {
				tattooValues[i] = oldTattooValues[i];
			}
		}
		else {
			loadTattooLocations(wmTags);
		}
		
		tattooColours = wmTags.getIntArray(PlayerNBTNames.TATTOO_COLOUR_LIST_NAME);
	}
	
	private void saveTattooLocations(NBTTagCompound wmTags) {
		for(int i=0; i<TattooHandler.tattoos.size(); i++) {
			String tattooName = "";
			if(tattooValues[i] != -1) {
				tattooName = TattooHandler.tattoos.get(tattooValues[i]).unlocalizedName;
			}
			wmTags.setString(PlayerNBTNames.TATTOO_LOCATION_LIST_NAME + "_" + i, tattooName);
		}
	}
	
	private void loadTattooLocations(NBTTagCompound wmTags) {
		for(int i=0; i<TattooHandler.tattoos.size(); i++) {
			String tattooName = wmTags.getString(PlayerNBTNames.TATTOO_LOCATION_LIST_NAME + "_" + i);
			if(tattooName != "" && TattooHandler.TattooNameToID.containsKey(tattooName)) {
				tattooValues[i] = TattooHandler.TattooNameToID.get(tattooName);
				tattooCounts[tattooValues[i]] ++;
			}
			else {
				tattooValues[i] = -1;
			}
		}
	}
	
	public void clearTattooData() {
		tattooValues = new int[TattooConstants.LOCATION_COUNT];
		for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
			tattooValues[i] = -1;
		}
		tattooCounts = new int[TattooHandler.tattoos.size()];
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
		
		tattooCounts = new int[TattooHandler.tattoos.size()];
		for(int i=0; i<tattooValues.length; i++) {
			if(tattooValues[i] >= 0) {
				tattooCounts[tattooValues[i]]++;
			}
		}
	}
}
