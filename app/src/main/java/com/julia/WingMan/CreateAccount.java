package com.julia.WingMan;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.julia.WingMan.R;

/**
 * Created by Constellations on 2016-12-03.
 */

public class CreateAccount extends AppCompatActivity {
    private EditText email, username, pw, confirmpw;
    private TextView text;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        email = (EditText) findViewById(R.id.emailIn);
        username = (EditText) findViewById(R.id.usernameIn);
        pw = (EditText) findViewById(R.id.passwordIn);
        confirmpw = (EditText) findViewById(R.id.confirmPassIn);
        text = (TextView) findViewById(R.id.text);
        //((MyApp) this.getApplication()).addToDB("julia", "123");
        printDatabase();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //Print the database
    public void printDatabase() {
        String dbString = ((MyApp) this.getApplication()).dbToString();
        //text.setText(((MyApp) this.getApplication()).getPw("julia"));
    }

    public void confirmCreate(View view) {
        boolean confirmed = true;
        String newUsr = username.getText().toString();
        if (newUsr != null){
            confirmed = !((MyApp) this.getApplication()).usrInDB(newUsr);
            if (!confirmed){
                Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                emptyInputs();
            }
        }
        else{
            confirmed = false;
            Toast.makeText(getApplicationContext(), "No user name entered", Toast.LENGTH_SHORT).show();
            emptyInputs();
        }

        if (confirmed && pw.getText().toString() != null && confirmpw.getText().toString() != null && pw.getText().toString().equals(confirmpw.getText().toString())) {
            confirmed = true;
        } else {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            confirmed = false;
            pw.setText("");
            confirmpw.setText("");
        }

        if (confirmed) {
            ((MyApp) this.getApplication()).setCurrentUsr(newUsr);
            ((MyApp) this.getApplication()).addUserToDB(newUsr, pw.getText().toString());
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }

    }

    public void emptyInputs(){
        username.setText("");
        email.setText("");
        pw.setText("");
        confirmpw.setText("");
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("CreateAccount Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

