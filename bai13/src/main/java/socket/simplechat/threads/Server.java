package socket.simplechat.threads;

import socket.simplechat.models.Message;
import socket.simplechat.views.ChatForm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread implements ChatForm.Listener {
    private boolean firstConnect;
    private ChatForm chatForm;
    private String name;
    private int port;
    private Message message;

    private BufferedReader reader;
    private BufferedWriter writer;

    public Server(String name, int port) {
        this.firstConnect = true;

        this.name = name;
        this.port = port;

        this.chatForm = new ChatForm(name);
        this.chatForm.setListener(this);
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write(name);
            writer.newLine();
            writer.flush();

            while (true) {
                String data = reader.readLine().trim();
                if (firstConnect) {
                    this.message = new Message(data, "da ket noi!");
                    this.firstConnect = false;
                } else {
                    this.message.setMessage(data);
                }
                this.chatForm.pushMessage(this.message);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnSendClick(String e) {
        try {
            writer.write(e);
            writer.newLine();
            writer.flush();

            this.chatForm.pushMessage(new Message("Toi", e));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
