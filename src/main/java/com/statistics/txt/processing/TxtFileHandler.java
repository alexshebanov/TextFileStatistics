package com.statistics.txt.processing;

import com.statistics.txt.entity.LineStatistics;
import com.statistics.txt.entity.TextFileStatistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtFileHandler {
    private TxtFileTextExtractor txtFileTextExtractor;
    private LineHandler lineHandler;

    public TxtFileHandler(TxtFileTextExtractor txtFileTextExtractor, LineHandler lineHandler) {
        this.txtFileTextExtractor = txtFileTextExtractor;
        this.lineHandler = lineHandler;
    }

    public TextFileStatistics textFileStatistics(File file) throws IOException {
        List<String> lines = null;
        try {
            lines = txtFileTextExtractor.textLines(file);
        }  catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        catch (IOException e) {
            throw new IOException("Error due file reading.");
        }
        List<LineStatistics> lineStatistics = new ArrayList<>();
        for (String line : lines) {
            lineStatistics.add(lineHandler.result(line));
        }
        return new TextFileStatistics(file.getName(), lineStatistics);
    }
}
