package com.jskgmail.attendance;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main99Activity extends AppCompatActivity {
    private ListView listView;
    ListViewAdapterAttendance lviewAdapter;


    private ArrayList<String> stringArrayList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main99);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        stringArrayList= new ArrayList<String>();



        listView=(ListView) findViewById(R.id.listView65);


        checkdata();
        lviewAdapter=new ListViewAdapterAttendance(this,stringArrayList);
        listView.setAdapter(lviewAdapter);

    }






    void checkdata()
    {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            if ((cn.getPo().equals(MainActivity.semno))) {

                stringArrayList.add(cn.getName());


            }
        }


    }













}
