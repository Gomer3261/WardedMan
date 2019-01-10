package com.ggollmer.wardedman.lib;

public class TattooConstants
{
	public static final int LOCATION_COUNT = 22;
	public static final int COLOUR_COUNT = 16;
	
	public static final String CONFIGURATION_CATEGORY = "Tattoo";
	
	public static final String TATTOO_LOSS_ON_DEATH_NAME = "Tattoo Loss On Death";
	public static final boolean TATTOO_LOSS_ON_DEATH_DEFAULT = false;
	public static boolean TATTOO_LOSS_ON_DEATH;
	
	public static final String TATTOO_MAX_CHARGE_NAME = "Tattoo Max Charge";
	public static final int TATTOO_MAX_CHARGE_DEFAULT = 1000000;
	public static int TATTOO_MAX_CHARGE;
	
	
	/* Tattoos */
	public static final int NO_TATOO_ID = -1;
	
	public static final String TATTOO_COST_STRING = "tattoo.tattooCost";
	
	public static final String DAMAGE_REDUCTION_NAME = "damage_reduction";
	public static final String FIRE_RESISTANCE_NAME = "fire_resistance";
	public static final String FEATHER_FALLING_NAME = "feather_falling";
	public static final String IRON_FIST_NAME = "iron_fist";
	public static final String FIRE_FIST_NAME = "fire_fist";
	public static final String STONE_FIST_NAME = "stone_fist";
	public static final String CHISELED_FIST_NAME = "chiseled_fist";
	public static final String THORNS_NAME = "thorns";
	public static final String MAGIC_RESIST_NAME = "magic_resist";
	public static final String PROJECTILE_PROTECTION_NAME = "projectile_protection";
	public static final String JUMP_BOOST_NAME = "jump_boost";
	public static final String EXPLOSION_RESISTANCE_NAME = "explosion_resistance";
	public static final String NIGHT_VISION_NAME = "night_vision";
	public static final String BLINDING_FIST_NAME = "blinding_fist";
	
	/* Tattoo Locations */
	public static final int ARMOR_SLOT_HAND = -1;
	public static final int ARMOR_SLOT_OFF_HAND = -1;
	public static final int ARMOR_SLOT_HELM = 3;
	public static final int ARMOR_SLOT_CHEST = 2;
	public static final int ARMOR_SLOT_LEGS = 1;
	public static final int ARMOR_SLOT_FEET = 0;
	
	public static final String FACE_LOCATION_NAME = "tattoo.location.face";
	public static final int FACE_LOCATION_ID = 0;
	public static final int FACE_LOCATION_SLOT = ARMOR_SLOT_HELM;
	
	public static final String RIGHT_CHEST_LOCATION_NAME = "tattoo.location.chest_right";
	public static final int RIGHT_CHEST_LOCATION_ID = 1;
	public static final int RIGHT_CHEST_LOCATION_SLOT = ARMOR_SLOT_CHEST;
	
	public static final String LEFT_CHEST_LOCATION_NAME = "tattoo.location.chest_left";
	public static final int LEFT_CHEST_LOCATION_ID = 2;
	public static final int LEFT_CHEST_LOCATION_SLOT = ARMOR_SLOT_CHEST;
	
	public static final String AB_LOCATION_NAME = "tattoo.location.abdominal";
	public static final int AB_LOCATION_ID = 3;
	public static final int AB_LOCATION_SLOT = ARMOR_SLOT_CHEST;
	
	public static final String RIGHT_PALM_LOCATION_NAME = "tattoo.location.palm_right";
	public static final int RIGHT_PALM_LOCATION_ID = 4;
	public static final int RIGHT_PALM_LOCATION_SLOT = ARMOR_SLOT_HAND;
	
	public static final String RIGHT_THIGH_LOCATION_NAME = "tattoo.location.thigh_right";
	public static final int RIGHT_THIGH_LOCATION_ID = 5;
	public static final int RIGHT_THIGH_LOCATION_SLOT = ARMOR_SLOT_LEGS;
	
	public static final String LEFT_THIGH_LOCATION_NAME = "tattoo.location.thigh_left";
	public static final int LEFT_THIGH_LOCATION_ID = 6;
	public static final int LEFT_THIGH_LOCATION_SLOT = ARMOR_SLOT_LEGS;
	
	public static final String LEFT_PALM_LOCATION_NAME = "tattoo.location.palm_left";
	public static final int LEFT_PALM_LOCATION_ID = 7;
	public static final int LEFT_PALM_LOCATION_SLOT = ARMOR_SLOT_OFF_HAND;
	
	public static final String RIGHT_FOOT_LOCATION_NAME = "tattoo.location.foot_right";
	public static final int RIGHT_FOOT_LOCATION_ID = 8;
	public static final int RIGHT_FOOT_LOCATION_SLOT = ARMOR_SLOT_FEET;
	
	public static final String LEFT_FOOT_LOCATION_NAME = "tattoo.location.foot_left";
	public static final int LEFT_FOOT_LOCATION_ID = 9;
	public static final int LEFT_FOOT_LOCATION_SLOT = ARMOR_SLOT_FEET;
	
	public static final String HEAD_LOCATION_NAME = "tattoo.location.head";
	public static final int HEAD_LOCATION_ID = 10;
	public static final int HEAD_LOCATION_SLOT = ARMOR_SLOT_HELM;
	
	public static final String LEFT_SHOULDER_LOCATION_NAME = "tattoo.location.shoulder_left";
	public static final int LEFT_SHOULDER_LOCATION_ID = 11;
	public static final int LEFT_SHOULDER_LOCATION_SLOT = ARMOR_SLOT_CHEST;
	
	public static final String RIGHT_SHOULDER_LOCATION_NAME = "tattoo.location.shoulder_right";
	public static final int RIGHT_SHOULDER_LOCATION_ID = 12;
	public static final int RIGHT_SHOULDER_LOCATION_SLOT = ARMOR_SLOT_CHEST;
	
	public static final String BACK_LOCATION_NAME = "tattoo.location.back";
	public static final int BACK_LOCATION_ID = 13;
	public static final int BACK_LOCATION_SLOT = ARMOR_SLOT_CHEST;
	
	public static final String LEFT_GLUTEAL_LOCATION_NAME = "tattoo.location.gluteal_left";
	public static final int LEFT_GLUTEAL_LOCATION_ID = 14;
	public static final int LEFT_GLUTEAL_LOCATION_SLOT = ARMOR_SLOT_LEGS;
	
	public static final String RIGHT_GLUTEAL_LOCATION_NAME = "tattoo.location.gluteal_right";
	public static final int RIGHT_GLUTEAL_LOCATION_ID = 15;
	public static final int RIGHT_GLUTEAL_LOCATION_SLOT = ARMOR_SLOT_LEGS;
	
	public static final String LEFT_HAND_LOCATION_NAME = "tattoo.location.hand_left";
	public static final int LEFT_HAND_LOCATION_ID = 16;
	public static final int LEFT_HAND_LOCATION_SLOT = ARMOR_SLOT_OFF_HAND;
	
	public static final String RIGHT_HAND_LOCATION_NAME = "tattoo.location.hand_right";
	public static final int RIGHT_HAND_LOCATION_ID = 17;
	public static final int RIGHT_HAND_LOCATION_SLOT = ARMOR_SLOT_HAND;
	
	public static final String LEFT_HAM_LOCATION_NAME = "tattoo.location.ham_left";
	public static final int LEFT_HAM_LOCATION_ID = 18;
	public static final int LEFT_HAM_LOCATION_SLOT = ARMOR_SLOT_LEGS;
	
	public static final String RIGHT_HAM_LOCATION_NAME = "tattoo.location.ham_right";
	public static final int RIGHT_HAM_LOCATION_ID = 19;
	public static final int RIGHT_HAM_LOCATION_SLOT = ARMOR_SLOT_LEGS;
	
	public static final String LEFT_CALF_LOCATION_NAME = "tattoo.location.calf_left";
	public static final int LEFT_CALF_LOCATION_ID = 20;
	public static final int LEFT_CALF_LOCATION_SLOT = ARMOR_SLOT_FEET;
	
	public static final String RIGHT_CALF_LOCATION_NAME = "tattoo.location.calf_right";
	public static final int RIGHT_CALF_LOCATION_ID = 21;
	public static final int RIGHT_CALF_LOCATION_SLOT = ARMOR_SLOT_FEET;
}
