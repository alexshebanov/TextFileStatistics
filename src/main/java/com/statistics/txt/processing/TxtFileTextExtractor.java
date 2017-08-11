package com.statistics.txt.processing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtFileTextExtractor {
    public List<String> textLines(File file) throws IOException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found");
        }
        BufferedReader reader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        while (reader.ready()) {
            lines.add(reader.readLine());
        }
        fileReader.close();
        return lines;
    }
}
