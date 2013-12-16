package com.ggollmer.wardedman.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class GuiButtonSelectable extends GuiButtonModal
{
	protected int xSelectedPosition;
	protected int ySelectedPosition;
	
	protected int selectId;
	
	protected boolean selected;
	
	public GuiButtonSelectable(int id, int selectId, int x, int y, int hx, int hy, int dx, int dy, int sx, int sy, int width, int height,
			ResourceLocation baseTexture) {
		super(id, x, y, hx, hy, dx, dy, width, height, "", baseTexture);
		this.selectId = selectId;
		this.xSelectedPosition = sx;
		this.ySelectedPosition = sy;
	}
	
	@Override
	public void drawBackground(Minecraft par1Minecraft, int par2, int par3)
    {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if(enabled) {
			if(selected) {
				par1Minecraft.getTextureManager().bindTexture(buttonTexture);
	            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xSelectedPosition, this.ySelectedPosition, this.width, this.height);
			} else {
				super.drawBackground(par1Minecraft, par2, par3);
			}
		}
    }
	
	public int getSelectionId() {
		return selectId;
	}
	
	public void setSelectionId(int id) {
		selectId = id;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
}
