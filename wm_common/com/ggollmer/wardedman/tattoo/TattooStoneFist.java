package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;

public class TattooStoneFist extends Tattoo
{
	public TattooStoneFist(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAND_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAND_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onEntityHurt(BreakSpeed event) {
		int tattooCount = TattooHandler.getPlayerTattooAmount(event.entityPlayer, this.id);
		if(tattooCount > 0) {
			event.newSpeed = event.originalSpeed + (event.originalSpeed*tattooCount/3);
		}
	}
}
