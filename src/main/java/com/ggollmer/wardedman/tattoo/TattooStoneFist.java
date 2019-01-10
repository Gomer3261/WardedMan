package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;

@EventBusSubscriber
public class TattooStoneFist extends Tattoo
{
	public TattooStoneFist(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAND_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAND_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onBlockBreak(BreakSpeed event) {
		int tattooCount = TattooHandler.getPlayerTattooAmount(event.getEntityPlayer(), this.id);
		if(tattooCount > 0  && TattooHandler.reducePlayerCharge(event.getEntityPlayer(), getActionCost()*tattooCount)) {
			event.setNewSpeed(event.getOriginalSpeed() + (event.getOriginalSpeed()*tattooCount/3));
		}
	}

	@Override
	public int getActionCost() {
		return 10;
	}
}
