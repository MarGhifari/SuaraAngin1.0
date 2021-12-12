package com.komc.suaraanginrev.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.komc.suaraanginrev.R;
import com.komc.suaraanginrev.activities.MainActivity;
//import com.komc.suaraanginrev.activities.UrlSample;
import com.komc.suaraanginrev.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    Context context;
    List newsList;

    RecyclerViewClickListener listener;

    public NewsAdapter(Context context, List newsList, RecyclerViewClickListener listener) {
        this.context = context;
        this.newsList = newsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_item, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsModel news = (NewsModel) newsList.get(position);

        holder.title.setText(news.getTitle());
        holder.source.setText(news.getSource());
        Picasso.get().load(news.getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView title, author, source, content;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.newsTitle);
            source = itemView.findViewById(R.id.newsSource);
            imageView = itemView.findViewById(R.id.newsImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
}
