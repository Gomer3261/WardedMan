package com.ggollmer.wardedman.core.handler;

import java.io.File;
import java.util.logging.Level;

import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.ItemIds;
import com.ggollmer.wardedman.lib.ItemNames;
import com.ggollmer.wardedman.lib.Reference;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler
{
	public static Configuration configuration;
	
	public static void init(File configFile) {
		configuration = new Configuration(configFile);
		
		try {
			configuration.load();
			
			ItemIds.TATTOO_NEEDLE = configuration.getItem(ItemNames.TATTOO_NEEDLE_NAME, ItemIds.TATTOO_NEEDLE_DEFAULT).getInt(ItemIds.TATTOO_NEEDLE_DEFAULT);
		} catch (Exception e) {
			LogHelper.log(Level.SEVERE, Reference.MOD_NAME + " had a problem loading its configuration.");
		} finally {
			configuration.save();
		}
	}
}