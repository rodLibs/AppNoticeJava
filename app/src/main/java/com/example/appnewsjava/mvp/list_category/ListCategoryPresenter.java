package com.example.appnewsjava.mvp.list_category;

import com.example.appnewsjava.data.pojo.Category;

import java.util.ArrayList;


public class ListCategoryPresenter implements ListCategoryInterface.Presenter {


    private ListCategoryInterface.View mView;
    private ListCategoryInterface.Model model;


    ListCategoryPresenter(ListCategoryInterface.View mView) {
        this.mView = mView;
        model = new ListCategoryModel(this);
    }


    @Override
    public void getListCategory() {
        model.requestListCategory();
    }

    @Override
    public void returnListCategory(ArrayList<Category> listCategory) {
        mView.showListCategory(listCategory);
    }
}
