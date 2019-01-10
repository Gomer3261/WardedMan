package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;

@EventBusSubscriber
public class TattooChiseledFist extends Tattoo
{
	public TattooChiseledFist(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAND_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAND_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onPlayerHarvest(HarvestCheck event) {
		int tattooCount = TattooHandler.getPlayerTattooAmount(event.getEntityPlayer(), this.id);
		if(tattooCount > 0) {
			if(event.getTargetBlock().getMaterial().isToolNotRequired()) {
				event.setCanHarvest(true);
				return;
			}
			
			int harvestLevel = event.getTargetBlock().getBlock().getHarvestLevel(event.getTargetBlock());
			if(harvestLevel < tattooCount && harvestLevel != -1  && TattooHandler.reducePlayerCharge(event.getEntityPlayer(), getActionCost()*tattooCount)) {
				event.setCanHarvest(true);
			}
		}
	}

	@Override
	public int getActionCost() {
		return 25;
	}
}
