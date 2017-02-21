package com.julia.WingMan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Julia on 2017-02-18.
 */

public class Academics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academics_layout);
        //addItemsGenderSpinner();
    }

    public void academicsSubmit(View view){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}
