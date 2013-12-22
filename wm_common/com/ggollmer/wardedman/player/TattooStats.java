package com.ggollmer.wardedman.player;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.PlayerNBTNames;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.tattoo.TattooHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Class used to store tattoo related information per player.
 * @author Gomer3261
 *
 */
public class TattooStats
{
	public String username;
	
	public int[] tattooValues;
	public int[] tattooCounts;
	public int[] tattooColours;
	public int tattooCharge;
	
	/**
	 * Empty constructor, initializes all values to contain valid data.
	 */
	public TattooStats() {
		clearTattooData();
	}
	
	/**
	 * Constructs object from an incoming packet, all data is calculated from packet data.
	 * @param packet The packet to process.
	 */
	public TattooStats(PacketTattooData packet) {
		this.loadFromPacket(packet);
	}
	
	/**
	 * Set the tattoo information based on an incoming tattoo request. (Server Only)
	 * @param tattooLocation The location of the tattoo.
	 * @param tattooId The Id to set the tattoo to.
	 * @param tattooColour The colour of the tattoo.
	 * @return true if the tattoo can be changed as requested.
	 */
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
	
	/**
	 * Used to remove a tattoo at the provided location.
	 * @param tattooLocation The location of the tattoo to remove.
	 * @return True if the tattoo is successfully removed.
	 */
	public boolean removeTattoo(int tattooLocation) {
		if(tattooValues[tattooLocation] == -1) return false;
		
		tattooCounts[tattooValues[tattooLocation]] --;
		tattooValues[tattooLocation] = -1;
		tattooColours[tattooLocation] = 0;
		
		WardedMan.tattooTracker.sendTattooUpdate(username, tattooLocation, -1, 0);
		
		return true;
	}
	
	/**
	 * Used to check if a tattoo operation can occur.
	 * @param drain The amount of energy the operation drains.
	 * @return True if the operation can occur.
	 */
	public boolean drainCharge(int drain) {
		if(drain > tattooCharge) return false;
		else {
			LogHelper.debugLog("Drain Server side of: " + drain + " Current Charge: " + tattooCharge);
			tattooCharge -= drain;
			WardedMan.tattooTracker.sendTattooChargeChange(username, tattooCharge);
		}
		return true;
	}
	
	/**
	 * Updates the charge level of the tattoo stats object. For GUI purposes.
	 * @param charge The new charge.
	 */
	public void updateCharge(int charge)
	{
		LogHelper.debugLog("Tattoo charge change Client side, current charge set to: " + charge);
		this.tattooCharge = charge;
	}
	
	/**
	 * Fully charges the player's tattoos.
	 */
	public void resetCharge()
	{
		this.tattooCharge = TattooConstants.TATTOO_MAX_CHARGE;
		LogHelper.debugLog("Tattoo charge reset to: " + tattooCharge);
	}
	
	/**
	 * Get the number of tattoos of a certain type.
	 * @param id The id of the tattoo.
	 * @return Number of tattoos of provided type.
	 */
	public int getTattooAmount(int id) {
		return tattooCounts[id];
	}
	
	/**
	 * Get the tattoo at the provided location.
	 * @param tattooLocation The location to check.
	 * @return The tattoo stored at the location provided. (-1 if no tattoo)
	 */
	public int getTattooId(int tattooLocation) {
		return tattooValues[tattooLocation];
	}
	
	/**
	 * Get the colour of the tattoo at the provided location.
	 * @param tattooLocation The location to check.
	 * @return The colour of the tattoo at the location provided.
	 */
	public int getTattooColour(int tattooLocation) {
		return tattooColours[tattooLocation];
	}
	
	
	/**
	 * Saves the entire tattoo stats object into a player's NBT tag.
	 * @param ePlayer The player to save to.
	 */
	public void saveToNBT(EntityPlayer ePlayer) {
		
		NBTTagCompound playerTags = ePlayer.getEntityData();
		if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME))
        {
            playerTags.setCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
        }
		NBTTagCompound wmTags = playerTags.getCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME);
		saveTattooLocations(wmTags);
		wmTags.setIntArray(PlayerNBTNames.TATTOO_COLOUR_LIST_NAME, tattooColours);
		wmTags.setInteger(PlayerNBTNames.TATTOO_CHARGE_NAME, tattooCharge);
	}
	
	/**
	 * Loads the tattoo object from a player's NBT tags.
	 * @param ePlayer The player to load from.
	 */
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
		
		if(wmTags.hasKey(PlayerNBTNames.TATTOO_CHARGE_NAME)) {
			tattooCharge = wmTags.getInteger(PlayerNBTNames.TATTOO_CHARGE_NAME);
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
	
	/**
	 * Clears a tattoo object resetting to it's original data.
	 */
	public void clearTattooData() {
		tattooValues = new int[TattooConstants.LOCATION_COUNT];
		for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
			tattooValues[i] = -1;
		}
		tattooCounts = new int[TattooHandler.tattoos.size()];
		tattooColours = new int[TattooConstants.LOCATION_COUNT];
		tattooCharge = TattooConstants.TATTOO_MAX_CHARGE;
	}
	
	/**
	 * Creates a packet containing all tattoo data.
	 * @return The requested packet.
	 */
	public PacketTattooData assemblePacket() {
		return new PacketTattooData(this.username, this.tattooCharge, this.tattooValues, this.tattooColours);
	}
	
	/**
	 * Loads the entire stats object from a packet.
	 * @param packet The packet to load from.
	 */
	public void loadFromPacket(PacketTattooData packet)
	{
		this.username = packet.username;
		this.tattooCharge = packet.tattooCharge;
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
