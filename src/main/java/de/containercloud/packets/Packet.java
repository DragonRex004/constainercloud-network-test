package de.containercloud.packets;

import java.util.HashMap;
import java.util.Map;

public class Packet {
    private String packetType;
    private HashMap<String, String> packetData = new HashMap<>();

    public Packet(String packetType) {
        this.packetType = packetType;
    }
    public Packet() {}

    public void addData(String key, String value) {
        this.packetData.put(key, value);
    }

    public String getPacketType() {
        return packetType;
    }

    public Map<String, String> getPacketData() {
        return packetData;
    }
}
