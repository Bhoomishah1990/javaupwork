package com.example.myapplication.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.VideoAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.viewmodels.VideoViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private VideoViewModel viewModel;
    private VideoAdapter videoadapter;
    int page = 1;
    private int visibleThreshold = 2;
    private boolean noData = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prepareRecyclerView();
        viewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        viewModel.getPopularVideos(page);

        viewModel.observeVideoLiveData().observe(this, videoList -> {
            if (videoList.size() != 0) {
                videoadapter.setVideoList(videoList);
            } else {
                noData = true;
                videoadapter.setVideoListWithEnd();
            }

        });
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) binding.rvVideos.getLayoutManager();

        binding.rvVideos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!noData) {
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!videoadapter.isLoading() && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        videoadapter.loadMoreLoading();
                        page++;
                        viewModel.getPopularVideos(page);

                    }
                }
            }
        });
    }

    private void prepareRecyclerView() {
        videoadapter = new VideoAdapter(this);
        binding.rvVideos.setLayoutManager(new LinearLayoutManager(this));
        binding.rvVideos.setAdapter(videoadapter);

    }
}
