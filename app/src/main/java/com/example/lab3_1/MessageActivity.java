
package com.example.lab3_1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Arrays;
import java.util.HashMap;

public class MessageActivity extends AppCompatActivity {
    public String displayText;
    myListAdapter myListAdapter = new myListAdapter();
    EditText enteredText;
    ArrayList<MessageInfo> messageInfo = new ArrayList<>();
    ContentValues newRowValues = new ContentValues();
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        Button sButton = findViewById(R.id.sendButton);
        Button rButton = findViewById(R.id.receiveButton);
        boolean isTablet = findViewById(R.id.fragmentLocation) != null;
        loadDataFromDatabase();

        sButton.setOnClickListener(click -> {
            enteredText = findViewById(R.id.editTextMessage);
            displayText = enteredText.getText().toString();
            MessageInfo info = new MessageInfo(displayText,1);
            messageInfo.add(info);
            myListAdapter.notifyDataSetChanged();
            newRowValues.put(MyOpener.COL_MESSAGE, displayText);
            newRowValues.put(MyOpener.COL_SENT, 1);
            long newId = db.insert(MyOpener.TABLE_NAME, null, newRowValues);
            enteredText.getText().clear();


        });
        rButton.setOnClickListener(click -> {
            enteredText = findViewById(R.id.editTextMessage);
            displayText = enteredText.getText().toString();
            MessageInfo info = new MessageInfo(displayText, 0);
            messageInfo.add(info);
            myListAdapter.notifyDataSetChanged();
            newRowValues.put(MyOpener.COL_MESSAGE, displayText);
            newRowValues.put(MyOpener.COL_SENT, 0);
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
                MessageInfo selectedMessage = messageInfo.get(position);
                deleteMessage(selectedMessage);
                messageInfo.remove(position);
                myListAdapter.notifyDataSetChanged();
            });
            alertDialog.setNegativeButton("No", (click, arg) -> {
            });
            alertDialog.create();
            alertDialog.show();
            return true;
        });
        myList.setOnItemClickListener((list,view,position,id)->{
            if(isTablet)
        {
            DetailFragment dFragment = new DetailFragment(); //add a DetailFragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentLocation, dFragment) //Add the fragment in FrameLayout
                    .commit(); //actually load the fragment. Calls onCreate() in DetailFragment
        }
        else //isPhone
        {
            Intent nextActivity = new Intent(this, EmptyActivity.class);
            startActivity(nextActivity); //make the transition
        }

        });
    }

    private void loadDataFromDatabase() {
        //get a database connection:
        MyOpener dbOpener = new MyOpener(this);
        db = dbOpener.getWritableDatabase(); //This calls onCreate() if you've never built the table before, or onUpgrade if the version here is newer

        // We want to get all of the columns. Look at MyOpener.java for the definitions:
        String[] columns = {MyOpener.COL_ID, MyOpener.COL_MESSAGE, MyOpener.COL_SENT};
        //query all the results from the database:
        Cursor results = db.query(false, MyOpener.TABLE_NAME, columns, null, null, null, null, null, null);

        //Now the results object has rows of results that match the query.
        //find the column indices:
        int messageColumnIndex = results.getColumnIndex(MyOpener.COL_MESSAGE);
        int idColIndex = results.getColumnIndex(MyOpener.COL_ID);
        int sentColIndex = results.getColumnIndex(MyOpener.COL_SENT);
        //iterate over the results, return true if there is a next item:
        while (results.moveToNext()) {
            String message = results.getString(messageColumnIndex);
            long id = results.getLong(idColIndex);
            int sent = results.getInt(sentColIndex);

            //add the new Contact to the array list:
            messageInfo.add(new MessageInfo(message, sent,id));
        }
        printCursor(results,1);
        //At this point, the contactsList array has loaded every row from the cursor.
    }
    public void printCursor(Cursor c, int version){

        Log.v("#Version Number ", String.valueOf(version));
        Log .v("#Number of columns ", String.valueOf(c.getColumnCount()));
        Log.v("#Column Names ", Arrays.toString(c.getColumnNames()));
        Log.v("#Number of rows ", String.valueOf(c.getCount()));

        int messColumnIndex = c.getColumnIndex(MyOpener.COL_MESSAGE);
        int idColIndex = c.getColumnIndex(MyOpener.COL_ID);
        int sentColIndex = c.getColumnIndex(MyOpener.COL_SENT);
c.moveToFirst();
        while(c.moveToNext()) {
            String message = c.getString(messColumnIndex);
            long idd = c.getLong(idColIndex);
            int sentt = c.getInt(sentColIndex);
            Log.v("#Message Value ", message);
            Log.v("#ID Value ", String.valueOf(idd));
            Log.v("#Sent Value ", String.valueOf(sentt));
        }




    }
    protected void deleteMessage(MessageInfo c)
    {
        db.delete(MyOpener.TABLE_NAME, MyOpener.COL_ID + "= ?", new String[] {Long.toString(c.getId())});
    }

    public class myListAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return messageInfo.size();
        }

        @Override
        public MessageInfo getItem(int position) {

            return messageInfo.get(position);

        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();

                if (messageInfo.get(position).getSend()==1) {
                    MessageInfo thisRow = getItem(position);
                    View newView = inflater.inflate(R.layout.leftmessage, parent, false);
                    TextView display = newView.findViewById(R.id.leftMessage);
                    display.setText(thisRow.getMessage());
                    return newView;
                }
                if (messageInfo.get(position).getSend()==0) {
                    View newView = inflater.inflate(R.layout.rightmessage, parent, false);
                    TextView display = newView.findViewById(R.id.rightMessage);
                    display.setText(messageInfo.get(position).getMessage());
                    return newView;
                }




            return null;
        }




    }
}
