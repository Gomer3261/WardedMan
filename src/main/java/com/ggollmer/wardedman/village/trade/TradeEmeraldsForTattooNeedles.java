package com.ggollmer.wardedman.village.trade;

import java.util.Random;

import com.ggollmer.wardedman.item.WardedManItems;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityVillager.ITradeList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class TradeEmeraldsForTattooNeedles implements ITradeList {

	public ItemStack resultStack;
	
	public EntityVillager.PriceInfo priceInfo;
	
	public TradeEmeraldsForTattooNeedles() {
		resultStack = new ItemStack(WardedManItems.tattooNeedle);
		
		priceInfo = new EntityVillager.PriceInfo(2, 5);
	}
	
	@Override
	public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
		int actualPrice = 1;
		
		if (priceInfo != null) {
			actualPrice = priceInfo.getPrice(random);
		}
		
		ItemStack stackToPay = new ItemStack(Items.EMERALD, actualPrice, 0);
		recipeList.add(new MerchantRecipe(stackToPay, resultStack));
	}
	
}
