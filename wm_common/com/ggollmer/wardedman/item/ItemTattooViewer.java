package com.ggollmer.wardedman.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.GuiConstants;
import com.ggollmer.wardedman.lib.ItemNames;

public class ItemTattooViewer extends ItemWardedMan
{
	public ItemTattooViewer(int id)
	{
		super(id);
		setUnlocalizedName(ItemNames.TATTOO_VIEWER_NAME);
		setCreativeTab(WardedMan.tabsWardedMan);
		maxStackSize = 1;
	}
	
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer thePlayer)
    {
		thePlayer.openGui(WardedMan.instance, GuiConstants.TATTOO_VIEWER_GUI_ID, thePlayer.worldObj, (int) thePlayer.posX, (int) thePlayer.posY, (int) thePlayer.posZ);
        return item;
    }
}
