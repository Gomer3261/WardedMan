package com.ggollmer.wardedman.tattoo;

import java.util.ArrayList;

import com.ggollmer.wardedman.core.helper.LocalizationHelper;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.util.ResourceLocation;

public abstract class Tattoo
{
	public final ResourceLocation tattooImage;
	public final int id;
	public final String unlocalizedName;
	
	public Tattoo(String name) {
		this.id = TattooHandler.tattoos.size();
		tattooImage = new ResourceLocation(Reference.MOD_ID, "textures/tattoo/" + name + ".png");
		unlocalizedName = "tattoo." + name;
		TattooHandler.tattoos.add(this);
		TattooHandler.validLocations.add(new ArrayList<Integer>());
		TattooHandler.TattooNameToID.put(unlocalizedName, id);
	}
	
	public abstract int getActionCost();
	
	public String getLocalizedName() {
		return LocalizationHelper.getLocalizedString(unlocalizedName + ".name");
	}
	
	public String getLocalizedDescription() {
		return LocalizationHelper.getLocalizedString(unlocalizedName + ".desc");
	}
	
	public String getLocalizedCostDescription()
	{
		return LocalizationHelper.getLocalizedString(TattooConstants.TATTOO_COST_STRING) + getActionCost() + " Ev";
	}
	
	public ResourceLocation getTattooImage() {
		return tattooImage;
	}
	
	public boolean tattooCanBeActivated() {
		return false;
	}
}
