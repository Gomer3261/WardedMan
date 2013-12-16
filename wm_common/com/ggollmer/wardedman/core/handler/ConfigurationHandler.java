package com.ggollmer.wardedman.core.handler;

import java.io.File;
import java.util.logging.Level;

import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.ItemIds;
import com.ggollmer.wardedman.lib.ItemNames;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.lib.VillageConstants;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler
{
	public static Configuration configuration;
	
	public static void init(File configFile) {
		configuration = new Configuration(configFile);
		
		try {
			configuration.load();
			
			/* Item Configuration */
			ItemIds.TATTOO_NEEDLE = configuration.getItem(ItemNames.TATTOO_NEEDLE_NAME, ItemIds.TATTOO_NEEDLE_DEFAULT).getInt(ItemIds.TATTOO_NEEDLE_DEFAULT);
			ItemIds.TATTOO_REMOVER = configuration.getItem(ItemNames.TATTOO_REMOVER_NAME, ItemIds.TATTOO_REMOVER_DEFAULT).getInt(ItemIds.TATTOO_REMOVER_DEFAULT);
			ItemIds.ENDER_DYE = configuration.getItem(ItemNames.ENDER_DYE_NAME, ItemIds.ENDER_DYE_DEFAULT).getInt(ItemIds.ENDER_DYE_DEFAULT);
			
			/* Tattoo Configuration */
			TattooConstants.TATTOO_LOSS_ON_DEATH = configuration.get(TattooConstants.CONFIGURATION_CATEGORY, TattooConstants.TATTOO_LOSS_ON_DEATH_NAME, TattooConstants.TATTOO_LOSS_ON_DEATH_DEFAULT).getBoolean(TattooConstants.TATTOO_LOSS_ON_DEATH_DEFAULT);
			
			/* Village Configuration */
			VillageConstants.TATTOO_ARTIST_ID = configuration.get(VillageConstants.CONFIGURATION_CATEGORY, VillageConstants.TATTOO_ARTIST_NAME, VillageConstants.TATTOO_ARTIST_ID_DEFAULT).getInt(VillageConstants.TATTOO_ARTIST_ID_DEFAULT);
			
		} catch (Exception e) {
			LogHelper.log(Level.SEVERE, Reference.MOD_NAME + " had a problem loading its configuration.");
		} finally {
			configuration.save();
		}
	}
}
