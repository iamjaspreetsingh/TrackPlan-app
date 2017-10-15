package com.jskgmail.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                FirebaseDatabase database = FirebaseDatabase.getInstance();


                mynaam=name.getText().toString();
                usernamee=username.getText().toString();
                DatabaseReference myRef = database.getReference(usernamee);
Log.d("djfdjfdfjdfn",usernamee);
                myRef.setValue(usernamee);




                myRef.child("name").setValue(mynaam);
                myRef.child("percent").setValue(MainActivity.percentagesending);


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
myRef.child("subjects").child(cn.getName()).setValue(per);
                    }}









                    }
        });











    }

    private void go() {






    }
}
