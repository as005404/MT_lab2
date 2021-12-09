package com.foxrider.mt_lab2.resource;

import com.foxrider.mt_lab2.model.NameDescription;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://192.168.122.1:8081/";

    @GET("model")
    Call<List<NameDescription>> getModel();
}


