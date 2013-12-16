package com.ggollmer.wardedman.village;

import java.util.List;
import java.util.Random;

import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.lib.VillageConstants;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillageHouse2;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentTattooArtistHouse extends ComponentVillageHouse2
{
	private int villagersSpawned=0;
	
	public ComponentTattooArtistHouse()
    {
    }
	
	public ComponentTattooArtistHouse(
			ComponentVillageStartPiece par0ComponentVillageStartPiece,
			int par7, Random par2Random,
			StructureBoundingBox structureboundingbox, int par6)
	{
		super(par0ComponentVillageStartPiece, par7, par2Random, structureboundingbox, par6);
	}

	@SuppressWarnings("rawtypes")
	public static ComponentTattooArtistHouse func_74915_a(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, 0, 0, 10, 6, 7, par6);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(par1List, structureboundingbox) == null ? new ComponentTattooArtistHouse(par0ComponentVillageStartPiece, par7, par2Random, structureboundingbox, par6) : null;
    }
	
	@Override
	protected void spawnVillagers(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6)
    {
		LogHelper.debugLog("Attempting to spawn villagers for house");
        if (this.villagersSpawned < par6)
        {
            for (int i1 = this.villagersSpawned; i1 < par6; ++i1)
            {
                int j1 = this.getXWithOffset(par3 + i1, par5);
                int k1 = this.getYWithOffset(par4);
                int l1 = this.getZWithOffset(par3 + i1, par5);

                if (!par2StructureBoundingBox.isVecInside(j1, k1, l1))
                {
                	LogHelper.debugLog("Attempted to spawn outside of house");
                    break;
                }

                ++this.villagersSpawned;
                LogHelper.debugLog("Spawning tattoo artist: " + j1 + " " + k1 + " " + l1);
                //EntityVillager entityvillager = (EntityVillager) EntityList.createEntityByID(this.getVillagerType(i1), par1World);
                EntityVillager entityvillager = new EntityVillager(par1World, this.getVillagerType(i1));
                entityvillager.setLocationAndAngles((double)j1 + 0.5D, (double)k1, (double)l1 + 0.5D, 0.0F, 0.0F);
                par1World.spawnEntityInWorld(entityvillager);
            }
        }
    }
	
	@Override
	protected int getVillagerType (int par1)
    {
        return VillageConstants.TATTOO_ARTIST_ID;
    }
}
