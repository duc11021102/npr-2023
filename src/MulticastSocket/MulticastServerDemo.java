package MulticastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MulticastServerDemo {
    public static void sendUDPMessage(String message,String ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length,group, port);
        socket.send(packet);
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter the message to send:");
            String data = scanner.nextLine();
            sendUDPMessage(data, "230.0.0.0", 10335);
        }
    }
}
