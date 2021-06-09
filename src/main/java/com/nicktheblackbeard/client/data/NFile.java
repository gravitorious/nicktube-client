package com.nicktheblackbeard.client.data;

import java.util.ArrayList;

/**
 * @author nicktheblackbeard
 * 9/6/21
 */
public class NFile{
    String name;
    String format;
    ArrayList<String> qualities;

    public NFile(String name, String format) {
        this.name = name;
        this.format = format;
    }

    public void addQuality(String quality){
        this.qualities.add(quality);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setQualities(ArrayList<String> qualities) {
        this.qualities = qualities;
    }

    public String getName() {
        return this.name;
    }

    public String getFormat() {
        return this.format;
    }

    public ArrayList<String> getQualities() {
        return this.qualities;
    }
}
