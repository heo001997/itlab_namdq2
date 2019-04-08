package socket.simplechat.threads;

import socket.simplechat.models.Message;
import socket.simplechat.utils.Global;
import socket.simplechat.views.ChatForm;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread implements ChatForm.Listener {
    private ChatForm chatForm;
    private String name;
    private int port;

    private BufferedReader reader;
    private BufferedWriter writer;

    public Server(String name, int port) {
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

            writer.write(name + Global.REGEX + "da ket noi!");
            writer.newLine();
            writer.flush();

            while (true) {
                String input = reader.readLine().trim();
                String[] datas = input.split(Global.REGEX);
                try {
                    Message message = new Message(datas[0], datas[1]);
                    this.chatForm.pushMessage(message);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.fillInStackTrace();
                }
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

    public void btnSendClick(String message) {
        try {
            writer.write(name + Global.REGEX + message);
            writer.newLine();
            writer.flush();

            this.chatForm.pushMessage(new Message("Toi", message));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
