package com.example.appnewsjava.data.network;

public interface CallbackNetwork {

    void onSucess(int code, String response);
    void onError(int code, String error);
}
