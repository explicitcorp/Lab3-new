package com.example.lab3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs = null;
    EditText typeField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText email = findViewById(R.id.enterEmailAddress);
        Button button = findViewById(R.id.loginButton);
        prefs = getSharedPreferences("SavedPrefs", Context.MODE_PRIVATE);
        email.setText(prefs.getString("emailAdd", "Enter Email Address"));;
        button.setOnClickListener(bt -> onPause());

    }

    @Override
    protected void onPause() {
        super.onPause();
        Button loginButton = findViewById(R.id.loginButton);
        typeField = findViewById(R.id.enterEmailAddress);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("emailAdd", typeField.getText().toString());
        editor.commit();
    }





    }
