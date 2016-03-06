package com.example.semasuka.nextweather.service;

import com.example.semasuka.nextweather.data.Channel;

/**
 * Created by semasuka on 06/03/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
