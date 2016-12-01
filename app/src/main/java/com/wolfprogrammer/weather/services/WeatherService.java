package com.wolfprogrammer.weather.services;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by thomas on 11/29/16.
 */

public class WeatherService extends AsyncTask<Void, Void, String> {

    private static final String APP_ID = "5c53be67bf4abef46c700d32e5ba4d6b";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final String UNITS = "imperial";
    private static final String LOCATION = "Atlanta,ga";
    private static final String FORECAST_COUNT = "6";
    //TODO: make query parameters more dynamic
    private static final String CURRENT_WEATHER_URL =
            BASE_URL + "weather?q=" + LOCATION + "&units=" + UNITS + "&appid=" + APP_ID;
    private static final String FORECAST_WEATHER_URL =
            BASE_URL + "forecast/daily?q=" + LOCATION + "&units=" + UNITS
                     + "&cnt=" + FORECAST_COUNT + "&appid=" + APP_ID;

    public interface TaskListener {
        void onTaskCompleted(JSONObject jsonObject);
    }
    private TaskListener taskListener;
    private String urlString;

    public void getCurrentWeather(TaskListener listener){
        if(listener == null) return;
        urlString = CURRENT_WEATHER_URL;
        taskListener = listener;
        this.execute();
    }

    public void getWeatherForecast(TaskListener listener){
        if(listener == null) return;
        urlString = FORECAST_WEATHER_URL;
        taskListener = listener;
        this.execute();
    }

    protected String doInBackground(Void... params) {
        return RequestService.getJson(urlString);
    }

    protected void onPostExecute(String json) {
        if(json != null) {
            Log.i("INFO", json);
            try {
                JSONObject jsonObject = (JSONObject) new JSONTokener(json).nextValue();
                taskListener.onTaskCompleted(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
