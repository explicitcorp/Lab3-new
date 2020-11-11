package com.example.lab3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

public class WeatherForcast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);
    }

    public class ForceastQuery extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}