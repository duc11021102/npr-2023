package TCPexample;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Tạo một ServerSocket để lắng nghe kết nối từ client trên cổng 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server is listening on port 12345...");

            while (true) {
                // Chấp nhận kết nối từ client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                // Tạo luồng đầu vào và đầu ra để truyền dữ liệu
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Đọc thông điệp từ client
                String message = in.readLine();
                System.out.println("Received message: " + message);

                // Chuyển đổi thông điệp thành chữ in hoa và gửi lại cho client
                String response = message.toUpperCase();
                out.println(response);
                System.out.println("Sent response: " + response);

                // Đóng kết nối với client
                clientSocket.close();
                System.out.println("Client disconnected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
