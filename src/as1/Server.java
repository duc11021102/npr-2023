package as1;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Tạo một ServerSocket để lắng nghe kết nối từ client trên cổng 12345
                ServerSocket serverSocket = new ServerSocket(11223);
            System.out.println("Server: Server đang chạy trên cổng 11223...");
            while (true) {
                // Chấp nhận kết nối từ client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server: Đã kết nối đến Client.");
                // Tạo luồng đầu vào và đầu ra để truyền dữ liệu
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                // Đọc số từ client
                String input = in.readLine();
                int n = Integer.parseInt(input);
                System.out.println("Server: Nhận n từ client = " + n);
                // Chuyển đổi số thành bình phương
                int square = n * n;
                // gửi lại cho client
                out.println(square);
                System.out.println("Server: Gửi đến client n bình phương = " + square);
                // Đóng kết nối với client
                clientSocket.close();
                System.out.println("Server: Ngắt kêt nối client.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

