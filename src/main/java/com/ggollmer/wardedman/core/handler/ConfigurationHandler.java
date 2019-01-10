package com.ggollmer.wardedman.core.handler;

import java.io.File;

import org.apache.logging.log4j.Level;

import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.lib.VillageConstants;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler
{
	public static Configuration configuration;
	
	public static void init(File configFile) {
		configuration = new Configuration(configFile);
		
		try {
			configuration.load();
			
			/* Tattoo Configuration */
			configuration.addCustomCategoryComment(TattooConstants.CONFIGURATION_CATEGORY, "Tattoo Configuration");
			TattooConstants.TATTOO_MAX_CHARGE = configuration.get(TattooConstants.CONFIGURATION_CATEGORY, TattooConstants.TATTOO_MAX_CHARGE_NAME, TattooConstants.TATTOO_MAX_CHARGE_DEFAULT).getInt();
			TattooConstants.TATTOO_LOSS_ON_DEATH = configuration.get(TattooConstants.CONFIGURATION_CATEGORY, TattooConstants.TATTOO_LOSS_ON_DEATH_NAME, TattooConstants.TATTOO_LOSS_ON_DEATH_DEFAULT).getBoolean();
			
			/* Village Configuration */
			configuration.addCustomCategoryComment(VillageConstants.CONFIGURATION_CATEGORY, "Village Configuration");
			VillageConstants.TATTOO_ARTIST_ID = configuration.get(VillageConstants.CONFIGURATION_CATEGORY, VillageConstants.TATTOO_ARTIST_NAME, VillageConstants.TATTOO_ARTIST_ID_DEFAULT).getInt();
			
		} catch (Exception e) {
			LogHelper.log(Level.FATAL, Reference.MOD_NAME + " had a problem loading its configuration.");
		} finally {
			if (configuration.hasChanged()) {
				configuration.save();
			}
		}
	}
}
