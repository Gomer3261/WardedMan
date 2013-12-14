package com.ggollmer.wardedman.client.gui;

import org.lwjgl.opengl.GL11;

import com.ggollmer.wardedman.core.helper.LocalizationHelper;
import com.ggollmer.wardedman.lib.Reference;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiTattooNeedle extends GuiScreen
{
	protected static final ResourceLocation needleGuiLocation = new ResourceLocation(Reference.MOD_ID, "textures/gui/tattooNeedleGui.png");
	
	/** The X size of the inventory window in pixels. */
    public int xSize = 176;

    /** The Y size of the inventory window in pixels. */
    public int ySize = 166;
    
    public int xOffset;
    public int yOffset;
    
    GuiButton cancelButton;
    GuiButton submitButton;
	
	public GuiTattooNeedle(EntityPlayer thePlayer, World world, int x, int y, int z)
    {
        super();
    }
	
	@Override
	public void initGui() {
		int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        
        this.buttonList.add(submitButton = new GuiButtonInLine(0, xOffset + 132, yOffset + 126, 176, 50, 39, 17, LocalizationHelper.getLocalizedString("gui.tattooNeedle.submit"), needleGuiLocation));
        this.buttonList.add(cancelButton = new GuiButtonInLine(0, xOffset + 132, yOffset + 144, 176, 50, 39, 17, LocalizationHelper.getLocalizedString("gui.tattooNeedle.cancel"), needleGuiLocation));
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawDefaultBackground();
		
        this.mc.getTextureManager().bindTexture(needleGuiLocation);
        int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        
        this.drawTexturedModalRect(xOffset, yOffset, 0, 0, this.xSize, this.ySize);
        
        super.drawScreen(par1, par2, par3);
    }
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if(button == cancelButton) {
			this.mc.displayGuiScreen((GuiScreen)null);
	        this.mc.setIngameFocus();
		}
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
