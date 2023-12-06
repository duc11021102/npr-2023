package as2;

import java.io.IOException;
import java.net.Socket;

public class DemoClient2 {
    public static void main(String[] args) throws IOException {
        Socket socketClient = null;
        try {
            socketClient = new Socket("localhost" , 10335);

        }catch (IOException e) {
            System.out.println(e);
        }

    }
}
