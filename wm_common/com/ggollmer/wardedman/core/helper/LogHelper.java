package com.ggollmer.wardedman.core.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ggollmer.wardedman.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class LogHelper
{
	private static Logger wardedManLogger = Logger.getLogger(Reference.MOD_ID);
	
	public static void init() {
		wardedManLogger.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level logLevel, String message) {
		wardedManLogger.log(logLevel, message);
	}
	
	public static void debugLog(String message) {
		if(Reference.DEBUG_MODE) {
			wardedManLogger.log(Level.INFO, message);
		}
	}
}
