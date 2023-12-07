package mid;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String serverAddress = "localhost";
        int serverPort = 10335;
        // tạo một đối tượng DatagramSocket ở client để nhận và gửi dữ liệu
        DatagramSocket clientSocket = new DatagramSocket();
        // Tạo một đối tượng InetAddress đại diện cho địa chỉ IP localhost
        InetAddress serverAddressInet = InetAddress.getByName(serverAddress);
        System.out.println("Client: Conneted to server");

        // tạo dữ liệu để gửi đi server
        String message = "2001040035";
        // chuyển message sang dạng byte để cbi gửi
        byte[] sendData = message.getBytes();

        // Tạo 1 DatagramPacket để gửi dữ liệu đến server
        DatagramPacket sendPacket = new DatagramPacket (sendData, sendData.length, serverAddressInet, serverPort);

        // gửi dữ liệu đến server
        System.out.println("Client: Send number " + message +  " to server!");
        clientSocket.send(sendPacket);

        // nhận gói dữ liệu trả về từ server
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        // giải nén gói dữ liệu trả về từ server
        String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Client: Server Response (number * 3) = " + serverResponse);

        while (true) {
            try {
                System.out.print("Enter a number (R) to send to the server: ");
                String numberString = scanner.nextLine();
                if(numberString.replaceAll(" ", "").toLowerCase().equals("quit")) {
                    System.out.println("Client: Stopped");
                    break;
                }else {
                    BigInteger numberR = new BigInteger(numberString);
//                BigInteger numberR = scanner.nextBigInteger();
                    String stringNumberR = numberR.toString();
                    byte[] sendNextData = stringNumberR.getBytes();
                    DatagramPacket sendNextPacket = new DatagramPacket(sendNextData, sendNextData.length, serverAddressInet, serverPort);
                    clientSocket.send(sendNextPacket);
                    byte[] receiveNextData = new byte[1024];
                    DatagramPacket receiveNextPacket = new DatagramPacket(receiveNextData, receiveNextData.length);
                    clientSocket.receive(receiveNextPacket);
                    String serverNextResponse = new String(receiveNextPacket.getData(), 0, receiveNextPacket.getLength());
                    System.out.println("Client: Server Response (R^3) = " + serverNextResponse);
                }

            } catch (NumberFormatException e) {
                System.out.println("Lỗi: " + e.getMessage());
                System.out.println("userInput không chứa một giá trị số thực hợp lệ.");

            }catch (InputMismatchException e) {
                System.out.println("Lỗi: " + e.getMessage());
                System.out.println("Dữ liệu nhập không phải số thực.");

            }
        }
        // Close the client socket
//        clientSocket.close();

    }
}
