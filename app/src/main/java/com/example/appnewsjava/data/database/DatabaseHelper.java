package com.example.appnewsjava.data.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.appnewsjava.data.model.Item;



@Database(entities = {Item.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    public abstract ItemDao itemDAO();
    private static DatabaseHelper INSTANCE;

    public static DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized(DatabaseHelper.class) {
                INSTANCE = Room.databaseBuilder(
                        context, DatabaseHelper.class,
                        "neovero_database.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
