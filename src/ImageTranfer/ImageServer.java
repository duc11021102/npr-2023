package ImageTranfer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ImageServer {
    public static void main(String[] args) {
        int port = 12345; // Chọn cổng bạn muốn sử dụng
        String imageDirectory = "C:\\Users\\minhduc\\Desktop\\server"; // Thay đổi đường dẫn đến thư mục chứa ảnh

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is running. Waiting for a client to connect...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                String requestedImage = dis.readUTF();

                File imageFile = new File(imageDirectory, requestedImage);

                if (imageFile.exists()) {
                    System.out.println("Sending image: " + requestedImage);

                    // Gửi tên và kích thước của ảnh
                    DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                    dos.writeUTF(requestedImage);
                    dos.writeLong(imageFile.length());

                    // Gửi nội dung của ảnh
                    FileInputStream fis = new FileInputStream(imageFile);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        dos.write(buffer, 0, bytesRead);
                    }

                    fis.close();
                    dos.close();
                } else {
                    System.out.println("Image not found: " + requestedImage);
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
