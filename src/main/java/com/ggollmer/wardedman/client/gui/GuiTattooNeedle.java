package com.ggollmer.wardedman.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.ggollmer.wardedman.core.proxy.CommonProxy;
import com.ggollmer.wardedman.lib.GuiConstants;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.network.PacketHandler;
import com.ggollmer.wardedman.network.packet.PacketTattooRequest;
import com.ggollmer.wardedman.player.TattooStats;
import com.ggollmer.wardedman.player.TattooTracker;
import com.ggollmer.wardedman.tattoo.TattooHandler;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

public class GuiTattooNeedle extends GuiScreen
{
	protected static final ResourceLocation NEEDLE_GUI_LOCATION = new ResourceLocation(Reference.MOD_ID, GuiConstants.TATTOO_NEEDLE_GUI_LOCATION);
	
	protected static final int[] X_TATTOO_COORDS = new int[]{25, 19, 31, 25, 7, 19, 32, 44, 19, 32, 75, 67, 83, 75, 69, 81, 56, 93, 68, 82, 69, 82};
	protected static final int[] Y_TATTOO_COORDS = new int[]{10, 29, 29, 43, 63, 69, 69, 63, 109, 109, 9, 27, 27, 42, 57, 57, 63, 62, 74, 74, 94, 94};
	
	protected static final int IMAGE_BUTTON_COUNT = 18;
	protected static final int IMAGE_COLS = 3;
	protected static final int IMAGE_ROWS = 6;
	
	protected static final int COLOUR_COLS = 8;
	protected static final int COLOUR_ROWS = 2;
	
	protected EntityPlayer player;
	
	/** The X size of the inventory window in pixels. */
    public int xSize = 176;

    /** The Y size of the inventory window in pixels. */
    public int ySize = 166;
    
    public int xOffset;
    public int yOffset;
    
    GuiButton cancelButton;
    GuiButton submitButton;
    
    List<GuiButtonSelectableTextureDisplay> locationButtons;
    int activeLocation = -1;
    
    List<GuiButtonSelectable> colourButtons;
    int activeColour = -1;
    
    List<GuiButtonSelectableTextureDisplay> imageButtons;
    int activeImage = -1;
	
	public GuiTattooNeedle(EntityPlayer thePlayer)
    {
        super();
        this.player = thePlayer;
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@Override
	public void initGui() {
		int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        
        locationButtons = new ArrayList<GuiButtonSelectableTextureDisplay>(TattooConstants.LOCATION_COUNT);
        for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
        	locationButtons.add(i, new GuiButtonSelectableTextureDisplay(
        						2+i, i,
        						xOffset + X_TATTOO_COORDS[i], yOffset + Y_TATTOO_COORDS[i],
        						176, 84, 176, 108, 176, 96, 12, 12,
        						NEEDLE_GUI_LOCATION));
        	TattooStats stats = TattooTracker.getPlayerTattooStats(player.getPersistentID());
        	locationButtons.get(i).darkenOnDisable = false;
        	List<String> tooltip = new ArrayList<String>();
        	if(stats.getTattooId(i) != -1) {
        		locationButtons.get(i).setInternalRect(TattooHandler.tattoos.get(stats.getTattooId(i)).tattooImage, stats.getTattooColour(i));
        		tooltip.add(I18n.format(TattooHandler.SlotIDToName.get(i)) + " Tattoo");
        		tooltip.add(TattooHandler.tattoos.get(stats.getTattooId(i)).getLocalizedName());
        		tooltip.add(TattooHandler.tattoos.get(stats.getTattooId(i)).getLocalizedDescription());
        		tooltip.add(TattooHandler.tattoos.get(stats.getTattooId(i)).getLocalizedCostDescription());
        		locationButtons.get(i).enabled = false;
        	} else {
        		tooltip.add(I18n.format(TattooHandler.SlotIDToName.get(i)));
        	}
        	locationButtons.get(i).setTooltip(tooltip);
        	buttonList.add(locationButtons.get(i));
        }
        
        colourButtons = new ArrayList<GuiButtonSelectable>(TattooConstants.COLOUR_COUNT);
        for(int i=0; i<COLOUR_ROWS; i++) {
        	for(int j=0; j<COLOUR_COLS; j++) {
        		colourButtons.add(j + i*COLOUR_COLS, new GuiButtonSelectableItemDisplay(
        						2+TattooConstants.LOCATION_COUNT+j+i*COLOUR_COLS, j+i*COLOUR_COLS,
        						xOffset + 8 + j*15, yOffset + 129 + i*15, 
        						190, 36, 204, 36, 176, 36, 14, 14, 
        						NEEDLE_GUI_LOCATION).setInternalItem(new ItemStack(CommonProxy.dye, 1, j + i*COLOUR_COLS)));
        						//.setInternalTexture(new ResourceLocation("textures/items/" + dye.getUnlocalizedName(new ItemStack(dye, 1, j + i*COLOUR_COLS)) + ".png")));
        		List<String> tooltip = new ArrayList<String>();
        		tooltip.add(CommonProxy.dye.getItemStackDisplayName(new ItemStack(CommonProxy.dye, 1, j + i*COLOUR_COLS)));
        		colourButtons.get(j + i*COLOUR_COLS).setTooltip(tooltip);
        		buttonList.add(colourButtons.get(j + i*COLOUR_COLS));
        	}
        }
        updateColourButtons();
        
        imageButtons = new ArrayList<GuiButtonSelectableTextureDisplay>(IMAGE_BUTTON_COUNT);
        for(int i=0; i<IMAGE_ROWS; i++) {
        	for(int j=0; j<IMAGE_COLS; j++) {
        		imageButtons.add(j + i*IMAGE_COLS, new GuiButtonSelectableTextureDisplay(
        						2+TattooConstants.LOCATION_COUNT+IMAGE_BUTTON_COUNT+j+i*IMAGE_COLS, j+i*IMAGE_COLS,
        						xOffset + 112 + j*19, yOffset + 8 + i*19,
        						176, 0, 194, 0, 176, 18, 18, 18,
        						NEEDLE_GUI_LOCATION));
        		buttonList.add(imageButtons.get(j + i*IMAGE_COLS));
        		imageButtons.get(j + i*IMAGE_COLS).enabled = false;
        	}
        }
        
        buttonList.add(submitButton = new GuiButtonModal(0, xOffset + 132, yOffset + 126, 176, 50, 176, 67, 39, 17, I18n.format(GuiConstants.TATTOO_NEEDLE_SUBMIT), NEEDLE_GUI_LOCATION));
        buttonList.add(cancelButton = new GuiButtonModal(1, xOffset + 132, yOffset + 144, 176, 50, 176, 67, 39, 17, I18n.format(GuiConstants.TATTOO_NEEDLE_CANCEL), NEEDLE_GUI_LOCATION));
        
        if(activeLocation >= 0) {
        	locationButtons.get(activeLocation).setSelected(true);
        	updateImageButtons(locationButtons.get(activeLocation).getSelectionId());
        }
        if(activeColour >= 0) colourButtons.get(activeColour).setSelected(true);
        if(activeImage >= 0) imageButtons.get(activeImage).setSelected(true);
        checkValidOperation();
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
        drawToolTip(par1, par2, par3);
    }
	
	public void drawToolTip(int par1, int par2, float par3) {
		for (GuiButtonModal guibutton : locationButtons) {
			guibutton.drawTooltip(par1, par2, this.width, this.height);
        }
		for (GuiButtonModal guibutton : imageButtons) {
			guibutton.drawTooltip(par1, par2, this.height, this.width);
        }
		for (GuiButtonModal guibutton : colourButtons) {
			guibutton.drawTooltip(par1, par2, this.width, this.height);
        }
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if(button == cancelButton) {
			closeGui();
		}
		else if(button == submitButton) {
			submitTattooRequest(locationButtons.get(activeLocation).getSelectionId(),
					imageButtons.get(activeImage).getSelectionId(),
					colourButtons.get(activeColour).getSelectionId());
			closeGui();
		}
		
		for(int i=0; i < TattooConstants.LOCATION_COUNT; i++) {
			if(locationButtons.get(i) == button) {
				if(activeLocation >= 0) locationButtons.get(activeLocation).setSelected(false);
				if(activeImage >= 0) imageButtons.get(activeImage).setSelected(false);
				activeLocation = i;
				activeImage = -1;
				locationButtons.get(activeLocation).setSelected(true);
				updateImageButtons(locationButtons.get(activeLocation).getSelectionId());
				checkValidOperation();
			}
		}
		for(int i=0; i < TattooConstants.COLOUR_COUNT; i++) {
			if(colourButtons.get(i) == button) {
				if(activeColour >= 0) colourButtons.get(activeColour).setSelected(false);
				activeColour = i;
				colourButtons.get(activeColour).setSelected(true);
				if(activeLocation >= 0) updateImageButtons(locationButtons.get(activeLocation).getSelectionId());
				checkValidOperation();
			}
		}
		for(int i=0; i < IMAGE_BUTTON_COUNT; i++) {
			if(imageButtons.get(i) == button) {
				if(activeImage != -1) imageButtons.get(activeImage).setSelected(false);
				activeImage = i;
				imageButtons.get(activeImage).setSelected(true);
				checkValidOperation();
			}
		}
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	public void notifyPickup(int damage)
	{
		colourButtons.get(damage).enabled = true;
	}
	
	private void checkValidOperation() {
		if(activeLocation >= 0 && activeColour >= 0 && activeImage >= 0) {
			submitButton.enabled = true;
		}
		else {
			submitButton.enabled = false;
		}
	}
	
	private void updateImageButtons(int locationId) {
		List<Integer> validTattoos = TattooHandler.getValidTattoosForLocation(locationId);
		for(int i=0; i<IMAGE_BUTTON_COUNT; i++) {
			if(i < validTattoos.size()) {
				if(activeColour >= 0) {
					imageButtons.get(i).setInternalRect(TattooHandler.tattoos.get(validTattoos.get(i)).tattooImage, ItemDye.DYE_COLORS[colourButtons.get(activeColour).getSelectionId()]);
				} else {
					imageButtons.get(i).setInternalTexture(TattooHandler.tattoos.get(validTattoos.get(i)).tattooImage);
				}
				
				List<String> tooltip = new ArrayList<String>();
				tooltip.add(TattooHandler.tattoos.get(validTattoos.get(i)).getLocalizedName());
				tooltip.add(TattooHandler.tattoos.get(validTattoos.get(i)).getLocalizedDescription());
				tooltip.add(TattooHandler.tattoos.get(validTattoos.get(i)).getLocalizedCostDescription());
				
				imageButtons.get(i).setTooltip(tooltip);
				imageButtons.get(i).enabled = true;
				imageButtons.get(i).setSelectionId(validTattoos.get(i));
			}
			else {
				imageButtons.get(i).setInternalTexture(null);
				imageButtons.get(i).setTooltip(null);
				imageButtons.get(i).enabled = false;
				imageButtons.get(i).setSelectionId(-1);
			}
		}
	}
	
	private void updateColourButtons() {
		for(GuiButtonSelectable button : colourButtons) {
			button.enabled = false;
		}
		for(ItemStack stack : player.inventory.mainInventory) {
			if(stack == null) continue;
			if(stack.getItem() == CommonProxy.dye) {
				if(stack.getItemDamage() < 16) {
					colourButtons.get(stack.getItemDamage()).enabled = true;
				}
			}
		}
	}
	
	private void closeGui() {
		this.mc.displayGuiScreen((GuiScreen)null);
        this.mc.setIngameFocus();
	}
	
	private void submitTattooRequest(int tattooLocation, int tattooId, int tattooColour) {
		PacketHandler.INSTANCE.sendToServer(new PacketTattooRequest(this.player.getPersistentID(), tattooLocation, tattooId, tattooColour));
	}
}
