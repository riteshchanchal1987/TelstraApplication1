package com.example.telstraapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DropBoxContent {

    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private ArrayList<ContentInfo> rows;


    public String getMainTitle() {
        return (title == null || title.isEmpty() || title.trim().equalsIgnoreCase("null")) ? "NA" : title;
    }

    public void setMainTitle(String _title) {
        this.title = _title;
    }

    public ArrayList<ContentInfo>  getRows() {
        return rows;
    }

    public void setMainTitle(ArrayList<ContentInfo>  _rows) {
        this.rows = _rows;
    }

}