import java.io.*;
import java.net.*;

public class Server {

    static ClientHandler c1 = null;
    static ClientHandler c2 = null;

    public static void main(String[] args) {
        System.out.println("Server started on port 6001");

        try (ServerSocket ss = new ServerSocket(6001)) {
            while (true) {
                Socket s = ss.accept();

                if (c1 == null) {
                    c1 = new ClientHandler(s);
                    new Thread(c1).start();
                } else if (c2 == null) {
                    c2 = new ClientHandler(s);
                    new Thread(c2).start();
                } else {
                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    dout.writeUTF("SYSTEM::Server Full");
                    s.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    static ClientHandler getPeer(ClientHandler ch) {
        return (ch == c1) ? c2 : c1;
    }

    static class ClientHandler implements Runnable {

        Socket socket;
        DataInputStream din;
        DataOutputStream dout;
        String name;
        boolean active = true;

        ClientHandler(Socket s) {
            socket = s;
        }

        public void run() {
            try {
                din = new DataInputStream(socket.getInputStream());
                dout = new DataOutputStream(socket.getOutputStream());

                // LOGIN
                String login = din.readUTF();
                if (!login.startsWith("LOGIN::")) return;

                name = login.substring(7).trim();
                if (name.isEmpty()) return;

                System.out.println(name + " connected");

                while (active) {
                    String msg = din.readUTF();
                    ClientHandler peer = getPeer(this);
                    if (peer == null) continue;

                    // TEXT MESSAGE (FIXED)
                    if (msg.startsWith("MSG::")) {
                        peer.dout.writeUTF(
                                "MSG::" + name + "::" + msg.substring(5)
                        );
                    }

                    // FILE MESSAGE
                    else if (msg.startsWith("FILE::")) {
                        peer.dout.writeUTF(msg);
                    }

                    // LOGOUT
                    else if (msg.startsWith("LOGOUT::")) {
                        active = false;
                        peer.dout.writeUTF(
                                "SYSTEM::" + name + " left the chat"
                        );
                        break;
                    }
                }

            } catch (Exception e) {
                System.out.println("Client disconnected unexpectedly");
            } finally {
                close();
            }
        }

        void close() {
            try { socket.close(); } catch (Exception ignored) {}
            if (this == c1) c1 = null;
            if (this == c2) c2 = null;
        }
    }
}