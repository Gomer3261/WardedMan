package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

@EventBusSubscriber
public class TattooJumpBoost extends Tattoo
{
	public TattooJumpBoost(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_FOOT_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_FOOT_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_CALF_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_CALF_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.getEntityLiving(), this.id);
			if(tattooCount > 0  && TattooHandler.reducePlayerCharge((EntityPlayer)event.getEntityLiving(), getActionCost()*tattooCount)) {
				event.getEntityLiving().motionY += 0.1F * tattooCount;
			}
		}
	}

	@Override
	public int getActionCost() {
		return 100;
	}
}
