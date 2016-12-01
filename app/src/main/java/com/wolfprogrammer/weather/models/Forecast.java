package com.wolfprogrammer.weather.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thomas on 11/29/16.
 */

public class Forecast implements Serializable {
    private static final String COUNT_KEY = "cnt";
    private static final String CITY_KEY = "city";
    private static final String LIST_KEY = "list";

    private Integer count;
    private City city;
    private ArrayList<ForecastItem> forecastList;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public ArrayList<ForecastItem> getForecastList() {
        if(forecastList == null) {
            forecastList = new ArrayList<>();
        }
        return forecastList;
    }

    public void setForecastList(ArrayList<ForecastItem> forecastList) {
        this.forecastList = forecastList;
    }

    public Forecast(JSONObject object) {
        try {
            if(object.has(COUNT_KEY)){
                count = object.getInt(COUNT_KEY);
            }
            if(object.has(CITY_KEY)){
                city = new City(object.getJSONObject(CITY_KEY));
            }
            if(object.has(LIST_KEY)){
                forecastList = new ArrayList<ForecastItem>();
                JSONArray jsonArray = object.getJSONArray(LIST_KEY);
                for (int i = 0; i < jsonArray.length(); i++) {
                    forecastList.add(new ForecastItem(jsonArray.getJSONObject(i)));
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
