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
	public static Item enderDye;
	public static Item tattooRemover;
	
	public static void init() {
		tattooNeedle = new ItemTattooNeedle(ItemIds.TATTOO_NEEDLE);
		enderDye = new ItemEnderDye(ItemIds.ENDER_DYE);
		tattooRemover = new ItemTattooRemover(ItemIds.TATTOO_REMOVER);
		
		GameRegistry.registerItem(tattooNeedle, ItemNames.TATTOO_NEEDLE_NAME);
		GameRegistry.registerItem(enderDye, ItemNames.ENDER_DYE_NAME);
		GameRegistry.registerItem(tattooRemover, ItemNames.TATTOO_REMOVER_NAME);
		
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(tattooNeedle.itemID, 0, 1, 3, 2));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, new WeightedRandomChestContent(tattooNeedle.itemID, 0, 1, 3, 2));
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(tattooNeedle.itemID, 0, 1, 3, 2));
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, new WeightedRandomChestContent(tattooNeedle.itemID, 0, 1, 3, 2));
	}
}
