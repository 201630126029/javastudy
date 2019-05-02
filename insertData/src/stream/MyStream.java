package stream;

import java.io.*;

public class MyStream {
    public static BufferedReader getBufferedReader(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    public static PrintWriter getPrintWriter(File file){
        PrintWriter print = null;
        try {
            print = new PrintWriter(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return print;
    }
}
