/**
 * @file ContentInfo.java
 * @brief This class will  have all attribute related to the country.
 * @author Ritesh
 * @date 18/10/2019
 */

package com.example.telstraapplication.model;

import com.google.gson.annotations.SerializedName;

public class ContentInfo {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("imageHref")
    private String imageUrl;


    public ContentInfo (String title, String description , String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }


    public String getTitle() {
        return (title == null || title.isEmpty() || title.trim().equalsIgnoreCase("null")) ? "NA" : title;
    }

    public void setTitle(String _title) {
        this.title = _title;
    }

    public String getDescription(){
        return (description == null || description.isEmpty() || description.trim().equalsIgnoreCase("null")) ? "NA" : title;
    }

    public void setDescription(String _description) {
        this.description = _description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String _imageUrl) {
        this.imageUrl = _imageUrl;
    }

    @Override
    public String toString() {
        return "ContentInfo {" +
                "title=" + title +
                ", description='" + description + '\'' +
                ", imageHref='" + imageUrl + '\'' +
                '}';
    }
}
