package as2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServer {

    public static void main(String args[]) throws IOException {
        ServerSocket listener = null;
        System.out.println("Server is waiting to accept user...");
        int clientNumber = 0;
        try {
            listener = new ServerSocket(7777);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        try {
            while (true) {
                Socket socketOfServer = listener.accept();
                System.out.println("Server: Server đã kết nối đến client");
                new ServiceThread(socketOfServer, clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }


    private static class ServiceThread extends Thread {
        private int clientNumber;
        private Socket socketOfServer;

        public ServiceThread(Socket socketOfServer, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;

            // Log
            log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
        }

        @Override
        public void run() {
            try {
                BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
                while (true) {
                    String line = is.readLine();
                    if (line.equals("QUIT")) {
                        os.write("OK");
                        os.newLine();
                        os.flush();
                        break;
                    }else {
                        if(line.matches("\\d+")) {
                            int output = Integer.parseInt(line);
                            os.write(""+  output * output );
                            os.newLine();
                            os.flush();
                        }else{
                            os.write("not integer");
                            os.newLine();
                            os.flush();
                        }

                    }
                }
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
}
