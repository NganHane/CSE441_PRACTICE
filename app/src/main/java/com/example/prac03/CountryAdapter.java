package com.example.prac03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    private Context context;
    private int resource;
    private List<Country> countryList;

    public CountryAdapter(Context context, int resource, List<Country> countryList) {
        super(context, resource, countryList);
        this.context = context;
        this.resource = resource;
        this.countryList = countryList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Country currentCountry = countryList.get(position);

        ImageView flagImageView = convertView.findViewById(R.id.flag_image);
        TextView nameTextView = convertView.findViewById(R.id.country_name);
        TextView capitalTextView = convertView.findViewById(R.id.country_capital);

        flagImageView.setImageResource(currentCountry.getFlagResourceId());
        nameTextView.setText(currentCountry.getName());
        capitalTextView.setText(currentCountry.getCapital());

        return convertView;
    }
}
