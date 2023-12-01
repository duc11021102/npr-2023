//import java.net.*;
//import java.io.*;
//
//public class b3 {
//    URL url = null;
//    BufferedReader in = null;
//    public  b3() {
//
//        try {
//            url = new URL("https://en.wikipedia.org/wiki/Computer_science");
//        } catch(MalformedURLException e) {
//            System.out.println("Cannot find webpage " + url);
//            System.exit(-1);
//        }
//
//        try {
//            URLConnection	aConnection = url.openConnection();
//            in = new BufferedReader(new InputStreamReader(aConnection.getInputStream()));
//        }
//        catch (IOException e) {
//            System.out.println("Cannot connect to webpage " + url);
//            System.exit(-1);
//        }
//        try {
//            // Now read the webpage file
//            String lineOfWebPage;
//            while ((lineOfWebPage = in.readLine()) != null)
//                System.out.println(lineOfWebPage);
//            in.close(); // Close the connection to the net
//        } catch(IOException e) {
//            System.out.println("Cannot read from webpage " + url);
//        }
//
//    }
//}
