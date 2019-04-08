package socket.groupchat.threads;


import socket.groupchat.utils.Global;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private int port;
    private String name;

    public Server(String name, int port) {
        this.name = name;
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        Socket socket = null;
        BufferedWriter writer = null;

        System.out.println("Server is running...\n");
        try {
            serverSocket = new ServerSocket(this.port);
            while (true) {
                socket = serverSocket.accept();
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                writer.write(Global.GROUP_IP + Global.REGEX + Global.GROUP_PORT);
                writer.newLine();
                writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
