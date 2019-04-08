package socket.simplechat.threads;

import socket.simplechat.models.Message;
import socket.simplechat.views.ChatForm;

import java.io.*;
import java.net.Socket;

public class Client extends Thread implements ChatForm.Listener {
    private ChatForm chatForm;
    private String ip;
    private int port;
    private String name;
    private Message message;
    private boolean firstConnect;

    private BufferedWriter writer;
    private BufferedReader reader;

    public Client(String name, String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.name = name;

        this.firstConnect = true;
    }

    @Override
    public void run() {
        Socket socketClient = null;
        try {
            socketClient = new Socket(ip, port);

            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            writer.write(name);
            writer.newLine();
            writer.flush();

            showForm();

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

    private void showForm() {
        this.chatForm = new ChatForm(name);
        this.chatForm.setListener(this);
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

    public static void main(String[] args) {
        Client client = new Client("client", "localhost", 5555);
        client.start();
    }
}
