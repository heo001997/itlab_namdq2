package socket.groupchat;

import socket.groupchat.threads.Client;
import socket.groupchat.utils.Global;

public class ManualClient {
    public static void main(String[] args) {
        Client client = new Client("Mr. trung", Global.SERVER_IP, Global.SERVER_PORT);
        client.start();
    }
}
