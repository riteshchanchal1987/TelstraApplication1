/**
 * @file DropBoxContent.java
 * @brief This class will be useful when are going to call api for content which has country title(country) and list of items
 * @author Ritesh
 * @date 18/10/2019
 */

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
