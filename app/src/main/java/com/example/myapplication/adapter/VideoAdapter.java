package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;
import com.example.myapplication.activity.DetailActivity;
import com.example.myapplication.databinding.VideoListLayoutBinding;
import com.example.myapplication.models.ApiResult;
import com.example.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private ArrayList<ApiResult> videoList = new ArrayList<>();
    private Context adapterContext;

    private boolean isLoading = true;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public VideoAdapter(Context appcontext) {
        adapterContext = appcontext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setVideoList(List<ApiResult> videoList) {
        this.videoList = new ArrayList<>(videoList);
        isLoading = false;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public VideoListLayoutBinding binding;

        public ViewHolder(VideoListLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                VideoListLayoutBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (isLoading()) {
            holder.binding.videoImage.startLoading();
            holder.binding.tvCategory.startLoading();
            holder.binding.videoName.startLoading();
        } else {
            Glide.with(holder.itemView)
                    .load("https:" + videoList.get(position).mainThumb)
                    .placeholder(com.example.myapplication.R.drawable.ic_launcher_background)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.binding.videoImage.setImageDrawable(
                                    ContextCompat.getDrawable(adapterContext, R.drawable.ic_launcher_background)
                            );
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(holder.binding.videoImage);
            holder.binding.videoName.setText(videoList.get(position).title);
            holder.binding.tvCategory.setText(videoList.get(position).category);
            holder.binding.cardView.setOnClickListener(v -> {
                Intent i = new Intent(adapterContext, DetailActivity.class);
                i.putExtra(Utils.EXT_OBJ, videoList.get(position));
                adapterContext.startActivity(i);
            });
            
            holder.binding.tvCategory.stopLoading();
            holder.binding.videoImage.stopLoading();
            holder.binding.videoName.stopLoading();
        }
    }

    @Override
    public int getItemCount() {
        if (videoList.size() == 0) {

            return 10;
        } else {
            return videoList.size();
        }
    }
}

