package com.ggollmer.wardedman.core.proxy;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.player.TattooStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler
{
	public void registerSoundHandler()
	{
    }

    public void initRenderingAndTextures()
    {
    }

    public void registerTileEntities()
    {
    }
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/* Packet handling helpers! */

	public void handleTattooRequestPacket(String username, int id, int location, int colour) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			TattooStats stats = WardedMan.tattooTracker.getPlayerTattooStats(username);
			if(stats != null) {
				stats.applyTattoo(location, id, colour);
			}
		}
	}
}
