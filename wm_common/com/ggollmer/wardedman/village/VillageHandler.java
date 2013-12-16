package com.ggollmer.wardedman.village;

import net.minecraft.world.gen.structure.MapGenStructureIO;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.VillageConstants;

import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillageHandler
{
	public static void init() {
		VillagerRegistry.instance().registerVillagerId(VillageConstants.TATTOO_ARTIST_ID);
		WardedMan.proxy.registerVillagerSkins();
		IVillageTradeHandler tradeHandler = new VillageTradeHandler();
		VillagerRegistry.instance().registerVillageTradeHandler(VillageConstants.TATTOO_ARTIST_ID, tradeHandler);
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageTattooArtistHandler());
		try
        {
			LogHelper.debugLog("Registering the village component!");
            MapGenStructureIO.func_143031_a(ComponentTattooArtistHouse.class, "WardedMan:TattoArtistStructure");
        }
        catch (Throwable e)
        {
        	e.printStackTrace();
        }
	}
}
