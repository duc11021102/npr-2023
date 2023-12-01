package as1;

import java.io.*;
import java.net.Socket;

public class DemoClient {
    public static void main(String[] args) {
       try{
           Socket socket = new Socket("localhost" , 11223);
           BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
           while (true) {
               System.out.println("Client: Nhap doan van ban:");
               String string = userInput.readLine();
               System.out.println("Client: Gui doan van ban " + string + " toi Server ");
               os.write(string);
               os.newLine();
               os.flush();
               String result = is.readLine();
               if (result.equals("OK")) {
                   break;
               }
               System.out.println("Client: Doan van ban sau khi thay doi la : " + result);
           }
           System.out.println("Dong ket noi");
           is.close();
           os.close();
           socket.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
