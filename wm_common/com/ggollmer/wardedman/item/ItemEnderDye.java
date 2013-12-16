package com.ggollmer.wardedman.item;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.ItemNames;

public class ItemEnderDye extends ItemWardedMan
{
	public ItemEnderDye(int id)
	{
		super(id);
		setUnlocalizedName(ItemNames.ENDER_DYE_NAME);
		setCreativeTab(WardedMan.tabsWardedMan);
		maxStackSize = 64;
	}
}
