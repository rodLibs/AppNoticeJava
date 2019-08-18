package com.example.appnewsjava.mvp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appnewsjava.R;
import com.example.appnewsjava.data.database.entity.Item;
import com.example.appnewsjava.mvp.list_news.ItemClickListener;
import java.util.ArrayList;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.MyViewHolder>{


    private Context context;
    private ArrayList<Item> listNews;
    private ItemClickListener listener;


    public AdapterRecycle(Context context, ArrayList<Item> listNews, ItemClickListener listener) {
        this.context = context;
        this.listNews = listNews;
        this.listener = listener;
    }


    @NonNull
    @Override
    public AdapterRecycle.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_news, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycle.MyViewHolder viewHolder, final int position) {
        final Item news = listNews.get(position);
        viewHolder.txtTitle.setText(news.getTitle());
        viewHolder.txtDate.setText(news.getCategory()+" - "+news.getPubDate().replace(" -0000",""));

        if (news.getImage() != null) {
            Glide.with(context).load(news.getImage()).centerCrop().placeholder(R.color.colorGrayBackgroundDarkFont).into(viewHolder.img);
        }else{
            Glide.with(context).load("").placeholder(R.color.colorGrayBackgroundDarkFont).into(viewHolder.img);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position, news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView txtTitle;
        final TextView txtDate;
        final ImageView img;


        public MyViewHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtDate = view.findViewById(R.id.txtDate);
            img = view.findViewById(R.id.img);
        }
    }
}