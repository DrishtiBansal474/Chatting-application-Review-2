🗨️ Java One-to-One Chat Application

A robust, GUI-based one-to-one chat application built using Java, Swing, and Socket Programming.
This project demonstrates client-server communication, real-time messaging, error handling, and modern chat features such as dark mode, file sharing, emojis, timestamps, and chat history.

📌 Project Overview

This application allows two users to chat with each other in real time using a client–server architecture.
The server manages connections, while each client provides a graphical chat interface.

The project is designed to be:

✔ Stable (no crashes)

✔ Well-validated (wrong input blocked)

✔ Modular & clean

✔ Suitable for college projects, viva, and reviews

🚀 Features Implemented
✅ Core Features (Mandatory)

User login / username setup

Real-time message sending & receiving

Client–server communication (TCP sockets)

GUI-based chat window (Java Swing)

Proper exit / logout handling

🛡️ Error Handling & Robustness

Empty message validation

Server not running detection

Wrong IP / port handling

Safe socket & IO handling

Graceful client disconnect

File size validation

🔗 Integration of Components

GUI ↔ Backend logic

Buttons ↔ Event handling

Client ↔ Server ↔ Client

Chat window ↔ Message list

⚡ Event Handling & Processing

ActionListener for Send button

Enter key support for sending messages

Dedicated thread for listening incoming messages

Optimized listeners (no redundancy)

🔍 Data Validation

Client-side validation (GUI level)

Server-side validation (backend level)

Empty username not allowed

Empty messages blocked

File size limit enforced (≤ 1 MB)

🌟 Innovation / Extra Features

😊 Emoji support

⏰ Message timestamps

🌙 Dark mode toggle

📁 File sharing (Base64 encoding)

💾 Chat history persistence

🔄 Auto-scrolling chat window

🏗️ Architecture
+------------+        TCP Socket        +------------+
|  Client A  |  <------------------->  |   Server   |
|  (Swing)   |                          | (Threaded)|
+------------+                          +------------+
                                             |
                                             |
                                      +------------+
                                      |  Client B  |
                                      |  (Swing)   |
                                      +------------+


Server supports only one-to-one chat

Each client runs on a separate JVM

Communication is done using DataInputStream & DataOutputStream

🧰 Technologies Used

Java SE

Java Swing (GUI)

Socket Programming (TCP)

Multithreading

Base64 Encoding (file transfer)

File I/O (chat history)

📂 Project Structure
ChatApplication/
│
├── Server.java          # Server-side logic
├── Client.java          # Client GUI + logic
├── chat_history.txt     # Auto-generated chat history
├── received_<file>      # Received files
└── README.md            # Project documentation

▶️ How to Run the Project
1️⃣ Compile the code
javac Server.java
javac Client.java

2️⃣ Start the server
java Server

3️⃣ Start Client 1
java Client

4️⃣ Start Client 2 (in another terminal)
java Client

5️⃣ Start chatting 🎉
🖥️ How the Application Works
🔐 Login

User enters a username

Username is validated on both client & server

💬 Messaging

Messages are sent in real time

Displayed with sender name & timestamp

📁 File Sharing

Files up to 1 MB supported

Encoded using Base64

Received files saved locally

🌙 Dark Mode

Toggle between light and dark themes

Improves user experience & readability

💾 Chat History

All messages stored in chat_history.txt

Loaded automatically on app restart

🚪 Logout

Window close triggers logout

Server notifies the other client

Resources are released safely

🧪 Error Handling Examples
try {
    dout.writeUTF("MSG::" + message);
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Connection Error");
}


Handled cases:

Server offline

Network failure

Invalid input

Client force close

🔮 Future Enhancements (Optional)

Group chat support

Database-based message storage

Encryption for messages

User authentication with password

Profile pictures & avatars

🏁 Conclusion

This project fully satisfies all proposed requirements:

✔ Functional

✔ Stable

✔ Well-documented

✔ Innovative


👨‍💻 Author

Java Chat Application Project
Developed for academic and learning purposes.