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

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
ListView list;
    ListViewAdaptersea adapter;
    SearchView search;
    String[] names;
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
        search= (SearchView) findViewById(R.id.searchView);
        search.setOnQueryTextListener(this);

list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("yoyoy",arrayList.get(position));
    }
});

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text=newText;
        Log.v("sosos",text);
int i=adapter.getCount();
        ArrayList<String> arrayList2=new ArrayList<>();
        ArrayList<String> arrayList22=new ArrayList<>();
        if(text.equals(""))
        {
            for (int j = 0; j < i; j++) {
              arrayList2=new ArrayList<>(null);
               arrayList22=new ArrayList<>(null);
                    arrayList2.add(arrayList.get(j));
                    arrayList22.add(arrayList1.get(j));
                    adapter=new ListViewAdaptersea(this,arrayList2,arrayList22);
                    list.setAdapter(adapter);}
        }else{ arrayList2.clear();
            arrayList22.clear();
            arrayList2=new ArrayList<>(null);
            arrayList22=new ArrayList<>(null);
           for (int j = 0; j < i; j++) {
               if ((String.valueOf(arrayList.get(j)).contains(text)) || ((String.valueOf(arrayList1.get(j)).contains(text)))) {
                   arrayList2.add(arrayList.get(j));
                   arrayList22.add(arrayList1.get(j));
                   adapter=new ListViewAdaptersea(this,arrayList2,arrayList22);
                   list.setAdapter(adapter);

               }}
           }


        return false;
    }
}
