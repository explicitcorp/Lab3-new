
package com.example.lab3_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {
public String displayText;
    myListAdapter myListAdapter = new myListAdapter();
    EditText enteredText ;
    ArrayList<String> messageDisplay;
   boolean sendSelection = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        messageDisplay   = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        Button sButton = findViewById(R.id.sendButton);
     Button rButton = findViewById(R.id.receiveButton);



        ListView myList = findViewById(R.id.listViewLayout);
        myList.setAdapter( myListAdapter);
       sButton.setOnClickListener(click -> {
           enteredText = findViewById(R.id.editTextMessage);
           displayText = enteredText.getText().toString();
           messageDisplay.add(displayText);
               myListAdapter.notifyDataSetChanged();
        });
   //     rButton.setOnClickListener(r -> receiveAction());



    }

  private void sendAction(){


    }
    private void receiveAction(){
    //    receiveSelection = true;
        String sent = enteredText.getText().toString();
        TextView sendText = findViewById(R.id.rightMessage);
        sendText.setText(sent);
        enteredText.getText().clear();
    }

    public class myListAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return messageDisplay.size();
        }

        @Override
        public String getItem(int position) {
            return messageDisplay.get(position).toString();
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             LayoutInflater inflater = getLayoutInflater();

                 View newView = inflater.inflate(R.layout.rightmessage, parent ,false);
            TextView display = newView.findViewById(R.id.rightMessage);
            ImageView displayImage = newView.findViewById(R.id.rightImage);
            display.setText(getItem(position));

                 return newView;


        }
    }

}


