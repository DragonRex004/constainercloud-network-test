package de.containercloud;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import de.containercloud.event.EventData;
import de.containercloud.event.EventManager;
import de.containercloud.event.impl.OnLoadConfig;
import de.containercloud.event.impl.OnServiceCreate;
import de.containercloud.packets.ErrorResponse;
import de.containercloud.packets.Packet;
import de.containercloud.packets.PacketType;
import de.containercloud.packets.SuccessResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class PacketServer {
    private Server server;
    private String KEY = "test";

    private EventManager manager;

    public PacketServer() throws IOException {
        this.server = new Server();
        this.manager = new EventManager();
        this.manager.registerEvent(new OnServiceCreate());
        this.manager.registerEvent(new OnLoadConfig());
        Kryo kryo = this.server.getKryo();
        kryo.register(Packet.class);
        kryo.register(HashMap.class);
        kryo.register(SuccessResponse.class);
        kryo.register(ErrorResponse.class);
        this.server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                System.out.println(connection.getRemoteAddressTCP());
            }
            @Override
            public void received(Connection connection, Object object) {
                if(object instanceof Packet packet) {
                    if(Objects.equals(packet.getPacketType(), PacketType.CUSTOM.key)) {
                        System.out.println("Packet found with CUSTOM Key");
                        if(Objects.equals(packet.getPacketData().get("key"), KEY)) {
                            System.out.println("Auth success");
                            connection.sendTCP(new SuccessResponse("Auth success"));
                            System.out.println("Response send!");
                        } else {
                            System.out.println("Auth failed");
                            connection.sendTCP(new ErrorResponse("Auth failed"));
                            System.out.println("Response send!");
                        }
                    }
                    if(Objects.equals(packet.getPacketType(), PacketType.CREATE_SERVICE.key)) {
                        System.out.println("Packet found with CREATE_SERVICE Key");
                        manager.callEvent("OnServiceCreate", EventData.serialize(new StringBuilder("CREATE_SERVICE")));
                    }
                    if(Objects.equals(packet.getPacketType(), PacketType.DELETE_SERVICE.key)) {
                        System.out.println("Packet found with DELETE_SERVICE Key");
                        manager.callEvent("OnLoadConfig", EventData.serialize(new StringBuilder("DELETE_SERVICE")));
                    }
                }
            }
            @Override
            public void disconnected(Connection connection) {

            }
        });
        this.server.start();

        this.server.bind(8080);
    }

    public static void main(String[] args) {
        try {
            new PacketServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
