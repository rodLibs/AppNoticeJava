package com.example.appnewsjava.mvp.list_news;

import com.example.appnewsjava.data.database.entity.Item;
import java.util.ArrayList;


public interface ListNewsInterface {

    interface Presenter{
        void getNews(String query_news);
        void returnListNews(ArrayList<Item> listNews);
        void returnMessageErro(String message);

    }

    interface View{
        void showNews(ArrayList<Item> listNews);
        void showMessageErro(String message);
    }

    interface Model{
        void requestGetNews(String query_news);
    }
}
