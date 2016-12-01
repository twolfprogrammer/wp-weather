package com.wolfprogrammer.weather.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by thomas on 11/30/16.
 */

public class DetailsViewModel implements Serializable {
    public static final String DETAILS_VIEW_MODEL_KEY = "DETAILS_VIEW_MODEL_KEY";

    private Date date;
    private Details details;
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DetailsViewModel(Date date, Details details, Condition condition) {
        this.date = date;
        this.details = details;
        this.condition = condition;
    }
}
