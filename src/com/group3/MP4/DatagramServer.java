package com.group3.MP4;

import java.io.IOException;
import java.net.* ;

/**
 *  A simple datagram server
 *  Shows how to send and receive UDP packets in Java
 *
 */
public class DatagramServer {
    private final static int PACKETSIZE = 100 ;

    public static void main( String args[] ) {

        // Check the arguments
        if( args.length != 2 )
        {
            System.out.println( "Error: Invalid usage.\nTry: java DatagramServer port# \"message[50 chars max]\"" ) ;
            System.exit(-1);
        }


        // Convert the arguments
        int port = 0;
        String message;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: invalid port. Please enter a positive integer.");
            e.printStackTrace();
            System.exit(-1);
        }
        message = args[1];
        if (message.length() > 50) {
            System.out.println("Error: invalid message length. Please use 50 characters or less.");
            System.exit(-1);
        }
        System.out.println("\nMessage to send:\n" + message);


        // Parse message into max-10-character long messages,
        //  with additional first message containing number of messages
        int numMessages = (int) Math.ceil((double) message.length() / 10.0) + 1;
        String[] messages = new String[numMessages];
        messages[0] = numMessages-1 + "";
        for (int i = 0; i < numMessages-1; i++) {
            if (i == numMessages - 2)
                messages[i+1] = (i+1) + message.substring(i * 10);
            else
                messages[i+1] = (i+1) + message.substring(i * 10, (i + 1) * 10);
        }
        System.out.println("\nMessages to send:");
        for (int i = 0; i < numMessages; i++)
            System.out.println(messages[i]);


        // Create server socket
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: the following port is invalid: port: " + port);
            e.printStackTrace();
            System.exit(-1);
        } catch (SocketException e) {
            System.out.println("Error: failed to open a socket on port: " + port);
            e.printStackTrace();
            System.exit(-1);
        }


        // Wait for a client to send a packet and retrieve client's address and port
        DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
        try {
            System.out.println("\nWaiting to receive a packet from a client...");
            socket.receive( packet ) ;
            System.out.println("\nConnection with Client established:");
        } catch (IOException e) {
            System.out.println("Error: exception when attempting to a receive a packet on the socket.");
            e.printStackTrace();
        }
        InetAddress address = packet.getAddress();
        port = packet.getPort();


        // Start loop to send messages to client
        int nextMessage= 0;
        while (nextMessage < numMessages) {
            byte[] data = messages[nextMessage].getBytes();
            packet = new DatagramPacket(data, data.length, address, port);

            // Send message segment
            try {
                socket.send(packet);
                System.out.println("To " + packet.getAddress() + " " + packet.getPort() + " - Sent: " + new String(packet.getData()));
            } catch (IOException e) {
                System.out.println("Error: exception when attempting to send packet with contents:\n" +
                        packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()));
                e.printStackTrace();
            }

            // Wait for an ACK
            packet.setData(new byte[PACKETSIZE]);
            String ack = "";
            try {
                // Set a receive timeout, 2000 milliseconds
                socket.setSoTimeout(2000);
                socket.receive(packet);
                ack = new String(packet.getData());
                System.out.println("From " + packet.getAddress() + " " + packet.getPort() + " - Received: " + ack);
            } catch (IOException e) {
                // Send timeout has occurred, need to resend the last packet
                System.out.println("Error: Timeout has occurred. Resending last message...");
                continue;
            }

            // Validate received ACK
            int next_trial = -1;
            try {
                // ACK = "ACK: " + trial + " Please send: " + (trial + 1);
                next_trial = Integer.parseInt(ack.substring(20,21));
                if (next_trial <= 0 || next_trial > nextMessage+1) {
                    System.out.println("Error: invalid ACK. Next trial("+next_trial+") should be greater than 0 and less than " + (nextMessage + 2));
                    socket.close();
                    System.exit(-1);
                }
            } catch (NumberFormatException e) {
                // check if ack is to terminate transmission
                if (ack.substring(7,29).equals("Received all messages.")) {
                    // All messages received, so quit
                    System.out.println("\nMessage transmission complete. Goodbye!");
                    break;
                }
                // Invalid ACK has been received, terminating the program
                System.out.println("Error: invalid ACK. Last character should have been an integer.");
                e.printStackTrace();
                socket.close();
                System.exit(-1);
            }
            if (next_trial < nextMessage + 1) {
                // Old ACK received, discarding and resending last packet
                System.out.println("Received ACK is old - discarding it and resending last packet...");
                continue;
            }

            // ACK is good. Update next_message
            nextMessage++;
        }


        // Cleanup
        if (socket != null)
            socket.close() ;
    }
}
