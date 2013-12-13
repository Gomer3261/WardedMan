package com.ggollmer.wardedman.item;

import com.ggollmer.wardedman.lib.ItemIds;
import com.ggollmer.wardedman.lib.ItemNames;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.item.Item;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class WardedManItems
{
	/* WardedManItems */
	public static Item tattooNeedle;
	
	public static void init() {
		tattooNeedle = new ItemTattooNeedle(ItemIds.TATTOO_NEEDLE);
		
		GameRegistry.registerItem(tattooNeedle, ItemNames.TATTOO_NEEDLE_NAME);
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(tattooNeedle.itemID, 1, 1, 3, 2));
	}
}
