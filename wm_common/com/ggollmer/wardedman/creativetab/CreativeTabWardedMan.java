package com.ggollmer.wardedman.creativetab;

import com.ggollmer.wardedman.lib.ItemIds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabWardedMan extends CreativeTabs
{
	public CreativeTabWardedMan(int par1, String modid)
	{
		super(par1, modid);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return ItemIds.TATTOO_NEEDLE;
	}
	
}
