package com.ggollmer.wardedman.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GuiButtonInLineSelection extends GuiButtonInLine
{
	protected ResourceLocation internalTexture;
	
	private int xSelectedPosition;
	private int ySelectedPosition;
	
	private boolean selected;
	
	public GuiButtonInLineSelection(int id, int x, int y, int hx, int hy, int dx, int dy, int sx, int sy, int width, int height,
			ResourceLocation baseTexture, ResourceLocation internalTexture)
	{
		super(id, x, y, hx, hy, dx, dy, width, height, "", baseTexture);
		this.internalTexture = internalTexture;
		this.xSelectedPosition = sx;
		this.ySelectedPosition = sy;
	}
	
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
		if(selected) {
			par1Minecraft.getTextureManager().bindTexture(buttonTexture);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xSelectedPosition, this.ySelectedPosition, this.width, this.height);
		} else {
			super.drawButton(par1Minecraft, par2, par3);
		}
    }
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
}
