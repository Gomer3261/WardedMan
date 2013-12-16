package com.ggollmer.wardedman.client.gui;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

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
		if(tooltip != null) {
			for (int k = 0; k < toolTipText.size(); ++k)
	        {
	            if (k == 0)
	            {
	                toolTipText.set(k, EnumChatFormatting.GOLD + toolTipText.get(k));
	            }
	            else
	            {
	                toolTipText.set(k, EnumChatFormatting.GRAY + toolTipText.get(k));
	            }
	        }
		}
		this.tooltip = toolTipText;
		return this;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mx, int my)
	{
		this.drawBackground(mc, mx, my);
    }

	public void drawBackground(Minecraft mc, int mx, int my)
	{
	    if (this.drawButton)
	    {
	        FontRenderer fontrenderer = mc.fontRenderer;
	        mc.getTextureManager().bindTexture(buttonTexture);
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        this.field_82253_i = mx >= this.xPosition && my >= this.yPosition && mx < this.xPosition + this.width && my < this.yPosition + this.height;
	        int hoverState = this.getHoverState(this.field_82253_i);
	        
	        switch(hoverState) {
	        	case 0:
	        		this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xDisabledPosition, this.yDisabledPosition, this.width, this.height);
	        		break;
	        	case 1:
	        		break;
	        	case 2: //Hover
	        		this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xHoverPosition, this.yHoverPosition, this.width, this.height);
	        		
	        		break;
	        }
	        
	        
	        this.mouseDragged(mc, mx, my);
	        int l = 14737632;
	
	        if (!this.enabled)
	        {
	            l = -6250336;
	        }
	        else if (this.field_82253_i)
	        {
	            l = 16777120;
	        }
	
	        this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
	    }
	}
	
	
	
	protected void drawTooltip(int mx, int my)
    {
        this.field_82253_i = mx >= this.xPosition && my >= this.yPosition && mx < this.xPosition + this.width && my < this.yPosition + this.height;
		if(tooltip != null && this.field_82253_i) {
        	drawHoveringText(tooltip, mx, my, fontRenderer);
		}
    }
	
	protected void drawHoveringText(List<String> lines, int mx, int my, FontRenderer font)
    {
        if (!lines.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int maxLineWidth = 0;
            Iterator<String> iterator = lines.iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();
                int lineWidth = font.getStringWidth(s);

                if (lineWidth > maxLineWidth)
                {
                    maxLineWidth = lineWidth;
                }
            }

            int xDrawPosition = mx + 12;
            int yDrawPosition = my - 12;
            int textHeight = 8;

            if (lines.size() > 1)
            {
                textHeight += 2 + (lines.size() - 1) * 10;
            }

            /*if (xDrawPosition + maxLineWidth > this.width)
            {
                xDrawPosition -= 28 + maxLineWidth;
            }

            if (yDrawPosition + textHeight + 6 > this.height)
            {
                yDrawPosition = this.height - textHeight - 6;
            }*/
            
            /* Render the border around the tooltip */
            this.zLevel += 10.0F;
            int l1 = -267386864;
            this.drawGradientRect(xDrawPosition - 3, yDrawPosition - 4, xDrawPosition + maxLineWidth + 3, yDrawPosition - 3, l1, l1);
            this.drawGradientRect(xDrawPosition - 3, yDrawPosition + textHeight + 3, xDrawPosition + maxLineWidth + 3, yDrawPosition + textHeight + 4, l1, l1);
            this.drawGradientRect(xDrawPosition - 3, yDrawPosition - 3, xDrawPosition + maxLineWidth + 3, yDrawPosition + textHeight + 3, l1, l1);
            this.drawGradientRect(xDrawPosition - 4, yDrawPosition - 3, xDrawPosition - 3, yDrawPosition + textHeight + 3, l1, l1);
            this.drawGradientRect(xDrawPosition + maxLineWidth + 3, yDrawPosition - 3, xDrawPosition + maxLineWidth + 4, yDrawPosition + textHeight + 3, l1, l1);
            int i2 = 1347420415;
            int j2 = (i2 & 16711422) >> 1 | i2 & -16777216;
            this.drawGradientRect(xDrawPosition - 3, yDrawPosition - 3 + 1, xDrawPosition - 3 + 1, yDrawPosition + textHeight + 3 - 1, i2, j2);
            this.drawGradientRect(xDrawPosition + maxLineWidth + 2, yDrawPosition - 3 + 1, xDrawPosition + maxLineWidth + 3, yDrawPosition + textHeight + 3 - 1, i2, j2);
            this.drawGradientRect(xDrawPosition - 3, yDrawPosition - 3, xDrawPosition + maxLineWidth + 3, yDrawPosition - 3 + 1, i2, i2);
            this.drawGradientRect(xDrawPosition - 3, yDrawPosition + textHeight + 2, xDrawPosition + maxLineWidth + 3, yDrawPosition + textHeight + 3, j2, j2);

            for (int k2 = 0; k2 < lines.size(); ++k2)
            {
                String line = (String)lines.get(k2);
                font.drawStringWithShadow(line, xDrawPosition, yDrawPosition, -1);

                if (k2 == 0)
                {
                    yDrawPosition += 2;
                }

                yDrawPosition += 10;
            }

            this.zLevel -= 10.0F;
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }
	
}
