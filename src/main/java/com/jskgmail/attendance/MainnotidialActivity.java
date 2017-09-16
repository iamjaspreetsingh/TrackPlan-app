package com.jskgmail.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainnotidialActivity extends AppCompatActivity {
    private ListView listView;
    ListViewAdapternotiset lviewAdapter;
    private ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnotidial);
        stringArrayList = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView779997);

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutdialogattended, null);

        Button yes = (Button) alertLayout.findViewById(R.id.yes);
        Button no = (Button) alertLayout.findViewById(R.id.no);
        Button ho = (Button) alertLayout.findViewById(R.id.holi);


        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String day = sdf.format(d);
        Log.i("gfdgffggfdgdf", day);
        final String ddd;
        if (day.contains("Mon"))
            ddd = "0";
        else if (day.contains("Tue"))
            ddd = "1";
        else if (day.contains("Wed"))
            ddd = "2";
        else if (day.contains("Thu"))
            ddd = "3";
        else if (day.contains("Fri"))
            ddd = "4";
        else if (day.contains("Sat"))
            ddd = "5";
        else
            ddd = "6";
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        Date dmy = new Date();
        String day1 = sdf1.format(dmy);
        SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
        Date dmy2 = new Date();
        final String day2 = sdf2.format(dmy2) + ".";
        TextView t = (TextView) findViewById(R.id.textView25);
        t.setText(day1);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(" Todays Attendance ");
        alert.setIcon(R.drawable.ic_today_black_24dp);
        // this is set the view from XML inside AlertDialog

        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch

        final AlertDialog dialog = alert.create();

        dialog.show();
        ho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                finish();

            }
        });


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.cancel();
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                List<Contact> contacts = db.getAllContacts();


                for (Contact cn : contacts) {
                    if ((cn.getPo().equals(MainActivity.semno))) {
                        if (cn.get_ttable().contains(ddd)) {
                            String p = cn.getPresent();
                            int pr = Integer.parseInt(p);
                            pr++;
                            cn.set_dateprea(day2);
                            Log.i("gooooooooooooooo", cn.datepre);
                            cn.setPresent(String.valueOf(pr));
                            db.updateContact(cn);
                        }
                    }
                }
                Toast toast = new Toast(getApplicationContext());
                ImageView view = new ImageView(getApplicationContext());
                view.setImageResource(R.mipmap.done);
                toast.setView(view);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();



                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                finish();


            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();


            }
        });


        for (Contact cn : contacts) {
            if ((cn.getPo().equals(MainActivity.semno))) {
                if (cn.get_ttable().contains(ddd)) {

                    stringArrayList.add(cn.getName());
                }

            }
        }

        lviewAdapter = new ListViewAdapternotiset(this, stringArrayList);
        listView.setAdapter(lviewAdapter);


    }

    @Override
    public void onBackPressed() {

            Toast.makeText(getApplicationContext(), "Your Attendance is marked for today ", Toast.LENGTH_LONG).show();
finish();



    }

}