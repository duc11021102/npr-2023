package as2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class DemoClient1 {
    public static void main(String[] args) throws IOException {
        Socket socketClient = null;
        BufferedReader is = null;
        BufferedWriter os = null;

        try {
            socketClient = new Socket("localhost", 10335);
        }catch (IOException e) {
            System.out.println(e);
        }

        try {
            Scanner scanner = new Scanner(System.in);
            is = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            while (true) {
                System.out.print("FROM CLIENT: Enter your number: ");
                String number = scanner.nextLine();
                os.write(number);
                os.newLine();
                os.flush();
                String result = is.readLine();
                if(result.equals("OK")){
                    break;
                } else if (result.equals("Not Integer")) {
                    System.out.println("FROM CLIENT: Your input is not a number ");
                }else {
                    System.out.println("FROM CLIENT: Result of square is " + result);
                }
            }
            os.close();
            is.close();
            socketClient.close();
        }catch (IOException e) {
            System.out.println(e);
        }

    }
}
