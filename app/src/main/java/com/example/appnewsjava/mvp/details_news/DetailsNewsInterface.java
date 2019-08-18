package com.example.appnewsjava.mvp.details_news;

import com.example.appnewsjava.data.database.entity.Item;


public interface DetailsNewsInterface {

    interface Presenter{
        void insertNewsDatabase(Item news);
        void removeNewsDatabase(Item news);

        void returnInsertNewsDatabase(Item news);
        void returnRemoveNewsDatabase(Item news);
    }

    interface View{
        void showResult(Item news);
    }

    interface Model{
        void requestInsertNewsDatabase(Item news);
        void requestRemoveNewsDatabase(Item news);
    }
}
