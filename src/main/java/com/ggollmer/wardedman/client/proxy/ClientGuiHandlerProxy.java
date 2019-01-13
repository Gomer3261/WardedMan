package com.ggollmer.wardedman.client.proxy;

import com.ggollmer.wardedman.client.gui.GuiTattooNeedle;
import com.ggollmer.wardedman.client.gui.GuiTattooRemover;
import com.ggollmer.wardedman.client.gui.GuiTattooViewer;
import com.ggollmer.wardedman.core.proxy.CommonGuiHandlerProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientGuiHandlerProxy extends CommonGuiHandlerProxy {
	
	@Override
	public void showTattoNeedleGui(EntityPlayer player) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiTattooNeedle(player));
	}
	
	@Override
	public void showTattoRemoverGui(EntityPlayer player) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiTattooRemover(player));
	}
	
	@Override
	public void showTattoViewerGui(EntityPlayer player) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiTattooViewer(player));
	}
	
}
