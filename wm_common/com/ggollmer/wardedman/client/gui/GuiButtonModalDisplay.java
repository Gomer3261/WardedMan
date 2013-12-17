package com.ggollmer.wardedman.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiButtonModalDisplay extends GuiButtonModal
{
	protected ResourceLocation internalTexture;
	protected int internalColour;
	public boolean darkenOnDisable;
	
	public GuiButtonModalDisplay(int id, int x, int y, int hx, int hy, int dx, int dy, int width, int height,
			ResourceLocation baseTexture) {
		super(id, x, y, hx, hy, dx, dy, width, height, "", baseTexture);
		this.internalTexture = null;
		this.darkenOnDisable = true;
	}
	
	public GuiButtonModalDisplay setInternalTexture(ResourceLocation internalText) {
		return setInternalRect(internalText, 255 + (255 << 8) + (255 << 16));
	}
	
	public GuiButtonModalDisplay setInternalRect(ResourceLocation internalText, int colour) {
		internalTexture = internalText;
		internalColour = colour;
		return this;
	}
	
	@Override
	public void drawBackground(Minecraft par1Minecraft, int par2, int par3)
    {
		super.drawBackground(par1Minecraft, par2, par3);
		if(internalTexture != null) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			par1Minecraft.getTextureManager().bindTexture(internalTexture);
			this.drawInternalTexturedModalRect(this.xPosition, this.yPosition, this.width, this.height);
		}
    }
	
	public void drawInternalTexturedModalRect(int par1, int par2, int par5, int par6)
    {
		int r = (this.internalColour >> 16) & 255; if(!enabled && darkenOnDisable) r = ((r - 120) < 0) ? 0 : r - 120;
		int g = (this.internalColour >> 8) & 255; if(!enabled && darkenOnDisable) g = ((g - 120) < 0) ? 0 : g - 120;
		int b = this.internalColour & 255; if(!enabled && darkenOnDisable) b = ((b - 120) < 0) ? 0 : b - 120;
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