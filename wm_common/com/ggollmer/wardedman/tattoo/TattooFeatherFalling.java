package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class TattooFeatherFalling extends Tattoo
{
	public TattooFeatherFalling(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_FOOT_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_FOOT_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_CALF_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_CALF_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onEntityFall(LivingFallEvent event) {
		if(event.entity instanceof EntityPlayer) {
			int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.entity, this.id);
			if(tattooCount > 0) {
				event.distance -= (event.distance/5f) * tattooCount;
			}
		}
	}

	@Override
	public int getActionCost() {
		return 50;
	}
}
