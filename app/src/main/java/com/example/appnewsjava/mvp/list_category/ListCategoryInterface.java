package com.example.appnewsjava.mvp.list_category;

import com.example.appnewsjava.data.pojo.Category;
import java.util.ArrayList;


public interface ListCategoryInterface {

    interface Presenter{
        void getListCategory();
        void returnListCategory(ArrayList<Category> listCategory);
    }

    interface View{
        void showListCategory(ArrayList<Category> listCategory);
    }

    interface Model{
        void requestListCategory();
    }

}
