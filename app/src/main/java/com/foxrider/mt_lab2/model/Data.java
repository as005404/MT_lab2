package com.foxrider.mt_lab2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data {
    private List<Meme> memes;

    @JsonProperty("memes")
    public List<Meme> getMemes() {
        return memes;
    }

    @JsonProperty("memes")
    public void setMemes(List<Meme> value) {
        this.memes = value;
    }
}
