package as1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(11223);
            System.out.println("Server: Server đang chạy trên cổng 11223 ");
            //chấp nhận kết nối từ client
            Socket clientSocket = socket.accept();
            System.out.println("Server: Server đã kết nối đến client");
            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            while (true) {
                String string = is.readLine();
                System.out.println("Server: Nhan van ban tu client: " + string);
                if(string.equals("QUIT")) {
                    os.write("OK");
                    os.newLine();
                    os.flush();
                    break;
                }
                String uppercaseString = string.toUpperCase();
                System.out.println("Server: Gui doan van ban da thay doi len client: " + uppercaseString);
                os.write(uppercaseString);
                os.newLine();
                os.flush();
            }
            System.out.println("Server: Dong ket noi");
            clientSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
