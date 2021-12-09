package com.foxrider.mt_lab2.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemeResponse {
    private boolean success;
    private Data data;

    @JsonProperty("success")
    public boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(boolean value) {
        this.success = value;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data value) {
        this.data = value;
    }
}
