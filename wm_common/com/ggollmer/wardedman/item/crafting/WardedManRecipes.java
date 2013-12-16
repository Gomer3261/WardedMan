package com.ggollmer.wardedman.item.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.ggollmer.wardedman.item.WardedManItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class WardedManRecipes
{
	public static void init() {
		GameRegistry.addShapelessRecipe(new ItemStack(WardedManItems.enderDye), Item.enderPearl, Item.dyePowder);
		
		GameRegistry.addRecipe(new ItemStack(WardedManItems.tattooRemover), new Object[] {"  x", " y ", "z  ", 'x', Item.diamond, 'y', WardedManItems.tattooNeedle, 'z', WardedManItems.enderDye});
	}
}
