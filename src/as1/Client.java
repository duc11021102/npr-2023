package as1;
import java.io.*;
import java.net.*;


public class Client {
    public static void main(String[] args) {
        try {
            // Kết nối đến server trên cổng 11223
            Socket socket = new Socket("localhost", 11223);
            // Tạo luồng đầu vào và đầu ra để truyền dữ liệu
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // Gửi một số đến server
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Client: Nhap so n:");
            String n = userInput.readLine();
            System.out.println("Client: Đang gửi n = " + n);
            out.println(n);
            // Nhận và in thông điệp phản hồi từ server
            int square = Integer.parseInt(in.readLine());
            System.out.println("Client: Bình phương của n = " + square);
            // Đóng kết nối
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

