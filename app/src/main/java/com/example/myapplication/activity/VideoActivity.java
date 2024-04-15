package com.example.myapplication.activity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.MediaController;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityVideoPlayBinding;
import com.example.myapplication.models.ApiResult;
import com.example.myapplication.utils.Utils;

public class VideoActivity extends AppCompatActivity {

    private ActivityVideoPlayBinding binding;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiResult data = getIntent().getSerializableExtra(Utils.EXT_OBJ, ApiResult.class);
        Uri uri = Uri.parse(data.videoUrl);
        binding.videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.videoView);
        mediaController.setMediaPlayer(binding.videoView);
        binding.videoView.setMediaController(mediaController);
        binding.videoView.start();


    }
}



