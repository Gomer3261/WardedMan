package com.ggollmer.wardedman.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.ggollmer.wardedman.core.helper.LocalizationHelper;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiTattooNeedle extends GuiScreen
{
	protected static final ResourceLocation needleGuiLocation = new ResourceLocation(Reference.MOD_ID, "textures/gui/tattooNeedleGui.png");
	
	protected static final int[] xTattooCoordinates = new int[]{25, 19, 31, 25, 7, 19, 32, 44, 19, 32, 75, 67, 83, 75, 69, 81, 56, 93, 68, 82, 69, 82};
	protected static final int[] yTattooCoordinates = new int[]{10, 29, 29, 43, 63, 69, 69, 63, 109, 109, 9, 27, 27, 42, 57, 57, 63, 62, 74, 74, 94, 94};
	
	/** The X size of the inventory window in pixels. */
    public int xSize = 176;

    /** The Y size of the inventory window in pixels. */
    public int ySize = 166;
    
    public int xOffset;
    public int yOffset;
    
    GuiButton cancelButton;
    GuiButton submitButton;
    
    List<GuiButton> tattooLocationButtons;
    GuiButton activeLocationButton;
    
    List<GuiButton> tattooColourButtons;
    GuiButton activeColourButton;
    
    List<GuiButton> tattooImageButtons;
    GuiButton activeImageButton;
	
	public GuiTattooNeedle(EntityPlayer thePlayer, World world, int x, int y, int z)
    {
        super();
    }
	
	@Override
	public void initGui() {
		int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        
        tattooLocationButtons = new ArrayList<GuiButton>(TattooConstants.TATTOO_LOCATION_COUNT);
        for(int i=0; i<TattooConstants.TATTOO_ID_COUNT; i++) {
        	tattooLocationButtons.add(i, new GuiButton(2+i, xOffset + xTattooCoordinates[i], yOffset + yTattooCoordinates[i], ))
        }
        
        tattooColourButtons = new ArrayList<GuiButton>(16);
        tattooImageButtons = new ArrayList<GuiButton>(TattooConstants.TATTOO_ID_COUNT);
        
        buttonList.add(submitButton = new GuiButtonInLine(0, xOffset + 132, yOffset + 126, 176, 50, 176, 67, 39, 17, LocalizationHelper.getLocalizedString("gui.tattooNeedle.submit"), needleGuiLocation));
        buttonList.add(cancelButton = new GuiButtonInLine(1, xOffset + 132, yOffset + 144, 176, 50, 176, 67, 39, 17, LocalizationHelper.getLocalizedString("gui.tattooNeedle.cancel"), needleGuiLocation));
        
        submitButton.enabled = false;
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
