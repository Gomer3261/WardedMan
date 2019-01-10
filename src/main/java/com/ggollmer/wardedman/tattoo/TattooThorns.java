package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

@EventBusSubscriber
public class TattooThorns extends Tattoo
{
	public TattooThorns(String name) {
		super(name);
		
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_CHEST_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_CHEST_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.LEFT_SHOULDER_LOCATION_ID, id);
		TattooHandler.validateTattooForLocation(TattooConstants.RIGHT_SHOULDER_LOCATION_ID, id);
	}
	
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent event) {
		if(event.getEntity() instanceof EntityPlayer && !event.getSource().isFireDamage() && !event.getSource().isUnblockable() && !event.getSource().isProjectile() && !event.getSource().isMagicDamage()) {
			int tattooCount = TattooHandler.getPlayerTattooAmount((EntityPlayer)event.getEntity(), this.id);
			if(tattooCount > 0  && TattooHandler.reducePlayerCharge((EntityPlayer)event.getEntity(), getActionCost()*tattooCount)) {
				Entity attacker = event.getSource().getTrueSource();
				if(attacker != null) {
					attacker.attackEntityFrom(DamageSource.causeThornsDamage(event.getEntityLiving()), event.getAmount() * tattooCount/2f);
				}
			}
		}
	}

	@Override
	public int getActionCost() {
		return 75;
	}
}
