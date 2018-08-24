package com.example.home.weatherstatistics.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.home.weatherstatistics.R;
import com.example.home.weatherstatistics.adapter.WeatherAdapter;
import com.example.home.weatherstatistics.data.DataBase;
import com.example.home.weatherstatistics.data.PointDao;
import com.example.home.weatherstatistics.model.MapPoint;
import com.example.home.weatherstatistics.model.Weather;
import com.example.home.weatherstatistics.network.Retrofit;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WeatherActivity extends AppCompatActivity {

    private static final String UNITS = "metric";
    private static final String KEY = "point";
    private static final String API_KEY = "c3c6989887ba072ba442425c9d10b8a4";

    @BindView(R.id.weather_recycler_view)
    RecyclerView weatherRecycler;

    private WeatherAdapter adapter;
    private MapPoint mapPoint;
    private PointDao pointDao;

    public static void start(Context context, MapPoint point) {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra(KEY, point);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        mapPoint = (MapPoint) getIntent().getSerializableExtra(KEY);
        pointDao = DataBase.getINSTANCE(getApplication()).getPointDao();

        adapter = new WeatherAdapter(new ArrayList<>(), this);
        weatherRecycler.setLayoutManager(new LinearLayoutManager(this));
        weatherRecycler.setAdapter(adapter);

        pointDao.getAllWeather(mapPoint.getPointId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weathers -> {
                    adapter.setWeathers(weathers);
                    adapter.notifyDataSetChanged();
                });
    }

    @OnClick(R.id.get_weather)
    void loadWeather() {
        if (mapPoint != null) {
            Retrofit.getWeather(mapPoint.getLatitude(), mapPoint.getLongitude(), UNITS, API_KEY, new Callback<Weather>() {
                @Override
                public void success(Weather weather, Response response) {
                    weather.setPointId(mapPoint.getPointId());
                    new Thread(() -> pointDao.saveWeather(weather)).start();
                }
                @Override
                public void failure(RetrofitError error) {
                }
            });
        }
    }
}