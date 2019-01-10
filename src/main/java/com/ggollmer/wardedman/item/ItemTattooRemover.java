package com.ggollmer.wardedman.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.lib.GuiConstants;

public class ItemTattooRemover extends ItemWardedMan
{
	public ItemTattooRemover(String registryName, int maxStackSize)
	{
		super(registryName, maxStackSize);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		playerIn.openGui(WardedMan.instance, GuiConstants.TATTOO_REMOVER_GUI_ID, playerIn.world, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
