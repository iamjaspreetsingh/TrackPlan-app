package com.jskgmail.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class MainteachersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AdView madview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainteachers);
        MobileAds.initialize(this,"ca-app-pub-9293221301322595/9833525905");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        madview=(AdView)findViewById(R.id.adView);
        AdRequest adrequest=new AdRequest.Builder().build();
        madview.loadAd(adrequest);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();
        int chh=0;
        for (Contact cn : contacts) {
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

            Intent i=new Intent(MainteachersActivity.this,MainsettingActivity.class);
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
}
