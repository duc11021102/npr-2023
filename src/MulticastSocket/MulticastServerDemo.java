package MulticastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MulticastServerDemo {
    public static void sendUDPMessage(String message,String ipAddress, int port) throws IOException {
        // sử dụng để tạo và quản lý các kết nối dựa trên giao thức datagram
        DatagramSocket serverSocket = new DatagramSocket();
        //tạo một đối tượng InetAddress để đại diện cho địa chỉ IP của một nhóm multicast
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        // packet dùng để đóng gói dữ liệu cùng với thông tin địa chỉ để cbi gửi
        DatagramPacket packet = new DatagramPacket(msg, msg.length,group, port);
        serverSocket.send(packet);
        serverSocket.close();
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
