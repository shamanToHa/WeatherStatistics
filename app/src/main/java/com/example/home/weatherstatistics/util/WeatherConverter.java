package com.example.home.weatherstatistics.util;

import android.arch.persistence.room.TypeConverter;

import com.example.home.weatherstatistics.model.Main;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class WeatherConverter {

    @TypeConverter
    public String fromMainWeather(Main main) {
        return new Gson().toJson(main);
    }

    @TypeConverter
    public Main toMainWeather(String weatherString) {
        Type type = new TypeToken<Main>() {
        }.getType();
        return new Gson().fromJson(weatherString, type);
    }
}
