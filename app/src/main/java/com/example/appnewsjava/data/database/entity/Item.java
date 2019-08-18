package com.example.appnewsjava.data.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "item")
public class Item implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "itemId")
    private long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "link")
    private String link;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "date")
    private String pubDate;

    @ColumnInfo(name = "image")
    private String image;


    public Item(long id, String title, String link, String description, String category, String pubDate, String image) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.category = category;
        this.pubDate = pubDate;
        this.image = image;
    }

    public Item() { }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
