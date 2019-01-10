package com.ggollmer.wardedman.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiButtonSelectableItemDisplay extends GuiButtonSelectable
{
	protected ItemStack internalItem;
	protected int internalColour;
	public boolean darkenOnDisable;
	
	public GuiButtonSelectableItemDisplay(int id, int selectId, int x, int y, int hx, int hy, int dx, int dy, int sx, int sy, int width, int height,
			ResourceLocation baseTexture) {
		super(id, selectId, x, y, hx, hy, dx, dy, sx, sy, width, height, baseTexture);
		this.internalItem = null;
		this.darkenOnDisable = true;
	}
	
	public GuiButtonSelectableItemDisplay setInternalItem(ItemStack item) {
		return setInternalRect(item, 255 + (255 << 8) + (255 << 16));
	}
	
	public GuiButtonSelectableItemDisplay setInternalRect(ItemStack item, int colour) {
		internalItem = item;
		internalColour = colour;
		return this;
	}
	
	@Override
	public void drawBackground(Minecraft mc, int mouseX, int mouseY)
    {
		super.drawBackground(mc, mouseX, mouseY);
		if(internalItem != null) {
			//GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			//mc.getRenderItem().renderItemIntoGUI(internalItem, this.x, this.y);

			TextureAtlasSprite textureAtlasSprite = mc.getRenderItem().getItemModelWithOverrides(internalItem, (World)null, (EntityLiving)null).getParticleTexture();
			mc.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			this.drawInternalTexturedModalRect(this.x, this.y, textureAtlasSprite, this.width, this.height);
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
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)textureSprite.getMinU(), (double)textureSprite.getMaxV()).color(r, g, b, 255).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)textureSprite.getMaxU(), (double)textureSprite.getMaxV()).color(r, g, b, 255).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)textureSprite.getMaxU(), (double)textureSprite.getMinV()).color(r, g, b, 255).endVertex();
        bufferbuilder.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)textureSprite.getMinU(), (double)textureSprite.getMinV()).color(r, g, b, 255).endVertex();
        tessellator.draw();
    }
}
