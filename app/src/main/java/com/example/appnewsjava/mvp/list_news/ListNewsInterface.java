package com.example.appnewsjava.mvp.list_news;

import com.example.appnewsjava.data.model.Item;
import java.util.ArrayList;


public interface ListNewsInterface {

    interface Presenter{
        void getNews(String query_news);
    }

    interface View{
        void showNews(ArrayList<Item> listNews);
        void showMessageErro(String message );
    }
}
