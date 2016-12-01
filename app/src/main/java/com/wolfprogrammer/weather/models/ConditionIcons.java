package com.wolfprogrammer.weather.models;

import com.wolfprogrammer.weather.R;

/**
 * Created by thomas on 11/29/16.
 */

public class ConditionIcons {
    public static Integer getDrawable (String icon) {
        Integer drawable;
        switch (icon) {
            case "01d":
                drawable = R.drawable.art_clear;
                break;
            case "01n":
                drawable = R.drawable.ic_clear;
                break;
            case "02d":
                drawable = R.drawable.art_light_clouds;
                break;
            case "02n":
                drawable = R.drawable.ic_light_clouds;
                break;
            case "03d":
            case "04d":
                drawable = R.drawable.art_clouds;
                break;
            case "03n":
            case "04n":
                drawable = R.drawable.ic_cloudy;
                break;
            case "09d":
                drawable = R.drawable.art_light_rain;
                break;
            case "09n":
                drawable = R.drawable.ic_light_rain;
                break;
            case "10d":
                drawable = R.drawable.art_rain;
                break;
            case "10n":
                drawable = R.drawable.ic_rain;
                break;
            case "11d":
                drawable = R.drawable.art_storm;
                break;
            case "11n":
                drawable = R.drawable.ic_storm;
                break;
            case "13d":
                drawable = R.drawable.art_snow;
                break;
            case "13n":
                drawable = R.drawable.ic_snow;
                break;
            case "50d":
                drawable = R.drawable.ic_fog;
                break;
            case "50n":
                drawable = R.drawable.ic_fog;
                break;
            default:
                drawable = R.drawable.art_clear;
                break;
        }
        return  drawable;
    }
}
