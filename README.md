Java One-to-One Chat Application

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


<img width="1917" height="1031" alt="Screenshot 2025-11-24 212557" src="https://github.com/user-attachments/assets/a2b25f8a-5c89-4582-88e6-c6eb626805b7" />
<img width="1919" height="1050" alt="Screenshot 2025-11-24 212624" src="https://github.com/user-attachments/assets/5aa79272-3b0d-4a1f-a44c-6ae2dafe1017" />
<img width="1911" height="1059" alt="Screenshot 2025-11-24 212648" src="https://github.com/user-attachments/assets/bfbb2e18-8d97-4324-a8b5-9ae078130f1d" />
<img width="1919" height="1013" alt="Screenshot 2025-11-24 212708" src="https://github.com/user-attachments/assets/073c4076-2814-42a3-93a6-dc8835a5fd32" />
<img width="1896" height="1018" alt="Screenshot 2025-11-24 212726" src="https://github.com/user-attachments/assets/3cd141be-a91e-4901-9a12-c2261dced7b1" />
<img width="1909" height="955" alt="Screenshot 2025-11-24 212743" src="https://github.com/user-attachments/assets/41cb7c2a-1898-4c1b-af3b-29b914ff2874" />
<img width="1897" height="1010" alt="Screenshot 2025-11-24 212759" src="https://github.com/user-attachments/assets/736533ea-254e-41c1-adbc-e4fd1696e869" />
<img width="1919" height="1006" alt="Screenshot 2025-11-24 212839" src="https://github.com/user-attachments/assets/7321fc30-b581-40b7-84d9-6817b1bd357a" />


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
