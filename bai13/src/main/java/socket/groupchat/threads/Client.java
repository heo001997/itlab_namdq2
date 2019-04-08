package socket.groupchat.threads;

import socket.groupchat.utils.Global;
import socket.simplechat.models.Message;
import socket.simplechat.views.ChatForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Client extends Thread implements ChatForm.Listener {
    private byte[] buf = new byte[256];
    private String ip;
    private int port;
    private String name;

    private ChatForm chatForm;

    private InetAddress group = null;
    private String groupIP = null;
    private int groupPort = 0;

    public Client(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;

        this.chatForm = new ChatForm(name);
        this.chatForm.getBtnSend().setEnabled(false);
        this.chatForm.setListener(this);
    }

    @Override
    public void run() {
        Socket socket;
        BufferedReader reader;

        push(new Message("Toi", "dang ket noi..."));

        try {
            socket = new Socket(this.ip, this.port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                try {
                    String message = reader.readLine().trim();
                    String[] data = message.split(Global.REGEX);
                    groupIP = data[0];
                    groupPort = Integer.valueOf(data[1]);

//                    System.out.println(this.name + ": received  group!");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.fillInStackTrace();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.fillInStackTrace();
                } finally {
                    break;
                }
            }

            MulticastSocket user = null;

            try {
//                System.out.println(this.name + ": connecting to group...");

                user = new MulticastSocket(groupPort);
                group = InetAddress.getByName(groupIP);
                user.joinGroup(group);

//                System.out.println(this.name + ": was connecting to group, starting chat!");
                this.chatForm.getBtnSend().setEnabled(true);
                push(new Message("Toi", "ket noi thanh cong, bat dau chat!"));

                while (true) {
                    DatagramPacket packet = new DatagramPacket(this.buf, this.buf.length);
                    user.receive(packet);
                    String receive = new String(packet.getData(), 0, packet.getLength());

                    String[] data = receive.split(Global.REGEX);
                    push(new Message(data[0], data[1]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException ee) {
                ee.fillInStackTrace();
            } catch (Exception e) {
                e.fillInStackTrace();
            } finally {
                try {
                    user.leaveGroup(group);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSendClick(String message) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            buf = (this.name + Global.REGEX + message).getBytes();

            DatagramPacket packet = new DatagramPacket(buf, buf.length, group, groupPort);
            socket.send(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            socket.close();
        }

        push(new Message("Toi", message));
    }

    private void push(Message message) {
        if (!message.getUsername().equals(this.name)) {
            this.chatForm.pushMessage(message);
        }
    }
}
