package thread;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.nio.BufferOverflowException;

public class Client {
    public static void main(String args[]){
        try {
            MulticastSocket socket = new MulticastSocket(4446);
            InetAddress address = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(address);
            DatagramPacket packet;
            for(int i=1; i<=5; i++){
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData());
                System.out.println(received);
            }
            socket.leaveGroup(address);
            socket.close();
        } catch (SocketException ex){
            ex.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
