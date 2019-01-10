package com.ggollmer.wardedman.core.helper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ggollmer.wardedman.lib.Reference;

public class LogHelper
{
	private static Logger wardedManLogger = LogManager.getLogger(Reference.MOD_ID);
	
	public static void log(Level logLevel, String message) {
		wardedManLogger.log(logLevel, message);
	}
	
	public static void debugLog(String message) {
		if(Reference.DEBUG_MODE) {
			wardedManLogger.log(Level.INFO, message);
		}
	}
}
