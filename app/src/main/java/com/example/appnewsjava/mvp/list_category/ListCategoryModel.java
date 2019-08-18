package com.example.appnewsjava.mvp.list_category;

import com.example.appnewsjava.data.pojo.Category;
import com.example.appnewsjava.util.Constants;
import java.util.ArrayList;

public class ListCategoryModel implements ListCategoryInterface.Model {


    private ListCategoryInterface.Presenter mPresenter;


    ListCategoryModel(ListCategoryInterface.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }



    @Override
    public void requestListCategory() {
        ArrayList<Category> listCategory = new  ArrayList();
        listCategory.add(new Category("FAVORITAS", Constants.FAVORITE));
        listCategory.add(new Category("TODAS",Constants.QUERY_NEWS_ALL));
        listCategory.add(new Category("MUNDO",Constants.QUERY_NEWS_WORLD));
        listCategory.add(new Category("BRASIL",Constants.QUERY_NEWS_BRAZIL));
        listCategory.add(new Category("CARROS",Constants.QUERY_NEWS_CARS));
        listCategory.add(new Category("CIÊNCIA E SAÚDE",Constants.QUERY_NEWS_SCIENCE_HEALTH));
        listCategory.add(new Category("ECONOMIA",Constants.QUERY_NEWS_ECONOMY));
        listCategory.add(new Category("EDUCAÇÃO",Constants.QUERY_NEWS_EDUCATION));
        listCategory.add(new Category("NATUREZA",Constants.QUERY_NEWS_NATURE));
        listCategory.add(new Category("POLITICA",Constants.QUERY_NEWS_POLITICALLY));
        listCategory.add(new Category("TECNOLOGIA",Constants.QUERY_NEWS_TECH));
        listCategory.add(new Category("TURISMO",Constants.QUERY_NEWS_TOURISM));
        mPresenter.returnListCategory(listCategory);
    }
}
