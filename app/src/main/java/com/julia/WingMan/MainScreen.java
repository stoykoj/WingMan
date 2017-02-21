package com.julia.WingMan;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.julia.WingMan.R;

import java.util.ArrayList;

/**
 * Created by Julia on 2016-12-03.
 */

public class MainScreen extends AppCompatActivity {
    final private Context context = this;

    private TextView list1;
    private Button[] list;
    private static int posn = 0;
    private LinearLayout linear;
    private static final int max = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen_layout);
        //list1 = (TextView)findViewById(R.id.item_list);
        list = new Button[max];

        ArrayList<ArrayList<String>> debts = ((MyApp) this.getApplication()).getDebts();
        for (int i=0; i<debts.size(); i++){
            if (debts.get(i).get(0).equals(((MyApp) this.getApplication()).getCurrentUsr())) {
                updateListOwe(debts.get(i).get(1), debts.get(i).get(2));
            }
            else if (debts.get(i).get(1).equals(((MyApp) this.getApplication()).getCurrentUsr())) {
                updateListOwes(debts.get(i).get(0), debts.get(i).get(2));
            }
        }

        /*updateList("You", "Jan", "44");
        updateList("Bill", "You", "76");*/

    }
    public void goAddTab(View view){
        Intent intent = new Intent(this, addTab.class);
        startActivity(intent);
    }

    public void goDatabase(View view){
        Intent intent = new Intent(this, ViewableDB.class);
        startActivity(intent);
    }

    public void goLogout(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void updateList1(String usr1, String usr2, String amt){
        String prev = list1.getText().toString();
        String update = usr1 +" owe(s) " +usr2 +" $" +amt;
        list1.setText(prev +"\n" +update);
    }


    static String usr1, usr2;
    public void updateListOwe(String otherUsr, String amt){
        this.usr1 = otherUsr;
        this.usr2 = ((MyApp) this.getApplication()).getCurrentUsr();
        linear = (LinearLayout)findViewById(R.id.linear);
        linear.setOrientation(LinearLayout.VERTICAL);
        if (posn < max){
            list[posn] = new Button(this);
            list[posn].setHeight(50);
            list[posn].setWidth(50);
            list[posn].setTag(posn);
            list[posn].setText("You owe " +otherUsr +" $" +amt);
            list[posn].setOnClickListener(btnClicked);

            linear.addView(list[posn]);
            posn++;
        }
    }

    public void updateListOwes(String otherUsr, String amt){
        this.usr1 = otherUsr;
        this.usr2 = ((MyApp) this.getApplication()).getCurrentUsr();
        linear = (LinearLayout)findViewById(R.id.linear);
        linear.setOrientation(LinearLayout.VERTICAL);
        if (posn < max){
            list[posn] = new Button(this);
            list[posn].setHeight(50);
            list[posn].setWidth(50);
            list[posn].setTag(posn);
            list[posn].setText(otherUsr +" owes you $" +amt);
            list[posn].setOnClickListener(btnClicked);

            linear.addView(list[posn]);
            posn++;
        }
    }

    public void removeDebtFromDB(String debtor, String debtee){
        ((MyApp) this.getApplication()).deleteDebt(debtor, debtee);
    }


    public void updateList(String usr1, String usr2, String amt){
        this.usr1 = usr1;
        this.usr2 = usr2;
        linear = (LinearLayout)findViewById(R.id.linear);
        linear.setOrientation(LinearLayout.VERTICAL);
        if (posn < max){
            list[posn] = new Button(this);
            list[posn].setHeight(50);
            list[posn].setWidth(50);
            list[posn].setTag(posn);
            list[posn].setText(usr1 +" owe(s) " +usr2 +" $" +amt);
            list[posn].setOnClickListener(btnClicked);

            linear.addView(list[posn]);
            posn++;
        }
    }

    View.OnClickListener btnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean removed = false;
            final Object tag = v.getTag();
            final LinearLayout linear2 = (LinearLayout)findViewById(R.id.linear);
            new AlertDialog.Builder(context)
                    .setTitle("Confirm Payment")
                    .setMessage("Did payment occur?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            Toast.makeText(getApplicationContext(), "Yay", Toast.LENGTH_SHORT).show();
                            //list[Integer.parseInt(tag.toString())] = null;
                            linear2.removeView(list[Integer.parseInt(tag.toString())]);
                           removeDebtFromDB(usr1, usr2);

                        }
                        //removed = true;
                    })
                    .setNegativeButton(android.R.string.no, null).show();

        }
    };
}
