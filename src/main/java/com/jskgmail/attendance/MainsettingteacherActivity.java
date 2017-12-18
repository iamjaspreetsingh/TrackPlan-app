package com.jskgmail.attendance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainsettingteacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsettingteacher);
        final EditText teachername=(EditText)findViewById(R.id.editText7);
        final EditText coname=(EditText)findViewById(R.id.editText8);
        Button don=(Button)findViewById(R.id.button13);
        SharedPreferences pref=getSharedPreferences("who",0);
        final String techername=pref.getString("teachername","N.A.");
        final String cllgname=pref.getString("cllgname","N.A.");
        Button pass=(Button)findViewById(R.id.passwordtea);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

gosetpass();


            }
        });
if (!techername.equals("N.A."))
    teachername.setText(techername);
if (!cllgname.equals("N.A."))
    coname.setText(cllgname);
        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("who", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("teachername", teachername.getText().toString());
                editor.putString("cllgname", coname.getText().toString());
                editor.commit();
                finish();
            }
        });
    }





    private void gosetpass()
    {



        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutpassword, null);
        final EditText pass=(EditText)alertLayout.findViewById(R.id.passwo);
        final EditText passch=(EditText)alertLayout.findViewById(R.id.editText2);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setTitle("Password ");
        alert.setIcon(R.drawable.ic_lock_outline_black_24dp);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ppp=passch.getText().toString();
              String  password = pass.getText().toString();
                if(ppp.equals(password)) {
                    SharedPreferences.Editor editor = getSharedPreferences("teachpass", 0).edit();
                    editor.putString("password", password);
                    editor.commit();
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Password set successfully .. ",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Passwords donot match Please try again ..",Toast.LENGTH_LONG).show();
                }

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();







    }













}
