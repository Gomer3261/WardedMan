package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class TattooBlindingFist extends Tattoo
{
	public TattooBlindingFist(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAND_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAND_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onEntityHurt(AttackEntityEvent event) {
		int tattooCount = TattooHandler.getPlayerTattooAmount(event.entityPlayer, this.id);
		if(tattooCount > 0) {
			if(event.target instanceof EntityLiving) {
				((EntityLiving) event.target).addPotionEffect(new PotionEffect(15, 20*tattooCount));
			}
		}
	}

	@Override
	public int getActionCost() {
		return 500;
	}
}
