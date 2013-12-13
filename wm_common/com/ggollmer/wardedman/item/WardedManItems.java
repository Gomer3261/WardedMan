package com.ggollmer.wardedman.item;

import com.ggollmer.wardedman.lib.ItemIds;
import com.ggollmer.wardedman.lib.ItemNames;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.item.Item;

public class WardedManItems
{
	/* WardedManItems */
	public static Item tattooNeedle;
	
	public static void init() {
		tattooNeedle = new ItemTattooNeedle(ItemIds.TATTOO_NEEDLE);
		
		GameRegistry.registerItem(tattooNeedle, ItemNames.TATTOO_NEEDLE_NAME);
	}	
}
