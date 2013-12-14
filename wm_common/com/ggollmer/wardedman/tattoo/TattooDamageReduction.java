package com.ggollmer.wardedman.tattoo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TattooDamageReduction extends Tattoo
{
	public TattooDamageReduction(int id, String name) {
		super(id, name);
	}

	@Override
	public void onTattooActivation(EntityPlayer player, int tattooCount) {}
	
	@ForgeSubscribe
	public void onEntityHurt(LivingHurtEvent event) {
		if(event.entity instanceof EntityPlayer) {
			int tattooCount = TattooHandler.getPlayerTattooAmount(this.id);
			if(tattooCount > 0) {
				event.ammount = event.ammount * 10.0f/tattooCount;
			}
		}
	}
}
