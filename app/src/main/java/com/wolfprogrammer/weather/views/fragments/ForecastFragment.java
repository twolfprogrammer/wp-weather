package com.wolfprogrammer.weather.views.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wolfprogrammer.weather.R;
import com.wolfprogrammer.weather.models.DetailsViewModel;
import com.wolfprogrammer.weather.models.Forecast;
import com.wolfprogrammer.weather.models.ForecastItem;
import com.wolfprogrammer.weather.services.WeatherService;
import com.wolfprogrammer.weather.views.activities.DetailActivity;
import com.wolfprogrammer.weather.views.adapters.ForecastListAdapter;

import org.json.JSONObject;

/**
 * Created by thomas on 11/29/16.
 */

public class ForecastFragment extends Fragment {

    final static String FORECAST_KEY = "forecast";

    View fragmentView;
    Forecast forecast;
    ForecastListAdapter adapter;
    ListView forecastListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_forecast, container, false);
        forecastListView = (ListView)fragmentView.findViewById(R.id.forecastListView);
        forecastListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetails(position);
            }
        });

        if(savedInstanceState != null && savedInstanceState.containsKey(FORECAST_KEY)){
            forecast = (Forecast)savedInstanceState.get(FORECAST_KEY);
            updateAdapter();
        }
        else {
            getForecast();
        }

        return fragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(FORECAST_KEY, forecast);
    }

    private void getForecast() {
        new WeatherService().getWeatherForecast(new WeatherService.TaskListener() {
            @Override
            public void onTaskCompleted(JSONObject jsonObject) {
                if(jsonObject == null) return;
                forecast = new Forecast(jsonObject);
                if (forecast == null || forecast.getForecastList().size() < 0) return;
                forecast.getForecastList().remove(0); // Removes today from list
                updateAdapter();
            }
        });
    }

    private void updateAdapter() {
        if(forecast == null) return;
        adapter = new ForecastListAdapter(getActivity(), forecast.getForecastList());
        forecastListView.setAdapter(adapter);
    }

    private void goToDetails(Integer position) {
        if(adapter == null || adapter.getCount() < 1) return;
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        ForecastItem item = (ForecastItem) adapter.getItem(position);
        DetailsViewModel detailsViewModel = new DetailsViewModel(item.getDate(),
                item.getWeatherDetails(), item.getConditions().get(0));
        intent.putExtra(DetailsViewModel.DETAILS_VIEW_MODEL_KEY, detailsViewModel);
        startActivity(intent);
    }
}
