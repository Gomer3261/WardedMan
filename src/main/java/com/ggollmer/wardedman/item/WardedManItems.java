package com.ggollmer.wardedman.item;

import java.util.Map;

import com.ggollmer.wardedman.lib.ItemNames;
import com.ggollmer.wardedman.lib.Reference;
import com.google.common.collect.Maps;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;

@EventBusSubscriber
public class WardedManItems
{
	/* WardedManItems */
	@ObjectHolder(Reference.MOD_ID + ":" + ItemNames.TATTOO_NEEDLE_NAME)
	public static ItemWardedMan tattooNeedle = null;
	
	@ObjectHolder(Reference.MOD_ID + ":" + ItemNames.ENDER_DYE_NAME)
	public static ItemWardedMan enderDye = null;
	
	@ObjectHolder(Reference.MOD_ID + ":" + ItemNames.TATTOO_REMOVER_NAME)
	public static ItemWardedMan tattooRemover = null;
	
	@ObjectHolder(Reference.MOD_ID + ":" + ItemNames.TATTOO_VIEWER_NAME)
	public static ItemWardedMan tattooViewer = null;
	
	private static final Map<ResourceLocation, String> NEEDLE_LOOT_TABLES = Maps.<ResourceLocation, String>newHashMap();
	
	public WardedManItems() {
		NEEDLE_LOOT_TABLES.put(LootTableList.CHESTS_SIMPLE_DUNGEON, "main");
		NEEDLE_LOOT_TABLES.put(LootTableList.CHESTS_END_CITY_TREASURE, "main");
		NEEDLE_LOOT_TABLES.put(LootTableList.CHESTS_ABANDONED_MINESHAFT, "main");
		NEEDLE_LOOT_TABLES.put(LootTableList.CHESTS_DESERT_PYRAMID, "main");
		NEEDLE_LOOT_TABLES.put(LootTableList.CHESTS_JUNGLE_TEMPLE, "main");
		NEEDLE_LOOT_TABLES.put(LootTableList.CHESTS_WOODLAND_MANSION, "main");
	}
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
			new ItemTattooNeedle(ItemNames.TATTOO_NEEDLE_NAME, 16),
			new ItemWardedMan(ItemNames.ENDER_DYE_NAME, 64),
			new ItemTattooRemover(ItemNames.TATTOO_REMOVER_NAME, 16),
			new ItemTattooViewer(ItemNames.TATTOO_VIEWER_NAME, 1)
		);
	}
	
	@SubscribeEvent
	public static void registerLootTables(LootTableLoadEvent event) {
		if(NEEDLE_LOOT_TABLES.containsKey(event.getName())) {
			final LootPool pool = event.getTable().getPool(NEEDLE_LOOT_TABLES.get(event.getName()));
			 
	        if (pool != null) {
	        	// pool2.addEntry(new LootEntryItem(ITEM, WEIGHT, QUALITY, FUNCTIONS, CONDITIONS, NAME));
	            pool.addEntry(new LootEntryItem(tattooNeedle, 10, 0, new LootFunction[] {new SetCount(new LootCondition[0], new RandomValueRange(1, 5))}, new LootCondition[0], Reference.MOD_ID + ":tattoo_needle"));
	        }
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		tattooNeedle.registerItemModel();
		enderDye.registerItemModel();
		tattooRemover.registerItemModel();
		tattooViewer.registerItemModel();
	}
}
