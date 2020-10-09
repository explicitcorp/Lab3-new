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
    EditText typeField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String savedString = prefs.getString("emailAdd", "Default Value");
        typeField = findViewById(R.id.enterEmailAddress);
        typeField.setText(savedString);
    }
    @Override
    protected void onPause() {
        super.onPause();
        prefs = getSharedPreferences("SavedPrefs", Context.MODE_PRIVATE);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(bt -> saveSharedPrefs(typeField.getText().toString()));
    }

    private void saveSharedPrefs(String stringToSave) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("emailAdd", stringToSave);
        editor.commit();
    }

}