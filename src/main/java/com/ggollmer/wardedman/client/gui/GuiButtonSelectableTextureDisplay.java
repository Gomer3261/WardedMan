package com.ggollmer.wardedman.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiButtonSelectableTextureDisplay extends GuiButtonSelectable
{
	protected ResourceLocation internalTexture = null;
	protected int internalColour;
	public boolean darkenOnDisable;
	
	public GuiButtonSelectableTextureDisplay(int id, int selectId, int x, int y, int hx, int hy, int dx, int dy, int sx, int sy, int width, int height,
			ResourceLocation baseTexture) {
		super(id, selectId, x, y, hx, hy, dx, dy, sx, sy, width, height, baseTexture);
		this.internalTexture = null;
		this.darkenOnDisable = true;
	}
	
	public GuiButtonSelectableTextureDisplay setInternalTexture(ResourceLocation internalText) {
		return setInternalRect(internalText, 255 + (255 << 8) + (255 << 16));
	}
	
	public GuiButtonSelectableTextureDisplay setInternalRect(ResourceLocation texture, int colour) {
		internalTexture = texture;
		internalColour = colour;
		return this;
	}
	
	@Override
	public void drawBackground(Minecraft mc, int mouseX, int mouseY)
    {
		super.drawBackground(mc, mouseX, mouseY);
		if(internalTexture != null) {
			mc.getTextureManager().bindTexture(internalTexture);
			this.drawInternalTexturedModalRect(this.x, this.y, this.width, this.height);
		}
    }
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void drawInternalTexturedModalRect(int x, int y, int width, int height)
    {
		int r = (this.internalColour >> 16) & 255; if(!enabled && darkenOnDisable) r = ((r - 120) < 0) ? 0 : r - 120;
		int g = (this.internalColour >> 8) & 255; if(!enabled && darkenOnDisable) g = ((g - 120) < 0) ? 0 : g - 120;
		int b = this.internalColour & 255; if(!enabled && darkenOnDisable) b = ((b - 120) < 0) ? 0 : b - 120;
		
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex(0.0, 1.0).color(r, g, b, 255).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex(1.0, 1.0).color(r, g, b, 255).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex(1.0, 0.0).color(r, g, b, 255).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex(0.0, 0.0).color(r, g, b, 255).endVertex();
        tessellator.draw();
    }
}
