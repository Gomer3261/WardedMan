package com.ggollmer.wardedman.tattoo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import com.ggollmer.wardedman.lib.TattooConstants;

public class TattooNightVision extends Tattoo
{

	public TattooNightVision(String name)
	{
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.FACE_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onEntityUpdate(LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			if(( (event.entityLiving.ticksExisted + event.entityLiving.entityId) % 20) == 0) {
				int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.entityLiving, this.id);
				if(tattooCount > 0) {
					event.entityLiving.addPotionEffect(new PotionEffect(16, 1200));
				}
				else {
					if(event.entityLiving.isPotionActive(16)) {
						event.entityLiving.removePotionEffect(16);
					}
				}
			}
		}
	}
}
