package com.julia.WingMan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.julia.WingMan.R;

/**
 * Created by Julia on 2016-12-03.
 */



public class ViewableDB extends AppCompatActivity {
    public DBHandler db;
    //private DBHandler db;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_layout);
        db = new DBHandler(this, null, null, 1);
        text = (TextView) findViewById(R.id.text);

        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        /*String dbString = db.dataBaseToString();
        text.setText(dbString);*/
        String dbString = ((MyApp) this.getApplication()).dbToString();
        text.setText(dbString);
    }
}
