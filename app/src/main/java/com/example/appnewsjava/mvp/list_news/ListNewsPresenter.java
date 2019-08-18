package com.example.appnewsjava.mvp.list_news;

import android.content.Context;

import com.example.appnewsjava.data.database.entity.Item;

import java.util.ArrayList;


public class ListNewsPresenter implements ListNewsInterface.Presenter {


    private ListNewsInterface.View mView;
    private ListNewsInterface.Model model;



    ListNewsPresenter(ListNewsInterface.View mView, Context context) {
        this.mView = mView;
        this.model = new ListNewsModel(this,context);
    }


    @Override
    public void getNews(String query_news) {
        model.requestGetNews(query_news);
    }
    @Override
    public void returnListNews(ArrayList<Item> listNews) {
        mView.showNews(listNews);
    }


    @Override
    public void returnMessageErro(String message) {
        mView.showMessageErro(message);
    }
}











