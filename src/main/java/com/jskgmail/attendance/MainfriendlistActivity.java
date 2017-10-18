package com.jskgmail.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");
        final ListView frlist=(ListView)findViewById(R.id.frcomp);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

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
                            Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                            if (!(dataSnapshot1.getKey().equals(ConnectActivity.usernamee))) {
                                arrayList2.add(dataSnapshot1.getKey());
                                arrayList22.add(" : " + dataSnapshot1.child("name").getValue());
                                arrayList202.add("" + dataSnapshot1.child("percent").getValue());
                            }
                        }
                    }

                }
                adapter = new ListViewAdfrlist(MainfriendlistActivity.this, arrayList2, arrayList22, arrayList202);


                frlist.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




}
}
