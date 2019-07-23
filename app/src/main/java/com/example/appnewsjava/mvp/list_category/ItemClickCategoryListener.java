package com.example.appnewsjava.mvp.list_category;

import com.example.appnewsjava.data.model.Category;


public interface ItemClickCategoryListener {
    void onItemClick(int position, Category category);
}
