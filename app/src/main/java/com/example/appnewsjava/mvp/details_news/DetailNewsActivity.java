package com.example.appnewsjava.mvp.details_news;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appnewsjava.R;
import com.example.appnewsjava.data.model.Item;

public class DetailNewsActivity extends AppCompatActivity implements DetailsNewsInterface.View  {


    private Item news;
    private DetailsNewsInterface.Presenter mPresenter;
    private boolean hasAltered = false;

    private ImageView imgFavorite;
    private ImageView img;
    private TextView txtDescription;
    private TextView txtTitle;
    private TextView txtDate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        imgFavorite = findViewById(R.id.imgFavorite);
        img = findViewById(R.id.img);
        txtDescription = findViewById(R.id.txtDescription);
        txtTitle = findViewById(R.id.txtTitle);
        txtDate = findViewById(R.id.txtDate);

        mPresenter = new DetailsNewsPresenter(DetailNewsActivity.this,DetailNewsActivity.this);
        news = (Item)getIntent().getSerializableExtra("news");
        setDataInComponents();
    }



    private void setDataInComponents(){
        if (news != null){
            if (news.getImage() != null) {
                Glide.with(DetailNewsActivity.this).load(news.getImage()).centerCrop().placeholder(R.color.colorGrayBackgroundDarkFont).into(img);
                String descr = news.getDescription().substring(0, news.getDescription().indexOf(">") - 1);
                txtDescription.setText(news.getDescription().replace(descr,"").replace("/><br />",""));
            }else{
                txtDescription.setText(news.getDescription());
            }
            txtTitle.setText(news.getTitle());
            txtDate.setText(news.getCategory() +" - "+ news.getPubDate().replace(" -0000",""));

            if (news.getId() != 000){
                imgFavorite.setImageResource(R.drawable.ic_favorite);
            }
        }
    }



    public void btShare(View v){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,news.getTitle() +"\n"+ news.getLink());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }



    public void btFavorite(View v){
        if (news.getId() != 000){
            mPresenter.removeNewsDatabase(news);
        } else{
            mPresenter.insertNewsDatabase(news);
        }
    }


    @Override
    public void showResult(final Item news) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mPresenter != null){
                    hasAltered = true;
                    if (news.getId() != 000){
                        imgFavorite.setImageResource(R.drawable.ic_favorite);
                    } else{
                        imgFavorite.setImageResource(R.drawable.ic_favorite_2);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (hasAltered){
            setResult(Activity.RESULT_OK);
        }
        finish();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }
}
