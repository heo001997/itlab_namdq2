package socket.groupchat;

import socket.groupchat.threads.Client;
import socket.groupchat.threads.Server;
import socket.groupchat.utils.Global;

public class Main {

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
    }
}
