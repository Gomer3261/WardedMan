package com.ggollmer.wardedman.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.GuiIds;
import com.ggollmer.wardedman.lib.ItemNames;

public class ItemTattooNeedle extends ItemWardedMan
{
	public ItemTattooNeedle(int id)
	{
		super(id);
		setUnlocalizedName(ItemNames.TATTOO_NEEDLE_NAME);
		setCreativeTab(WardedMan.tabsWardedMan);
	}
	
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer thePlayer)
    {
		thePlayer.openGui(WardedMan.instance, GuiIds.TATTOO_NEEDLE_GUI_ID, thePlayer.worldObj, (int) thePlayer.posX, (int) thePlayer.posY, (int) thePlayer.posZ);
        return item;
    }
}
