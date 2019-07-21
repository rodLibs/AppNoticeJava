package com.example.appnewsjava.data.database;

import androidx.room.Dao;
import androidx.room.*;
import com.example.appnewsjava.data.model.Item;
import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(Item news);

    @Update
    void update(Item news);

    @Delete
    void delete(Item news);

    @Query("SELECT * FROM item")
    List<Item> getAllNews();

    @Query("SELECT * FROM item where itemId = :id")
    Item getById(Long id);

    @Query("DELETE FROM item")
    void deleteTableNews();
}
