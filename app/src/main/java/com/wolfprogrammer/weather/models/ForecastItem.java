package com.wolfprogrammer.weather.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by thomas on 11/29/16.
 */

public class ForecastItem implements Serializable {

    private static final String DATE_KEY = "dt";
    private static final String TEMP_KEY = "temp";
    private static final String MIN_TEMP_KEY = "min";
    private static final String MAX_TEMP_KEY = "max";
    private static final String PRESSURE_KEY = "pressure";
    private static final String HUMIDITY_KEY = "humidity";
    private static final String CONDITIONS_KEY = "weather";
    private static final String WIND_SPEED_KEY = "speed";
    private static final String WIND_DEGREE_KEY = "deg";
    private static final String CLOUDS_KEY = "clouds";
    private static final String RAIN_KEY = "rain";

    private Date date;
    private Details weatherDetails;
    private ArrayList<Condition> conditions;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Details getWeatherDetails() {
        return weatherDetails;
    }

    public void setWeatherDetails(Details weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    public ForecastItem(JSONObject object) {
        try {
            if(object.has(DATE_KEY)){
                Long dateTime = object.getLong(DATE_KEY);
                date = new Date(dateTime * 1000);
            }
            weatherDetails = new Details();
            if(object.has(TEMP_KEY)){
                JSONObject tempObject = object.getJSONObject(TEMP_KEY);
                if(tempObject.has(MIN_TEMP_KEY)){
                    weatherDetails.setTemperatureMin(tempObject.getInt(MIN_TEMP_KEY));
                }
                if(tempObject.has(MAX_TEMP_KEY)){
                    weatherDetails.setTemperatureMax(tempObject.getInt(MAX_TEMP_KEY));
                }
            }
            if(object.has(PRESSURE_KEY)){
                weatherDetails.setPressure(object.getInt(PRESSURE_KEY));
            }
            if(object.has(HUMIDITY_KEY)){
                weatherDetails.setHumidity(object.getInt(HUMIDITY_KEY));
            }
            if(object.has(CONDITIONS_KEY)){
                JSONArray objects = object.getJSONArray(CONDITIONS_KEY);
                conditions = new ArrayList<>();
                for(int i = 0; i < objects.length(); i++) {
                    conditions.add(new Condition(objects.getJSONObject(i)));
                }
            }
            if(object.has(WIND_SPEED_KEY) && object.has(WIND_DEGREE_KEY)){
                Integer windSpeed = object.getInt(WIND_SPEED_KEY);
                Integer windDegree = object.getInt(WIND_DEGREE_KEY);
                weatherDetails.setWind(new Wind(windSpeed, windDegree));
            }
            if(object.has(CLOUDS_KEY)){
                weatherDetails.setClouds(object.getDouble(CLOUDS_KEY));
            }
            if(object.has(RAIN_KEY)){
                weatherDetails.setRain(object.getDouble(RAIN_KEY));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
