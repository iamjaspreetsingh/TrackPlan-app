package com.jskgmail.attendance;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sp1=getSharedPreferences("yourprefs", Activity.MODE_PRIVATE);
        int myvalue1=sp1.getInt("123999",1);
        SharedPreferences sp=getSharedPreferences("who", Activity.MODE_PRIVATE);
        String who=sp.getString("iam","");
        if((myvalue1==1))
        {
            if (who.equals("")) {
                Intent i = new Intent(LoginActivity.this, WhoareyouActivity.class);
                startActivity(i);
            }
            else if(who.equals("teacher")) {
                Intent i = new Intent(LoginActivity.this, MainteachersActivity.class);
                startActivity(i);
            }
            else

            {
            Intent i=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);}
            finish();

        }
        SharedPreferences pref=getSharedPreferences("password",0);
        final String passwo=pref.getString("password","");
        Log.i("passssssssss",passwo);
        final EditText passenter=(EditText)findViewById(R.id.password);

        Button can=(Button)findViewById(R.id.canc);


        Button don=(Button)findViewById(R.id.don);
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { final String p=passenter.getText().toString();
                Log.i("passsssssentersss",p);
                if(p.equals(passwo))
                {  Intent i=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            else Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_LONG).show();
            }


        });
       can.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               finish();


           }
       });

}




}