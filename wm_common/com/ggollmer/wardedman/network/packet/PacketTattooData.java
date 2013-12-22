package com.ggollmer.wardedman.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.ggollmer.wardedman.WardedMan;
import com.ggollmer.wardedman.core.helper.LogHelper;
import com.ggollmer.wardedman.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketTattooData extends PacketWardedMan
{
	public String username;
	public int tattooCharge;
	public int[] tattooValues;
	public int[] tattooColours;
	
	
	public PacketTattooData() {
		super(PacketTypeHandler.TATTOODAT, false);
	}
	
	public PacketTattooData(String username, int charge, int[] values, int[] colours) {
		super(PacketTypeHandler.TATTOODAT, false);
		this.username = username;
		this.tattooCharge = charge;
		this.tattooValues = values;
		this.tattooColours = colours;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		username = data.readUTF();
		tattooCharge = data.readInt();
		int count;
		
		count = data.readInt();
		tattooValues = new int[count];
		for(int i=0; i<count; i++) {
			tattooValues[i] = data.readInt();
		}
		
		count = data.readInt();
		tattooColours = new int[count];
		for(int i=0; i<count; i++) {
			tattooColours[i] = data.readInt();
		}
    }

	@Override
    public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(username);
		data.writeInt(tattooCharge);
		
		data.writeInt(tattooValues.length);
		for(int i=0; i<tattooValues.length; i++) {
			data.writeInt(tattooValues[i]);
		}
		
		data.writeInt(tattooColours.length);
		for(int i=0; i<tattooColours.length; i++) {
			data.writeInt(tattooColours[i]);
		}
    }

	@Override
    public void execute(INetworkManager network, Player player) {
		LogHelper.debugLog("PacketTattooData -- received");
		WardedMan.proxy.handleTattooDataPacket(this);
    }
}
