package com.example.prac03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Country> countryList;
    private CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.country_list);
        countryList = new ArrayList<>();

        countryList.add(new Country("India", "New Delhi", "1,428.6 million people", "2,973,190 Km²", "481 people/Km²", "17.76 %", R.drawable.india_flag));
        countryList.add(new Country("China", "Beijing", "1,411.8 million people", "9,388,211 Km²", "150 people/Km²", "18.47 %", R.drawable.china_flag));
        countryList.add(new Country("United States", "Washington DC", "331.9 million people", "9,525,067 Km²", "35 people/Km²", "4.25 %", R.drawable.usa_flag));
        countryList.add(new Country("Indonesia", "Jakarta", "273.5 million people", "1,904,569 Km²", "143 people/Km²", "3.51 %", R.drawable.indonesia_flag));
        countryList.add(new Country("Pakistan", "Islamabad", "225.2 million people", "881,913 Km²", "255 people/Km²", "2.83 %", R.drawable.pakistan_flag));
        countryList.add(new Country("Nigeria", "Abuja", "206.1 million people", "923,768 Km²", "223 people/Km²", "2.64 %", R.drawable.nigeria_flag));
        countryList.add(new Country("Brazil", "Brasilia", "212.6 million people", "8,515,767 Km²", "25 people/Km²", "2.73 %", R.drawable.brazil_flag));
        countryList.add(new Country("Bangladesh", "Dhaka", "166.3 million people", "147,570 Km²", "1,127 people/Km²", "2.11 %", R.drawable.bangladesh_flag));
        countryList.add(new Country("Russia", "Moscow", "146.8 million people", "17,098,242 Km²", "9 people/Km²", "1.87 %", R.drawable.russia_flag));
        countryList.add(new Country("Mexico", "Mexico City", "128.9 million people", "1,964,375 Km²", "66 people/Km²", "1.65 %", R.drawable.mexico_flag));

        adapter = new CountryAdapter(this, R.layout.country_item, countryList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Country selectedCountry = countryList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("country_name", selectedCountry.getName());
                intent.putExtra("capital_name", selectedCountry.getCapital());
                intent.putExtra("population", selectedCountry.getPopulation());
                intent.putExtra("area", selectedCountry.getArea());
                intent.putExtra("density", selectedCountry.getDensity());
                intent.putExtra("world_share", selectedCountry.getWorldShare());
                startActivity(intent);
            }
        });
    }
}

