package com.jskgmail.attendance;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainsettingActivity extends AppCompatActivity {
    private ArrayList<String> stringArrayList, stringArrayList1, stringArrayList2, stringArrayList3, stringArrayList4;
    private ListView listView;
    static String password ;
    String semno;
    String semselected;
static  int hour=0,min=0;
    ListViewAdapter lviewAdapter;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_mainsetting);
        final Button reset=(Button)findViewById(R.id.button5);


        final Button delet=(Button)findViewById(R.id.button6);
        final Switch notif=(Switch)findViewById(R.id.switch1);
        final Switch auth=(Switch)findViewById(R.id.authen);
final Button notisetttime=(Button)findViewById(R.id.button10) ;
        final Button passw=(Button)findViewById(R.id.password);

        final Switch notifiii=(Switch)findViewById(R.id.switch2);

        gotosetcurrsem();




















        SharedPreferences sp2=getSharedPreferences("yourprefsofp",Activity.MODE_PRIVATE);
        int myvalue2=sp2.getInt("99999",1);
        if(myvalue2==0)
        { notifiii.setChecked(true);}
        else  if(myvalue2==1)
            notifiii.setChecked(false);

        notifiii.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(notifiii.isChecked()==false)
                { setdisableprmu("99999");


                }
                else {setenablprmu("99999");
                }

            }
        });
if(myvalue2==0)
    Log.d("svavavva","avvaavva");




















        SharedPreferences sp=getSharedPreferences("yourpref",Activity.MODE_PRIVATE);
        int myvalue=sp.getInt("123455",0);
        SharedPreferences sp1=getSharedPreferences("yourprefs",Activity.MODE_PRIVATE);
        int myvalue1=sp1.getInt("123999",1);
        if(myvalue==0)
        { notif.setChecked(true);}
        else  if(myvalue==1)
            notif.setChecked(false);
        if(myvalue==1)
        {  notisetttime.setEnabled(false); if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        notisetttime.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
        notisetttime.setTextSize(21);}}
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notif.isChecked()==false)
                { setdisable("123455");
                    notisetttime.setEnabled(false); if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    notisetttime.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                    notisetttime.setTextSize(21);}

                }
                else {setenable("123455");  notisetttime.setEnabled(true);  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  notisetttime.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
                    notisetttime.setTextSize(21);}
                }
            }
        });
        if(myvalue1==0)
        { auth.setChecked(true);}
        else  if(myvalue1==1)
            auth.setChecked(false);
        if(myvalue1==1)
        {  passw.setEnabled(false);
          }
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(auth.isChecked()==true)
                { setenableauth("123999"); passw.setEnabled(true);  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  passw.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
                    passw.setTextSize(21);}
                    SharedPreferences pref=getSharedPreferences("password",0);
                    final String passwo=pref.getString("password","");
                    if(passwo.equals(""))
                    Toast.makeText(getApplicationContext(),"Please set the Password",Toast.LENGTH_LONG).show();
                }
                else {



                    setdisableauth("123999");
                    passw.setEnabled(false); if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    passw.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                    passw.setTextSize(21);}
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goo();

            } });
        delet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {goo1();

        }});
        notisetttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go22();
            }
        });
        passw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences pref=getSharedPreferences("password",0);
                final String passwo=pref.getString("password","");
                if(passwo.equals(""))

gosetpass();
                else
                {
                    checkcurr();



                }


            }



        });


    }

    private void checkcurr() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutpasswordcheckcurr, null);
        final EditText pass123=(EditText)alertLayout.findViewById(R.id.passcurr);


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
                String ppp=pass123.getText().toString();
                SharedPreferences pref=getSharedPreferences("password",0);
                final String passwo=pref.getString("password","");
                    if(ppp.equals(passwo)) {
                       gosetpass();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Password entered is wrong Please try again ..",Toast.LENGTH_LONG).show();
                }

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();




























    }
    void gotosetcurrsem()
    {
        SharedPreferences pref1=getSharedPreferences("semcurrent",0);
        final String insem122=pref1.getString("semcurrent","0");

        Spinner currsem= (Spinner) findViewById(R.id.currsemspinner);

        if(insem122.equals("0"))
        {setsemin();}
        SharedPreferences pref2=getSharedPreferences("semcurrent",0);
        final String insem12255=pref2.getString("semcurrent","0");


        List<String> category1 = new ArrayList<String>();
        category1.add("  1st  ");
        category1.add("  2nd  ");
        category1.add("  3rd  ");
        category1.add("  4th  ");
        category1.add("  5th  ");
        category1.add("  6th  ");
        category1.add("  7th  ");
        category1.add("  8th  ");
        category1.add("  9th  ");
        category1.add("  10th  ");
        category1.add("  11th  ");
        category1.add("  12th  ");


        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currsem.setAdapter(dataAdapter1);

currsem.setSelection(Integer.parseInt(insem12255)-1);
        currsem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semselected = (parent.getItemAtPosition(position).toString().replaceAll(" ","").replaceAll("th","").replace("st","").replace("nd","").replace("rd",""));



                SharedPreferences.Editor editor = getSharedPreferences("semcurrent", 0).edit();
                editor.putString("semcurrent", String.valueOf(semselected));
                editor.commit();

                Log.i("semcurdfsfdfsdffsfr",semselected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }





    void setsemin()
    {
    LayoutInflater inflater = getLayoutInflater();
    View alertLayout = inflater.inflate(R.layout.layoutcurrsemonce, null);

    Spinner currsemin = (Spinner) alertLayout.findViewById(R.id.spinnerinitialsem);

    List<String> category1 = new ArrayList<String>();
    category1.add("1st");
    category1.add("2nd");
    category1.add("3rd");
    category1.add("4th");
    category1.add("5th");
    category1.add("6th");
    category1.add("7th");
    category1.add("8th");
    category1.add("9th");
    category1.add("10th");
    category1.add("11th");
    category1.add("12th");


    ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category1);
    dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    currsemin.setAdapter(dataAdapter1);

    currsemin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            semselected = (parent.getItemAtPosition(position).toString().replaceAll(" ","").replaceAll("th","").replace("st","").replace("nd","").replace("rd",""));


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });








    AlertDialog.Builder alert = new AlertDialog.Builder(this);

    // this is set the view from XML inside AlertDialog
    alert.setView(alertLayout);
    // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setTitle("Current semester/class ");
        alert.setIcon(R.drawable.ic_live_help_black_24dp);
        alert.setCancelable(false);


    alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {


        @Override
        public void onClick(DialogInterface dialog, int which) {


            Log.i("semcurdfsfdfsdffsfr",semselected);

            DatabaseHandler db = new DatabaseHandler(getApplicationContext());
            List<Contact> contacts = db.getAllContacts();
            SharedPreferences.Editor editor = getSharedPreferences("semcurrent", 0).edit();
            editor.putString("semcurrent", String.valueOf(semselected));
            editor.commit();

            for (Contact cn : contacts) {
                cn.po=(semselected);
                db.updateContact(cn);
                Log.i("semcurdfsfdfsdffsfr",semselected);
                Log.i("semfrfrfrfrfrr",cn.po);
            }

gotosetcurrsem();
        }
    });
    AlertDialog dialog = alert.create();
    dialog.show();





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
            password = pass.getText().toString();
            if(ppp.equals(password)) {
                SharedPreferences.Editor editor = getSharedPreferences("password", 0).edit();
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

    private void go22() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            Button setnoti=(Button)findViewById(R.id.button10);
            Toast.makeText(getApplicationContext(),"Your mobile doesnot support this feature",Toast.LENGTH_SHORT).show();
            setnoti.setVisibility(View.GONE);
        }
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layouttimepicker, null);

        final TimePicker timep=(TimePicker)alertLayout.findViewById(R.id.timePicker);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


            alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialog, int which) { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timep.getHour();
                    min = timep.getMinute();
                    Log.i(String.valueOf(hour), "fgggggggggggggggggggggggggg");
                }
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();











    }

    void goo()
    {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutwarning1, null);



        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(" Reset all ");
        alert.setIcon(R.drawable.ic_help_outline_black_24dp);
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);

        // disallow cancel of AlertDialog on click of back button and outside touch

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });


        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                go1();

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();



    }
    void goo1()
    {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutwarning2, null);



        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(" Delete all ");
        alert.setIcon(R.drawable.ic_help_outline_black_24dp);
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });


        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                go2();

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();


    }
void go1()
{

    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
    List<Contact> contacts = db.getAllContacts();
    for (Contact cn : contacts) {
        if ((cn.getPo().equals(MainActivity.semno))) {
            cn.setPresent("0");
            cn.setAbssent("0");

            db.updateContact(cn);


        }
    }
}


void go2()
{


    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
    List<Contact> contacts = db.getAllContacts();
    for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))) {
        db.deleteContact(cn);


    }
    }
}

public void setenable(String key)
{
    SharedPreferences sp=getSharedPreferences("yourpref", Activity.MODE_PRIVATE);
    SharedPreferences.Editor editor=sp.edit();
    editor.putInt(key,0);
    editor.commit();
}
    public void setdisable(String key)
    {
        SharedPreferences sp=getSharedPreferences("yourpref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt(key,1);
        editor.commit();
    }

    public void setdisableprmu(String key)
    {
        SharedPreferences sp=getSharedPreferences("yourprefsofp", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt(key,1);
        editor.commit();
    }
    public void setenablprmu(String key)
    {
        SharedPreferences sp=getSharedPreferences("yourprefsofp", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt(key,0);
        editor.commit();
    }


    public void setenableauth(String key)
    {
        SharedPreferences sp=getSharedPreferences("yourprefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt(key,0);
        editor.commit();
    }
    public void setdisableauth(String key)
    {
        SharedPreferences sp=getSharedPreferences("yourprefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt(key,1);
        editor.commit();
    }
}
