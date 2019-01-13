package com.ggollmer.wardedman.core.proxy;

/*import com.ggollmer.wardedman.client.gui.GuiTattooNeedle;
import com.ggollmer.wardedman.client.gui.GuiTattooRemover;
import com.ggollmer.wardedman.client.gui.GuiTattooViewer;
import com.ggollmer.wardedman.lib.GuiConstants;*/

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonGuiHandlerProxy implements IGuiHandler
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
		return null;
		// TODO: This class is loaded client and server, but these UIs only appear on the client
		// This means we can't import them at all on the client.
		// It appears this method of showing UI is only for containers anyways.
		// But I WOULD enjoy somehow making this sided... probaby the same way we use proxies.
		/*switch(ID) {
			case GuiConstants.TATTOO_NEEDLE_GUI_ID:
				return new GuiTattooNeedle(player);
			case GuiConstants.TATTOO_REMOVER_GUI_ID:
				return new GuiTattooRemover(player);
			case GuiConstants.TATTOO_VIEWER_GUI_ID:
				return new GuiTattooViewer(player);
			default:
				return null;
		}*/
	}
	
	public void showTattoNeedleGui(EntityPlayer player) {}
	public void showTattoRemoverGui(EntityPlayer player) {}
	public void showTattoViewerGui(EntityPlayer player) {}
}
