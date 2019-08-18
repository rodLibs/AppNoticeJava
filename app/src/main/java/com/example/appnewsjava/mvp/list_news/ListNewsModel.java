package com.example.appnewsjava.mvp.list_news;

import android.content.Context;
import android.os.AsyncTask;
import com.example.appnewsjava.R;
import com.example.appnewsjava.data.database.database.DatabaseHelper;
import com.example.appnewsjava.data.database.entity.Item;
import com.example.appnewsjava.data.network.CallbackNetwork;
import com.example.appnewsjava.data.network.Network;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class ListNewsModel implements ListNewsInterface.Model {


    private Context context;
    private ListNewsInterface.Presenter mPresenter;
    private DatabaseHelper databaseHelper;


    public ListNewsModel(ListNewsInterface.Presenter mPresenter, Context context) {
        this.context = context;
        this.mPresenter = mPresenter;
        databaseHelper = DatabaseHelper.getInstance(context);
    }



    @Override
    public void requestGetNews(String query_news) {
        if (query_news.equals("favorites")){
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    List<Item> listNews = databaseHelper.itemDAO().getAllNews();
                    mPresenter.returnListNews(new ArrayList(listNews));
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
                            mPresenter.returnMessageErro(context.getString(R.string.msg_error));
                        }
                    } else {
                        mPresenter.returnMessageErro(""+code);
                    }
                }
                @Override
                public void onError(int code, String error) {
                    mPresenter.returnMessageErro(error+" "+code);
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
        mPresenter.returnListNews(list);
    }
}