package com.example.home.weatherstatistics.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home.weatherstatistics.R;
import com.example.home.weatherstatistics.model.Weather;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<Weather> weathers;
    private Context context;

    public WeatherAdapter(List<Weather> weathers, Context context) {
        this.weathers = weathers;
        this.context = context;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.temperature.setText(String.valueOf(weathers.get(position).getMain().getTemp()));
        holder.pressure.setText(String.valueOf(weathers.get(position).getMain().getPressure()));
        holder.humidity.setText(String.valueOf(weathers.get(position).getMain().getHumidity()));
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView temperature;
        private TextView pressure;
        private TextView humidity;

        public ViewHolder(View itemView) {
            super(itemView);
            temperature = itemView.findViewById(R.id.item_temperature);
            pressure = itemView.findViewById(R.id.item_pressure);
            humidity = itemView.findViewById(R.id.item_humidity);
        }
    }
}
