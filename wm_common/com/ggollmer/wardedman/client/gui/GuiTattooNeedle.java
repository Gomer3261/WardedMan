package com.ggollmer.wardedman.client.gui;

import org.lwjgl.opengl.GL11;

import com.ggollmer.wardedman.lib.Reference;

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

	
	public GuiTattooNeedle(EntityPlayer thePlayer, World world, int x, int y, int z)
    {
        super();
    }
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
    {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawDefaultBackground();
		
        this.mc.getTextureManager().bindTexture(needleGuiLocation);
        int xOffset = (this.width - this.xSize) / 2;
        int yOffset = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
	
	@Override
	protected void mouseClicked(int x, int y, int button) {
		this.mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
		closeGui();
    }
	
	@Override
	protected void mouseMovedOrUp(int x, int y, int button) {
		
	}
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time) {
		
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	public void closeGui() {
		this.mc.displayGuiScreen((GuiScreen)null);
        this.mc.setIngameFocus();
	}
}
