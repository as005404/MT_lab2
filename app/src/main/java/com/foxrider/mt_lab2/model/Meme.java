package com.foxrider.mt_lab2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meme {
    private String id;
    private String name;
    private String url;
    private long width;
    private long height;
    private long boxCount;

    @JsonProperty("id")
    public String getID() {
        return id;
    }

    @JsonProperty("id")
    public void setID(String value) {
        this.id = value;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.name = value;
    }

    @JsonProperty("url")
    public String getURL() {
        return url;
    }

    @JsonProperty("url")
    public void setURL(String value) {
        this.url = value;
    }

    @JsonProperty("width")
    public long getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(long value) {
        this.width = value;
    }

    @JsonProperty("height")
    public long getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(long value) {
        this.height = value;
    }

    @JsonProperty("box_count")
    public long getBoxCount() {
        return boxCount;
    }

    @JsonProperty("box_count")
    public void setBoxCount(long value) {
        this.boxCount = value;
    }
}
