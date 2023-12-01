package ImageTranfer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ImageDownloader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String serverAddress = "localhost"; // Thay đổi địa chỉ IP hoặc tên miền của máy chủ
        String downloadDirectory;
        System.out.println("Nhập địa chỉ Server port: "); // Thay đổi cổng mà máy chủ đang lắng nghe
        int serverPort = scanner.nextInt();
        System.out.print("Nhập địa chỉ thư mục của ảnh cần tải: ");
        String requestedImage = scanner.nextLine();
        System.out.print("Nhập địa chỉ thư mục để lưu ảnh: ");
        downloadDirectory = scanner.nextLine();

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            // Gửi tên ảnh cần tải đến máy chủ
//            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//            os.write(requestedImage);
//            os.newLine();
//            os.flush();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(requestedImage);

            // Nhận thông tin ảnh (tên và kích thước)
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String imageFileName = dis.readUTF();
            long imageSize = dis.readLong();

            // Tạo thư mục để lưu ảnh nếu nó không tồn tại
            File downloadFolder = new File(downloadDirectory);
            if (!downloadFolder.exists()) {
                downloadFolder.mkdirs();
            }

            // Lưu ảnh vào thư mục đã chỉ định
            File imageFile = new File(downloadDirectory, imageFileName);
            FileOutputStream fos = new FileOutputStream(imageFile);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while (imageSize > 0 && (bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, imageSize))) != -1) {
                fos.write(buffer, 0, bytesRead);
                imageSize -= bytesRead;
            }

            fos.close();
            dis.close();
            dos.close();
            socket.close();

            System.out.println("Ảnh đã được tải xuống thành công và lưu tại: " + imageFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}

