package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TattooThorns extends Tattoo
{
	public TattooThorns(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_CHEST_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_CHEST_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_SHOULDER_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_SHOULDER_LOCATION_ID, id);
	}
	
	@ForgeSubscribe
	public void onEntityHurt(LivingHurtEvent event) {
		if(event.entity instanceof EntityPlayer && !event.source.isFireDamage() && !event.source.isUnblockable() && !event.source.isProjectile() && !event.source.isMagicDamage()) {
			int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.entity, this.id);
			if(tattooCount > 0) {
				Entity attacker = event.source.getSourceOfDamage();
				if(attacker != null) {
					attacker.attackEntityFrom(DamageSource.causeThornsDamage(event.entityLiving), event.ammount * tattooCount/2f);
				}
			}
		}
	}

	@Override
	public int getActionCost() {
		return 75;
	}
}
