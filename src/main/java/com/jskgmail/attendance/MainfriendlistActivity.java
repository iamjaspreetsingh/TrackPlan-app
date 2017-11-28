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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainfriendlistActivity extends AppCompatActivity {
    ListViewAdfrlist adapter;
    ArrayList<String> arrayList2 = new ArrayList<>();
    ArrayList<String> arrayList22 = new ArrayList<>();
    ArrayList<String> arrayList202 = new ArrayList<>();
    int positioni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfriendlist);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        final FirebaseDatabase database= FirebaseDatabase.getInstance();


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

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

                positioni = position;


                {

                    final ListView frlist = (ListView) findViewById(R.id.frcomp);
                    DatabaseReference myRef= database.getReference("user");

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                DatabaseFriend db1 = new DatabaseFriend(getApplicationContext());
                                List<Friends> contacts = db1.getAllContacts();

                                for (Friends cn : contacts) {
                                    Log.d("cccccc", cn.getName());

                                    if (dataSnapshot1.getKey().equals(cn.getName())) {
                                        Log.d("soso", dataSnapshot1.getKey());
                                        Log.e("sosooo", "" + dataSnapshot1.child("name").getValue());
                                        if (!(dataSnapshot1.getKey().equals(ConnectActivity.usernamee))) {
                                            arrayList2.add(dataSnapshot1.getKey());
                                            arrayList22.add(" : " + dataSnapshot1.child("name").getValue());
                                            arrayList202.add("" + dataSnapshot1.child("percent").getValue());

                                        }

                                    }
                                }


                            }

                            if (positioni == 1) {

                                arrayList2.add(ConnectActivity.usernamee);
                                arrayList22.add(ConnectActivity.mynaam);
                                arrayList202.add(MainActivity.percentagesending);
                                for (int i = 0; i < arrayList2.size(); i++) {
                                    for (int j = i; j < arrayList2.size(); j++) {
                                        if (arrayList202.get(i).equals(""))
                                            arrayList202.set(i, "0.0");
                                        if (arrayList202.get(j).equals(""))
                                            arrayList202.set(j, "0.0");
                                        if (Float.valueOf(arrayList202.get(i)) < Float.valueOf(arrayList202.get(j))) {
                                            String temp = arrayList202.get(i);
                                            arrayList202.set(i, arrayList202.get(j));
                                            arrayList202.set(j, temp);

                                            String temp1 = arrayList22.get(i);
                                            arrayList22.set(i, arrayList22.get(j));
                                            arrayList22.set(j, temp1);

                                            String temp2 = arrayList2.get(i);

                                            arrayList2.set(i, arrayList2.get(j));
                                            arrayList2.set(j, temp2);
                                        }
                                    }

                                }


                            } else if (positioni == 0)

                            {
                                arrayList2.add(ConnectActivity.usernamee);
                                arrayList22.add(ConnectActivity.mynaam);
                                arrayList202.add(MainActivity.percentagesending);
                                Log.d("zzzzzzz", "dec");

                                for (int i = 0; i < arrayList2.size(); i++) {
                                    for (int j = i; j < arrayList2.size(); j++)
                                        if (Float.valueOf(arrayList202.get(i)) > Float.valueOf(arrayList202.get(j))) {
                                            String temp = arrayList202.get(i);

                                            arrayList202.set(i, arrayList202.get(j));
                                            arrayList202.set(j, temp);

                                            String temp1 = arrayList22.get(i);

                                            arrayList22.set(i, arrayList22.get(j));
                                            arrayList22.set(j, temp1);

                                            String temp2 = arrayList2.get(i);

                                            arrayList2.set(i, arrayList2.get(j));
                                            arrayList2.set(j, temp2);
                                        }

                                }


                            }
                            adapter = new ListViewAdfrlist(MainfriendlistActivity.this, arrayList2, arrayList22, arrayList202);
                            frlist.setAdapter(adapter);
                            arrayList2 = new ArrayList<>();
                            arrayList22 = new ArrayList<>();
                            arrayList202 = new ArrayList<>();
















                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });




                }










































            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });}

    void go(int positioni){}
    }

