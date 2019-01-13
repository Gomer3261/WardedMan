package com.ggollmer.wardedman.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.ggollmer.wardedman.WardedMan;
//import com.ggollmer.wardedman.lib.GuiConstants;

public class ItemTattooNeedle extends ItemWardedMan
{
	public ItemTattooNeedle(String registryName, int maxStackSize)
	{
		super(registryName, maxStackSize);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		WardedMan.guiHandler.showTattoNeedleGui(playerIn);
		//playerIn.openGui(WardedMan.instance, GuiConstants.TATTOO_NEEDLE_GUI_ID, playerIn.world, );
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
