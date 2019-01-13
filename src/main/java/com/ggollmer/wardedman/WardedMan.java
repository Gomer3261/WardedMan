package com.ggollmer.wardedman;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;

import com.ggollmer.wardedman.core.handler.ConfigurationHandler;
import com.ggollmer.wardedman.core.proxy.CommonGuiHandlerProxy;
//import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.core.proxy.CommonProxy;
import com.ggollmer.wardedman.creativetab.CreativeTabWardedMan;
import com.ggollmer.wardedman.lib.Reference;
import com.ggollmer.wardedman.tattoo.TattooHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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
		dependencies = Reference.DEPENDENCIES,
		useMetadata=true
		/*certificateFingerprint = Reference.FINGERPRINT*/
		)
public class WardedMan
{
	

	@Instance(Reference.MOD_ID)
	public static WardedMan instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
	@SidedProxy(clientSide = Reference.CLIENT_GUI_PROXY_CLASS, serverSide = Reference.SERVER_GUI_PROXY_CLASS)
	public static CommonGuiHandlerProxy guiHandler;
	
	public static final CreativeTabs tabsWardedMan = new CreativeTabWardedMan(Reference.MOD_ID);
	
	@EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event)
	{
		//LogHelper.log(Level.ERROR, Strings.LOG_FINGERPRINT_ERROR);
    }
	
	/**
	 * Called before WardedMan is loaded into minecraft.
	 * @param event The FML load event.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.CHANNEL_NAME + File.separator + Reference.MOD_ID + ".cfg"));
		
		proxy.preInit(event);
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
        
        TattooHandler.init();
        //VillageHandler.init();
        
        //tattooTracker = new TattooTracker();
        //dropMonitor = new DropMonitor();
        //MinecraftForge.EVENT_BUS.register(dropMonitor);
	}
	
	/**
	 * Called as WardedMan is loaded into minecraft.
	 * @param event The FML load event.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	/**
	 * Called after WardedMan is loaded into minecraft.
	 * @param event The FML load event.
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}