package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class TattooJumpBoost extends Tattoo
{
	public TattooJumpBoost(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_FOOT_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_FOOT_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_CALF_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_CALF_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onEntityJump(LivingJumpEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.entityLiving, this.id);
			if(tattooCount > 0  && TattooHandler.reducePlayerCharge((EntityPlayer)event.entityLiving, getActionCost()*tattooCount)) {
				event.entityLiving.motionY += 0.1F * tattooCount;
			}
		}
	}

	@Override
	public int getActionCost() {
		return 100;
	}
}
