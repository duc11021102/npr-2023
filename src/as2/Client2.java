package as2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
    public static void main(String[] args) {
        Socket socketOfClient = null;
        BufferedWriter os = null;
        BufferedReader is = null;
        try {
            socketOfClient = new Socket("localhost", 7777);
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + "localhost");
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + "localhost");
            return;
        }
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Client: Enter a number: ");
                String input = userInput.readLine();
                os.write(input);
                os.newLine();
                os.flush();
                String result = is.readLine();
                if(result.equals("OK")) {
                    break;
                }
                System.out.println("Client: Square of number is: " + result);
            }
            os.close();
            is.close();
            socketOfClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Client: Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("Client: IOException:  " + e);
        }
    }

}
