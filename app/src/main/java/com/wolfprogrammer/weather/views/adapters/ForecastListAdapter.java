package com.wolfprogrammer.weather.views.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.wolfprogrammer.weather.R;
import com.wolfprogrammer.weather.models.ForecastItem;
import com.wolfprogrammer.weather.models.ConditionIcons;
import com.wolfprogrammer.weather.utilities.DateHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by thomas on 11/29/16.
 */

public class ForecastListAdapter implements ListAdapter{

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<ForecastItem> dataSource;

    private static class ViewHolder {
        ImageView iconImageView;
        TextView dateTextView;
        TextView conditionTextView;
        TextView maxTempTextView;
        TextView minTempTextView;
    }

    public ForecastListAdapter(Context context, ArrayList<ForecastItem> items){
        this.context = context;
        dataSource = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ForecastItem item = dataSource.get(position);

        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.listview_item_forecast, parent, false);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
            holder.conditionTextView = (TextView) convertView.findViewById(R.id.conditionTextView);
            holder.maxTempTextView = (TextView) convertView.findViewById(R.id.maxTempTextView);
            holder.minTempTextView = (TextView) convertView.findViewById(R.id.minTempTextView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.iconImageView.setImageResource(
                ConditionIcons.getDrawable(item.getConditions().get(0).getIcon()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.US);
        holder.dateTextView.setText(DateHelper.isTomorrow(item.getDate())
                ? context.getString(R.string.tomorrow) : dateFormat.format(item.getDate()));

        holder.conditionTextView.setText(item.getConditions().get(0).getMain());

        holder.maxTempTextView.setText(String.format(Locale.US, "%s%s",
                item.getWeatherDetails().getTemperatureMax().toString(),
                context.getString(R.string.degrees_symbol)));

        holder.minTempTextView.setText(String.format(Locale.US, "%s%s",
                item.getWeatherDetails().getTemperatureMin().toString(),
                context.getString(R.string.degrees_symbol)));

        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) { }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) { }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
