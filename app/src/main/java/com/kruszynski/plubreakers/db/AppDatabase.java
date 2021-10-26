package com.kruszynski.plubreakers.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kruszynski.plubreakers.codefinder.model.Product;
import com.kruszynski.plubreakers.codetest.model.ProductTest;
import com.kruszynski.plubreakers.db.dao.ProductDao;
import com.kruszynski.plubreakers.db.dao.ProductTestDao;

@Database(entities = {ProductTest.class, Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private final static String DATA_BASE_NAME = "plu_breakers.db";
    private static AppDatabase instance = null;

    public abstract ProductTestDao productTestDao();
    public abstract ProductDao productDao();

    public static AppDatabase instance(Context context) {
        if (instance == null) {
            return Room.databaseBuilder(context, AppDatabase.class, DATA_BASE_NAME).createFromAsset("database/plu_breakers.db").allowMainThreadQueries().build();
        }
        return instance;
    }
}
