package com.ggollmer.wardedman.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiButtonSelectableSpriteDisplay extends GuiButtonSelectable
{
	protected TextureAtlasSprite internalSprite = null;
	protected int internalColour;
	public boolean darkenOnDisable;
	
	public GuiButtonSelectableSpriteDisplay(int id, int selectId, int x, int y, int hx, int hy, int dx, int dy, int sx, int sy, int width, int height,
			ResourceLocation baseTexture) {
		super(id, selectId, x, y, hx, hy, dx, dy, sx, sy, width, height, baseTexture);
		this.internalSprite = null;
		this.darkenOnDisable = true;
	}
	
	public GuiButtonSelectableSpriteDisplay setInternalSprite(TextureAtlasSprite sprite) {
		// HAAAAX
		return setInternalRect(sprite, 255 + (255 << 8) + (255 << 16));
	}
	
	public GuiButtonSelectableSpriteDisplay setInternalRect(TextureAtlasSprite sprite, int colour) {
		internalSprite = sprite;
		internalColour = colour;
		return this;
	}
	
	@Override
	public void drawBackground(Minecraft mc, int mouseX, int mouseY)
    {
		super.drawBackground(mc, mouseX, mouseY);
		if(internalSprite != null) {
			// Hacky garbage.
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			this.drawInternalTexturedModalRect(this.x, this.y, internalSprite, this.width, this.height);
		}
    }
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void drawInternalTexturedModalRect(int par1, int par2, TextureAtlasSprite textureSprite, int par5, int par6)
    {
		int r = (this.internalColour >> 16) & 255; if(!enabled && darkenOnDisable) r = ((r - 120) < 0) ? 0 : r - 120;
		int g = (this.internalColour >> 8) & 255; if(!enabled && darkenOnDisable) g = ((g - 120) < 0) ? 0 : g - 120;
		int b = this.internalColour & 255; if(!enabled && darkenOnDisable) b = ((b - 120) < 0) ? 0 : b - 120;
		
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.color(r, g, b, 1.0f);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)textureSprite.getMinU(), (double)textureSprite.getMaxV()).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)textureSprite.getMaxU(), (double)textureSprite.getMaxV()).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)textureSprite.getMaxU(), (double)textureSprite.getMinV()).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)textureSprite.getMinU(), (double)textureSprite.getMinV()).endVertex();
        tessellator.draw();
    }
}
