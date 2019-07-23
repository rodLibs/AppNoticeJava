package com.example.appnewsjava.mvp.list_category;

import com.example.appnewsjava.data.model.Category;
import java.util.ArrayList;


public interface ListCategoryInterface {

    interface Presenter{
        void getListCategory();
    }

    interface View{
        void showListCategory(ArrayList<Category> listCategory);
    }
}
