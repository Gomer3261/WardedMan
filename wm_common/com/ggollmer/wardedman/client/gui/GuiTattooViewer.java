package com.ggollmer.wardedman.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LocalizationHelper;
import com.ggollmer.wardedman.lib.GuiConstants;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;
import com.ggollmer.wardedman.player.TattooStats;
import com.ggollmer.wardedman.tattoo.TattooHandler;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiTattooViewer extends GuiScreen
{
	protected static final ResourceLocation REMOVER_GUI_LOCATION = new ResourceLocation(Reference.MOD_ID, GuiConstants.TATTOO_VIEWER_GUI_LOCATION);
	
	protected static final int[] X_TATTOO_COORDS = new int[]{40, 30, 50, 40,  9, 31, 50, 71,  32,  50, 122, 108, 137, 122, 112, 133, 91, 152, 113, 131, 114, 132};
	protected static final int[] Y_TATTOO_COORDS = new int[]{10, 36, 36, 63, 82, 86, 86, 82, 142, 142,  11,  35,  35,  55,  73,  73, 82,  81,  97,  97, 125, 125};
	
	protected EntityPlayer player;
	
	/** The X size of the inventory window in pixels. */
    public int xSize = 176;

    /** The Y size of the inventory window in pixels. */
    public int ySize = 166;
    
    public int xOffset;
    public int yOffset;
    
    List<GuiButtonModalDisplay> locationButtons;
	
	public GuiTattooViewer(EntityPlayer thePlayer, World world, int x, int y, int z)
    {
        super();
        this.player = thePlayer;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        
        locationButtons = new ArrayList<GuiButtonModalDisplay>(TattooConstants.LOCATION_COUNT);
        for(int i=0; i<TattooConstants.LOCATION_COUNT; i++) {
        	locationButtons.add(i, new GuiButtonModalDisplay(
        						i,
        						xOffset + X_TATTOO_COORDS[i], yOffset + Y_TATTOO_COORDS[i],
        						176, 0, 192, 0, 16, 16,
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
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
}
