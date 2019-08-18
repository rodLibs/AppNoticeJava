package com.example.appnewsjava.mvp.details_news;

import android.content.Context;
import android.os.AsyncTask;
import com.example.appnewsjava.data.database.database.DatabaseHelper;
import com.example.appnewsjava.data.database.entity.Item;
import java.util.Date;

public class DetailsNewsModel implements DetailsNewsInterface.Model {


    private DatabaseHelper databaseHelper;
    private DetailsNewsInterface.Presenter mPresenter;



    DetailsNewsModel(DetailsNewsInterface.Presenter mPresenter, Context context) {
        this.mPresenter = mPresenter;
        this.databaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public void requestInsertNewsDatabase(final Item news) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                news.setId(new Date().getTime());
                databaseHelper.itemDAO().insert(news);
                mPresenter.returnInsertNewsDatabase(news);
            }
        });
    }

    @Override
    public void requestRemoveNewsDatabase(final Item news) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                databaseHelper.itemDAO().delete(news);
                news.setId(000);
                mPresenter.returnRemoveNewsDatabase(news);
            }
        });
    }
}