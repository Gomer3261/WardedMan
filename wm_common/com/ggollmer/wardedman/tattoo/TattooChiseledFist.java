package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;

public class TattooChiseledFist extends Tattoo
{
	public TattooChiseledFist(int id, String name) {
		super(id, name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAND_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAND_LOCATION_ID, id);
	}

	@Override
	public void onTattooActivation(EntityPlayer player, int tattooCount) {}
	
	@ForgeSubscribe
	public void onEntityHurt(HarvestCheck event) {
		if(event.entityPlayer.inventory.mainInventory[event.entityPlayer.inventory.currentItem] == null)
		{
			int tattooCount = TattooHandler.getPlayerTattooAmount(event.entityPlayer, this.id);
			if(tattooCount > 0) {
				if(event.block.blockMaterial.isToolNotRequired()) {
					event.success = true;
					return;
				}
				
				int harvestLevel = MinecraftForge.getBlockHarvestLevel(event.block, 0, "pickaxe");
				if(harvestLevel < tattooCount && harvestLevel != -1) {
					event.success = true;
				}
			}
		}
	}
}
