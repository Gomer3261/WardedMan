package com.ggollmer.wardedman.item;

import com.ggollmer.wardedman.WardedMan;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ItemWardedMan extends Item
{
	
	public ItemWardedMan(String registryName, int maxStackSize)
	{
		setRegistryName(registryName);
		setUnlocalizedName(registryName);
		setCreativeTab(WardedMan.tabsWardedMan);
		setNoRepair();
		
		this.maxStackSize = maxStackSize;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerItemModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
