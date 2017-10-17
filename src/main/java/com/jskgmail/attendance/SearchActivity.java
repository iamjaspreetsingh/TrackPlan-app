package com.jskgmail.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
ListView list;
    ListViewAdaptersea adapter;
    SearchView search;
    String[] names;
     int i;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayList1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        names=new String[]{"ab","dbd","ffddf","gfgf"
        };
        list=(ListView)findViewById(R.id.listsearch);






        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");

        myRef.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                        Log.d("soso", "" + dataSnapshot1.getKey());
                    Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                    arrayList.add("" + dataSnapshot1.getKey());
arrayList1.add(" : " + dataSnapshot1.child("name").getValue());


                }


            } @Override
            public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


        adapter=new ListViewAdaptersea(this,arrayList,arrayList1);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("sosososososozzzz",arrayList.get(position));
            }
        });


        search= (SearchView) findViewById(R.id.searchView);
        search.setOnQueryTextListener(this);



    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }
    int kk=0;
    @Override
    public boolean onQueryTextChange(String newText) {
        String text=newText;
        Log.v("sosos",text);
text=text.toLowerCase();




        final ArrayList<String> arrayList2=new ArrayList<>();
        final ArrayList<String> arrayList22=new ArrayList<>();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");


        final String finalText = text;
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    if ((( dataSnapshot1.getKey()).toLowerCase().contains(finalText)) || ((String.valueOf(dataSnapshot1.child("name").getValue()).toLowerCase().contains(finalText))))
                    {

                        Log.d("soso",  dataSnapshot1.getKey());
                        Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                        arrayList2.add("" + dataSnapshot1.getKey());
                        arrayList22.add(" : " + dataSnapshot1.child("name").getValue());

                    }
                }


            } @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        adapter=new ListViewAdaptersea(this,arrayList2,arrayList22);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("sosososososozzzz",arrayList2.get(position));
                goo(arrayList2.get(position));
            }
        });













        return true;
    }







   void goo(final String s)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");



        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                 if( dataSnapshot1.getKey().equals(s)) {
                     Log.d("soso", dataSnapshot1.getKey());
                     Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                     Log.d("sosooperc", "" + dataSnapshot1.child("percent").getValue());
                     Log.d("sosooosub", "" + dataSnapshot1.child("subjects").getValue());
                 }
                }


            } @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });












        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();

String[] sub = new String[50];
int i=0,j=1;
        for (Contact cn : contacts) {

            if ((cn.getPo().equals(MainActivity.semno))) {

                int ab = Integer.parseInt(cn.getAbssent());
                int pr = Integer.parseInt(cn.getPresent());
                float per;
                if ((ab == 0) && (pr == 0))
                    per = 0;
                else
                    per = (float) (pr * 100 / (pr + ab));
             sub[i]=  (cn.getName());
                sub[j]= String.valueOf(per);
            i+=2;j+=2;

            }

        }

String usernamee=ConnectActivity.usernamee;
    String naam=    ConnectActivity.mynaam;
      String percent=  MainActivity.percentagesending;









    }
}
