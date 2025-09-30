package org.example.tcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;

            // Send the username of the recipient
            writer.write(username);
            writer.newLine();
            writer.flush();

            System.out.println("Conected to the server as [" + username + "]");

        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void getNotifications() {
        new Thread(() -> {
            while(socket.isConnected()) {
                System.out.println("Waiting for notifications...\n");
                try {

                    String notification = reader.readLine();
                    System.out.println("[Notification]:" + notification);

                } catch (IOException e) {
                    closeEverything(socket, reader, writer);
                    break;
                }
            }
            System.out.println("Server disconnected");
        }).start();
    }

    public void sendNotification(String message, String username) {
        try {
            System.out.println("Sending notification: '" + message + "' to [" + username + "]");

            // Send the username of the recipient
            writer.write(username);
            writer.newLine();
            writer.flush();

            // Send the message
            writer.write(message);
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }

    public void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer) {
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

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            Client c = new Client(socket, "user");

            c.sendNotification("Hello, my name is User!", "user");
            c.getNotifications();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
