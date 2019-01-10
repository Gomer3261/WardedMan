package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.EntityLiving;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

@EventBusSubscriber
public class TattooBlindingFist extends Tattoo
{
	public TattooBlindingFist(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAND_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAND_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onEntityHurt(AttackEntityEvent event) {
		if(event.getTarget() instanceof EntityLiving) {
			int tattooCount = TattooHandler.getPlayerTattooAmount(event.getEntityPlayer(), this.id);
			if(tattooCount > 0 && TattooHandler.reducePlayerCharge(event.getEntityPlayer(), getActionCost()*tattooCount)) {
				((EntityLiving) event.getTarget()).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 15, 20*tattooCount));
			}
		}
	}

	@Override
	public int getActionCost() {
		return 200;
	}
}
