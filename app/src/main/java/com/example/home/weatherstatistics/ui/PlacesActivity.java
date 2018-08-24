package com.example.home.weatherstatistics.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.home.weatherstatistics.R;
import com.example.home.weatherstatistics.adapter.PointsAdapter;
import com.example.home.weatherstatistics.data.DataBase;
import com.example.home.weatherstatistics.data.PointDao;
import com.example.home.weatherstatistics.model.MapPoint;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PlacesActivity extends AppCompatActivity {

    private static final String KEY = "point";
    private PointsAdapter adapter;
    private PointDao pointDao;

    @BindView(R.id.points_recycler_view)
    RecyclerView pointsRecycler;


    public static void start(Context context, MapPoint point) {
        Intent intent = new Intent(context, PlacesActivity.class);
        intent.putExtra(KEY, point);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        ButterKnife.bind(this);

        adapter = new PointsAdapter(new ArrayList<>(), this);
        pointsRecycler.setAdapter(adapter);
        pointsRecycler.setLayoutManager(new LinearLayoutManager(this));

        pointDao = DataBase.getINSTANCE(getApplication()).getPointDao();

        MapPoint point = (MapPoint) getIntent().getSerializableExtra(KEY);
        if (point != null) {
            new Thread(() -> pointDao.savePoint(point)).start();
        }

        pointDao.getAllPoints()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mapPoints -> adapter.setPoints(mapPoints));

        findViewById(R.id.add_place).setOnClickListener(v -> MapsActivity.start(this));
    }
}
