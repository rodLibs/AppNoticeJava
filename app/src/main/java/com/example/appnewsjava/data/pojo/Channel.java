package com.example.appnewsjava.data.pojo;

import com.example.appnewsjava.data.database.entity.Item;

import java.util.ArrayList;

public class Channel {

    private ArrayList<Item> item;

    public Channel(ArrayList<Item> item) {
        this.item = item;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }
}
