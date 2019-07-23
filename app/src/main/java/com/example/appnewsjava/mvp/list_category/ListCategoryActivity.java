package com.example.appnewsjava.mvp.list_category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appnewsjava.R;
import com.example.appnewsjava.data.model.Category;
import com.example.appnewsjava.mvp.adapters.AdapterRecycleCategory;
import com.example.appnewsjava.mvp.list_news.ListNewsActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListCategoryActivity extends AppCompatActivity implements ListCategoryInterface.View, ItemClickCategoryListener{


    private ListCategoryInterface.Presenter mPresenter;
    private AdapterRecycleCategory adapterRecycleCategory;
    private SimpleDateFormat dfDate = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
    private RecyclerView recycleView;
    private TextView txtDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        getSupportActionBar().hide();

        recycleView = findViewById(R.id.recycleView);
        txtDate = findViewById(R.id.txtDate);

        mPresenter = new ListCategoryPresenter(ListCategoryActivity.this,ListCategoryActivity.this);
        mPresenter.getListCategory();
        txtDate.setText(dfDate.format(new Date()));
    }


    @SuppressLint("WrongConstant")
    @Override
    public void showListCategory(ArrayList<Category> listCategory) {
        adapterRecycleCategory = new AdapterRecycleCategory(ListCategoryActivity.this, listCategory,ListCategoryActivity.this);
        recycleView.setAdapter(adapterRecycleCategory);
        recycleView.setLayoutManager(new GridLayoutManager(ListCategoryActivity.this, 2, GridLayoutManager.VERTICAL, false));
    }


    @Override
    public void onItemClick(int position, Category category) {
        startActivity(new Intent(ListCategoryActivity.this, ListNewsActivity.class).putExtra("category",category));
    }
}
