
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {

  //  EditText enteredText = findViewById(R.id.editTextMessage);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
   //     Button sButton = findViewById(R.id.sendButton);
//       Button rButton = findViewById(R.id.receiveButton);

     /*   sButton.setOnClickListener(click -> {
messageDisplay.add(enteredText.getText().toString());
myListAdapter.notifyDataSetChanged();

        });
       // rButton.setOnClickListener(r -> receiveAction());

*/
    //    ListView myList = findViewById(R.id.listViewLayout);
   //     myList.setAdapter( myListAdapter = new myListAdapter() );

    }
/*
    private void sendAction(){
        sendSelection = true;
        String sent = enteredText.getText().toString();
        TextView sendText = findViewById(R.id.leftMessage);
        sendText.setText(sent);
        enteredText.getText().clear();
    }
    private void receiveAction(){
        receiveSelection = true;
        String sent = enteredText.getText().toString();
        TextView sendText = findViewById(R.id.rightMessage);
        sendText.setText(sent);
        enteredText.getText().clear();
    }
*/
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
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             LayoutInflater inflater = getLayoutInflater();

                 View newView = inflater.inflate(R.layout.rightmessage, parent ,false);

                 return newView;


        }
    }

}


