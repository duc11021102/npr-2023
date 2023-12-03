package MulticastSocket;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastClient2 implements Runnable {

    @Override
    public void run(){
        try {
            receiveUDPMessage("230.0.0.0", 4321);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UDPMulticastClient2 udpClient2 = new UDPMulticastClient2();
        Thread t=new Thread(udpClient2);
        t.start();
    }

    public void receiveUDPMessage(String ip, int port) throws IOException {
        byte[] buffer=new byte[1024];
        // tạo một đối tượng MulticastSocket và liên kết nó với cổng 4321
        MulticastSocket socket=new MulticastSocket(4321);
        // Gia nhập nhóm multicast được chỉ định bởi group. Điều này có nghĩa là
        // socket sẽ tham gia vào nhóm multicast với địa chỉ IP là "230.0.0.0"
        InetAddress group=InetAddress.getByName("230.0.0.0");
        socket.joinGroup(group);

        System.out.println("Waiting for multicast message...");
        int n=1;
        while(true){
            DatagramPacket packet=new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String msg=new String(packet.getData(),packet.getOffset(),packet.getLength());
            System.out.println("[Multicast UDP message received] "+n+": "+msg);
            n++;
            if("OK".equals(msg)) {
                System.out.println("No more message. Exiting : "+msg);
                break;
            }
        }
        socket.leaveGroup(group);
        socket.close();
    }


}

