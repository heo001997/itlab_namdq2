package socket.groupchat.threads;

import socket.groupchat.utils.Global;

import java.io.IOException;
import java.net.*;

public class Main {
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


        // Khoi tao server, dung chuyen huong client sang groupchat
        Server server = new Server("Server", Global.SERVER_PORT);
        server.start();

        // Khoi tao cac client de kiem tra
        Client client = new Client("Nam", Global.SERVER_IP, Global.SERVER_PORT);
        client.start();

        client = new Client("Khanh", Global.SERVER_IP, Global.SERVER_PORT);
        client.start();

        client = new Client("Duy", Global.SERVER_IP, Global.SERVER_PORT);
        client.start();

        client = new Client("Dat", Global.SERVER_IP, Global.SERVER_PORT);
        client.start();


        // Thuc hien cau lenh test
        int i = 1;
        while (i < 10) {
            Thread.sleep(3000);
            System.out.println("");
            pushMessage("This is test message " + i++ + "!");
        }
    }
}
