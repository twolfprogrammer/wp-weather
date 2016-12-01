package com.wolfprogrammer.weather.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolfprogrammer.weather.R;
import com.wolfprogrammer.weather.models.Current;
import com.wolfprogrammer.weather.models.ConditionIcons;
import com.wolfprogrammer.weather.models.DetailsViewModel;
import com.wolfprogrammer.weather.services.WeatherService;
import com.wolfprogrammer.weather.views.activities.DetailActivity;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CurrentFragment extends Fragment {

    final static String CURRENT_WEATHER_KEY = "current";

    View fragmentView;
    Current currentWeather;
    TextView dateTextView;
    TextView currentTempTextView;
    TextView minTempTextView;
    TextView conditionTextView;
    ImageView iconImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_current, container, false);
        dateTextView = (TextView)fragmentView.findViewById(R.id.dateTextView);
        currentTempTextView = (TextView)fragmentView.findViewById(R.id.currentTempTextView);
        minTempTextView = (TextView)fragmentView.findViewById(R.id.minTempTextView);
        conditionTextView = (TextView)fragmentView.findViewById(R.id.conditionTextView);
        iconImageView = (ImageView) fragmentView.findViewById(R.id.iconImageView);

        fragmentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetails();
            }
        });

        if(savedInstanceState != null && savedInstanceState.containsKey(CURRENT_WEATHER_KEY)) {
            currentWeather = (Current)savedInstanceState.getSerializable(CURRENT_WEATHER_KEY);
            populateView();
        }
        else {
            getCurrentWeather();
        }

        return  fragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CURRENT_WEATHER_KEY, currentWeather);
    }

    private void getCurrentWeather() {
        new WeatherService().getCurrentWeather(new WeatherService.TaskListener() {
            @Override
            public void onTaskCompleted(JSONObject jsonObject) {
                if(jsonObject == null) return;
                currentWeather = new Current(jsonObject);
                populateView();
            }
        });
    }

    private void populateView() {
        if(currentWeather == null) return;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d", Locale.US);
        dateTextView.setText(String.format(Locale.US, "%s, %s",
                getString(R.string.today),
                dateFormat.format(currentWeather.getDate())));

        currentTempTextView.setText(String.format(Locale.US, "%s%s",
                currentWeather.getWeatherDetails().getTemperature().toString(),
                getString(R.string.degrees_symbol)));

        minTempTextView.setText(String.format(Locale.US, "%s%s",
                currentWeather.getWeatherDetails().getTemperatureMin().toString(),
                getString(R.string.degrees_symbol)));

        if (currentWeather.getConditions().size() > 0) {
            conditionTextView.setText(currentWeather.getConditions().get(0).getMain());
        }

        iconImageView.setImageResource(ConditionIcons.getDrawable(currentWeather.getConditions().get(0).getIcon()));
    }

    private void goToDetails() {
        if(currentWeather == null) return;
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        DetailsViewModel detailsViewModel = new DetailsViewModel(currentWeather.getDate(),
                currentWeather.getWeatherDetails(), currentWeather.getConditions().get(0));
        intent.putExtra(DetailsViewModel.DETAILS_VIEW_MODEL_KEY, detailsViewModel);
        startActivity(intent);
    }
}
