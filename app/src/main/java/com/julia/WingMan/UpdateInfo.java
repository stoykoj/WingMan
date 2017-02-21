package com.julia.WingMan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia on 2017-02-18.
 */

public class UpdateInfo extends AppCompatActivity {
    //private Spinner genderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_info_layout);
        //addItemsGenderSpinner();
    }

    public void submit(View view){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    /*public void addItemsGenderSpinner(){
        genderSpinner = (Spinner) findViewById(R.id.gender_spinner);
        List<String> list = new ArrayList<String>();
        list.add("Female");
        list.add("Male");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(dataAdapter);
    }*/
}
