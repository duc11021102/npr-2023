package mid;

import java.io.*;
import java.net.*;

import java.net.*;
import java.math.BigInteger;
public class ServerTest {
    private static boolean firstTime = true;

    private  static int defaultPort = 12312;

    public static void main(String[] args){
        int port = 10335; // Change this to the port you want to use
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            System.out.println("Server: Server is running. Waiting for a client to connect...");
            while (true) {
                // khai báo một mảng byte có kích thước 1024 byte, được sử dụng để lưu trữ dữ liệu
                byte[] receiveData = new byte[1024];
                // Tạo 1 DatagramPacket để nhận data from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                // Nhận data từ client.
                serverSocket.receive(receivePacket);
                // lấy địa chỉ và port của client vừa gửi data
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                System.out.println("Server: Connected to Client: " + clientAddress + ":" + clientPort);

                // xử lí khi có nhiều client cùng kết nối đến server , chia client
                if (firstTime == true) {
                    defaultPort = clientPort;
                }
                if (firstTime == false && defaultPort != clientPort){
                    firstTime = true;
                    defaultPort = clientPort;
                }


                if(firstTime) {
                    firstTime = false;
                    //đọc dữ liệu từ client
                    String data = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Server: Number from client: " + data);
                    // caculate * 3 data from client ;
                    BigInteger dataNumber  = new BigInteger(data);
                    // chuyển số 3 thành biginteger
                    BigInteger three = BigInteger.valueOf(3);
                    BigInteger result = dataNumber.multiply(three);
                    System.out.println("Server: Result caculated is : " + result);
                    // đóng gói kết quả để cbi gửi lại client
                    byte[] sendData = result.toString().getBytes();
                    DatagramPacket sendPacket = new DatagramPacket (sendData, sendData.length, clientAddress, clientPort);
                    // gửi lên client
                    serverSocket.send(sendPacket);
                } else  {
                    //đọc dữ liệu từ client
                    String data = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Server: Number from client: " + data);
                    // caculate ^ 3 data from client ;
                    BigInteger dataNumber  = new BigInteger(data);
                    BigInteger result = dataNumber.pow(3);
                    System.out.println("Server: Result caculated is : " + result);
                    // đóng gói kết quả để cbi gửi lại client
                    byte[] sendData = result.toString().getBytes();
                    DatagramPacket sendPacket = new DatagramPacket (sendData, sendData.length, clientAddress, clientPort);
                    // gửi lên client
                    serverSocket.send(sendPacket);
                }



            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
