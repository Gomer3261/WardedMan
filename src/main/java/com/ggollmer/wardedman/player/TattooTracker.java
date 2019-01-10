package com.ggollmer.wardedman.player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.Level;

import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.PlayerNBTNames;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.network.PacketHandler;
import com.ggollmer.wardedman.network.packet.PacketTattooCharge;
import com.ggollmer.wardedman.network.packet.PacketTattooData;
import com.ggollmer.wardedman.network.packet.PacketTattooUpdate;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Class that controls tattoo data for all players within the game, as well as
 * syncing client/server in SMP.
 * 
 * @author Gomer3261
 *
 */

@EventBusSubscriber
public class TattooTracker {
	public static ConcurrentHashMap<UUID, TattooStats> tattooStatsMap = new ConcurrentHashMap<UUID, TattooStats>();

	@SubscribeEvent
	public static void onPlayerLogin(PlayerLoggedInEvent event) {
		NBTTagCompound playerTags = event.player.getEntityData();
		if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME)) {
			playerTags.setTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
		}
		TattooStats stats = new TattooStats();
		stats.userId = event.player.getPersistentID();
		stats.loadFromNBT(event.player);

		for (TattooStats outgoingStats : tattooStatsMap.values()) {
			PacketHandler.INSTANCE.sendTo(outgoingStats.assemblePacket(), (EntityPlayerMP) event.player);
		}
		PacketHandler.INSTANCE.sendToAll(stats.assemblePacket());

		tattooStatsMap.put(stats.userId, stats);
	}

	@SubscribeEvent
	public static void onPlayerLogout(PlayerLoggedOutEvent event) {
		saveTattooStats(event.player, true);
	}

	@SubscribeEvent
	public static void onPlayerChangedDimension(PlayerChangedDimensionEvent event) {
		saveTattooStats(event.player, false);
	}

	@SubscribeEvent
	public static void onPlayerRespawn(PlayerRespawnEvent event) {
		if (TattooConstants.TATTOO_LOSS_ON_DEATH) { // Should be enabled on hardcore mode?!
			NBTTagCompound playerTags = event.player.getEntityData();
			if (!playerTags.hasKey(PlayerNBTNames.WARDED_MAN_NBT_NAME)) {
				playerTags.setTag(PlayerNBTNames.WARDED_MAN_NBT_NAME, new NBTTagCompound());
			}
			TattooStats stats = new TattooStats();
			stats.userId = event.player.getPersistentID();

			PacketHandler.INSTANCE.sendToAll(stats.assemblePacket());

			tattooStatsMap.put(stats.userId, stats);
		}
	}

	@SubscribeEvent
	public static void onPlayerSleep(PlayerSleepInBedEvent event) {
		if (!event.getEntityPlayer().getEntityWorld().isRemote) {
			if (tattooStatsMap.containsKey(event.getEntityPlayer().getPersistentID())) {
				tattooStatsMap.get(event.getEntityPlayer().getPersistentID()).resetCharge();
				sendTattooChargeChange(event.getEntityPlayer().getPersistentID(),
						tattooStatsMap.get(event.getEntityPlayer().getPersistentID()).tattooCharge);
			} else {
				LogHelper.log(Level.FATAL, "Player without tattoo stats just slept!");
			}
		}
	}

	/**
	 * Handle tattoo loading client side.
	 * 
	 * @param packet
	 */
	public static void handleTattooDataPacket(PacketTattooData packet) {
		if (tattooStatsMap.contains(packet.userId)) {
			tattooStatsMap.get(packet.userId).loadFromPacket(packet);
		} else {
			tattooStatsMap.put(packet.userId, new TattooStats(packet));
		}
	}

	/**
	 * Gets the tattoo stats for a provided user UUID.
	 * 
	 * @param userId
	 *            The UUID to look up stats for.
	 * @return The stats associated with the provided userId.
	 */
	public static TattooStats getPlayerTattooStats(UUID userId) {
		if (tattooStatsMap.containsKey(userId)) {
			return tattooStatsMap.get(userId);
		} else {
			return null;
		}
	}

	/**
	 * Sends an update packet for tattoo stats (When a tatto is changed) to client
	 * side.
	 * 
	 * @param username
	 *            The username for the tattoo data.
	 * @param location
	 *            The location of the update.
	 * @param id
	 *            The new id after the update.
	 * @param colour
	 *            The colour of the tattoo after the update.
	 */
	public static void sendTattooUpdate(UUID userId, int location, int id, int colour) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			PacketHandler.INSTANCE.sendToAll(new PacketTattooUpdate(userId, location, id, colour));
		}
	}

	/**
	 * Sends a charge update packet to client side.
	 * 
	 * @param username
	 *            The username of the tattoo owner.
	 * @param charge
	 *            The new charge for the user.
	 */
	public static void sendTattooChargeChange(UUID userId, int charge) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			PacketHandler.INSTANCE.sendToAll(new PacketTattooCharge(userId, charge));
		}
	}

	private static void saveTattooStats(EntityPlayer player, boolean clean) {
		if (player != null) {
			TattooStats stats = tattooStatsMap.get(player.getPersistentID());
			if (stats != null) {
				stats.saveToNBT(player);
				if (clean)
					tattooStatsMap.remove(player.getPersistentID());
			}
		}
	}
}
