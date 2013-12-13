package com.ggollmer.wardedman.item;

import com.ggollmer.wardedman.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemWardedMan extends Item
{
	

	public ItemWardedMan(int id)
	{
		super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
		maxStackSize = 1;
		setNoRepair();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(new ResourceLocation(Reference.MOD_ID.toLowerCase(), getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1)).toString());
	}
}
