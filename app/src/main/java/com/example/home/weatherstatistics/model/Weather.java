package com.example.home.weatherstatistics.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.home.weatherstatistics.util.WeatherConverter;

@Entity
public class Weather {

    @PrimaryKey(autoGenerate = true)
    public int weatherId;
    @TypeConverters(WeatherConverter.class)
    private Main main;
    private int pointId;

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public int getPointId() {
        return pointId;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
