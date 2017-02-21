package com.julia.WingMan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Julia on 2017-02-18.
 */

public class Workout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_layout);
        //addItemsGenderSpinner();
    }

    public void workoutSubmit(View view){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
