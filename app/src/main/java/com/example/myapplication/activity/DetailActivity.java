package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.ImagesAdapter;
import com.example.myapplication.databinding.ActivityDetailLayoutBinding;
import com.example.myapplication.models.ApiResult;
import com.example.myapplication.utils.Utils;

import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailLayoutBinding binding;


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiResult data = getIntent().getSerializableExtra(Utils.EXT_OBJ, ApiResult.class);
        binding.tvTitle.setText(data.title);
        binding.tvStarName.setText(data.starName);
        binding.tvCategory.setText( data.category);
        List<String> img = Arrays.asList(data.imageThumb.toString().replace("", "").replace("]", "").replace("\"", "")
                .replace("\'", "").replace("https:", "").split(","));
    /*    binding.viewpagerImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));*/
        binding.viewpagerImages.setAdapter(new ImagesAdapter(img, this));
        binding.dot2.setViewPager(binding.viewpagerImages);
        binding.tvTime.setText(data.videoTime);
        binding.btnPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, VideoActivity.class);
                i.putExtra(Utils.EXT_OBJ, data);
                startActivity(i);
            }
        });

    }
}



