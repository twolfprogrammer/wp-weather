package com.wolfprogrammer.weather.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by thomas on 11/29/16.
 */

public class City implements Serializable {
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String COORDINATE_KEY = "coord";
    private static final String COUNTRY_KEY = "country";

    private Long id;
    private String name;
    private Coordinate coordinate;
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public City(JSONObject object) {
        try {
            if(object.has(ID_KEY)){
                id = object.getLong(ID_KEY);
            }
            if(object.has(NAME_KEY)){
                name = object.getString(NAME_KEY);
            }
            if(object.has(COORDINATE_KEY)){
                coordinate = new Coordinate(object.getJSONObject(COORDINATE_KEY));
            }
            if(object.has(COUNTRY_KEY)){
                country = object.getString(COUNTRY_KEY);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
