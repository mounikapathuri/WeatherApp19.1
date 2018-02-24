package com.example.mounikapathuri.weatherapp191;

import org.json.JSONException;

/**
 * Created by mounikapathuri on 24-02-2018.
 */

public interface DataListener {
    void getData(String data) throws JSONException;
}
