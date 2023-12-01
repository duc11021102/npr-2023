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
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please enter a real number: ");
            String userInput = inFromUser.readLine();
            if(userInput.equals("QUIT")){
                break;
            }
            try{
                // Parse the user input as a float (you can use double if needed);
                float numberR = Float.parseFloat(userInput);
                // Convert the real number to bytes and send it to the server
                sendData = ByteBuffer.allocate(4).putFloat(numberR).array();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                // Extract the received data as a float and print the cube value
                float cubeValue = ByteBuffer.wrap(receivePacket.getData()).getFloat();
//                BigDecimal value = new BigDecimal(Float.toString(cubeValue));
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