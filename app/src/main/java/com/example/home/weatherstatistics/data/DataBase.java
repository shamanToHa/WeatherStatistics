package com.example.home.weatherstatistics.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.home.weatherstatistics.model.MapPoint;
import com.example.home.weatherstatistics.model.Weather;

@Database(entities = {MapPoint.class, Weather.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {

    private static DataBase INSTANCE;
    private final static Object lock = new Object();

    public abstract PointDao getPointDao();

    public static DataBase getINSTANCE(Context context) {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBase.class, "Weather.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
