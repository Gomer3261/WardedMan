package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TattooExplosionResistance extends Tattoo
{
	public TattooExplosionResistance(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.AB_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.BACK_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onEntityHurt(LivingHurtEvent event) {
		if(event.entity instanceof EntityPlayer) {
			if(event.source.isExplosion()) {
				int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.entityLiving, this.id);
				if(tattooCount > 0  && TattooHandler.reducePlayerCharge((EntityPlayer)event.entityLiving, getActionCost()*tattooCount)) {
					event.ammount -= (event.ammount/4f) * tattooCount;
				}
			}
		}
	}

	@Override
	public int getActionCost() {
		return 200;
	}
}
