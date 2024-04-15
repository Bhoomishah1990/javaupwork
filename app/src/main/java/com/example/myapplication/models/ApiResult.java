package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiResult implements Serializable {
    @SerializedName("title")
    public String title = null;
    @SerializedName("url")
    public String url = null;
    @SerializedName("upload_time")
    public String uploadTime = null;
    @SerializedName("video_time")
    public String videoTime = null;
    @SerializedName("star_name")
    public String starName = null;
    @SerializedName("category")
    public String category = null;
    @SerializedName("video_url")
    public String videoUrl = null;
    @SerializedName("main_thumb")
    public String mainThumb = null;
    @SerializedName("image_thumb")
    public String imageThumb = null;
    @SerializedName("id")
    public  String id = null;

    ApiResult(
            String title,
            String url,
            String uploadTime,
            String videoTime,
            String starName,
            String category,
            String videoUrl,
            String mainThumb,
            String imageThumb,
            String id
    ) {
        this.title = title;
        this.url = url;
        this.uploadTime = uploadTime;
        this.videoTime = videoTime;
        this.starName = starName;
        this.category = category;
        this.videoUrl = videoUrl;
        this.mainThumb = mainThumb;
        this.imageThumb = imageThumb;
        this.id = id;
    }


}