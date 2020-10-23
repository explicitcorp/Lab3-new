
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

import java.util.ArrayList;
import java.util.HashMap;

public class MessageActivity extends Activity {

    myListAdapter myListAdapter = new myListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        Button send = findViewById(R.id.sendButton);
        Button receive = findViewById(R.id.receiveButton);
        final android.widget.ListView myList = (android.widget.ListView) findViewById(R.id.listViewLayout);
        myList.setAdapter( (ListAdapter) myListAdapter );

        final ArrayList<String> list = new ArrayList<String>();

        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }

    public class myListAdapter extends BaseAdapter{
        private ArrayList<String> elements = new ArrayList<>();
        private  LayoutInflater inflater = getLayoutInflater();

        @Override
        public int getCount() {
            return elements.size();
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
            View newView = inflater.inflate(R.layout.listview, parent ,false);
            return newView;
        }
    }

}


