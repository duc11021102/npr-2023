package TCPexample;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Kết nối đến server trên cổng 12345
            Socket socket = new Socket("localhost", 12345);

            // Tạo luồng đầu vào và đầu ra để truyền dữ liệu
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Gửi một thông điệp đến server
            String message = "Hello, Server!";
            out.println(message);
            System.out.println("Sent message: " + message);

            // Nhận và in thông điệp phản hồi từ server
            String response = in.readLine();
            System.out.println("Received response: " + response);

            // Đóng kết nối
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
