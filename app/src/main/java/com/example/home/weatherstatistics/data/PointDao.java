package com.example.home.weatherstatistics.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.home.weatherstatistics.model.MapPoint;
import com.example.home.weatherstatistics.model.Weather;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PointDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveWeather(Weather... weathers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePoint(MapPoint... mapPoints);

    @Query("SELECT * FROM weather WHERE pointId  = :noteId ")
    Flowable<List<Weather>> getAllWeather(int noteId);

    @Query("SELECT * FROM mappoint")
    Flowable<List<MapPoint>> getAllPoints();

    @Query("DELETE FROM mappoint")
    void deleteAllPoints();
}
