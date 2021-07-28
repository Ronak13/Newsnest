package com.example.newsnest.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsnest.R;
import com.example.newsnest.model.ModelClass;
import com.example.newsnest.webViewActivity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<ModelClass> newsLists;

    public ItemAdapter(Context context, ArrayList<ModelClass> newsLists) {
        this.context = context;
        this.newsLists = newsLists;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        ModelClass newsList = newsLists.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, webViewActivity.class);
                intent.putExtra("url", newsList.getUrl());
                context.startActivity(intent);
            }
        });
        if (newsList.getAuthor() == null) {
            holder.mNewsAuthor.setText("NO Author found");
        } else {
            holder.mNewsAuthor.setText("Authors:- " + newsList.getAuthor());

        }
        holder.mNewsDesc.setText(newsList.getDescription());
        holder.mNewsHeading.setText(newsList.getTitle());

        // converting UTC to IST time
        Instant s = Instant.parse(newsList.getPublishedAt());
        ZoneId.of("Asia/Kolkata");
        LocalDateTime l = LocalDateTime.ofInstant(s, ZoneId.of("Asia/Kolkata"));
        String t = l.toString();
        LocalDateTime ldt = LocalDateTime.parse(t, DateTimeFormatter.ISO_DATE_TIME);
        String IST = ldt.format(DateTimeFormatter.ofPattern("MMM dd,yyyy HH:mm:ss a"));
        holder.mNewsPublished.setText("Published At:- " + IST);


        // loading image
        Glide
                .with(context)
                .load(newsList.geturlToImage())
                .placeholder(R.drawable.no_img)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return newsLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNewsHeading, mNewsDesc, mNewsAuthor, mNewsPublished;
        private CardView cardView;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mNewsHeading = itemView.findViewById(R.id.newsheading_tv);
            mNewsAuthor = itemView.findViewById(R.id.newsauther_tv);
            mNewsDesc = itemView.findViewById(R.id.newsdesc_tv);
            imageView = itemView.findViewById(R.id.newsimage_iv);
            mNewsPublished = itemView.findViewById(R.id.newspublished_tv);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
