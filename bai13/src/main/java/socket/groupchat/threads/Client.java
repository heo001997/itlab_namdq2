package socket.groupchat.threads;


import socket.groupchat.utils.Global;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client extends Thread {
    private MulticastSocket socket = null;
    private byte[] buf = new byte[256];

    @Override
    public void run() {
        InetAddress group = null;
        System.out.println(Thread.currentThread().getName() + " is running:");
        try {
            socket = new MulticastSocket(Global.GROUP_PORT);
            group = InetAddress.getByName(Global.GROUP_IP);
            socket.joinGroup(group);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String receive = new String(packet.getData(), 0, packet.getLength());

                System.out.println(Thread.currentThread().getName() + ": " + receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.leaveGroup(group);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
