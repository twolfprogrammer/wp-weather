package com.wolfprogrammer.weather.utilities;

/**
 * Created by thomas on 11/30/16.
 */

public class CardinalDirectionsHelper {
    public static String getString(Integer degrees) {
        if((degrees > 337 && degrees < 361) || (degrees >= 0 && degrees < 23)) {
            return "N";
        }
        else if(degrees > 23 && degrees < 68) {
            return "NE";
        }
        else if(degrees > 68 && degrees < 113) {
            return "E";
        }
        else if(degrees > 113 && degrees < 158) {
            return "SE";
        }
        else if(degrees > 158 && degrees < 203) {
            return "S";
        }
        else if(degrees > 203 && degrees < 248) {
            return "SW";
        }
        else if(degrees > 248 && degrees < 293) {
            return "W";
        }
        else if(degrees > 293 && degrees < 338) {
            return "NW";
        }
        else {
            throw new IndexOutOfBoundsException("Degrees out of bounds, must be between 0 - 360");
        }
    }
}
