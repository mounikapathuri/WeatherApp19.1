package com.example.mounikapathuri.weatherapp191;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by mounikapathuri on 24-02-2018.
 */

class DataProcessing extends AsyncTask<Void,Void,String> {

    private DataListener dataListener;
    private Context mcontext;
    private String URL;

    public DataProcessing(Context context, String url, DataListener listener) {

        dataListener=listener;
        mcontext = context;
        URL = url;
    }


    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(120, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(120, TimeUnit.SECONDS);

        Request request = new DownloadManager.Request.Builder().url(URL).build();
        String responseData = null;
        try {
            Reszponse response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                responseData = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }


    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        try {
            dataListener.getData(aVoid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
