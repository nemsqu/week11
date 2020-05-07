package com.example.week11;

import java.io.Serializable;

public class Changes implements Serializable {

    private String text;
    private int textSize, boxHeight, boxWidth, rows;
    private boolean bold, allowChanges;

    public Changes(String text, int size, boolean bold, int height, int width, int rows, boolean allowChanges){
        this.text = text;
        this.textSize = size;
        this.bold = bold;
        this.boxHeight = height;
        this.boxWidth = width;
        this.rows = rows;
        this.allowChanges = allowChanges;
    }

    public String getText() {
        return text;
    }

    public boolean isBold() {
        return bold;
    }

    public int getBoxHeight() {
        return boxHeight;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public int getRows() {
        return rows;
    }

    public int getTextSize() {
        return textSize;
    }

    public boolean isAllowChanges() {
        return allowChanges;
    }
}
