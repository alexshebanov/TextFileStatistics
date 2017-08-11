package com.statistics.txt.processing;

import com.statistics.txt.entity.LineStatistics;

import java.util.ArrayList;
import java.util.List;

public class LineHandler {
    public LineStatistics result(String line) {
        String[] words = line.split(" ");

        //Cleaning words from punctuation symbols
        //and adding them to the list
        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String newWord = words[i].replaceAll("\\p{P}", "");
            words[i] = newWord;
            if (!(newWord.isEmpty()))
                wordList.add(newWord);
        }

        //If wordList doesn't contain any letter - this will be returned
        if (wordList.isEmpty())
            return new LineStatistics("line is empty", "", "", 0, 0.);

        //Finding the statistics
        String longestWord = wordList.get(0);
        String shortestWord = wordList.get(0);
        int symbolsCount = 0;
        for (String word : wordList) {
            if (word.length() > longestWord.length())
                longestWord = word;
            if (word.length() < shortestWord.length())
                shortestWord = word;
            symbolsCount += word.length();
        }

        double averageWordLength = (double) symbolsCount / wordList.size();
        return new LineStatistics(line, longestWord, shortestWord, line.length(), averageWordLength);
    }
}
