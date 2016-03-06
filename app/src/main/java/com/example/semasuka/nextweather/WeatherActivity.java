package com.example.semasuka.nextweather;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semasuka.nextweather.data.Channel;
import com.example.semasuka.nextweather.data.Item;
import com.example.semasuka.nextweather.service.WeatherServiceCallback;
import com.example.semasuka.nextweather.service.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        weatherIconImageView=(ImageView)findViewById(R.id.weatherIconImageView);
        temperatureTextView=(TextView)findViewById(R.id.temperatureTextView);
        conditionTextView=(TextView)findViewById(R.id.conditionTextView);
        locationTextView=(TextView)findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("kigali,RW");

    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/weather" + item.getCondition().getCode(), null, getPackageName());



        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable= getResources().getDrawable(resourceId);
        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());


        locationTextView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();

    }
}
