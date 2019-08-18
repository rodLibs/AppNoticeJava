package com.example.appnewsjava.mvp.details_news;

import android.content.Context;
import com.example.appnewsjava.data.database.entity.Item;


public class DetailsNewsPresenter implements DetailsNewsInterface.Presenter{


    private DetailsNewsInterface.View mView;
    private DetailsNewsInterface.Model model;


    DetailsNewsPresenter(DetailsNewsInterface.View mView, Context context) {
        this.mView = mView;
        this.model = new DetailsNewsModel(this, context);
    }


    @Override
    public void insertNewsDatabase(final Item news) {
        model.requestInsertNewsDatabase(news);
    }
    @Override
    public void returnInsertNewsDatabase(Item news) {
        mView.showResult(news);
    }



    @Override
    public void removeNewsDatabase(final Item news) {
        model.requestRemoveNewsDatabase(news);
    }
    @Override
    public void returnRemoveNewsDatabase(Item news) {
        mView.showResult(news);
    }
}