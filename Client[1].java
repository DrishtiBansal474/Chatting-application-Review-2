import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Client {

    JFrame frame;
    JTextField input;
    JPanel chatPanel;
    JScrollPane scroll;
    JButton themeBtn;

    Socket socket;
    DataInputStream din;
    DataOutputStream dout;

    String username;
    boolean darkMode = false;

    File historyFile = new File("chat_history.txt");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);
    }

    Client() {
        askUsername();
        initGUI();
        loadHistory();
        connectServer();
        listenMessages();
    }

    /* ================= USERNAME ================= */
    void askUsername() {
        username = JOptionPane.showInputDialog("Enter username:");
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty");
            System.exit(0);
        }
    }

    /* ================= GUI ================= */
    void initGUI() {
        frame = new JFrame("Chat - " + username);
        frame.setSize(420, 600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(Color.WHITE);

        scroll = new JScrollPane(chatPanel);
        scroll.setBorder(null);
        frame.add(scroll, BorderLayout.CENTER);

        input = new JTextField();
        input.addActionListener(e -> sendMessage());

        JButton sendBtn = new JButton("Send");
        JButton fileBtn = new JButton("📁");
        themeBtn = new JButton("🌙");

        sendBtn.addActionListener(e -> sendMessage());
        fileBtn.addActionListener(e -> chooseFile());
        themeBtn.addActionListener(e -> toggleTheme());

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(input, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new GridLayout(1, 3));
        buttons.add(fileBtn);
        buttons.add(themeBtn);
        buttons.add(sendBtn);

        bottom.add(buttons, BorderLayout.EAST);
        frame.add(bottom, BorderLayout.SOUTH);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logout();
            }
        });

        applyTheme();
        frame.setVisible(true);
    }

    /* ================= THEME ================= */
    void toggleTheme() {
        darkMode = !darkMode;
        applyTheme();
    }

    void applyTheme() {
        Color bg = darkMode ? new Color(25, 25, 25) : Color.WHITE;
        Color fg = darkMode ? Color.WHITE : Color.BLACK;

        frame.getContentPane().setBackground(bg);
        chatPanel.setBackground(bg);
        input.setBackground(darkMode ? new Color(45, 45, 45) : Color.WHITE);
        input.setForeground(fg);
        themeBtn.setText(darkMode ? "☀" : "🌙");
    }

    /* ================= CONNECTION ================= */
    void connectServer() {
        try {
            socket = new Socket("127.0.0.1", 6001);
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("LOGIN::" + username);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Server not running");
            System.exit(0);
        }
    }

    /* ================= SEND MESSAGE ================= */
    void sendMessage() {
        String msg = input.getText().trim();
        if (msg.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Message cannot be empty");
            return;
        }
        try {
            dout.writeUTF("MSG::" + msg);
            addBubble("Me", msg);
            saveHistory("Me: " + msg);
            input.setText("");
        } catch (Exception e) {
            addSystem("Failed to send message");
        }
    }

    /* ================= FILE SHARING ================= */
    void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            sendFile(chooser.getSelectedFile());
        }
    }

    void sendFile(File file) {
        try {
            byte[] data = Files.readAllBytes(file.toPath());
            if (data.length > 1024 * 1024) {
                JOptionPane.showMessageDialog(frame, "File too large (max 1MB)");
                return;
            }
            String encoded = Base64.getEncoder().encodeToString(data);
            dout.writeUTF("FILE::" + file.getName() + "::" + encoded);
            addSystem("File sent: " + file.getName());
        } catch (Exception e) {
            addSystem("File send failed");
        }
    }

    /* ================= RECEIVE ================= */
    void listenMessages() {
        new Thread(() -> {
            try {
                while (true) {
                    String msg = din.readUTF();

                    if (msg.startsWith("MSG::")) {
                        String[] p = msg.split("::", 3);
                        addBubble(p[1], p[2]);
                        saveHistory(p[1] + ": " + p[2]);
                    }
                    else if (msg.startsWith("FILE::")) {
                        receiveFile(msg);
                    }
                    else if (msg.startsWith("SYSTEM::")) {
                        addSystem(msg.substring(8));
                    }
                }
            } catch (Exception e) {
                addSystem("Disconnected from server");
            }
        }).start();
    }

    void receiveFile(String msg) {
        try {
            String[] p = msg.split("::", 3);
            byte[] data = Base64.getDecoder().decode(p[2]);
            File file = new File("received_" + p[1]);
            Files.write(file.toPath(), data);
            addSystem("File received: " + file.getName());
        } catch (Exception e) {
            addSystem("File receive failed");
        }
    }

    /* ================= WHATSAPP BUBBLES ================= */
    void addBubble(String sender, String text) {

        boolean isMe = sender.equals("Me");

        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);

        JLabel bubble = new JLabel(
                "<html><p style='width:200px'>" + text + "</p></html>"
        );
        bubble.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        bubble.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        bubble.setOpaque(true);

        JLabel time = new JLabel(
                new SimpleDateFormat("HH:mm").format(new Date())
        );
        time.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        time.setForeground(Color.DARK_GRAY);

        JPanel msgBox = new JPanel(new BorderLayout());
        msgBox.add(bubble, BorderLayout.CENTER);
        msgBox.add(time, BorderLayout.SOUTH);
        msgBox.setOpaque(false);

        if (isMe) {
            bubble.setBackground(new Color(37, 211, 102)); // WhatsApp green
            row.add(msgBox, BorderLayout.EAST);
        } else {
            bubble.setBackground(new Color(230, 230, 230)); // receiver gray
            row.add(msgBox, BorderLayout.WEST);
        }

        chatPanel.add(row);
        chatPanel.add(Box.createVerticalStrut(5));
        chatPanel.revalidate();
        scrollToBottom();
    }

    void addSystem(String msg) {
        JLabel l = new JLabel("[ " + msg + " ]", SwingConstants.CENTER);
        l.setForeground(Color.GRAY);
        chatPanel.add(l);
        chatPanel.revalidate();
        scrollToBottom();
    }

    void scrollToBottom() {
        SwingUtilities.invokeLater(() ->
            scroll.getVerticalScrollBar().setValue(
                scroll.getVerticalScrollBar().getMaximum()
            )
        );
    }

    /* ================= HISTORY ================= */
    void saveHistory(String msg) {
        try (FileWriter fw = new FileWriter(historyFile, true)) {
            fw.write(msg + "\n");
        } catch (Exception ignored) {}
    }

    void loadHistory() {
        if (!historyFile.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(historyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                chatPanel.add(new JLabel(line));
            }
        } catch (Exception ignored) {}
    }

    /* ================= LOGOUT ================= */
    void logout() {
        try {
            dout.writeUTF("LOGOUT::" + username);
        } catch (Exception ignored) {}
        System.exit(0);
    }
}
