package com.ggollmer.wardedman.tattoo;

import net.minecraftforge.common.MinecraftForge;

public class TattooHandler
{
	public static Tattoo tattooDamageReduction;
	
	public static void init() {
		
		tattooDamageReduction = new TattooDamageReduction(0, null, null);
		
		MinecraftForge.EVENT_BUS.register(tattooDamageReduction);
	}
	
	public static int getPlayerTattooAmount(int id)
	{
		return 0;
	}

}
