-->Java One-to-One Chat Application

A simple GUI-based one-to-one chat application developed using Java, Swing, and Socket Programming.
This project demonstrates client–server communication and real-time messaging.

📌 Project Description

.This application allows two users to chat with each other in real time using a client–server model.

.One program works as a Server

.Two programs work as Clients

.Communication happens using TCP sockets

🎯 Objectives

.To understand socket programming in Java

.To implement client–server architecture

.To design a GUI-based chat system

.To handle real-time communication

🚀 Features

.User login with username

.Real-time text messaging

.GUI using Java Swing

.One-to-one client communication

.Proper exit and logout handling

.Basic error handling

🌟 Additional Features

.Emoji support

.Message timestamps

.Dark mode

.File sharing (up to 1 MB)

.Chat history storage

🏗️ System Architecture
Client 1  <---- TCP Socket ---->  Server  <---- TCP Socket ---->  Client 2


.Server supports only one-to-one chat

.Each client runs in a separate terminal

🧰 Technologies Used

.Java SE

.Java Swing

.Socket Programming (TCP)

.Multithreading

📂 Project Structure
ChatApplication/
│
├── Server.java
├── Client.java
├── chat_history.txt
├── received_files/
└── README.md

▶️ How to Run the Project
Step 1: Compile
javac Server.java
javac Client.java

Step 2: Run Server
java Server

Step 3: Run Clients
java Client


Run the client in two different terminals to start chatting.

🖥️ Screenshots

(Add your screenshots here after taking them)

🔹 Server Running
[ Screenshot of Server console ]

🔹 Client Login Screen
[ Screenshot of Username Input GUI ]

🔹 Chat Window
[ Screenshot of Chat Interface ]

🔹 Dark Mode (Optional)
[ Screenshot of Dark Mode Chat ]

🔹 File Sharing
[ Screenshot of File Send/Receive ]

🧪 Error Handling

Empty messages are not allowed

Server not running error handled

Wrong input validation

Safe socket closing

🏁 Conclusion

.This project successfully demonstrates:

.Client–server communication

.Real-time chat application

.GUI development using Java Swing

.It is suitable for college submission and viva examination.
