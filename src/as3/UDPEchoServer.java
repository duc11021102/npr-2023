package as3;

import java.math.BigDecimal;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

class UDPEchoServer {
    public static void main(String args[]) throws Exception {
        int port = 9876;
        //tạo một UDPServer để nhận các gói tin từ gửi đến cổng 9876
        DatagramSocket serverSocket = new DatagramSocket(port);
        System.out.println("Server is running...");

        while (true) {
            // tạo mảng byte chứa 4 byte dữ liệu
            byte[] receiveData = new byte[4]; // 4 bytes for a float
            byte[] sendData; // 4 bytes for a float

            // tạo 1 gói để nhận dữ liệu từ mạng và lưu nó trong mảng receiveData
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            // gói tin được gửi đến cổng serverSocket, xong dữ liệu sẽ được sao chép vào receivePacket
            serverSocket.receive(receivePacket);

            // Extract the received data as a float
            // chuyển dữ liệu dạng mảng byte sang dạng float
            float numberR = ByteBuffer.wrap(receivePacket.getData()).getFloat();

            // Calculate the cube value
            float cubeValue = numberR * numberR * numberR;

            // Convert the cube value to bytes and send it to the client

            // chuyển giá tri từ dạng float sang mảng byte
            sendData = ByteBuffer.allocate(4).putFloat(cubeValue).array();
            // đóng gói dữ liệu
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            // gửi dữ liệu đến client
            serverSocket.send(sendPacket);
        }
    }
}

