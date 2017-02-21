package com.julia.WingMan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;

import com.julia.WingMan.R;

/**
 * Created by Julia on 2017-02-18.
 */

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_layout);
    }

    public void goUpdateInfo(View view){
        Intent intent = new Intent(this, UpdateInfo.class);
        startActivity(intent);
    }

    public void logout(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void goDating(View view){
        startActivity(new Intent(this, Dating.class));
    }
    public void goWorkout(View view){
        startActivity(new Intent(this, Workout.class));
    }
    public void goAcademics(View view){
        startActivity(new Intent(this, Academics.class));
    }
}
