package com.example.lab3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String savedString = prefs.getString("emailAdd", "Default Value");
        EditText typeField = findViewById(R.id.enterEmailAddress);
        typeField.setText(savedString);
    }
    private void saveSharedPrefs(String stringToSave) {
        prefs = getSharedPreferences("SavedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("emailAdd", stringToSave);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Button loginButton = findViewById(R.id.loginButton);
        EditText typeField1 = findViewById(R.id.enterEmailAddress);
        loginButton.setOnClickListener(bt -> saveSharedPrefs(typeField1.getText().toString()));
    }





    }
