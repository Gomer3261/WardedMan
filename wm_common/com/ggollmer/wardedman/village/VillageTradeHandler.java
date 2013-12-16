package com.ggollmer.wardedman.village;

import java.util.Random;

import com.ggollmer.wardedman.item.WardedManItems;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class VillageTradeHandler implements IVillageTradeHandler {

	@Override
	public void manipulateTradesForVillager(EntityVillager villager,
			MerchantRecipeList recipeList, Random random)
	{
		recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 8, 0), new ItemStack(Item.enderPearl, 1 , 0), new ItemStack(WardedManItems.tattooNeedle, 1, 0)));
		
		for(int i=0; i<ItemDye.dyeColors.length; i++) {
			recipeList.add(new MerchantRecipe(new ItemStack(Item.emerald, 1, 0), null, new ItemStack(Item.dyePowder, 8, i)));
		}
	}

}
