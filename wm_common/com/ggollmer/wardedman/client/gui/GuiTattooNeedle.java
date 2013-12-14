package com.ggollmer.wardedman.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.ggollmer.wardedman.core.helper.LocalizationHelper;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.tattoo.TattooHandler;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiTattooNeedle extends GuiScreen
{
	protected static final ResourceLocation NEEDLE_GUI_LOCATION = new ResourceLocation(Reference.MOD_ID, "textures/gui/tattooNeedleGui.png");
	
	protected static final int[] X_TATTOO_COORDS = new int[]{25, 19, 31, 25, 7, 19, 32, 44, 19, 32, 75, 67, 83, 75, 69, 81, 56, 93, 68, 82, 69, 82};
	protected static final int[] Y_TATTOO_COORDS = new int[]{10, 29, 29, 43, 63, 69, 69, 63, 109, 109, 9, 27, 27, 42, 57, 57, 63, 62, 74, 74, 94, 94};
	
	protected static final int IMAGE_COLS = 3;
	protected static final int IMAGE_ROWS = 6;
	
	protected static final int COLOUR_COLS = 8;
	protected static final int COLOUR_ROWS = 2;
	
	/** The X size of the inventory window in pixels. */
    public int xSize = 176;

    /** The Y size of the inventory window in pixels. */
    public int ySize = 166;
    
    public int xOffset;
    public int yOffset;
    
    GuiButton cancelButton;
    GuiButton submitButton;
    
    List<GuiButtonInLineSelectable> locationButtons;
    int activeLocation = -1;
    
    List<GuiButtonInLineSelectable> colourButtons;
    int activeColour = -1;
    
    List<GuiButtonInLineSelectable> imageButtons;
    int activeImage = -1;
	
	public GuiTattooNeedle(EntityPlayer thePlayer, World world, int x, int y, int z)
    {
        super();
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        
        locationButtons = new ArrayList<GuiButtonInLineSelectable>(TattooConstants.LOCATION_COUNT);
        for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
        	locationButtons.add(i, new GuiButtonInLineSelectable(
        						2+i,
        						xOffset + X_TATTOO_COORDS[i], yOffset + Y_TATTOO_COORDS[i],
        						176, 84, 176, 108, 176, 96, 12, 12,
        						NEEDLE_GUI_LOCATION));
        	buttonList.add(locationButtons.get(i));
        }
        
        colourButtons = new ArrayList<GuiButtonInLineSelectable>(TattooConstants.COLOUR_COUNT);
        for(int i=0; i<COLOUR_ROWS; i++) {
        	for(int j=0; j<COLOUR_COLS; j++) {
        		colourButtons.add(j + i*COLOUR_COLS, new GuiButtonInLineSelectable(
        						2+TattooConstants.LOCATION_COUNT+j+i*COLOUR_COLS, 
        						xOffset + 8 + j*15, yOffset + 129 + i*15, 
        						190, 36, 204, 36, 176, 36, 14, 14, 
        						NEEDLE_GUI_LOCATION, new ResourceLocation("textures/items/dye_powder" + "_" + ItemDye.dyeItemNames[j + i*COLOUR_COLS] + ".png")));
        		buttonList.add(colourButtons.get(j + i*COLOUR_COLS));
        	}
        }
        
        imageButtons = new ArrayList<GuiButtonInLineSelectable>(TattooConstants.ID_COUNT);
        for(int i=0; i<IMAGE_ROWS; i++) {
        	for(int j=0; j<IMAGE_COLS; j++) {
        		ResourceLocation temp = null;
        		if(TattooHandler.tattoos[j + i*IMAGE_COLS] != null) {
        			temp = TattooHandler.tattoos[j + i*IMAGE_COLS].tattooImage;
        		}
        		imageButtons.add(j + i*IMAGE_COLS, new GuiButtonInLineSelectable(
        						2+TattooConstants.LOCATION_COUNT+TattooConstants.ID_COUNT+j+i*IMAGE_COLS, 
        						xOffset + 112 + j*19, yOffset + 8 + i*19,
        						176, 0, 194, 0, 176, 18, 18, 18,
        						NEEDLE_GUI_LOCATION, temp));
        		buttonList.add(imageButtons.get(j + i*IMAGE_COLS));
        		if(temp == null) imageButtons.get(j + i*IMAGE_COLS).enabled = false;
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
		if(button == cancelButton) {
			closeGui();
		}
		else if(button == submitButton) {
			submitTattooRequest(activeLocation, activeImage, activeColour);
		}
		
		
		for(int i=0; i < TattooConstants.LOCATION_COUNT; i++) {
			if(locationButtons.get(i) == button) {
				if(activeLocation >= 0) locationButtons.get(activeLocation).setSelected(false);
				activeLocation = i;
				locationButtons.get(activeLocation).setSelected(true);
				checkValidOperation();
			}
		}
		for(int i=0; i < TattooConstants.COLOUR_COUNT; i++) {
			if(colourButtons.get(i) == button) {
				if(activeColour >= 0) colourButtons.get(activeColour).setSelected(false);
				activeColour = i;
				colourButtons.get(activeColour).setSelected(true);
				checkValidOperation();
			}
		}
		for(int i=0; i < TattooConstants.ID_COUNT; i++) {
			if(imageButtons.get(i) == button) {
				if(activeImage != -1) imageButtons.get(activeImage).setSelected(false);
				activeImage = i;
				imageButtons.get(activeImage).setSelected(true);
				checkValidOperation();
			}
		}
	}
	
	private void checkValidOperation() {
		if(activeLocation >= 0 && activeColour >= 0 && activeImage >= 0) {
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
	
	private void closeGui() {
		this.mc.displayGuiScreen((GuiScreen)null);
        this.mc.setIngameFocus();
	}
	
	private void submitTattooRequest(int tattooLocation, int tattooId, int tattooColour) {
		// TODO This should send out a request to the server to apply a tattoo!
	}
}
