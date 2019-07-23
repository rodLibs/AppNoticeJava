package com.example.appnewsjava.mvp.list_news;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.appnewsjava.R;
import com.example.appnewsjava.data.model.Category;
import com.example.appnewsjava.data.model.Item;
import com.example.appnewsjava.mvp.adapters.AdapterRecycle;
import com.example.appnewsjava.mvp.details_news.DetailNewsActivity;
import com.example.appnewsjava.util.Constants;

import java.util.ArrayList;

public class ListNewsActivity extends AppCompatActivity implements ListNewsInterface.View, ItemClickListener {


    private ListNewsInterface.Presenter mPresenter = null;
    private AdapterRecycle adapterRecycle = null;
    private boolean isFavorite = false;
    private Category category = null;

    private RecyclerView recycleView = null;
    private ProgressBar progress = null;
    private TextView txtCategory = null;
    private SwipeRefreshLayout swipeRefresh = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        getSupportActionBar().hide();


        recycleView = findViewById(R.id.recycleView);
        progress = findViewById(R.id.progress);
        txtCategory = findViewById(R.id.txtCategory);
        swipeRefresh = findViewById(R.id.swipeRefresh);


        showProgress();
        category = (Category) getIntent().getSerializableExtra("category");
        mPresenter = new ListNewsPresenter(this, this);
        mPresenter.getNews(category.getUrl());
        txtCategory.setText(category.getName());
    }


    private void showProgress() {
        progress.setVisibility(View.VISIBLE);
        progress.setIndeterminate(true);
    }

    private void hideProgress() {
        progress.setVisibility(View.INVISIBLE);
    }


    public void btFavorite(View v) {
        isFavorite = true;
        mPresenter.getNews(Constants.FAVORITE);
    }


    @Override
    public void showNews(final ArrayList<Item> listNews) {
        runOnUiThread(new Runnable() {
            @SuppressLint("WrongConstant")
            @Override
            public void run() {
                adapterRecycle = new AdapterRecycle(ListNewsActivity.this, listNews, ListNewsActivity.this);
                recycleView.setAdapter(adapterRecycle);
                recycleView.setLayoutManager(new LinearLayoutManager(ListNewsActivity.this, LinearLayoutManager.VERTICAL, false));
                swipeRefresh.setRefreshing(false);
                swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        isFavorite = false;
                        mPresenter.getNews(category.getUrl());
                    }
                });
                if (isFavorite) {
                    txtCategory.setText(getString(R.string.news_favorites));
                } else {
                    txtCategory.setText(category.getName());
                }
                hideProgress();
            }
        });
    }


    @Override
    public void onItemClick(int position, Item news) {
        startActivityForResult(new Intent(ListNewsActivity.this, DetailNewsActivity.class).putExtra("news", news), 100);
    }


    @Override
    public void showMessageErro(String message) {
        hideProgress();
        setDialog(message);
    }


    private void setDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alert = new AlertDialog.Builder(ListNewsActivity.this);
                alert.setMessage(message);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            if (isFavorite) {
                mPresenter.getNews(Constants.FAVORITE);
            }
        }
    }
}