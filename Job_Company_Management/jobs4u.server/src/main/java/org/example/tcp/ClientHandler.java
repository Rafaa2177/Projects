package org.example.tcp;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    private String username;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = reader.readLine();
            System.out.println("Client Username: " + username);
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    @Override
    public void run() {
        while(socket.isConnected()) {
            try {
                String usr = reader.readLine();
                String notification = reader.readLine();

                System.out.println("Receiving a Notification for [" + usr + "]: '" + notification + "'");
                sendNotification(notification, usr);
            } catch (IOException e) {
                closeEverything(socket, reader, writer);
                break;
            }
        }
    }


    public void sendNotification(String message, String username) {
        Server.getClients().forEach(client -> {
            try {
                System.out.println("Checking if [" + client.username + "] is the recipient [" + username + "]...");
                if (client.username.equals(username)){
                    System.out.println("Sending notification: '" + message + "' to [" + username + "]");
                    client.writer.write(message);
                    client.writer.newLine();
                    client.writer.flush();
                }
            } catch (IOException e) {
                closeEverything(client.socket, client.reader, client.writer);
            }
        });
    }

    private void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer) {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ex) {
            System.out.println("Could not close socket");
            ex.printStackTrace();
        }
    }

}