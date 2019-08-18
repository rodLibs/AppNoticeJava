package com.example.appnewsjava.mvp.list_news;


import com.example.appnewsjava.data.database.entity.Item;

public interface ItemClickListener {

     void onItemClick(int position, Item news);
}
