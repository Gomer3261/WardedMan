package com.ggollmer.wardedman.creativetab;

import com.ggollmer.wardedman.item.WardedManItems;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabWardedMan extends CreativeTabs
{
	public CreativeTabWardedMan(String modId)
	{
		super(modId);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		return new ItemStack(WardedManItems.tattooNeedle);
	}
	
}
