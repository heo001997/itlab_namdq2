package socket.simplechat;

import socket.simplechat.threads.Server;
import socket.simplechat.utils.Global;

public class ServerDemo {
    public static void main(String[] args) {
        Server server = new Server("Mr. Server", Global.PORT);
        server.start();
    }
}
