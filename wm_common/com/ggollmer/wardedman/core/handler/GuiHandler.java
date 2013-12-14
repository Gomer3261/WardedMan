package com.ggollmer.wardedman.core.handler;

import com.ggollmer.wardedman.client.gui.GuiTattooNeedle;
import com.ggollmer.wardedman.lib.GuiIds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		switch(ID) {
			case GuiIds.TATTOO_NEEDLE_GUI_ID:
				return new GuiTattooNeedle(player, world, x, y, z);
			default:
				return null;
		}
	}
}
