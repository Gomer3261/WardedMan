package com.ggollmer.wardedman.client.gui;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.config.GuiUtils;

public class GuiButtonModal extends GuiButton
{
	protected ResourceLocation buttonTexture;
	
	protected FontRenderer fontRenderer;
	
	protected List<String> tooltip;
	
	public int xHoverPosition;
	public int yHoverPosition;
	
	public int xDisabledPosition;
	public int yDisabledPosition;
	
	public GuiButtonModal(int id, int x, int y, int hx, int hy, int dx, int dy, int width, int height,
			String name, ResourceLocation texture)
	{
		super(id, x, y, width, height, name);
		fontRenderer = Minecraft.getMinecraft().fontRenderer;
		this.tooltip = null;
		xHoverPosition = hx;
		yHoverPosition = hy;
		xDisabledPosition = dx;
		yDisabledPosition = dy;
		buttonTexture = texture;
	}
	
	public GuiButtonModal setTooltip(List<String> toolTipText) {
		if(toolTipText != null) {
			for (int k = 0; k < toolTipText.size(); ++k)
	        {
	            if (k == 0)
	            {
	                toolTipText.set(k, TextFormatting.GOLD + toolTipText.get(k));
	            }
	            else
	            {
	                toolTipText.set(k, TextFormatting.GRAY + toolTipText.get(k));
	            }
	        }
		}
		this.tooltip = toolTipText;
		return this;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		this.drawBackground(mc, mouseX, mouseY);
    }

	public void drawBackground(Minecraft mc, int mouseX, int mouseY)
	{
	    if (this.visible)
	    {
	        FontRenderer fontrenderer = mc.fontRenderer;
	        mc.getTextureManager().bindTexture(buttonTexture);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        
	        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
	        int hoverState = this.getHoverState(this.hovered);
	        
	        switch(hoverState) {
	        	case 0:
	        		this.drawTexturedModalRect(this.x, this.y, this.xDisabledPosition, this.yDisabledPosition, this.width, this.height);
	        		break;
	        	case 1:
	        		break;
	        	case 2: //Hover
	        		this.drawTexturedModalRect(this.x, this.y, this.xHoverPosition, this.yHoverPosition, this.width, this.height);
	        		
	        		break;
	        }
	        
	        this.mouseDragged(mc, mouseX, mouseY);
	        int l = 14737632;
	
	        if (!this.enabled)
	        {
	            l = -6250336;
	        }
	        else if (this.hovered)
	        {
	            l = 16777120;
	        }
	
	        this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, l);
	        GL11.glDisable(GL11.GL_BLEND);
	    }
	}
	
	protected void drawTooltip(int mx, int my, int windowWidth, int windowHeight)
    {
        this.hovered = mx >= this.x && my >= this.y && mx < this.x + this.width && my < this.y + this.height;
		if(tooltip != null && this.hovered) {
			GuiUtils.drawHoveringText(null, tooltip, x, y, windowWidth, windowHeight, windowWidth, fontRenderer);
		}
    }
}
