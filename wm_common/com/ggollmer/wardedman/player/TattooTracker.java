package com.ggollmer.wardedman.player;

import java.util.concurrent.ConcurrentHashMap;

import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.PlayerNBTNames;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.network.PacketTypeHandler;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.network.packet.PacketTattooUpdate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class TattooTracker implements IPlayerTracker
{
	public ConcurrentHashMap<String, TattooStats> tattooStatsMap = new ConcurrentHashMap<String, TattooStats>();

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		NBTTagCompound playerTags = player.getEntityData();
		if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME))
        {
            playerTags.setCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
        }
		TattooStats stats = new TattooStats();
        stats.username = player.username;
        stats.loadFromNBT(player);
        
        for(TattooStats outgoingStats : tattooStatsMap.values()) {
        	PacketDispatcher.sendPacketToPlayer(PacketTypeHandler.populatePacket(outgoingStats.assemblePacket()), (Player)player);
        }
        PacketDispatcher.sendPacketToAllPlayers(PacketTypeHandler.populatePacket(stats.assemblePacket()));
        
        tattooStatsMap.put(player.username, stats);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		saveTattooStats(player, true);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
		saveTattooStats(player, false);
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
		LogHelper.debugLog("Player has respawned!");
		if(TattooConstants.TATTOO_LOSS_ON_DEATH) { // Should be enabled on hardcore mode?!
			NBTTagCompound playerTags = player.getEntityData();
			if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME))
	        {
	            playerTags.setCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
	        }
			TattooStats stats = new TattooStats();
	        stats.username = player.username;
	        
	        PacketDispatcher.sendPacketToAllPlayers(PacketTypeHandler.populatePacket(stats.assemblePacket()));
	        
	        tattooStatsMap.put(player.username, stats);
		}
	}
	
	public void handleTattooDataPacket(PacketTattooData packet) {
		if(tattooStatsMap.contains(packet.username)) {
			tattooStatsMap.get(packet.username).loadFromPacket(packet);
		}
		else {
			tattooStatsMap.put(packet.username, new TattooStats(packet));
		}
	}
	
	public TattooStats getPlayerTattooStats(String username) {
		if(tattooStatsMap.containsKey(username)) {
			return tattooStatsMap.get(username);
		}
		else {
			return null;
		}
	}
	
	public void sendTattooUpdate(String username, int location, int id, int colour) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			PacketDispatcher.sendPacketToAllPlayers(PacketTypeHandler.populatePacket(new PacketTattooUpdate(username, location, id, colour)));
		}
	}

	private void saveTattooStats(EntityPlayer player, boolean clean)
	{
		if (player != null)
        {
			TattooStats stats = tattooStatsMap.get(player.username);
			if(stats != null) {
				stats.saveToNBT(player);
				if(clean) tattooStatsMap.remove(player.username);
			}
        }
	}
}
