package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

@EventBusSubscriber
public class TattooFeatherFalling extends Tattoo
{
	public TattooFeatherFalling(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_FOOT_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_FOOT_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_CALF_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_CALF_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onEntityFall(LivingFallEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
			int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.getEntityLiving(), this.id);
			if(tattooCount > 0  && TattooHandler.reducePlayerCharge((EntityPlayer)event.getEntityLiving(), getActionCost()*tattooCount)) {
				event.setDistance(event.getDistance() - (event.getDistance()/5f) * tattooCount);
			}
		}
	}

	@Override
	public int getActionCost() {
		return 50;
	}
}
