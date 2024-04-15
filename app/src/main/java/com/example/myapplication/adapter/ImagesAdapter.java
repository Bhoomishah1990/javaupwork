package com.example.myapplication.adapter;

import android.app.Service;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ImagesLayoutBinding;

import java.util.List;

public class ImagesAdapter extends PagerAdapter {
    private final List<String> data;
    private final Context adapterContext;

    public ImagesAdapter(List<String> data, Context appContext) {
        this.data = data;
        this.adapterContext = appContext;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup parent, int position) {
        // ModelObject modelObject = ModelObject.values()[position];


        LayoutInflater layoutInflater = (LayoutInflater) adapterContext.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        ImagesLayoutBinding itemView = ImagesLayoutBinding.inflate(
               layoutInflater,
                parent,
                false
        );
        Glide.with(itemView.img)
                .load("https:" + data.get(position))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        itemView.img.setImageDrawable(
                                ContextCompat.getDrawable(
                                        adapterContext,
                                        R.drawable.ic_launcher_background
                                )
                        );
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return true;
                    }
                })
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.img);


        parent.addView(itemView.getRoot());
        return itemView.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

   /* public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImagesLayoutBinding binding;

        public ViewHolder(@NonNull ImagesLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }*/

  /*  @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ImagesLayoutBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }*/

   /* @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load("https:" + data.get(position))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.binding.img.setImageDrawable(
                                ContextCompat.getDrawable(
                                        adapterContext,
                                        R.drawable.ic_launcher_background
                                )
                        );
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return true;
                    }
                })
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.binding.img);
    }
*/

}

