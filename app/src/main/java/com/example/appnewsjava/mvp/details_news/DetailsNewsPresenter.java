package com.example.appnewsjava.mvp.details_news;

import android.content.Context;
import android.os.AsyncTask;

import com.example.appnewsjava.data.database.DatabaseHelper;
import com.example.appnewsjava.data.model.Item;

import java.util.Date;

public class DetailsNewsPresenter implements DetailsNewsInterface.Presenter{


    private DatabaseHelper databaseHelper;
    private DetailsNewsInterface.View mView;
    private Context context;


    public DetailsNewsPresenter(DetailsNewsInterface.View mView, Context context) {
        this.mView = mView;
        this.context = context;
         this.databaseHelper = DatabaseHelper.getInstance(context);
    }


    @Override
    public void insertNewsDatabase(final Item news) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                news.setId(new Date().getTime());
                databaseHelper.itemDAO().insert(news);
                mView.showResult(news);
            }
        });
    }


    @Override
    public void removeNewsDatabase(final Item news) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                databaseHelper.itemDAO().delete(news);
                news.setId(000);
                mView.showResult(news);
            }
        });
    }
}