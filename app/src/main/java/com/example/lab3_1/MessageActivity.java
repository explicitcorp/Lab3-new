
package com.example.lab3_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {

   //myListAdapter myListAdapter = new myListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        // ListView myList = findViewById(R.id.listViewLayout);
        // myList.setAdapter( myListAdapter);
    }
}
/*
    public class myListAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             LayoutInflater inflater = getLayoutInflater();
            View newView = inflater.inflate(R.layout.listview, parent ,false);
            return newView;
        }
    }

}
*/

