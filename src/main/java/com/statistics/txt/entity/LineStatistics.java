package com.statistics.txt.entity;

public class LineStatistics {
    private String line;
    private String longestWord;
    private String shortestWord;
    private int length;
    private double averageWordLength;

    public LineStatistics(String line, String longestWord, String shortestWord, int length, double averageWordLength) {
        this.line = line;
        this.longestWord = longestWord;
        this.shortestWord = shortestWord;
        this.length = length;
        this.averageWordLength = averageWordLength;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public void setShortestWord(String shortestWord) {
        this.shortestWord = shortestWord;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(double averageWordLength) {
        this.averageWordLength = averageWordLength;
    }
}
