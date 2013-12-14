package com.ggollmer.wardedman.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class GuiButtonSelectableDisplay extends GuiButtonSelectable
{
	protected ResourceLocation internalTexture;
	protected int internalColour;
	
	public GuiButtonSelectableDisplay(int id, int selectId, int x, int y, int hx, int hy, int dx, int dy, int sx, int sy, int width, int height,
			ResourceLocation baseTexture) {
		super(id, selectId, x, y, hx, hy, dx, dy, sx, sy, width, height, baseTexture);
		this.internalTexture = null;
	}
	
	public GuiButtonSelectableDisplay setInternalTexture(ResourceLocation internalText) {
		return setInternalRect(internalText, 255 + (255 << 8) + (255 << 16));
	}
	
	public GuiButtonSelectableDisplay setInternalRect(ResourceLocation internalText, int colour) {
		internalTexture = internalText;
		internalColour = colour;
		return this;
	}
	
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
		super.drawButton(par1Minecraft, par2, par3);
		if(internalTexture != null) {
			par1Minecraft.getTextureManager().bindTexture(internalTexture);
			this.drawInternalTexturedModalRect(this.xPosition, this.yPosition, this.width, this.height);
		}
    }
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void drawInternalTexturedModalRect(int par1, int par2, int par5, int par6)
    {
		int r = (this.internalColour >> 16) & 255; 
		int g = (this.internalColour >> 8) & 255;
		int b = this.internalColour & 255;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque(r, g, b);
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)0, (double)1);
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)1, (double)1);
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)1, (double)0);
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)0, (double)0);
        tessellator.draw();
        tessellator.setColorOpaque(255, 255, 255);
    }
}