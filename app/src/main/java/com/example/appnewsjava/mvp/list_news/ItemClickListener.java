package com.example.appnewsjava.mvp.list_news;


import com.example.appnewsjava.data.model.Item;

public interface ItemClickListener {

     void onItemClick(int position, Item news);
}
