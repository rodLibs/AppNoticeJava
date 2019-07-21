package com.example.appnewsjava.data.model;

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
