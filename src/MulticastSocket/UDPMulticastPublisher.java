package MulticastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * máy gửi sử dụng MulticastSocket để gửi một thông điệp đến
 * nhóm multicast với địa chỉ IP là 230.0.0.0 và cổng là 4321.
 * Máy nhận sẽ sử dụng cũng MulticastSocket để tham gia vào
 * nhóm và nhận thông điệp từ người gửi.
 * */

public class UDPMulticastPublisher {

    // function gui du lieu
    public static void sendUDPMessage(String message,String ipAddress, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length,group, port);
        socket.send(packet);
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        int n = 1;
        while(true) {
            String s = "Hi there! This is a multicast message number ";
            s = s + Integer.toString(n) + " from publisher!";
            n++;
            sendUDPMessage(s, "230.0.0.0",4321);
            try {
                // tạo độ trễ , vòng lặp sẽ thực thi lại sau mỗi 1 giây
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

