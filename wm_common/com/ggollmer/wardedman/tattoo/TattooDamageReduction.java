package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TattooDamageReduction extends Tattoo
{
	public TattooDamageReduction(int id, String name) {
		super(id, name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_CHEST_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_CHEST_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.AB_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_THIGH_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_THIGH_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_SHOULDER_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_SHOULDER_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.BACK_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_GLUTEAL_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_GLUTEAL_LOCATION_ID, id);
	}

	@Override
	public void onTattooActivation(EntityPlayer player, int tattooCount) {}
	
	@ForgeSubscribe
	public void onEntityHurt(LivingHurtEvent event) {
		if(event.entity instanceof EntityPlayer) {
			int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.entity, this.id);
			if(tattooCount > 0) {
				event.ammount -= (event.ammount/12f) * tattooCount;
			}
		}
	}
}
