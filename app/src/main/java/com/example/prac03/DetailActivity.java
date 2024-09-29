package com.example.prac03;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView flagImageView = findViewById(R.id.flag_image);
        TextView countryDetailsTextView = findViewById(R.id.country_details);

        Country country = (Country) getIntent().getSerializableExtra("country");

        if (country != null) {
            flagImageView.setImageResource(country.getFlagResourceId());
            countryDetailsTextView.setText("Nation: " + country.getName() +
                    "\nCapital: " + country.getCapital() +
                    "\nPopulation: " + country.getPopulation() +
                    "\nArea: " + country.getArea() +
                    "\nDensity: " + country.getDensity() +
                    "\nWorld Share: " + country.getWorldShare());
        }
    }
}


