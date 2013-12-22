package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class TattooIronFist extends Tattoo
{
	public TattooIronFist(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_PALM_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_HAND_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_HAND_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onEntityHurt(AttackEntityEvent event) {
		int tattooCount = TattooHandler.getPlayerTattooAmount(event.entityPlayer, this.id);
		if(tattooCount > 0 && TattooHandler.reducePlayerCharge(event.entityPlayer, getActionCost()*tattooCount)) {
			event.target.attackEntityFrom(DamageSource.causePlayerDamage(event.entityPlayer), 1 * tattooCount);
		}
	}

	@Override
	public int getActionCost() {
		return 25;
	}
}
