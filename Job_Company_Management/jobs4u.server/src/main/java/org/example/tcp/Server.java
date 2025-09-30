package org.example.tcp;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

   private ServerSocket serverSocket ;

    private static List<ClientHandler> clients = new ArrayList<>();

   public Server(ServerSocket serverSocket) {
       this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {

            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static List<ClientHandler> getClients(){
       return clients;
    }

    public static void main(String[] args) {
        int port = 1234;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
            Server server = new Server(serverSocket);
            server.startServer();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }




}
