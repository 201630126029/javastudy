package main;

import dao.ConnectionOption;
import dao.Option;
import stream.MyStream;

import java.io.BufferedReader;
import java.io.File;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Siryu\\Desktop\\github\\-\\gzy\\数据库\\Books";
        File file = new File(fileName);
        String[] names = file.list();
        for(String name:names){
            File sonFile = new File(file, name);
            if(!sonFile.exists() || sonFile.isFile() )continue;

            String[] bookFileNames = sonFile.list();
            for(String bookFileName : bookFileNames){
                if(!bookFileName.contains(".")){
                    BookOption.insertBook(sonFile,bookFileName);
                }
            }
        }

    }
}