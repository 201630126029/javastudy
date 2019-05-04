package main;

import dao.Option;
import stream.MyStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对Book表进行操作
 */
public class BookOption {
    /**
     * 插入一本书，这本书的信息在文件里面
     * @param file 文件，文件加.jpg是书对应的图片 文件加.txt是书的描述信息
     */
    public static void insertBook(File file, String bookname){
        try {
            BufferedReader in = MyStream.getBufferedReader(new File(file,bookname));
            int bookID = Integer.parseInt(in.readLine());
            String bookName = in.readLine();
            int price = Integer.parseInt(in.readLine());
            String author = in.readLine();
            String Type = in.readLine();
            String introduce = getIntroduce(new File(file, bookName+".txt"));
            List<String> columns = new ArrayList<>();
            List<String> columnsValues=new ArrayList<>();
            columns.add("BookID");
            columnsValues.add(new Integer(bookID).toString());
            columns.add("BookName");
            columnsValues.add("\'"+bookName+"'");
            columns.add("Author");
            columnsValues.add("\'"+author+"'");
            columns.add("Price");
            columnsValues.add(new Integer(price).toString());
            columns.add("Introduce");
            columnsValues.add("\'"+introduce+"'");
            columns.add("Type");
            columnsValues.add("\'"+Type+"'");
            Option.insert("Book",columns, columnsValues);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过文件得到文件内的所有信息的String
     * @param file
     * @return 文件内容
     */
    public static String getIntroduce(File file){
        String result = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            result="";
            String line ;
            while((line=br.readLine()) != null){
                result += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result.length() < 100){
            return result;
        }
        else {
            return result.substring(0,99);
        }
    }
}
