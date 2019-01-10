package com.ggollmer.wardedman.village;

import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.VillageConstants;
import com.ggollmer.wardedman.village.trade.TradeEmeraldsForTattooNeedles;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;

@EventBusSubscriber
public class VillageHandler
{
	/* WardedManItems */
	@ObjectHolder(Reference.MOD_ID + ":" + VillageConstants.TATTOO_ARTIST_NAME)
	public static final VillagerProfession tattooArtist = null;
	
	public static VillagerCareer inkEnthusiast;
	
	@SubscribeEvent
	public static void registerProfessions(RegistryEvent.Register<VillagerProfession> event) {
		event.getRegistry().register(new VillagerProfession(
					Reference.MOD_ID + ":" + VillageConstants.TATTOO_ARTIST_NAME,
					Reference.MOD_ID + ":" + VillageConstants.TATTOO_ARTIST_TEXTURE_LOCATION,
					Reference.MOD_ID + ":" + VillageConstants.TATTOO_ARTIST_TEXTURE_LOCATION
				)
		);
	}
	
	public static void registerCareersAndTrades() {
		inkEnthusiast = (new VillagerCareer(tattooArtist, VillageConstants.TATTOO_ARTIST_CAREER_NAME))
			.addTrade(1, new TradeEmeraldsForTattooNeedles());
	}
}
