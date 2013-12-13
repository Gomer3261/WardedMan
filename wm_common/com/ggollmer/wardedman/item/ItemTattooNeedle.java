package com.ggollmer.wardedman.item;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.ItemNames;

public class ItemTattooNeedle extends ItemWardedMan
{
	public ItemTattooNeedle(int id)
	{
		super(id);
		setUnlocalizedName(ItemNames.TATTOO_NEEDLE_NAME);
		setCreativeTab(WardedMan.tabsWardedMan);
	}
}
