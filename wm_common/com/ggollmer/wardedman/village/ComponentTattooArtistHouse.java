package com.ggollmer.wardedman.village;

import com.ggollmer.wardedman.lib.VillageConstants;

import net.minecraft.world.gen.structure.ComponentVillageHouse2;

public class ComponentTattooArtistHouse extends ComponentVillageHouse2
{
	@Override
	protected int getVillagerType (int par1)
    {
        return VillageConstants.TATTOO_ARTIST_ID;
    }
}
