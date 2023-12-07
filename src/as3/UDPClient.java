package as3;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.*;

public class UDPClient {
    public static void main(String args[]) throws Exception {

        int port = 9876;
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        while (true) {
            byte[] sendData;
            byte[] receiveData = new byte[1024];
            // dùng BufferedReader đọc dữ liệu từ bàn phím , giống như Scanner
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please enter a real number: ");
            String userInput = inFromUser.readLine();
            if(userInput.equals("QUIT")){
                break;
            }
            try{
                // chuyển dữ liệu người nhập từ dạng string sang dạng float
                float numberR = Float.parseFloat(userInput);
                // chuyển dữ liệu từ dạnh float sang dạng byte để cbi gửi đi
                sendData = ByteBuffer.allocate(4).putFloat(numberR).array();
                // đóng gói dữ liệu vào DatagramPacket
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                // gửi dữ liệu
                clientSocket.send(sendPacket);
                // tạo 1 đối tượng gói dữ liệu DatagramPacket để nhận dữ liệu trả về
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                // nhận dữ liệu trả về
                clientSocket.receive(receivePacket);
                // giải nén gói dữ liệu và chuyển sang dạng float
                float cubeValue = ByteBuffer.wrap(receivePacket.getData()).getFloat();
                System.out.println("Cube value of " + numberR + " is: " + cubeValue);
            }catch (NumberFormatException e) {
                System.out.println("Lỗi: " + e.getMessage());
                System.out.println("userInput không chứa một giá trị số thực hợp lệ.");
            }catch (InputMismatchException e) {
                System.out.println("Lỗi: " + e.getMessage());
                System.out.println("Dữ liệu nhập không phải số thực.");
            }
        }
    }
}