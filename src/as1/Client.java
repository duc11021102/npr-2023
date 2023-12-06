package as1;

import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socketClient = new Socket("localhost", 7777);
        while (true) {
            BufferedReader is = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            try {
                System.out.print("FROM CLIENT: Enter your N number:");
                Scanner scanner = new Scanner(System.in);
                String data = scanner.nextLine();
                if (data.replaceAll(" ", "").toLowerCase().equals("quit")) {
                    os.write("quit");
                    os.newLine();
                    os.flush();
                    socketClient.close();
                    break;
                }
                else {
                    int dataNumber = Integer.parseInt(data);
                    os.write(data);
                    os.newLine();
                    os.flush();
                }
                int numberResponse = is.read();
                System.out.println("FROM CLIENT: Square of N is: " + numberResponse);
            }catch (NumberFormatException e) {
                System.out.println("FROM CLIENT: Not a number");
            }


    }
}}
