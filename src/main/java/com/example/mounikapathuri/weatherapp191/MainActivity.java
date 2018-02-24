package com.example.mounikapathuri.weatherapp191;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements DataListener {
    private String URL = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";
    private ListView mListView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mListView = (ListView) findViewById(R.id.lvWeatherList);
        textView = (TextView) findViewById(R.id.tv);

        if (isConnectedToInternet()) {
            DataProcessing dataProcessing = new DataProcessing(this, URL, this);
            dataProcessing.execute();
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    //checking the internet connectivity for accessing data
    private boolean isConnectedToInternet() {
        boolean isInternetConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            isInternetConnected = true;
        }
        return isInternetConnected;
    }

    @Override
    //pulling data
    public void getData(String data) throws JSONException {
        JSONObject pareJsonObject = new JSONObject(data);
        try {

            JSONArray jsonArray = pareJsonObject.getJSONArray("weather");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String mMain = jsonObject.getString("main");
                String mDesc = jsonObject.getString("description");
                String mWeather = "Main :- " + mMain + "\nDescription :- " + mDesc + " ";
                textView.setText(mWeather);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
