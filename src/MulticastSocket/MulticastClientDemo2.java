package MulticastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClientDemo2 implements Runnable {

    public static void main(String[] args) {
        MulticastClientDemo2 multicastClientDemo2 = new MulticastClientDemo2();
        Thread thread =  new Thread(multicastClientDemo2);
        thread.start();
    }

    public void receiveUDPMessage(String ip, int port) throws IOException {
        byte[] buffer=new byte[1024];
        // tạo một đối tượng MulticastSocket và liên kết nó với cổng 10335
        MulticastSocket socket=new MulticastSocket(10335);
        // Gia nhập nhóm multicast được chỉ định bởi group. Điều này có nghĩa là
        // socket sẽ tham gia vào nhóm multicast với địa chỉ IP là "230.0.0.0"
        InetAddress group=InetAddress.getByName("230.0.0.0");
        socket.joinGroup(group);
        System.out.println("Waiting for multicast message...");
        while (true) {
            DatagramPacket packet=new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String message =new String(packet.getData(),packet.getOffset(),packet.getLength());
            System.out.println("[Multicast UDP2 message received] : "  + message);
        }
    }
    @Override
    public void run() {
        try {
            receiveUDPMessage("230.0.0.0" , 10335);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
