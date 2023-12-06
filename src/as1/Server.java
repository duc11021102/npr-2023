package as1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("FROM SERVER: Waiting to accept user...");
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        Socket socket = serverSocket.accept();
        System.out.println("FROM SERVER: Accept client.");
        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        while (true) {
                try {
                    String data = is.readLine();
                    System.out.println("FROM SERVER: Number from client is: " + data);
                    Integer dataNumber = Integer.parseInt(data);
                    int sqrNumber = dataNumber * dataNumber;
                    System.out.println("FROM SERVER: Number after square is: " + sqrNumber);
                    os.write(sqrNumber);
                    os.newLine();
                    os.flush();
                } catch (NumberFormatException e) {
                    System.out.println("FROM SERVER: Stopped");
                    serverSocket.close();
                    break;
                }
        }

    }
}
