package com.ggollmer.wardedman.village;

import net.minecraft.util.ResourceLocation;

import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.VillageConstants;

import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillageHandler
{
	public static void init() {
		LogHelper.debugLog("Registering villager!" + VillageConstants.TATTOO_ARTIST_ID + " " + new ResourceLocation(Reference.MOD_ID, VillageConstants.TATTOO_ARTIST_TEXTURE_LOCATION).toString());
		VillagerRegistry.instance().registerVillagerId(VillageConstants.TATTOO_ARTIST_ID);
		VillagerRegistry.instance().registerVillagerSkin(VillageConstants.TATTOO_ARTIST_ID, new ResourceLocation(Reference.MOD_ID, VillageConstants.TATTOO_ARTIST_TEXTURE_LOCATION));
		IVillageTradeHandler tradeHandler = new VillageTradeHandler();
		VillagerRegistry.instance().registerVillageTradeHandler(VillageConstants.TATTOO_ARTIST_ID, tradeHandler);
	}
}
