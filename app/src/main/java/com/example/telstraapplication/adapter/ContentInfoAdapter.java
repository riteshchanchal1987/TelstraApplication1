/**
 * @file ContentInfoAdapter.java
 * @brief This is an adapter class responsible for showing json data.
 * @author Ritesh
 * @date 18/10/2019
 */

package com.example.telstraapplication.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.telstraapplication.model.ContentInfo;
import com.example.telstraapplication.R;
import com.example.telstraapplication.view.MainActivity;

import java.util.List;

public class ContentInfoAdapter extends RecyclerView.Adapter<ContentInfoAdapter.MyViewHolder>  {

    private MainActivity mainActivity;
    private List<ContentInfo> contentInfoList;


    public ContentInfoAdapter (MainActivity mainActivity, List<ContentInfo> contentInfoList) {
        this.mainActivity = mainActivity;
        this.contentInfoList = contentInfoList;
    }


    @NonNull
    @Override
    public ContentInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContentInfoAdapter.MyViewHolder holder, int position) {

        ContentInfo contentInfo = contentInfoList.get(position);

        holder.tvTitle.setText(contentInfo.getTitle());
        holder.tvDescription.setText(String.valueOf(contentInfo.getDescription()));
        // loading album cover using Glide library
        Glide.with(mainActivity)
                .load(contentInfo.getImageUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.ivThumbnail.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.ivThumbnail.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return contentInfoList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;

        public TextView tvDescription;

        public ImageView ivThumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);

        }
    }

}
