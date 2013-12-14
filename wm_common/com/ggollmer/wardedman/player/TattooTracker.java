package com.ggollmer.wardedman.player;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;

import com.ggollmer.wardedman.lib.PlayerNBTNames;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.IPlayerTracker;

public class TattooTracker implements IPlayerTracker
{
	public ConcurrentHashMap<String, PlayerTattooStats> tattooStatsMap = new ConcurrentHashMap<String, PlayerTattooStats>();

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		NBTTagCompound playerTags = player.getEntityData();
		if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME))
        {
            playerTags.setCompoundTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
        }
		PlayerTattooStats stats = new PlayerTattooStats();
        stats.player = new WeakReference<EntityPlayer>(player);
        
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
		PlayerTattooStats stats = getPlayerTattooStats(player.username);
        stats.player = new WeakReference<EntityPlayer>(player);
	}
	
	public PlayerTattooStats getPlayerTattooStats(String username) {
		if(tattooStatsMap.containsKey(username)) {
			return tattooStatsMap.get(username);
		}
		else {
			return null;
		}
	}
	
	public EntityPlayer getEntityPlayer(String username) {
		if(tattooStatsMap.containsKey(username)) {
			return tattooStatsMap.get(username).player.get();
		}
		else {
			return null;
		}
	}

	private void saveTattooStats(EntityPlayer player, boolean clean)
	{
		if (player != null)
        {
			PlayerTattooStats stats = tattooStatsMap.get(player.username);
			if(stats != null) {
				stats.saveToNBT(player);
				if(clean) tattooStatsMap.remove(player.username);
			}
        }
	}
}