package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TattooExplosionResistance extends Tattoo
{
	public TattooExplosionResistance(int id, String name) {
		super(id, name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.AB_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.BACK_LOCATION_ID, id);
	}

	@Override
	public void onTattooActivation(EntityPlayer player, int tattooCount) {}
	
	@ForgeSubscribe
	public void onEntityHurt(LivingHurtEvent event) {
		if(event.entity instanceof EntityPlayer) {
			if(event.source.isExplosion()) {
				int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.entity, this.id);
				if(tattooCount > 0) {
					event.ammount -= (event.ammount/4f) * tattooCount;
				}
			}
		}
	}
}
