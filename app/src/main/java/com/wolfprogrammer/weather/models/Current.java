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

public class Current implements Serializable {

    private static final String ID_KEY = "id";
    private static final String DATE_KEY = "dt";
    private static final String COORDINATE_KEY = "coord";
    private static final String NAME_KEY = "name";
    private static final String BASE_KEY = "base";
    private static final String MAIN_WEATHER_KEY = "main";
    private static final String VISIBILITY_KEY = "visibility";
    private static final String TEMPERATURE_KEY = "temp";
    private static final String PRESSURE_KEY = "pressure";
    private static final String HUMIDITY_KEY = "humidity";
    private static final String TEMPERATURE_MIN_KEY = "temp_min";
    private static final String TEMPERATURE_MAX_KEY = "temp_max";
    private static final String WIND_KEY = "wind";
    private static final String WEATHER_CONDITION_KEY = "weather";

    private String Name;
    private String Base;
    private Details weatherDetails;
    private Date date;
    private Long id;
    private Coordinate coordinate;
    private ArrayList<Condition> conditions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Details getWeatherDetails() {
        return weatherDetails;
    }

    public void setWeatherDetails(Details weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    public String getBase() {
        return Base;
    }

    public void setBase(String base) {
        Base = base;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Condition> getConditions() {
        if(conditions == null) {
            conditions = new ArrayList<>();
        }
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }

    public Current(JSONObject object) {
        try {
            if(object.has(NAME_KEY)){
                Name = object.getString(NAME_KEY);
            }
            if(object.has(BASE_KEY)){
                Base = object.getString(BASE_KEY);
            }
            weatherDetails = new Details();
            if(object.has(VISIBILITY_KEY)){
                weatherDetails.setVisibility(object.getInt(VISIBILITY_KEY));
            }
            if(object.has(MAIN_WEATHER_KEY)){
                JSONObject mainWeatherObject = object.getJSONObject(MAIN_WEATHER_KEY);
                if(mainWeatherObject.has(TEMPERATURE_KEY)){
                    weatherDetails.setTemperature(mainWeatherObject.getInt(TEMPERATURE_KEY));
                }
                if(mainWeatherObject.has(TEMPERATURE_MIN_KEY)){
                    weatherDetails.setTemperatureMin(mainWeatherObject.getInt(TEMPERATURE_MIN_KEY));
                }
                if(mainWeatherObject.has(TEMPERATURE_MAX_KEY)){
                    weatherDetails.setTemperatureMax(mainWeatherObject.getInt(TEMPERATURE_MAX_KEY));
                }
                if(mainWeatherObject.has(PRESSURE_KEY)){
                    weatherDetails.setPressure(mainWeatherObject.getInt(PRESSURE_KEY));
                }
                if(mainWeatherObject.has(HUMIDITY_KEY)){
                    weatherDetails.setHumidity(mainWeatherObject.getInt(HUMIDITY_KEY));
                }
            }
            if(object.has(DATE_KEY)){
                Long dateTime = object.getLong(DATE_KEY);
                date = new Date(dateTime * 1000);
            }
            if(object.has(ID_KEY)){
                id = object.getLong(ID_KEY);
            }
            if(object.has(COORDINATE_KEY)){
                coordinate = new Coordinate(object.getJSONObject(COORDINATE_KEY));
            }
            if(object.has(WEATHER_CONDITION_KEY)){
                JSONArray objects = object.getJSONArray(WEATHER_CONDITION_KEY);
                conditions = new ArrayList<>();
                for(int i = 0; i < objects.length(); i++) {
                    conditions.add(new Condition(objects.getJSONObject(i)));
                }
            }
            if(object.has(WIND_KEY)){
                weatherDetails.setWind(new Wind(object.getJSONObject(WIND_KEY)));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
