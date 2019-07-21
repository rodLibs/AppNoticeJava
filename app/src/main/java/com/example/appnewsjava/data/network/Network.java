package com.example.appnewsjava.data.network;

import android.content.Context;

import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Network {


    private Context context;
    private Long TIME_OUT = 5000L;
    private String URL = " https://g1.globo.com/dynamo/";


    public Network(Context context) {
        this.context = context;
    }


    public void getNews(String query_news, final CallbackNetwork cb) {
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS).build();

        Request request = new Request.Builder()
                .url(URL + query_news)
                .get()
                .build();

        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String value = response.body().string();
                int code = response.code();
                if (response.isSuccessful()) {
                    cb.onSucess(code, value);
                } else {
                    cb.onError(code, value);
                }
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (!call.isCanceled()) {
                    cb.onError(0,e.getMessage());
                }
            }
        });
    }
}
