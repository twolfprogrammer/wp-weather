package com.wolfprogrammer.weather.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by thomas on 11/29/16.
 */

public class Coordinate implements Serializable {
    private static final String LONGITUDE_KEY = "lon";
    private static final String LATITUDE_KEY = "lat";

    private Double longitude;
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Coordinate(JSONObject object) {
        try {
            if(object.has(LONGITUDE_KEY)){
                longitude = object.getDouble(LONGITUDE_KEY);
            }
            if(object.has(LATITUDE_KEY)){
                latitude = object.getDouble(LATITUDE_KEY);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
