package socket.simplechat.views;

import socket.simplechat.models.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatForm extends JFrame implements ActionListener {
    private JButton btnSend;
    private JTextField tfMessage;
    private JTextArea taGroup;
    private String name;

    private Listener listener;

    public ChatForm(String name) {
        this.name = name;
        initUI();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void initUI() {
        setSize(400, 400);
        setTitle(name);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        tfMessage = new JTextField();
        panel1.add(tfMessage, BorderLayout.CENTER);

        btnSend = new JButton("Send");
        panel1.add(btnSend, BorderLayout.EAST);

        taGroup = new JTextArea();
        panel2.add(taGroup, BorderLayout.CENTER);
        panel2.add(panel1, BorderLayout.SOUTH);

        setContentPane(panel2);
        setVisible(true);

        btnSend.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            listener.btnSendClick(tfMessage.getText());
        } catch (NullPointerException ed) {
            ed.fillInStackTrace();
        }
    }

    public void pushMessage(Message message) {
        taGroup.append(message.getUsername() + ": " + message.getMessage() + "\n");
        tfMessage.setText("");
    }

    public interface Listener {
        void btnSendClick(String e);
    }
}
