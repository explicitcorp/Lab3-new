package com.example.lab3_1;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    String messageImport;
    private Bundle dataPass;
    private AppCompatActivity parentActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataPass = getArguments();
        messageImport = dataPass.getString(MessageActivity.MESSAGE);
        Boolean sentImport = dataPass.getBoolean(MessageActivity.SENT);
        String idImport = dataPass.getString(MessageActivity.ID);

      View  myView = inflater.inflate(R.layout.fragment_detail, container, false);
        CheckBox chBox = myView.findViewById(R.id.checkBox2);
        TextView message = (TextView)myView.findViewById(R.id.message);
        TextView idText = (TextView)myView.findViewById(R.id.idText);
        Button hideButton = (Button)myView.findViewById(R.id.finishButton);


message.setText(messageImport);
idText.setText(idImport);
chBox.setChecked(sentImport);
        hideButton.setOnClickListener( clk -> {

            //Tell the parent activity to remove
            parentActivity.getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        return myView;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //context will either be FragmentExample for a tablet, or EmptyActivity for phone
        parentActivity = (AppCompatActivity)context;
    }
}