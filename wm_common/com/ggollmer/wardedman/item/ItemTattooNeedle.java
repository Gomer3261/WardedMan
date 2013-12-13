package com.ggollmer.wardedman.item;

import net.minecraft.item.ItemStack;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.ItemNames;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTattooNeedle extends ItemWardedMan
{
	public ItemTattooNeedle(int id)
	{
		super(id);
		setUnlocalizedName(ItemNames.TATTOO_NEEDLE_NAME);
		setCreativeTab(WardedMan.tabsWardedMan);
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
}
