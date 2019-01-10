package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

@EventBusSubscriber
public class TattooProjectileProtection extends Tattoo
{
	public TattooProjectileProtection(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.FACE_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.HEAD_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event) {
		if(event.getEntity() instanceof EntityPlayer && event.getSource().isProjectile()) {
			int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.getEntityLiving(), this.id);
			if(tattooCount > 0 && TattooHandler.reducePlayerCharge((EntityPlayer)event.getEntityLiving(), getActionCost()*tattooCount)) {
				event.setAmount(event.getAmount() - (event.getAmount()/4f) * tattooCount);
			}
		}
	}

	@Override
	public int getActionCost() {
		return 100;
	}
}
