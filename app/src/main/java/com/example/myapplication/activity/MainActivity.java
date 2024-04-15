package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.adapter.VideoAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.viewmodels.VideoViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private VideoViewModel viewModel;
    private VideoAdapter videoadapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareRecyclerView();
        viewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        viewModel.getPopularVideos();

        viewModel.observeVideoLiveData().observe(this, videoList -> {
            videoadapter.setVideoList(videoList);

        });
    }

    private void prepareRecyclerView() {
        videoadapter = new VideoAdapter(this);
        binding.rvVideos.setLayoutManager(new LinearLayoutManager(this));
        binding.rvVideos.setAdapter(videoadapter);

    }
}
