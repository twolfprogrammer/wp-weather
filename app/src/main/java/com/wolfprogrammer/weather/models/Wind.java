package com.wolfprogrammer.weather.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by thomas on 11/29/16.
 */

public class Wind implements Serializable {
    private static final String SPEED_KEY = "speed";
    private static final String DEGREE_KEY = "deg";

    private Integer speed;
    private Integer degree;

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Wind(Integer speed, Integer degree) {
        this.speed = speed;
        this.degree = degree;
    }

    public Wind(JSONObject object) {
        try {
            if(object.has(SPEED_KEY)){
                speed = object.getInt(SPEED_KEY);
            }
            if(object.has(DEGREE_KEY)){
                degree = object.getInt(DEGREE_KEY);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
