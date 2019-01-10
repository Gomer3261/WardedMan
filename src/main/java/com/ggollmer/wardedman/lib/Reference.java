package com.ggollmer.wardedman.lib;

public class Reference
{
	public static final boolean DEBUG_MODE = true;
	
	public static final String MOD_ID = "wardedman";
	public static final String MOD_NAME = "WaredMan";
	public static final String MOD_VERSION = "@VERSION@ (build @BUILD_NUMBER@)";
	public static final String CHANNEL_NAME = MOD_ID;
    public static final String DEPENDENCIES = ""; //"required-after:forge@[14.23.2.2634,)";
    public static final int SECOND_IN_TICKS = 20;
    public static final String SERVER_PROXY_CLASS = "com.ggollmer.wardedman.core.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "com.ggollmer.wardedman.core.proxy.ClientProxy";
}
