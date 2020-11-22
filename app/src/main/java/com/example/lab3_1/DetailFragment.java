package com.example.lab3_1;

import android.os.Bundle;

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
        Button hideButton = myView.findViewById(R.id.finishButton);


message.setText(messageImport);
idText.setText(idImport);
chBox.setChecked(sentImport);


        return myView;

    }
}