package com.jskgmail.attendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
static ListView list,friendlist;
    ListViewAdaptersea adapter;

static     ListViewAdapteraddfri fradapter;
    static ArrayList<String> arrayList29=new ArrayList<>();
    static ArrayList<String> arrayList229=new ArrayList<>();
    static ArrayList<String> arrayList2299=new ArrayList<>();
    ArrayList<String> arrayList2=new ArrayList<>();
    ArrayList<String> arrayList22=new ArrayList<>();
    ArrayList<String> arrayList202=new ArrayList<>();
    SearchView search;
    String[] names;
     int i;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> arrayList1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);







        list=(ListView)findViewById(R.id.listsearch);
         friendlist=(ListView)findViewById(R.id.friendlist);

        String percent=  MainActivity.percentagesending;
        TextView t=(TextView)findViewById(R.id.textView55);
        t.setText("My overall % : "+percent+" %");
        TextView tt=(TextView)findViewById(R.id.textView42);
tt.setText(" Logged in as \n "+ConnectActivity.mynaam+"\n ("+ConnectActivity.usernamee+")");

        search= (SearchView) findViewById(R.id.searchView);
        search.setOnQueryTextListener(this);

        final ImageButton imageButton=(ImageButton)findViewById(R.id.imageButton6);
final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar3);
progressBar.setIndeterminate(true);
         arrayList29=new ArrayList<>();
        arrayList229=new ArrayList<>();
        arrayList2299=new ArrayList<>();


        fradapter=new ListViewAdapteraddfri(SearchActivity.this,arrayList29,arrayList229,arrayList2299);


        friendlist.setAdapter(fradapter);

        arrayList29=new ArrayList<>();
        arrayList229=new ArrayList<>();
        arrayList2299=new ArrayList<>();



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    DatabaseFriend db = new DatabaseFriend(getApplicationContext());
                    List<Friends> contacts = db.getAllContacts();

                    for (Friends cn : contacts) {
Log.d("checccccc",cn.getName());
                    }
                    int chthisuser=0;



                    for (Friends cn : contacts) {

                        if(cn.getName().equals(dataSnapshot1.getKey())) {

if((""+dataSnapshot1.child("disc").getValue()).equals("1"))

                                { arrayList29.add(dataSnapshot1.getKey());
                                arrayList229.add(" : " + dataSnapshot1.child("name").getValue());
                                    arrayList2299.add(""+dataSnapshot1.child("percent").getValue());

                            }
                        }


                    }



                }
               fradapter=new ListViewAdapteraddfri(SearchActivity.this,arrayList29,arrayList229,arrayList2299);

progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.GONE);
             friendlist.setAdapter(fradapter);

friendlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


go(position);




        return true;
    }
});

            } @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(SearchActivity.this,MainfriendlistActivity.class);
                startActivity(i);

            }
        });





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
        newText=newText.toLowerCase();
list.setVisibility(View.VISIBLE);
if(text.equals(""))
    list.setVisibility(View.GONE);
        arrayList2=new ArrayList<>();
        arrayList22=new ArrayList<>();
        arrayList202=new ArrayList<>();


        adapter=new ListViewAdaptersea(SearchActivity.this,arrayList2,arrayList22,arrayList202);


        list.setAdapter(adapter);

         arrayList2=new ArrayList<>();
    arrayList22=new ArrayList<>();
        arrayList202=new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("user");


        final String finalNewText = newText;
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {









                arrayList2=new ArrayList<>();
                arrayList22=new ArrayList<>();
                arrayList202=new ArrayList<>();


                adapter=new ListViewAdaptersea(SearchActivity.this,arrayList2,arrayList22,arrayList202);


                list.setAdapter(adapter);

                arrayList2=new ArrayList<>();
                arrayList22=new ArrayList<>();
                arrayList202=new ArrayList<>();

















                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    DatabaseFriend db=new DatabaseFriend(getApplicationContext());
List<Friends> contacts=db.getAllContacts();



                    if ((( dataSnapshot1.getKey()).toLowerCase().contains(finalNewText)) || ((String.valueOf(dataSnapshot1.child("name").getValue()).toLowerCase().contains(finalNewText))))
                    {
                        if(((""+dataSnapshot1.child("disc").getValue()).equals("1")))

                        {
                        Log.d("sosoooo",  dataSnapshot1.getKey());
                        Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                        if(!(dataSnapshot1.getKey().equals(ConnectActivity.usernamee))) {
                            if(!(arrayList2.contains(dataSnapshot1.getKey())))
                            {arrayList2.add( dataSnapshot1.getKey());
                            arrayList22.add(" : " + dataSnapshot1.child("name").getValue());
                         arrayList202.add(""+dataSnapshot1.child("percent").getValue());
                        }}
                    }}

                }
                adapter=new ListViewAdaptersea(SearchActivity.this, arrayList2, arrayList22,arrayList202);


                list.setAdapter(adapter);


            } @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
















        return false;
    }



void go(final int position)
{

    LayoutInflater inflater = getLayoutInflater();
    View alertLayout = inflater.inflate(R.layout.layoutdeletefriend, null);


    AlertDialog.Builder alert = new AlertDialog.Builder(this);

    // this is set the view from XML inside AlertDialog
    alert.setView(alertLayout);
    // disallow cancel of AlertDialog on click of back button and outside touch
    alert.setTitle("Confirm delete");
    alert.setIcon(R.drawable.ic_add_alert_black_24dp);
    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });


    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {


        @Override
        public void onClick(DialogInterface dialog, int which) {



            DatabaseFriend db = new DatabaseFriend(getApplicationContext());
            List<Friends> contacts = db.getAllContacts();


            for (Friends cn : contacts) {
                if(cn.getName().equals(arrayList29.get(position)))
                {
                    db.deleteContact(cn);
                    db.updateContact(cn);
                    break;
                }


            }

            arrayList29.remove(position);
            arrayList229.remove(position);
            arrayList2299.remove(position);

            fradapter=new ListViewAdapteraddfri(SearchActivity.this,arrayList29,arrayList229,arrayList2299);
            friendlist.setAdapter(fradapter);

        }
    });


    AlertDialog dialog = alert.create();
    dialog.show();

}

}
