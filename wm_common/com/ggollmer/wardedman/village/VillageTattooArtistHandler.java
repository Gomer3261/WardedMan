package com.ggollmer.wardedman.village;

import java.util.List;
import java.util.Random;

import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureVillagePieceWeight;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageTattooArtistHandler implements IVillageCreationHandler
{

	@Override
	public StructureVillagePieceWeight getVillagePieceWeight(Random random,
			int i)
	{
		return new StructureVillagePieceWeight(ComponentTattooArtistHouse.class, 30, i + random.nextInt(4));
	}

	@Override
	public Class<?> getComponentClass()
	{
		return ComponentTattooArtistHouse.class;
	}

	@Override
	public Object buildComponent(StructureVillagePieceWeight villagePiece,
			ComponentVillageStartPiece startPiece, List pieces, Random random,
			int p1, int p2, int p3, int p4, int p5)
	{
		// TODO Currently extending existing minecraft houses. Might be nice to create our own unique building after modjam.
		return ComponentTattooArtistHouse.func_74915_a(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}

}
