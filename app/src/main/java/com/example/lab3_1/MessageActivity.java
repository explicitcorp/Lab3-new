
package com.example.lab3_1;

import android.app.Activity;
import android.app.AlertDialog;
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
    EditText enteredText;
    ArrayList<Message> messageDisplay;
    boolean sendSelection = false;
    boolean receiveSelection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        messageDisplay = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        Button sButton = findViewById(R.id.sendButton);
        Button rButton = findViewById(R.id.receiveButton);


        sButton.setOnClickListener(click -> {
            enteredText = findViewById(R.id.editTextMessage);
            displayText = enteredText.getText().toString();
            messageDisplay.add(new Message(displayText, true));
            myListAdapter.notifyDataSetChanged();
            enteredText.getText().clear();
        });
        rButton.setOnClickListener(click -> {
            enteredText = findViewById(R.id.editTextMessage);
            displayText = enteredText.getText().toString();
            messageDisplay.add(new Message(displayText, false));
            myListAdapter.notifyDataSetChanged();
            enteredText.getText().clear();
        });
        ListView myList = findViewById(R.id.listViewLayout);
        myList.setAdapter(myListAdapter);
        myList.setOnItemLongClickListener((p,b,pos,id) -> {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Delete Message?")
                    .setPositiveButton("Yes", (click, arg) -> {
                        messageDisplay.remove(pos);
                        myList.deferNotifyDataSetChanged();
                    }).setNegativeButton("No", (click, arg) -> {
            });



        });

    }

    public class myListAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return messageDisplay.size();
        }

        @Override
        public String getItem(int position) {

            return messageDisplay.get(position).message;

        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();

            if (!messageDisplay.get(position).sent) {
                View newView = inflater.inflate(R.layout.leftmessage, parent, false);
                TextView display = newView.findViewById(R.id.leftMessage);
                display.setText(getItem(position));
                return newView;
            }
            if (messageDisplay.get(position).sent) {
                View newView = inflater.inflate(R.layout.rightmessage, parent, false);
                TextView display = newView.findViewById(R.id.rightMessage);
                display.setText(getItem(position));
                return newView;
            }
            return null;
        }


    }


    public class Message {
        private boolean sent;
        private String message;

        public Message(String message, boolean sent) {
            this.message = message;
            this.sent = sent;
        }

public String getMessage(){
    return message;
}
 public boolean sent(){
            return sent;
 }


    }


}