package com.julia.WingMan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.julia.WingMan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia on 2016-12-03.
 */

public class addTab extends AppCompatActivity {
    private String otherUser;
    private EditText inOtherUser;
    private Spinner debtorSpin;
    private Spinner debteeSpin;
    private EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtab_layout);
        inOtherUser = (EditText) findViewById(R.id.other_user_input);
        //debtorSpin = (Spinner) findViewById(R.id.debtor_spinner);
        addItemsDebtorSpinner();
        addItemsDebteeSpinner();
    }

    public void confirmTab(View view){
        boolean confirmed = false;
        String debtor, debtee;
        String debtor2, debtee2;
        debtorSpin = (Spinner)findViewById(R.id.debtor_spinner);
        debtor = debtorSpin.getSelectedItem().toString();
        debteeSpin = (Spinner)findViewById(R.id.debtee_spinner);
        debtee = debteeSpin.getSelectedItem().toString();
        amount = (EditText)findViewById(R.id.dollar_amt);

        String other;
        if (debtor.equals("You")){
            debtor2 = ((MyApp) this.getApplication()).getCurrentUsr();
            debtee2 = debtee;
            other = debtee;
        } else{
            debtee2 = ((MyApp) this.getApplication()).getCurrentUsr();
            debtor2 = debtor;
            other = debtor;
        }


        boolean debtExists = (((MyApp) this.getApplication()).debtExists(other, ((MyApp) this.getApplication()).getCurrentUsr()));

        String amt = amount.getText().toString();
        float amtF = Float.parseFloat(amt);

        if (debtExists){
            if (((MyApp) this.getApplication()).getDebtor(debtor2, debtee2).equals(((MyApp) this.getApplication()).getCurrentUsr())){
                if (debtor.equals("You")){
                    amtF += ((MyApp) this.getApplication()).getAmount(debtor, debtee);
                }
                else{
                    amtF = ((MyApp) this.getApplication()).getAmount(debtor, debtee) - amtF;
                }
            }
            else{
                if (debtee.equals("You")){
                    amtF += ((MyApp) this.getApplication()).getAmount(debtor, debtee);
                }
                else{
                    amtF = ((MyApp) this.getApplication()).getAmount(debtor, debtee) - amtF;
                }
            }

        }

        amt = Float.toString(amtF);


        String message = debtor +" owe(s) " +debtee +" $" +amt;
        final Intent intent = new Intent(this, MainScreen.class);
        new AlertDialog.Builder(this)
                .setTitle("Confirm Tab")
                .setMessage("Confirm this tab: " +message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        startActivity(intent);
                    }

                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        amount.setText("");
                        inOtherUser.setText("");
                        addItemsDebteeSpinner();
                        addItemsDebtorSpinner();
                    }}).show();
        String otherUsr;
        if (!debtExists) {
            if (debtor.equals("You")) {//otherUsr = debtee;
                ((MyApp) this.getApplication()).addDebt(((MyApp) this.getApplication()).getCurrentUsr(), debtee, amt);
            } else if (debtee.equals("You")) {
                //otherUsr = debtor;
                ((MyApp) this.getApplication()).addDebt(debtor, ((MyApp) this.getApplication()).getCurrentUsr(), amt);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_LONG).show();
            }
        }
        else{
            if (debtor.equals("You")) {
                if (amtF < 0){
                    ((MyApp) this.getApplication()).deleteDebt(((MyApp) this.getApplication()).getCurrentUsr(), debtee);
                    ((MyApp) this.getApplication()).addDebt(debtee, ((MyApp) this.getApplication()).getCurrentUsr(), Float.toString(amtF*-1));
                }else {
                    ((MyApp) this.getApplication()).updateDebt(((MyApp) this.getApplication()).getCurrentUsr(), debtee, amt);
                }
            } else if (debtee.equals("You")) {
                if (amtF < 0){
                    ((MyApp) this.getApplication()).deleteDebt(debtor, ((MyApp) this.getApplication()).getCurrentUsr());
                    ((MyApp) this.getApplication()).addDebt(((MyApp) this.getApplication()).getCurrentUsr(), debtor, Float.toString(amtF*-1));
                }else {
                    ((MyApp) this.getApplication()).updateDebt(((MyApp) this.getApplication()).getCurrentUsr(), debtee, amt);
                }
                //((MyApp) this.getApplication()).updateDebt(debtor, ((MyApp) this.getApplication()).getCurrentUsr(), amt);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_LONG).show();
            }
        }


    }
    public void cancelTab(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    public void confirmBtn(View view){
        otherUser = (String)inOtherUser.getText().toString();

        if (((MyApp) this.getApplication()).usrInDB(otherUser)) {
            debtorSpin = (Spinner) findViewById(R.id.debtor_spinner);
            List<String> list = new ArrayList<String>();
            list.add("You");
            list.add(otherUser);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            debtorSpin.setAdapter(dataAdapter);
            debteeSpin = (Spinner) findViewById(R.id.debtee_spinner);
            List<String> list2 = new ArrayList<String>();
            list2.add("You");
            list2.add(otherUser);
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            debteeSpin.setAdapter(dataAdapter2);
        }
        else{
            Toast.makeText(getApplicationContext(), "Username not found in database", Toast.LENGTH_LONG).show();
            inOtherUser.setText("");
        }
    }

    public void addItemsDebtorSpinner(){
        debtorSpin = (Spinner) findViewById(R.id.debtor_spinner);
        List<String> list = new ArrayList<String>();
        list.add("You");
        list.add("Other Person");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        debtorSpin.setAdapter(dataAdapter);
    }
    public void addItemsDebteeSpinner(){
        debteeSpin = (Spinner) findViewById(R.id.debtee_spinner);
        List<String> list = new ArrayList<String>();
        list.add("You");
        list.add("Other Person");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        debteeSpin.setAdapter(dataAdapter);
    }


}
