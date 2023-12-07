package MulticastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClientDemo implements Runnable {

    public static void main(String[] args) {
            MulticastClientDemo multicastClientDemo = new MulticastClientDemo();
            // tọa một luồng mới , đối tượng multicastClientDemo sẽ được thực thi ở luồng mới
            // khi tạo một client mới sẽ chạy ở một luồng riêng biệt mới
            Thread thread =  new Thread(multicastClientDemo);
            thread.start();
    }

    public void receiveUDPMessage(String ip, int port) throws IOException {
        byte[] data=new byte[1024];
        // tạo một đối tượng MulticastSocket và liên kết nó với cổng 10335
        MulticastSocket socket=new MulticastSocket(10335);
        // Gia nhập nhóm multicast được chỉ định bởi group. Điều này có nghĩa là
        // socket sẽ tham gia vào nhóm multicast với địa chỉ IP là "230.0.0.0"
        InetAddress group=InetAddress.getByName("230.0.0.0");
        // socket sẽ tham gia vào nhóm multicast với địa chỉ IP là "230.0.0.0" và trở thành 1 tvien của nhóm
        socket.joinGroup(group);
        System.out.println("Waiting for multicast message...");
        while (true) {
            // tạo gói dữ liệu để chuẩn bị nhận dữ liệu
            DatagramPacket packet=new DatagramPacket(data, data.length);
            // nhận dữ liệu từ server
            socket.receive(packet);
            // giải nén dữ liệu sang dạng chuỗi
            String message =new String(packet.getData(),packet.getOffset(),packet.getLength());
            System.out.println("[Multicast UDP1 message received] : "  + message);
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
