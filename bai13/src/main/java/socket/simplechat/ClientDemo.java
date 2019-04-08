package socket.simplechat;

import socket.simplechat.threads.Client;
import socket.simplechat.utils.Global;

import java.io.IOException;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Client client = new Client("Mr. Client", Global.IP, Global.PORT);
        client.start();
    }
}
