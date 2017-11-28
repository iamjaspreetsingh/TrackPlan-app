package com.jskgmail.attendance;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jetradar.desertplaceholder.DesertPlaceholder;

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

        DesertPlaceholder desertPlaceholder = (DesertPlaceholder) findViewById(R.id.placeholder);
        desertPlaceholder.setOnButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do stuff

            }
        });



        listView=(ListView) findViewById(R.id.listView65);

        checkdata();
        lviewAdapter=new ListViewAdapterAttendance(this,stringArrayList);
        listView.setAdapter(lviewAdapter);
        TextView t=(TextView)findViewById(R.id.textView59);
if(lviewAdapter.isEmpty())
{t.setVisibility(View.INVISIBLE);
   desertPlaceholder.setVisibility(View.VISIBLE);
}
else {
    desertPlaceholder.setVisibility(View.GONE);
t.setVisibility(View.VISIBLE);
}







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
