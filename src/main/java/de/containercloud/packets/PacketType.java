package de.containercloud.packets;

public enum PacketType {
    // Packet-Data Task.id
    CREATE_SERVICE("create_service"),
    // Packet-Data Service.id
    DELETE_SERVICE("delete_service"),
    // Packet-Data
    CUSTOM("custom");

    public String key;

    PacketType(String key) {
        this.key = key;
    }
}
