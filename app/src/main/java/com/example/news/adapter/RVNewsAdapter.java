package com.example.news.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.model.NewsModel;

import java.util.ArrayList;

public class RVNewsAdapter extends RecyclerView.Adapter<RVNewsAdapter.NewsViewHolder> {

    private ArrayList<NewsModel> list = new ArrayList<>();
    private Context context;
    private OnItemClickListener listener;

    public RVNewsAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel item = list.get(position);
        holder.tv_title.setText(item.getTitle());
        holder.tv_content.setText(item.getDescription());
        holder.tv_author.setText(item.getAuthor());
        holder.tv_time.setText(item.getPublishedAt());
        Glide.with(context).load(item.getUrlToImage()).into(holder.iv_pic);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item.getUrl()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<NewsModel> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_content, tv_author, tv_time;
        private ImageView iv_pic;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.item_tv_title);
            tv_content = itemView.findViewById(R.id.item_tv_content);
            tv_author = itemView.findViewById(R.id.item_tv_author);
            tv_time = itemView.findViewById(R.id.item_tv_time);
            iv_pic = itemView.findViewById(R.id.item_iv_pic);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(String url);
    }
}