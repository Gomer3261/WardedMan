package com.ggollmer.wardedman.item.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

import com.ggollmer.wardedman.item.WardedManItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class WardedManRecipes
{
	public static void init() {
		for(int i=0; i<ItemDye.dyeItemNames.length; i++) {
			GameRegistry.addShapelessRecipe(new ItemStack(WardedManItems.enderDye), Item.enderPearl, new ItemStack(Item.dyePowder, 1, i));
		}
		
		GameRegistry.addRecipe(new ItemStack(WardedManItems.tattooRemover), new Object[] {"  x", " y ", "z  ", 'x', Item.diamond, 'y', WardedManItems.tattooNeedle, 'z', WardedManItems.enderDye});
		GameRegistry.addRecipe(new ItemStack(WardedManItems.tattooViewer), new Object[] {"xyx", "yzy", " x ", 'x', Item.ingotGold, 'y', Block.thinGlass, 'z', WardedManItems.enderDye});
	}
}
