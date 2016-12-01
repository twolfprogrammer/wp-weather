package com.wolfprogrammer.weather.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by thomas on 11/29/16.
 */

public class Condition implements Serializable {
    private static final String ID_KEY = "id";
    private static final String MAIN_KEY = "main";
    private static final String DESCRIPTION_KEY = "description";
    private static final String ICON_KEY = "icon";

    private Long id;
    private String main;
    private String description;
    private String icon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Condition(JSONObject object) {
        try {
            if(object.has(ID_KEY)){
                id = object.getLong(ID_KEY);
            }
            if(object.has(MAIN_KEY)){
                main = object.getString(MAIN_KEY);
            }
            if(object.has(DESCRIPTION_KEY)){
                description = object.getString(DESCRIPTION_KEY);
            }
            if(object.has(ICON_KEY)){
                icon = object.getString(ICON_KEY);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
