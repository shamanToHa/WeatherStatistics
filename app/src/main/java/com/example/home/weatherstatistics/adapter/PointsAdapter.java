package com.example.home.weatherstatistics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home.weatherstatistics.R;
import com.example.home.weatherstatistics.ui.WeatherActivity;
import com.example.home.weatherstatistics.model.MapPoint;

import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.ViewHolder> {

    private List<MapPoint> points;
    private Context context;

    public PointsAdapter(List<MapPoint> points, Context context) {
        this.points = points;
        this.context = context;
    }

    public void setPoints(List<MapPoint> points) {
        this.points = points;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_point, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.longitude.setText(String.valueOf(points.get(position).getLongitude()));
        holder.latitude.setText(String.valueOf(points.get(position).getLatitude()));
    }

    @Override
    public int getItemCount() {
        return points.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView latitude;
        private TextView longitude;

        public ViewHolder(View itemView) {
            super(itemView);
            latitude = itemView.findViewById(R.id.point_latitude);
            longitude = itemView.findViewById(R.id.point_longitude);
            itemView.setOnClickListener(v -> WeatherActivity.start(context, points.get(getAdapterPosition())));
        }
    }
}
