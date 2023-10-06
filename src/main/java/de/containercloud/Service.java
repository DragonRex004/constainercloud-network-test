package de.containercloud;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.containercloud.packets.ErrorResponse;
import de.containercloud.packets.Packet;
import de.containercloud.packets.PacketType;
import de.containercloud.packets.SuccessResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Service {
    private Client client;

    public Service() throws IOException {
        this.client = new Client();
        Kryo kryo = client.getKryo();
        kryo.register(Packet.class);
        kryo.register(HashMap.class);
        kryo.register(ErrorResponse.class);
        kryo.register(SuccessResponse.class);

        this.client.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
            }

            @Override
            public void received(Connection connection, Object object) {
                if(object instanceof SuccessResponse res) {
                    System.out.println("Response: " + res.getRes());
                }
                if(object instanceof ErrorResponse err) {
                    System.out.println("Response: " + err.getRes());
                }
            }

            @Override
            public void disconnected(Connection connection) {

            }
        });
        this.client.start();
        this.client.connect(5000, "localhost", 8080);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            if(Objects.equals(scanner.next(), "test")) {
                Packet packet = new Packet(PacketType.CUSTOM.key);
                packet.addData("key", "lukas");
                this.client.sendTCP(packet);
                System.out.println("Packet CUSTOM send");
            }
            if(Objects.equals(scanner.next(), "create")) {
                Packet packet = new Packet(PacketType.CREATE_SERVICE.key);
                packet.addData("service-name", "Test-Service");
                this.client.sendTCP(packet);
                System.out.println("Packet CREATE send");
            }
            if(Objects.equals(scanner.next(), "delete")) {
                Packet packet = new Packet(PacketType.DELETE_SERVICE.key);
                packet.addData("service-name", "Test-Service");
                this.client.sendTCP(packet);
                System.out.println("Packet DELETE send");
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Service();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
