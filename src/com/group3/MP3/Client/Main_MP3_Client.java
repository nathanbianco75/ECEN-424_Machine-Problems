package com.group3.MP3.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main_MP3_Client {

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket chatSocket;
    private Thread listenThread;

    public void run(String serverIP, String serverPort) {
        int port = Integer.parseInt(serverPort);

        // try to establish connection to the server:
        try {
            chatSocket = new Socket(serverIP, port);
            InputStreamReader stream = new InputStreamReader(chatSocket.getInputStream());
            reader = new BufferedReader(stream);
            writer = new PrintWriter(chatSocket.getOutputStream());
        }
        catch (IOException e) {
            System.out.println("\nError connecting to the server at " + serverIP + ':' + serverPort + "\n\t" + e);
            // e.printStackTrace();
            return; // connection failed, so quit
        }

        // receive server welcome message
        try {
            String message = reader.readLine();
            System.out.println("\nReceived message from server:\n" + message);
        }
        catch (IOException e) {
            System.out.println("\nError receiving server message\n\t" +  e);
            // e.printStackTrace();
        }

        // send messages to server or disconnect/quit
        Scanner input = new Scanner(System.in);
        System.out.println("\nType in a message to send to the server, then hit <Enter>:");
        System.out.println("(Type \"\\disconnect to exit)");
        while (true) {
            String userIn = input.nextLine();
            if (userIn.equals("\\disconnect")) {
                break;
            }
            writer.println(userIn);
            writer.flush();
        }
    }

    public static void main(String[] args) {
        new Main_MP3_Client().run(args[0], args[1]);
    }
}
