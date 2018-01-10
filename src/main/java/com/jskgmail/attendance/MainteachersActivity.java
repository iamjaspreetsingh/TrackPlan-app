package com.jskgmail.attendance;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainteachersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AdView madview;
   static FirebaseDatabase database;
    ListViewAdteach lviewAdapter;
    static String colname="";
    private ArrayList<String> stringArrayList, stringArrayList1;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainteachers);
         database = FirebaseDatabase.getInstance();
        MobileAds.initialize(this,"ca-app-pub-9293221301322595/9833525905");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        madview=(AdView)findViewById(R.id.adView);
        AdRequest adrequest=new AdRequest.Builder().build();
        madview.loadAd(adrequest);
        final ListView listView=(ListView)findViewById(R.id.lvt);
        stringArrayList = new ArrayList<String>();
        stringArrayList1 = new ArrayList<String>();

check();
        BA = BluetoothAdapter.getDefaultAdapter();
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn, 0);

        pairedDevices = BA.getBondedDevices();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addclass();


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Databaseteaclass db = new Databaseteaclass(getApplicationContext());
        List<Teacherclass> contacts = db.getAllContacts();
        int chh=0;
        for (Teacherclass cn : contacts) {
            chh++;}
        if (chh==0) {
            TapTargetView.showFor(MainteachersActivity.this,                 // `this` is an Activity
                    TapTarget.forView(findViewById(R.id.fab1), "Add the classes", "Start adding the classes to take attendance")
                            // All options below are optional
                            .transparentTarget(true)              // Specify whether the target is transparent (displays the content underneath)
                            // Specify a custom drawable to draw as the target
                            .targetRadius(50),                  // Specify the target radius (in dp)
                    new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);

                            // This call is optional

                        }
                    });

        }






        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i=new Intent(MainteachersActivity.this,MaincameraaActivity.class);
                startActivity(i);





            }
        });



        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer1,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id=item.getItemId();
        if(id==R.id.setting) {

            Intent i=new Intent(MainteachersActivity.this,MainsettingteacherActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


/* public void on(View v){
      if (!BA.isEnabled()) {
        Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnOn, 0);
        Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
    } else {
        Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
    }
}

    public void off(View v){
        BA.disable();
        Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
    }


    public  void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }


    public void list(View v){
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);
    }
    */



    void addclass()
    {
        SharedPreferences pref=getSharedPreferences("who",0);
        final String techername=pref.getString("teachername","N.A.");
      colname=pref.getString("cllgname","N.A.");
        SharedPreferences pref1=getSharedPreferences("teachpass",0);
        final String pass=pref1.getString("password","N.A.");

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutaddclass, null);
         final EditText subname=(EditText)alertLayout.findViewById(R.id.editText6);
        final EditText classname=(EditText)alertLayout.findViewById(R.id.editText9);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setTitle("Add class ");
        alert.setIcon(R.drawable.ic_add_circle_outline_black_24dp);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final ListView listView=(ListView)findViewById(R.id.lvt);


        alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {



                DatabaseReference myRef = database.getReference("Colleges");
                DatabaseReference myRef1 = database.getReference("Colleges").child(colname).child(subname.getText().toString()+classname.getText().toString());
                myRef1.child("Take").setValue("0");

                myRef1.child("Techname").setValue(techername);
                myRef1.child("Subname").setValue(subname.getText().toString());
                myRef1.child("classname").setValue(classname.getText().toString());
                myRef1.child("Password").setValue(pass);
                myRef1.child("Students").setValue("stu");
                stringArrayList.add(subname.getText().toString());
                stringArrayList1.add(classname.getText().toString());
                lviewAdapter = new ListViewAdteach(MainteachersActivity.this, stringArrayList1, stringArrayList);
                listView.setAdapter(lviewAdapter);

                Databaseteaclass db = new Databaseteaclass(MainteachersActivity.this);
                db.addContact(new Teacherclass(subname.getText().toString(), classname.getText().toString()));













            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();








    }

void check()
{ListView listView=(ListView)findViewById(R.id.lvt);
    Databaseteaclass db1 = new Databaseteaclass(getApplicationContext());
    List<Teacherclass> contacts = db1.getAllContacts();

    for (Teacherclass cn : contacts) {
        Log.d("cccccc", cn.getName());
        stringArrayList.add(cn.getName());
        stringArrayList1.add(cn.getcName());

}
    lviewAdapter = new ListViewAdteach(MainteachersActivity.this, stringArrayList1, stringArrayList);
    listView.setAdapter(lviewAdapter);



}






}
