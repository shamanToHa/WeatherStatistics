package com.example.home.weatherstatistics.network;

import com.example.home.weatherstatistics.model.Weather;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class Retrofit {
    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/";
    private static ApiInterface apiInterface;

    static {
        initialize();
    }

    interface ApiInterface {
        @GET("/weather")
        void getWeather(@Query("lat") double latitude,
                        @Query("lon") double longitude,
                        @Query("units") String units,
                        @Query("appid") String appid,
                        Callback<Weather> callback);
    }

    private static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiInterface = restAdapter.create(ApiInterface.class);
    }

    public static void getWeather(double latitude, double longitude, String units, String appid, Callback<Weather> callback) {
        apiInterface.getWeather(latitude, longitude, units, appid, callback);
    }
}
