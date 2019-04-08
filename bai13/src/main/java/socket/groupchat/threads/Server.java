package socket.groupchat.threads;


import socket.groupchat.utils.Global;

import java.io.IOException;
import java.net.*;

public class Server {
    private static DatagramSocket socket;
    private static InetAddress group;
    private static byte[] buf;

    public static void pushMessage(String message) {
        try {
            socket = new DatagramSocket();
            group = InetAddress.getByName(Global.GROUP_IP);
            buf = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, Global.GROUP_PORT);
            socket.send(packet);
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) throws InterruptedException {

        Client client = new Client();
        client.start();

        client = new Client();
        client.start();

        client = new Client();
        client.start();

        client = new Client();
        client.start();


        while (true) {
            Thread.sleep(3000);
            pushMessage("hahaha");
        }
    }
}
