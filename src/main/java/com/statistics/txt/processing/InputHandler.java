package com.statistics.txt.processing;


import com.statistics.txt.db.SQLEntitySource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//Class with only method, which handles input file or directory
public class InputHandler {

    private TxtFileHandler txtFileHandler;
    private SQLEntitySource sqlEntitySource;

    public InputHandler(TxtFileHandler txtFileHandler, SQLEntitySource sqlEntitySource) {
        this.txtFileHandler = txtFileHandler;
        this.sqlEntitySource = sqlEntitySource;
    }

    public void handle(File inputFile) {
        if (inputFile.isDirectory()) {
            //Using file filter to find only .txt files or directories
            File[] files = inputFile.listFiles(pathname -> {
                if (pathname == null) {
                    return false;
                }
                if (pathname.isDirectory()) {
                    return true;
                }
                String filename = pathname.getAbsolutePath();
                return filename.endsWith(".txt");
            });
            for (File file : files) {
                if (file.isDirectory())
                    handle(file);
                //Concurrent handling
                else {
                    new Thread(new TextFileThread(file)).start();
                }
            }
        }
        //In this condition single txt file is handling
        //There's no reason to create separate thread
        else if (inputFile.getName().endsWith(".txt")) {
            try {
                sqlEntitySource.putFileStatistics(txtFileHandler.textFileStatistics(inputFile));
            }   catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            catch (IOException e) {
                System.out.println("Error due handling " + inputFile.getName() + " file");
            }
        } else return;
    }

    //Thread for .txt file handling
    class TextFileThread implements Runnable {
        public File textFile;

        public TextFileThread(File textFile) {
            this.textFile = textFile;
        }

        @Override
        public void run() {
            try {
                sqlEntitySource.putFileStatistics(txtFileHandler.textFileStatistics(textFile));
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
                System.out.println("Error due handling " + textFile.getName() + " file.");
            }
        }
    }
}
