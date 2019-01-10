package com.ggollmer.wardedman.tattoo;

import java.util.ArrayList;

import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.lib.TattooConstants;

import net.minecraft.client.resources.I18n;
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
		return I18n.format(unlocalizedName + ".name");
		//return LocalizationHelper.getLocalizedString(unlocalizedName + ".name");
	}
	
	public String getLocalizedDescription() {
		return I18n.format(unlocalizedName + ".desc");
	}
	
	public String getLocalizedCostDescription()
	{
		return I18n.format(TattooConstants.TATTOO_COST_STRING) + getActionCost() + " Ev";
	}
	
	public ResourceLocation getTattooImage() {
		return tattooImage;
	}
	
	public boolean tattooCanBeActivated() {
		return false;
	}
}
