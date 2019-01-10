package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

@EventBusSubscriber
public class TattooFireResistance extends Tattoo
{
	public TattooFireResistance(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.AB_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_THIGH_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_THIGH_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_GLUTEAL_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_GLUTEAL_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAM_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
			if(event.getSource().isFireDamage()) {
				int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.getEntityLiving(), this.id);
				if(tattooCount > 0  && TattooHandler.reducePlayerCharge((EntityPlayer)event.getEntityLiving(), getActionCost()*tattooCount)) {
					event.setAmount(event.getAmount() - (event.getAmount()/10f) * tattooCount);
				}
			}
		}
	}

	@Override
	public int getActionCost() {
		return 20;
	}
}
