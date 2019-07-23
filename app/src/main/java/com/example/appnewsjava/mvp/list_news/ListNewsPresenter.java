package com.example.appnewsjava.mvp.list_news;

import android.content.Context;
import android.os.AsyncTask;

import com.example.appnewsjava.R;
import com.example.appnewsjava.data.database.DatabaseHelper;
import com.example.appnewsjava.data.model.Item;
import com.example.appnewsjava.data.network.CallbackNetwork;
import com.example.appnewsjava.data.network.Network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;


public class ListNewsPresenter implements ListNewsInterface.Presenter {


    private ListNewsInterface.View mView;
    private Context context;
    private DatabaseHelper databaseHelper;


    public ListNewsPresenter(ListNewsInterface.View mView, Context context) {
        this.mView = mView;
        this.context = context;
        databaseHelper = DatabaseHelper.getInstance(context);
    }


    @Override
    public void getNews(String query_news) {
        if (query_news.equals("favorites")){
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    List<Item> listNews = databaseHelper.itemDAO().getAllNews();
                    mView.showNews(new ArrayList(listNews));
                }
            });
        }else {
            Network net = new Network(context);
            net.getNews(query_news, new CallbackNetwork() {


                @Override
                public void onSucess(int code, String response) {
                    if (code == 200) {
                        try {
                            parseXmlToJson(response);
                        } catch (JSONException e) {
                            mView.showMessageErro(context.getString(R.string.msg_error));
                        }
                    } else {
                        mView.showMessageErro(""+code);
                    }
                }
                @Override
                public void onError(int code, String error) {
                    mView.showMessageErro(error+" "+code);
                }
            });
        }
    }



    private void parseXmlToJson(String value) throws JSONException {
        JSONObject xmlToJson = new XmlToJson.Builder(value).build().toJson();
        JSONArray jsonArray = xmlToJson.getJSONObject("rss").getJSONObject("channel").getJSONArray("item");
        ArrayList list = new ArrayList<Item>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonItem = jsonArray.getJSONObject(i);

            String image = null;
            if (jsonItem.has("media:content")){
                image = jsonItem.getJSONObject("media:content").getString("url");
            }
            list.add(new Item(0,jsonItem.getString("title"),jsonItem.getString("link"),jsonItem.getString("description"),
                    jsonItem.getString("category"),jsonItem.getString("pubDate"),image));
        }
        mView.showNews(list);
    }
}











