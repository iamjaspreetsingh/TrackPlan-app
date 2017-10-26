package com.jskgmail.attendance;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainfriendlistActivity extends AppCompatActivity {
    ListViewAdfrlist adapter;
    final ArrayList<String> arrayList2=new ArrayList<>();
    final ArrayList<String> arrayList22=new ArrayList<>();
    final ArrayList<String> arrayList202=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfriendlist);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final ListView frlist=(ListView)findViewById(R.id.frcomp);
        final Spinner spinner=(Spinner)findViewById(R.id.spinner);

        List<String> category1 = new ArrayList<String>();
        category1.add("Inc. Attendance");
        category1.add("Dec. Attendance");


        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter1);

       spinner.setSelection(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








                    DatabaseFriend db1 = new DatabaseFriend(getApplicationContext());
                    List<Friends> contacts = db1.getAllContacts();

                    for (Friends cn : contacts) {
                        Log.d("cccccc", cn.getName());
                    }


                                arrayList2.add("");
                                arrayList22.add(" : " );
                                arrayList202.add("" );


        adapter = new ListViewAdfrlist(MainfriendlistActivity.this, arrayList2, arrayList22, arrayList202);


        frlist.setAdapter(adapter);






    }








}

