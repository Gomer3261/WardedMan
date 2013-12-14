package com.ggollmer.wardedman.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class GuiButtonInLineSelectable extends GuiButtonInLine
{
	protected ResourceLocation internalTexture;
	
	private int xSelectedPosition;
	private int ySelectedPosition;
	
	private boolean selected;
	
	public GuiButtonInLineSelectable(int id, int x, int y, int hx, int hy, int dx, int dy, int sx, int sy, int width, int height,
			ResourceLocation baseTexture) {
		this(id, x, y, hx, hy, dx, dy, sx, sy, width, height, baseTexture, null);
	}
	
	public GuiButtonInLineSelectable(int id, int x, int y, int hx, int hy, int dx, int dy, int sx, int sy, int width, int height,
			ResourceLocation baseTexture, ResourceLocation internalTexture) {
		super(id, x, y, hx, hy, dx, dy, width, height, "", baseTexture);
		this.internalTexture = internalTexture;
		this.xSelectedPosition = sx;
		this.ySelectedPosition = sy;
	}
	
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		if(selected) {
			par1Minecraft.getTextureManager().bindTexture(buttonTexture);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xSelectedPosition, this.ySelectedPosition, this.width, this.height);
		} else {
			super.drawButton(par1Minecraft, par2, par3);
		}
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
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)0, (double)1);
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)1, (double)1);
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)1, (double)0);
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)0, (double)0);
        tessellator.draw();
    }
}
