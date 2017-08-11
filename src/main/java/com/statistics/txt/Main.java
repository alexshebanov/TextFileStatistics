package com.statistics.txt;

import com.statistics.txt.db.SQLEntitySource;
import com.statistics.txt.processing.InputHandler;
import com.statistics.txt.processing.TxtFileHandler;
import com.statistics.txt.processing.LineHandler;
import com.statistics.txt.processing.TxtFileTextExtractor;

import java.io.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //Here will be db connection properties
        Properties dbProps = new Properties();
        try {
            dbProps.load(new FileInputStream("src/main/resources/db.properties"));
        } catch (IOException e) {
            System.out.println("Unable to open properties file.");
        }
        System.out.println("Enter .txt file or directory path:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = null;
        try {
            path = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error due the reading.");
        }
        File file = new File(path);
        InputHandler handler = new InputHandler(new TxtFileHandler(new TxtFileTextExtractor(),
                new LineHandler()), new SQLEntitySource(dbProps));
        handler.handle(file);
    }
}
