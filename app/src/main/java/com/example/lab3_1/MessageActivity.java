
package com.example.lab3_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    ArrayList<MessageInfo> messageDisplay = new ArrayList<>();
    ArrayList<MessageInfo> messageInfo = new ArrayList<>();
    ContentValues newRowValues = new ContentValues();
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        messageDisplay = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        Button sButton = findViewById(R.id.sendButton);
        Button rButton = findViewById(R.id.receiveButton);

        loadDataFromDatabase();

        sButton.setOnClickListener(click -> {
            enteredText = findViewById(R.id.editTextMessage);
            displayText = enteredText.getText().toString();
            MessageInfo info = new MessageInfo(displayText);
            info.setSend(true);
            messageDisplay.add(info);
            myListAdapter.notifyDataSetChanged();
            newRowValues.put(MyOpener.COL_MESSAGE, displayText);
            long newId = db.insert(MyOpener.TABLE_NAME, null, newRowValues);
            enteredText.getText().clear();


        });
        rButton.setOnClickListener(click -> {
            enteredText = findViewById(R.id.editTextMessage);
            displayText = enteredText.getText().toString();
            MessageInfo info = new MessageInfo(displayText);
            info.setSend(false);
            messageDisplay.add(info);
            myListAdapter.notifyDataSetChanged();
            newRowValues.put(MyOpener.COL_MESSAGE, displayText);
            long newId = db.insert(MyOpener.TABLE_NAME, null, newRowValues);
            enteredText.getText().clear();

        });
        ListView myList = findViewById(R.id.listViewLayout);
        myList.setAdapter(myListAdapter);
        myList.setOnItemLongClickListener((parent, view, position, id) -> {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Are you sure?");
            alertDialog.setMessage("Delete Message?");
            alertDialog.setPositiveButton("Yes", (click, arg) -> {
                messageDisplay.remove(position);
                myListAdapter.notifyDataSetChanged();
            });
            alertDialog.setNegativeButton("No", (click, arg) -> {
            });
            alertDialog.create();
            alertDialog.show();
            return true;
        });
    }

    private void loadDataFromDatabase() {
        //get a database connection:
        MyOpener dbOpener = new MyOpener(this);
        db = dbOpener.getWritableDatabase(); //This calls onCreate() if you've never built the table before, or onUpgrade if the version here is newer


        // We want to get all of the columns. Look at MyOpener.java for the definitions:
        String[] columns = {MyOpener.COL_ID, MyOpener.COL_MESSAGE};
        //query all the results from the database:
        Cursor results = db.query(false, MyOpener.TABLE_NAME, columns, null, null, null, null, null, null);

        //Now the results object has rows of results that match the query.
        //find the column indices:
        int messageColumnIndex = results.getColumnIndex(MyOpener.COL_MESSAGE);
        int idColIndex = results.getColumnIndex(MyOpener.COL_ID);

        //iterate over the results, return true if there is a next item:
        while (results.moveToNext()) {
            String message = results.getString(messageColumnIndex);
            long id = results.getLong(idColIndex);

            //add the new Contact to the array list:
            messageInfo.add(new MessageInfo(message, id));
        }

        //At this point, the contactsList array has loaded every row from the cursor.
    }


    public class myListAdapter extends BaseAdapter {


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

            if (!messageDisplay.get(position).isSend()) {
                View newView = inflater.inflate(R.layout.leftmessage, parent, false);
                TextView display = newView.findViewById(R.id.leftMessage);
                display.setText(getItem(position));
                return newView;
            }
            if (messageDisplay.get(position).isSend()) {
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
