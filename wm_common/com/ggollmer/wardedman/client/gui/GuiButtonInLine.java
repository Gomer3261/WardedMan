package com.ggollmer.wardedman.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiButtonInLine extends GuiButton
{
	protected ResourceLocation buttonTexture;
	
	public int xHoverPosition;
	public int yHoverPosition;
	
	public int xDisabledPosition;
	public int yDisabledPosition;
	
	public GuiButtonInLine(int id, int x, int y, int hx, int hy, int dx, int dy, int width, int height,
			String name, ResourceLocation texture)
	{
		super(id, x, y, width, height, name);
		xHoverPosition = hx;
		yHoverPosition = hy;
		xDisabledPosition = dx;
		yDisabledPosition = dy;
		buttonTexture = texture;
	}
	
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            FontRenderer fontrenderer = par1Minecraft.fontRenderer;
            par1Minecraft.getTextureManager().bindTexture(buttonTexture);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int hoverState = this.getHoverState(this.field_82253_i);
            
            switch(hoverState) {
            	case 0:
            		this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xDisabledPosition, this.yDisabledPosition, this.width, this.height);
            		break;
            	case 1:
            		break;
            	case 2:
            		this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xHoverPosition, this.yHoverPosition, this.width, this.height);
            		break;
            }
            
            
            this.mouseDragged(par1Minecraft, par2, par3);
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
	
}
