package socket.simplechat.threads;

import socket.simplechat.models.Message;
import socket.simplechat.utils.Global;
import socket.simplechat.views.ChatForm;

import java.io.*;
import java.net.Socket;

public class Client extends Thread implements ChatForm.Listener {
    private ChatForm chatForm;
    private String ip;
    private int port;
    private String name;

    private BufferedWriter writer;
    private BufferedReader reader;

    public Client(String name, String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.name = name;

        this.chatForm = new ChatForm(name);
        this.chatForm.setListener(this);
    }

    @Override
    public void run() {
        Socket socketClient = null;
        try {
            socketClient = new Socket(ip, port);

            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

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
            System.out.println("Khong the ket noi den may chu!");
        } finally {
            try {
                socketClient.close();
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
