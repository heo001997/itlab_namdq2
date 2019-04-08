package socket.groupchat.threads;

import socket.groupchat.utils.Global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Client extends Thread {
    private byte[] buf = new byte[256];
    private String ip;
    private int port;
    private String name;

    public Client(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket;
        BufferedReader reader;


        try {
            socket = new Socket(this.ip, this.port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String groupIP = null;
            int groupPort = 0;
            while (true) {
                try {
                    String message = reader.readLine().trim();
                    String[] data = message.split(Global.REGEX);
                    groupIP = data[0];
                    groupPort = Integer.valueOf(data[1]);

                    System.out.println(this.name + ": received  group!");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.fillInStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {
                    break;
                }
            }

            InetAddress group = null;
            MulticastSocket user = null;

            try {
                System.out.println(this.name + ": connecting to group...");

                user = new MulticastSocket(groupPort);
                group = InetAddress.getByName(groupIP);
                user.joinGroup(group);

                System.out.println(this.name + ": was connecting to group, starting chat!");

                while (true) {
                    DatagramPacket packet = new DatagramPacket(this.buf, this.buf.length);
                    user.receive(packet);
                    String receive = new String(packet.getData(), 0, packet.getLength());
                    System.out.println(this.name + " received: '" + receive + "'");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    user.leaveGroup(group);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
