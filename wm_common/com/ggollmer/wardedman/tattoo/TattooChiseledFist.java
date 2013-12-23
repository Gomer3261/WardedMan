package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;

public class TattooChiseledFist extends Tattoo
{
	public TattooChiseledFist(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAND_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAND_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onPlayerHarvest(HarvestCheck event) {
		int tattooCount = TattooHandler.getPlayerTattooAmount(event.entityPlayer, this.id);
		if(tattooCount > 0) {
			if(event.block.blockMaterial.isToolNotRequired()) {
				event.success = true;
				return;
			}
			
			int harvestLevel = MinecraftForge.getBlockHarvestLevel(event.block, 0, "pickaxe");
			if(harvestLevel < tattooCount && harvestLevel != -1  && TattooHandler.reducePlayerCharge(event.entityPlayer, getActionCost()*tattooCount)) {
				event.success = true;
			}
		}
	}

	@Override
	public int getActionCost() {
		return 25;
	}
}
