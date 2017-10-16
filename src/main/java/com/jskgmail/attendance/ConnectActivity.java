package com.jskgmail.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ConnectActivity extends AppCompatActivity {
  static   String mynaam="",usernamee="fd";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        final EditText name=(EditText)findViewById(R.id.editText3);
        final EditText username=(EditText)findViewById(R.id.editText4);
        Button button=(Button)findViewById(R.id.button7) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] check = {0};
final String TAG="what";
                mynaam=name.getText().toString();
                usernamee=username.getText().toString();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("user");
                DatabaseReference myRef1 = database.getReference("user").child(usernamee);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {if(dataSnapshot1.getKey().equals(usernamee))
                            {Log.d("plzzz", ""+dataSnapshot1.getKey());
                                check[0] =1;

                                Toast.makeText(getApplicationContext(),"Username exists already",Toast.LENGTH_LONG).show();
                                break;
                            }}

                    }



                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });




                        if(check[0] ==0)


                        {




                            myRef1.child("username").setValue(usernamee);
                            myRef1.child("name").setValue(mynaam);
                            myRef1.child("percent").setValue(MainActivity.percentagesending);


                            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                            List<Contact> contacts = db.getAllContacts();



                            for (Contact cn : contacts) {

                                if ((cn.getPo().equals(MainActivity.semno))) {

                                    int ab = Integer.parseInt(cn.getAbssent());
                                    int pr = Integer.parseInt(cn.getPresent());
                                    float per;
                                    if ((ab == 0) && (pr == 0))
                                        per = 0;
                                    else
                                        per = (float) (pr * 100 / (pr + ab));
                                    myRef1.child("subjects").child(cn.getName()).setValue(per);
                                }}









                            Log.d("djfdjfdfjdfn",usernamee);




                            Intent i=new Intent(ConnectActivity.this,SearchActivity.class);
                            startActivity(i);






















                        }




















                    }
        });











    }

    private void go() {







    }
}
