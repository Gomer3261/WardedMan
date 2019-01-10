package com.ggollmer.wardedman.tattoo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.ggollmer.wardedman.lib.TattooConstants;

@EventBusSubscriber
public class TattooNightVision extends Tattoo
{

	public TattooNightVision(String name)
	{
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.FACE_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			if(( (event.getEntityLiving().ticksExisted + event.getEntityLiving().getEntityId()) % 20) == 0) {
				int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.getEntityLiving(), this.id);
				if(tattooCount > 0 && !event.getEntity().getEntityWorld().isDaytime() && TattooHandler.reducePlayerCharge((EntityPlayer)event.getEntityLiving(), getActionCost()*tattooCount)) {
					event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1200));
				}
				else {
					if(event.getEntityLiving().getActivePotionEffect(MobEffects.NIGHT_VISION) != null) {
						event.getEntityLiving().removePotionEffect(MobEffects.NIGHT_VISION);
					}
				}
			}
		}
	}

	@Override
	public int getActionCost()
	{
		return 10;
	}
}
