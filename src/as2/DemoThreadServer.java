package as2;

import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class DemoThreadServer  {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int clientNumber = 0;
        System.out.println("FROM SERVER: Waiting client connect");
        try {
            serverSocket = new ServerSocket(10335);
        }catch (IOException e) {
            System.out.println(e);
        }

        try {
            while (true) {
                Socket socketOfServer = serverSocket.accept();
                System.out.println("FROM SERVER: Server connected to client");
                ServiceThread serviceThread = new ServiceThread(socketOfServer, clientNumber++);
                serviceThread.start();
            }
        }finally {
            serverSocket.close();
        }

    }

    private static class ServiceThread extends Thread {
        private int clientNumber;
        private Socket socketServer ;

        public ServiceThread(Socket socketServer, int clientNumber) {
            this.socketServer = socketServer;
            this.clientNumber = clientNumber;
            System.out.println("FROM SERVER: Connected to client " + clientNumber + " with " + socketServer);
        }
        @Override
        public void run() {
            try{
                BufferedReader is = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketServer.getOutputStream()));
                while (true) {

                    String number = is.readLine();
                    if(number.replaceAll(" ", "").toLowerCase().equals("quit")){
                        os.write("OK");
                        os.newLine();
                        os.flush();

                    }else {
                        if (number.matches("\\d+")){
                            int numberInt = Integer.parseInt(number);
                            os.write("" + numberInt * numberInt);
                            os.newLine();
                            os.flush();
                        }else {
                            os.write("Not Integer");
                            os.newLine();
                            os.flush();
                        }
                    }
                }
            }catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}
