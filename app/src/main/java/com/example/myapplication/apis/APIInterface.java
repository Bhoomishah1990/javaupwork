package com.example.myapplication.apis;

import com.example.myapplication.models.ApiResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("videos?/per_page=10")
    Call<List<ApiResult>> getPopularVideos(@Query("page") String page);
}
