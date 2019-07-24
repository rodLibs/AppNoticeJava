package com.example.appnewsjava.mvp.details_news;

import com.example.appnewsjava.data.model.Item;

public interface DetailsNewsInterface {

    interface Presenter{
        void insertNewsDatabase(Item news);
        void removeNewsDatabase(Item news);
    }

    interface View{
        void showResult(Item news);
    }
}
