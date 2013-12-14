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
	protected static final ResourceLocation NEEDLE_GUI_LOCATION = new ResourceLocation(Reference.MOD_ID, "textures/gui/tattooNeedleGui.png");
	
	protected static final int[] X_TATTOO_COORDIANTES = new int[]{25, 19, 31, 25, 7, 19, 32, 44, 19, 32, 75, 67, 83, 75, 69, 81, 56, 93, 68, 82, 69, 82};
	protected static final int[] Y_TATTOO_COORDINATES = new int[]{10, 29, 29, 43, 63, 69, 69, 63, 109, 109, 9, 27, 27, 42, 57, 57, 63, 62, 74, 74, 94, 94};
	
	protected static final int TATTOO_IMAGE_COLS = 3;
	protected static final int TATTOO_IMAGE_ROWS = 6;
	
	protected static final int TATTOO_COLOUR_COLS = 8;
	protected static final int TATTOO_COLOUR_ROWS = 2;
	
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
        for(int i=0; i<TattooConstants.TATTOO_LOCATION_COUNT; i++) {
        	tattooLocationButtons.add(i, new GuiButtonInLineSelection(2+i, xOffset + X_TATTOO_COORDIANTES[i], yOffset + Y_TATTOO_COORDINATES[i], 176, 84, 176, 108, 176, 96, 12, 12, NEEDLE_GUI_LOCATION, null));
        	buttonList.add(tattooLocationButtons.get(i));
        }
        
        tattooColourButtons = new ArrayList<GuiButton>(TattooConstants.TATTOO_COLOUR_COUNT);
        for(int i=0; i<TATTOO_COLOUR_ROWS; i++) {
        	for(int j=0; j<TATTOO_COLOUR_COLS; j++) {
        		tattooColourButtons.add(j + i*TATTOO_COLOUR_COLS, new GuiButtonInLineSelection(2+TattooConstants.TATTOO_LOCATION_COUNT+j+i*TATTOO_COLOUR_COLS, xOffset + 8 + j*15, yOffset + 129 + i*15, 190, 36, 204, 36, 176, 36, 14, 14, NEEDLE_GUI_LOCATION, null));
        		buttonList.add(tattooColourButtons.get(j + i*TATTOO_COLOUR_COLS));
        	}
        }
        
        tattooImageButtons = new ArrayList<GuiButton>(TattooConstants.TATTOO_ID_COUNT);
        for(int i=0; i<TATTOO_IMAGE_ROWS; i++) {
        	for(int j=0; j<TATTOO_IMAGE_COLS; j++) {
        		tattooImageButtons.add(j + i*TATTOO_IMAGE_COLS, new GuiButtonInLineSelection(2+TattooConstants.TATTOO_LOCATION_COUNT+TattooConstants.TATTOO_ID_COUNT+j+i*TATTOO_IMAGE_COLS, xOffset + 112 + j*19, yOffset + 8 + i*19, 176, 0, 194, 0, 176, 18, 18, 18, NEEDLE_GUI_LOCATION, null));
        		buttonList.add(tattooImageButtons.get(j + i*TATTOO_IMAGE_COLS));
        	}
        }
        
        buttonList.add(submitButton = new GuiButtonInLine(0, xOffset + 132, yOffset + 126, 176, 50, 176, 67, 39, 17, LocalizationHelper.getLocalizedString("gui.tattooNeedle.submit"), NEEDLE_GUI_LOCATION));
        buttonList.add(cancelButton = new GuiButtonInLine(1, xOffset + 132, yOffset + 144, 176, 50, 176, 67, 39, 17, LocalizationHelper.getLocalizedString("gui.tattooNeedle.cancel"), NEEDLE_GUI_LOCATION));
        
        submitButton.enabled = false;
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawDefaultBackground();
		
        this.mc.getTextureManager().bindTexture(NEEDLE_GUI_LOCATION);
        int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        
        this.drawTexturedModalRect(xOffset, yOffset, 0, 0, this.xSize, this.ySize);
        
        super.drawScreen(par1, par2, par3);
    }
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if(button == cancelButton || button == submitButton) {
			this.mc.displayGuiScreen((GuiScreen)null);
	        this.mc.setIngameFocus();
		}
		for(GuiButton locationButton : tattooLocationButtons) {
			if(locationButton == button) {
				if(activeLocationButton != null) ((GuiButtonInLineSelection)activeLocationButton).setSelected(false);
				activeLocationButton = locationButton;
				((GuiButtonInLineSelection)activeLocationButton).setSelected(true);
				checkValidOperation();
			}
		}
		for(GuiButton colourButton : tattooColourButtons) {
			if(colourButton == button) {
				if(activeColourButton != null) ((GuiButtonInLineSelection)activeColourButton).setSelected(false);
				activeColourButton = colourButton;
				((GuiButtonInLineSelection)activeColourButton).setSelected(true);
				checkValidOperation();
			}
		}
		for(GuiButton imageButton : tattooImageButtons) {
			if(imageButton == button) {
				if(activeImageButton != null) ((GuiButtonInLineSelection)activeImageButton).setSelected(false);
				activeImageButton = imageButton;
				((GuiButtonInLineSelection)activeImageButton).setSelected(true);
				checkValidOperation();
			}
		}
	}
	
	private void checkValidOperation() {
		if(activeLocationButton != null && activeColourButton != null && activeImageButton != null) {
			submitButton.enabled = true;
		}
		else {
			submitButton.enabled = false;
		}
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
