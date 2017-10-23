package com.jskgmail.attendance;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main99Activity extends AppCompatActivity {
    private ListView listView;
    ImageView sad;
    ListViewAdapterAttendance lviewAdapter;


    private ArrayList<String> stringArrayList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main99);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        stringArrayList= new ArrayList<String>();



        listView=(ListView) findViewById(R.id.listView65);
      sad=(ImageView)findViewById(R.id.sad);

        checkdata();
        lviewAdapter=new ListViewAdapterAttendance(this,stringArrayList);
        listView.setAdapter(lviewAdapter);
if(lviewAdapter.isEmpty())
{
    sad.setVisibility(View.VISIBLE);
}
else sad.setVisibility(View.GONE);
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
