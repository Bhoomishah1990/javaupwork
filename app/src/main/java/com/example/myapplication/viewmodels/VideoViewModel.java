package com.example.myapplication.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.apis.APIClient;
import com.example.myapplication.models.ApiResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoViewModel extends ViewModel {
    private   MutableLiveData<List<ApiResult>> videoLiveData =
            new MutableLiveData();
    public LiveData<List<ApiResult>> observeVideoLiveData() {
        return videoLiveData;
    }

    public void getPopularVideos() {
        APIClient.getClient().getPopularVideos("1").enqueue(new Callback<List<ApiResult>>() {
            @Override
            public void onResponse(Call<List<ApiResult>> call, Response<List<ApiResult>> response) {
                if (response.body() != null) {
                   videoLiveData.setValue( response.body());
                } else {
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<ApiResult>> call, Throwable t) {

                Log.d("TAG", t.getMessage().toString());

            }
        });
    }
}
