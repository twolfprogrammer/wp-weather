package com.wolfprogrammer.weather.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolfprogrammer.weather.R;
import com.wolfprogrammer.weather.models.ConditionIcons;
import com.wolfprogrammer.weather.models.DetailsViewModel;
import com.wolfprogrammer.weather.utilities.CardinalDirectionsHelper;
import com.wolfprogrammer.weather.utilities.DateHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    TextView dayTextView;
    TextView dateTextView;
    TextView maxTempTextView;
    TextView minTempTextView;
    TextView humidityTextView;
    TextView pressureTextView;
    TextView windTextView;
    TextView conditionTextView;
    ImageView iconImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dayTextView = (TextView) findViewById(R.id.dayTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        maxTempTextView = (TextView) findViewById(R.id.maxTempTextView);
        minTempTextView = (TextView) findViewById(R.id.minTempTextView);
        humidityTextView = (TextView) findViewById(R.id.humidityTextView);
        pressureTextView = (TextView) findViewById(R.id.pressureTextView);
        windTextView = (TextView) findViewById(R.id.windTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        iconImageView = (ImageView) findViewById(R.id.iconImageView);
        populateView();
    }

    private void populateView() {
        if(getIntent().hasExtra(DetailsViewModel.DETAILS_VIEW_MODEL_KEY)) {
            DetailsViewModel detailsViewModel = (DetailsViewModel) getIntent()
                    .getSerializableExtra(DetailsViewModel.DETAILS_VIEW_MODEL_KEY);

            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.US);
            dayTextView.setText(DateHelper.isToday(detailsViewModel.getDate()) ?
                    getString(R.string.today) : (DateHelper.isTomorrow(detailsViewModel.getDate()) ?
                    getString(R.string.tomorrow) : dateFormat.format(detailsViewModel.getDate())));

            dateFormat = new SimpleDateFormat("MMMM d", Locale.US);
            dateTextView.setText(dateFormat.format(detailsViewModel.getDate()));

            maxTempTextView.setText(String.format(Locale.US, "%s%s",
                    detailsViewModel.getDetails().getTemperatureMax().toString(),
                    getString(R.string.degrees_symbol)));

            minTempTextView.setText(String.format(Locale.US, "%s%s",
                    detailsViewModel.getDetails().getTemperatureMin().toString(),
                    getString(R.string.degrees_symbol)));

            humidityTextView.setText(String.format(Locale.US, "%s %s %%",
                    getString(R.string.humidity_prefix),
                    detailsViewModel.getDetails().getHumidity().toString()));

            pressureTextView.setText(String.format(Locale.US, "%s %s %s",
                    getString(R.string.pressure_prefix),
                    detailsViewModel.getDetails().getPressure().toString(),
                    getString(R.string.hectopascal_unit)));

            windTextView.setText(String.format(Locale.US, "%s %s %s %s",
                    getString(R.string.wind_prefix),
                    detailsViewModel.getDetails().getWind().getSpeed().toString(),
                    getString(R.string.miles_per_hour_unit),
                    CardinalDirectionsHelper.getString(detailsViewModel.getDetails().getWind().getDegree())));

            conditionTextView.setText(detailsViewModel.getCondition().getMain());

            iconImageView.setImageResource(ConditionIcons.getDrawable(detailsViewModel.getCondition().getIcon()));
        }
    }
}
