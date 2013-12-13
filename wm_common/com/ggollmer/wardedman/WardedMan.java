package com.ggollmer.wardedman;

import net.minecraft.creativetab.CreativeTabs;

import com.ggollmer.wardedman.core.handler.LocalizationHandler;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.creativetab.CreativeTabWardedMan;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.network.PacketHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

/**
 * IneveraCraft
 *
 * Inevera.java
 *
 * @author gomer3261
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@Mod(
		modid=Reference.MOD_ID,
		name=Reference.MOD_NAME,
		version=Reference.MOD_VERSION,
		dependencies = Reference.DEPENDENCIES/*,
		certificateFingerprint = Reference.FINGERPRINT*/
		)
@NetworkMod(
		channels = { Reference.CHANNEL_NAME },
		clientSideRequired = true,
		serverSideRequired = false,
		packetHandler = PacketHandler.class)
public class WardedMan
{
	

	@Instance(Reference.MOD_ID)
	public static WardedMan instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static com.ggollmer.wardedman.core.proxy.CommonProxy proxy;
	
	public static final CreativeTabs tabsWardedMan = new CreativeTabWardedMan(CreativeTabs.getNextID(), Reference.MOD_ID);
	
	@EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
	{
        //LogHelper.log(Level.SEVERE, Strings.LOG_FINGERPRINT_ERROR);
    }
	
	/**
	 * Called before WardedMan is loaded into minecraft.
	 * @param event The FML load event.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		LogHelper.init();
		
		LocalizationHandler.loadLanguages();
	}
	
	/**
	 * Called as WardedMan is loaded into minecraft.
	 * @param event The FML load event.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	}
	
	/**
	 * Called after WardedMan is loaded into minecraft.
	 * @param event The FML load event.
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}