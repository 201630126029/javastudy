package thread;

import com.sun.org.apache.xpath.internal.operations.Quo;
import sun.security.x509.InvalidityDateExtension;

import javax.lang.model.type.IntersectionType;
import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.util.Date;

public class Server {
    public static void main(String args[]) throws IOException{
        new QuoteServerThread("haha").start();
    }
}

class QuoteServerThread extends Thread{
    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    public QuoteServerThread(String name) throws IOException{
        super(name);
        socket = new DatagramSocket(8080);
        in = new BufferedReader(new FileReader("temp.txt"));
    }
    public void run(){
        while(moreQuotes){
            try{
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String str = null;
                if(in==null) str=new Date().toString();
                else {
                    str = get();
                }
                buf =str.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet= new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket.close();
        }
    }
    protected String get(){
        String value=null;
        try{
            if((value = in.readLine()) == null){
                in.close();
                moreQuotes=false;
                value="No";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return value;
        }
    }
}
