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
            System.out.println( "\nError: Invalid usage.\nTry: java DatagramServer port# \"message[50 chars max]\"" ) ;
            System.exit(-1);
        }


        // Convert the arguments
        int port = 0;
        String message;
        try {
            port = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            System.out.println("\nError: invalid port. Please enter a positive integer.");
            e.printStackTrace();
            System.exit(-1);
        }
        message = args[1];
        if (message.length() > 50) {
            System.out.println("\nError: invalid message length. Please use 50 characters or less.");
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
        }
        catch (IllegalArgumentException e) {
            System.out.println("\nError: the following port is invalid: port: " + port);
            e.printStackTrace();
            System.exit(-1);
        }
        catch (SocketException e) {
            System.out.println("\nError: failed to open a socket on port: " + port);
            e.printStackTrace();
            System.exit(-1);
        }


        // Wait for a client to send a packet and retrieve client's address and port
        DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
        try {
            socket.receive( packet ) ;
        } catch (IOException e) {
            System.out.println("\nError: exception when attempting to a receive a packet on the socket.");
            e.printStackTrace();
        }
        InetAddress address = packet.getAddress();
        port = packet.getPort();


        // Send messages to client
        int nextMessage= 0;
        int lastACK = -1;
        while (true) {
            byte[] data = messages[nextMessage].getBytes();
            packet = new DatagramPacket(data, data.length, address, port);

            // TODO send packet, wait for ack, timeout if needed, break when nextMessage > numMessages
            break;
        }



        if (socket != null)
            socket.close() ;

        /*
        try {
            // Convert the argument to ensure that is it valid
            int port = Integer.parseInt( args[0] ) ;

            // Construct the socket
            DatagramSocket socket = new DatagramSocket( port ) ;

            System.out.println( "The server is ready..." ) ;

            for( ;; ) {
                // Create a packet
                DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;

                // Receive a packet (blocking)
                socket.receive( packet ) ;

                // Print the packet
                System.out.println( packet.getAddress() + " " + packet.getPort() + ": " + new String(packet.getData()) ) ;

                // Return the packet to the sender
                socket.send( packet ) ;
            }
        }
        catch( Exception e )
        {
            System.out.println( e ) ;
        }
        */
    }
}
