package com.ggollmer.wardedman.tattoo;

import com.ggollmer.wardedman.core.helper.LocalizationHelper;
import com.ggollmer.wardedman.lib.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public abstract class Tattoo
{
	public final ResourceLocation tattooImage;
	public final int id;
	public String unlocalizedName;
	
	public Tattoo(int id, String name) {
		this.id = id;
		tattooImage = new ResourceLocation(Reference.MOD_ID, "textures/tattoo/" + name + ".png");
		setUnlocalizedName(name);
	}
	
	public void setUnlocalizedName(String name) {
		unlocalizedName = "tattoo." + name;
	}
	
	public String getLocalizedName() {
		return LocalizationHelper.getLocalizedString(unlocalizedName + ".name");
	}
	
	public String getLocalizedDescription() {
		return LocalizationHelper.getLocalizedString(unlocalizedName + ".desc");
	}
	
	public abstract void onTattooActivation(EntityPlayer player, int tattooCount);
	
	public boolean tattooCanBeActivated() {
		return false;
	}
}
