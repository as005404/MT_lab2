package com.foxrider.mt_lab2.resource;

import com.foxrider.mt_lab2.model.MemeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = " https://api.imgflip.com/";

    @GET("get_memes")
    Call<MemeResponse> getModel();
}


