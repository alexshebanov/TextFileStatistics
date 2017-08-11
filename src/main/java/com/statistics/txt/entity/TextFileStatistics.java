package com.statistics.txt.entity;


import java.util.List;

public class TextFileStatistics {
    private String name;
    private List<LineStatistics> lineStatistics;

    public TextFileStatistics(String name, List<LineStatistics> lineStatistics) {
        this.name = name;
        this.lineStatistics = lineStatistics;
    }

    public String getName() {
        return name;
    }

    public List<LineStatistics> getLineStatistics() {
        return lineStatistics;
    }
}
