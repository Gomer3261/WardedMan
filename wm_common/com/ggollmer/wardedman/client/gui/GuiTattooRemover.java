package com.ggollmer.wardedman.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LocalizationHelper;
import com.ggollmer.wardedman.lib.GuiConstants;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.network.PacketTypeHandler;
import com.ggollmer.wardedman.network.packet.PacketTattooRemove;
import com.ggollmer.wardedman.player.TattooStats;
import com.ggollmer.wardedman.tattoo.TattooHandler;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class GuiTattooRemover extends GuiScreen
{
	protected static final ResourceLocation REMOVER_GUI_LOCATION = new ResourceLocation(Reference.MOD_ID, GuiConstants.TATTOO_REMOVER_GUI_LOCATION);
	
	protected static final int[] X_TATTOO_COORDS = new int[]{40, 30, 50, 40,  9, 31, 50, 70,  32,  50, 122, 108, 137, 122, 112, 133, 91, 152, 114, 132, 114, 132};
	protected static final int[] Y_TATTOO_COORDS = new int[]{8,  30, 30, 55, 71, 77, 77, 71, 123, 123,   8,  31,  31,  48,  66,  66, 71,  71,  84,  84, 111, 111};
	
	protected EntityPlayer player;
	
	/** The X size of the inventory window in pixels. */
    public int xSize = 176;

    /** The Y size of the inventory window in pixels. */
    public int ySize = 166;
    
    public int xOffset;
    public int yOffset;
    
    GuiButton cancelButton;
    GuiButton submitButton;
    
    List<GuiButtonSelectableDisplay> locationButtons;
    int activeLocation = -1;
	
	public GuiTattooRemover(EntityPlayer thePlayer, World world, int x, int y, int z)
    {
        super();
        this.player = thePlayer;
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        
        locationButtons = new ArrayList<GuiButtonSelectableDisplay>(TattooConstants.LOCATION_COUNT);
        for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
        	locationButtons.add(i, new GuiButtonSelectableDisplay(
        						2+i, i,
        						xOffset + X_TATTOO_COORDS[i], yOffset + Y_TATTOO_COORDS[i],
        						176, 0, 192, 0, 176, 16, 16, 16,
        						REMOVER_GUI_LOCATION));
        	TattooStats stats = WardedMan.tattooTracker.getPlayerTattooStats(player.username);
        	locationButtons.get(i).darkenOnDisable = true;
        	List<String> tooltip = new ArrayList<String>();
        	if(stats.getTattooId(i) != -1) {
        		locationButtons.get(i).setInternalRect(TattooHandler.tattoos.get(stats.getTattooId(i)).tattooImage, stats.getTattooColour(i));
        		tooltip.add(LocalizationHelper.getLocalizedString(TattooHandler.SlotIDToName.get(i)) + " Tattoo");
        		tooltip.add(TattooHandler.tattoos.get(stats.getTattooId(i)).getLocalizedName());
        		tooltip.add(TattooHandler.tattoos.get(stats.getTattooId(i)).getLocalizedDescription());
        		tooltip.add(TattooHandler.tattoos.get(stats.getTattooId(i)).getLocalizedCostDescription());
        		locationButtons.get(i).enabled = true;
        	} else {
        		tooltip.add(LocalizationHelper.getLocalizedString(TattooHandler.SlotIDToName.get(i)));
        		locationButtons.get(i).enabled = false;
        	}
        	locationButtons.get(i).setTooltip(tooltip);
        	buttonList.add(locationButtons.get(i));
        }
        
        buttonList.add(submitButton = new GuiButtonModal(0, xOffset + 91, yOffset + 144, 176, 32, 176, 49, 39, 17, LocalizationHelper.getLocalizedString(GuiConstants.TATTOO_REMOVER_SUBMIT), REMOVER_GUI_LOCATION));
        buttonList.add(cancelButton = new GuiButtonModal(1, xOffset + 132, yOffset + 144, 176, 32, 176, 49, 39, 17, LocalizationHelper.getLocalizedString(GuiConstants.TATTOO_REMOVER_CANCEL), REMOVER_GUI_LOCATION));
        
        if(activeLocation >= 0) {
        	locationButtons.get(activeLocation).setSelected(true);
        }
        checkValidOperation();
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawDefaultBackground();
		
        this.mc.getTextureManager().bindTexture(REMOVER_GUI_LOCATION);
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
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if(button == cancelButton) {
			closeGui();
		}
		else if(button == submitButton) {
			submitRemovalRequest(locationButtons.get(activeLocation).getSelectionId());
			closeGui();
		}
		
		for(int i=0; i < TattooConstants.LOCATION_COUNT; i++) {
			if(locationButtons.get(i) == button) {
				if(activeLocation >= 0) locationButtons.get(activeLocation).setSelected(false);
				activeLocation = i;
				locationButtons.get(activeLocation).setSelected(true);
				checkValidOperation();
			}
		}
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	private void checkValidOperation() {
		if(activeLocation >= 0) {
			submitButton.enabled = true;
		}
		else {
			submitButton.enabled = false;
		}
	}
	
	private void closeGui() {
		this.mc.displayGuiScreen((GuiScreen)null);
        this.mc.setIngameFocus();
	}
	
	private void submitRemovalRequest(int tattooLocation) {
		PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketTattooRemove(this.player.username, tattooLocation)));
	}
}
