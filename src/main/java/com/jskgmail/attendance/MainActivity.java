package com.jskgmail.attendance;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static  String semno="",percentagesending="";
    private ListView listView;
    ListViewAdapter lviewAdapter;
    ArrayList<String> monthh = new ArrayList<String>();
private AdView madview;
    private static String month[] = {
            "j", "ddd", "dfdf"
    };
    private final static String number[] = {
            "jsddsds", "ddsdsdd", "dfdscddf"
    };
    private ArrayList<String> stringArrayList, stringArrayList1, stringArrayList2, stringArrayList3, stringArrayList4;
    String subj, crit, delsub, frname, delsub1,resub="";
    SeekBar criteria;
    static String subbb,recrit="75",delsub2;
    int check = 0;
    static int critt = 25;
   static float percenall=0;
    static int totalsub=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        MobileAds.initialize(this,"ca-app-pub-9293221301322595/9833525905");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sp=getSharedPreferences("yourpref", Activity.MODE_PRIVATE);
        int myvalue=sp.getInt("123455",0);
        SimpleDateFormat sdf1 =new SimpleDateFormat("ddMMyyyy");
        Date dmy=new Date();
        String day1 =sdf1.format(dmy);
        Log.e("ddd",ConnectActivity.mynaam);

        final TextView t=(TextView)findViewById(R.id.empty);
        t.setVisibility(View.INVISIBLE);

     madview=(AdView)findViewById(R.id.adView);
     AdRequest adrequest=new AdRequest.Builder().build();
     madview.loadAd(adrequest);

        SimpleDateFormat sdf =new SimpleDateFormat("EEEE");
        Date d=new Date();
        String day =sdf.format(d);
        Log.i("gfdgffggfdgdf",day);
        final String ddd;
        if(day.contains("Mon"))
            ddd="0";
        else if(day.contains("Tue"))
            ddd="1";
        else if(day.contains("Wed"))
            ddd="2";
        else if(day.contains("Thu"))
            ddd="3";
        else if(day.contains("Fri"))
            ddd="4";
        else if(day.contains("Sat"))
            ddd="5";
        else
            ddd="6";

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();

        int ch=0;
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if ((cn.get_dateprea().contains(day1))&&(!(cn.get_ttable().contains(String.valueOf(ddd))))){ch=1;}}}

                if((myvalue==0)&&(ch==0))
        addNotification();
        Log.i("notofocationisllllllllllllllllllllllllllllllllllllll",String.valueOf(myvalue));

        final CheckedTextView se=(CheckedTextView)findViewById(R.id.checkedTextView);
        final CheckedTextView se1=(CheckedTextView)findViewById(R.id.checkedTextView2);
        final CheckedTextView se2=(CheckedTextView)findViewById(R.id.checkedTextView3);
        final CheckedTextView se3=(CheckedTextView)findViewById(R.id.checkedTextView4);
        se.setVisibility(listView.GONE);
        se1.setVisibility(listView.GONE);
        se2.setVisibility(listView.GONE);
        se3.setVisibility(listView.GONE);
        stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();
        stringArrayList3 = new ArrayList<String>();
        stringArrayList4 = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView6);

        DatabaseHandler db1 = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts1 = db1.getAllContacts();



        checkdata();
        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);

          if (lviewAdapter.isEmpty()==true) {
              Log.i("viviviviviv","dsdsds");
              listView.setAdapter(lviewAdapter);

              t.setVisibility(View.VISIBLE);
          }
          else  listView.setAdapter(lviewAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        se.setVisibility(listView.GONE);
        se1.setVisibility(listView.GONE);
        se2.setVisibility(listView.GONE);
        se3.setVisibility(listView.GONE);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
});
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                frname = stringArrayList1.get(position);

                sendsubname();

                Intent i = new Intent(MainActivity.this, CaldroidSampleActivity.class);
                startActivity(i);


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                delsub1 = stringArrayList1.get(position);
delsub2=delsub1;

                int pos = position;
                gotooptions(pos);
                Log.i("ds", String.valueOf(pos));

                return true;
            }
        });
      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addsub();
                t.setVisibility(View.INVISIBLE);

                listView.setAdapter(lviewAdapter);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    protected void onResume()
    {
        super.onResume();
        final TextView t=(TextView)findViewById(R.id.empty);
        t.setVisibility(View.INVISIBLE);

        SharedPreferences sp=getSharedPreferences("yourpref", Activity.MODE_PRIVATE);
        int myvalue=sp.getInt("123455",0);
        SimpleDateFormat sdf1 =new SimpleDateFormat("ddMMyyyy");
        Date dmy=new Date();
        String day1 =sdf1.format(dmy);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();
        int ch=0;
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if (cn.get_dateprea().contains(day1)) {ch=1;}}}

        if((myvalue==0)&&(ch==0))
            addNotification();
        Log.i("notofocationisllllllllllllllllllllllllllllllllllllll",String.valueOf(myvalue));

        final CheckedTextView se=(CheckedTextView)findViewById(R.id.checkedTextView);
        final CheckedTextView se1=(CheckedTextView)findViewById(R.id.checkedTextView2);
        final CheckedTextView se2=(CheckedTextView)findViewById(R.id.checkedTextView3);
        final CheckedTextView se3=(CheckedTextView)findViewById(R.id.checkedTextView4);
        se.setVisibility(listView.GONE);
        se1.setVisibility(listView.GONE);
        se2.setVisibility(listView.GONE);
        se3.setVisibility(listView.GONE);
        stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();
        stringArrayList3 = new ArrayList<String>();
        stringArrayList4 = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView6);


        checkdata();
        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
        if (lviewAdapter.isEmpty()==true) {
            Log.i("viviviviviv","dsdsds");
            listView.setAdapter(lviewAdapter);

            t.setVisibility(View.VISIBLE);
        }
        else  listView.setAdapter(lviewAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                se.setVisibility(listView.GONE);
                se1.setVisibility(listView.GONE);
                se2.setVisibility(listView.GONE);
                se3.setVisibility(listView.GONE);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                frname = stringArrayList1.get(position);

                sendsubname();

                Intent i = new Intent(MainActivity.this, CaldroidSampleActivity.class);
                startActivity(i);


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                delsub1 = stringArrayList1.get(position);
                delsub2=delsub1;

                int pos = position;
                gotooptions(pos);
                Log.i("ds", String.valueOf(pos));

                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addsub();



                    t.setVisibility(View.INVISIBLE);

                 listView.setAdapter(lviewAdapter);
                listView.setAdapter(lviewAdapter);

            }
        });



    }

    public void addNotification() {
        Calendar calendar = Calendar.getInstance();
        int curHr = calendar.get(Calendar.HOUR_OF_DAY);
        int curMin = calendar.get(Calendar.MINUTE);
        Log.i(String.valueOf(curHr),String.valueOf(curMin));
if(!((curHr>=MainsettingActivity.hour)&&(curMin>MainsettingActivity.min-1))) {
    calendar.set(Calendar.HOUR_OF_DAY, MainsettingActivity.hour);
    calendar.set(Calendar.MINUTE, MainsettingActivity.min);
    calendar.set(Calendar.SECOND, 40);
    Intent intent1 = new Intent(getApplicationContext(), AlarmReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

    am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    calendar.add(android.icu.util.Calendar.DATE,1);
}   }


    void gotooptions(final int position1) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutoptions, null);

        final Button rename = (Button) alertLayout.findViewById(R.id.button);

        Button reset = (Button) alertLayout.findViewById(R.id.button3);
        Button delet = (Button) alertLayout.findViewById(R.id.button4);


        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch

        final AlertDialog dialog = alert.create();
        dialog.show();

        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delsub = delsub1;
                delet();
                lviewAdapter.notifyDataSetChanged();
                listView.setAdapter(lviewAdapter);
                dialog.dismiss();

            }
        });
rename.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog.dismiss();
       String renamename= resub();

Log.i(renamename,"fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");




    }
});




        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                String resetsub = delsub1;
                List<Contact> contacts = db.getAllContacts();
                for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))) {
                    if (cn.getName().equals(resetsub)) {
                        cn.setPresent("0");
                        cn.setAbssent("0");

                        db.updateContact(cn);
                        lviewAdapter.notifyDataSetChanged();
                        listView.setAdapter(lviewAdapter);
                        o();
                        dialog.dismiss();
                    }
                }
            }}
        });




    }
    void checkdataforper() {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if ((cn.getPo().equals(MainActivity.semno))){
            totalsub=cn.getID();
        }}}

        for (Contact cn : contacts) {

            if ((cn.getPo().equals(MainActivity.semno))) {

                int ab = Integer.parseInt(cn.getAbssent());
                int pr = Integer.parseInt(cn.getPresent());
                float per;
                if ((ab == 0) && (pr == 0))
                    per = 0;
                else
                    per = (float) (pr * 100 / (pr + ab));

                per=(float)Math.round(per*100)/100;
                percenall = percenall + per;



        }}
    }


    void checkdata() {
        SharedPreferences pref1=getSharedPreferences("sorting",0);
        final String whichsort=pref1.getString("sorting","0");
        Log.i("fdfdfsortingtypeee",whichsort);
        if(whichsort.equals("0"))
        {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        int ch = 1;
        SharedPreferences pref=getSharedPreferences("semcurrent",0);
         semno=pref.getString("semcurrent","0");
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if ((cn.getPo().equals(MainActivity.semno))){
            totalsub = cn.getID() + 1;
        }}}

        for (Contact cn : contacts) {


            if ((cn.getPo().equals(semno))){


                stringArrayList1.add( cn.getName());
                stringArrayList.add( cn.getPresent()+"      Total:  "+(Integer.parseInt(cn.getPresent())+(Integer.parseInt(cn.getAbssent()))));
                int ab = Integer.parseInt(cn.getAbssent());
                int pr = Integer.parseInt(cn.getPresent());
                float per;
                if ((ab == 0) && (pr == 0))
                    per = 0;
                else
                    per = (float) (pr * 100 / (pr + ab));
                int percen = (int) per;

                percenall = percenall + percen;
                stringArrayList2.add( String.valueOf(percen) + " %");
                int incdec;
                if ((pr == 0) && (ab == 0))
                    incdec = 0;
                else
                    incdec = -Integer.parseInt(cn.getPhoneNumber()) + percen;



                int crittt=Integer.parseInt(cn.getPhoneNumber());
                int tot=pr+ab;
                int pp=pr,tott=tot;
                int next=0,nextt=0;
                per =((float)pr/tot)*100;
                float percc=per;
                if((pr==0)&&(ab==0))
                {
                    stringArrayList3.add("Add the Attendance");
                }

                else if(Integer.parseInt(cn.getPhoneNumber())>per) {
                    while(percc<crittt)
                    {
                        pp++;
                        tott++;
                        percc =((float)pp/tott)*100;
                        next++;
                    }
                    stringArrayList3.add("Need to attend next "+next+" classes !");
                }
                else if(crittt<per)  {



                    while(percc>crittt)
                    {

                        tott++;
                        percc =((float)pp/tott)*100;
                        nextt++;
                    }

                    nextt--;
                    if(nextt==1)
                        stringArrayList3.add ("May leave next 1 class !!.");
                    else   if(nextt!=0)
                        stringArrayList3.add("May leave next "+nextt+" classes !!");
                    else  stringArrayList3.add("On the Track !!");
                }
                else  stringArrayList3.add("On the Track !!");


























                if (incdec >= 0)
                    stringArrayList4.add( "0");
                else
                    stringArrayList4.add( "1");

                ch++;

            }















        }}
        else if(whichsort.equals("byname0"))
        { checkdatafornamee(0);}
        else if(whichsort.equals("byname1"))
            checkdatafornamee(1);
        else if(whichsort.equals("bypresent0"))
            checkdataforpr(0);
        else if(whichsort.equals("bypresent1"))
            checkdataforpr(1);
        else if(whichsort.equals("bypercent0"))
            checkdataforpercenta(0);
        else if(whichsort.equals("bypercent1"))
            checkdataforpercenta(1);
        else if(whichsort.equals("byabsent0"))
            checkdataforab(0);
        else if(whichsort.equals("byabsent1"))
            checkdataforab(1);
        else if(whichsort.equals("byrearrange"))
            checkdataforrearrange();

        }

String resub()
{

    LayoutInflater inflater = getLayoutInflater();
    View alertLayout = inflater.inflate(R.layout.layoutaddsub, null);

    final EditText etUsername = (EditText) alertLayout.findViewById(R.id.et_username);



    AlertDialog.Builder alert = new AlertDialog.Builder(this);
    alert.setTitle("Modify Subject");
    alert.setIcon(R.drawable.ic_edit_black_24dp);
    // this is set the view from XML inside AlertDialog
    alert.setView(alertLayout);
    // disallow cancel of AlertDialog on click of back button and outside touch

    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });


    alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
           int cheeee=1;
rename(etUsername.getText().toString());

        }
    });
    AlertDialog dialog = alert.create();
    dialog.show();

final TextView critsh=(TextView)alertLayout.findViewById(R.id.textView38);
    SeekBar criteria = (SeekBar) alertLayout.findViewById(R.id.seekBar);
    criteria.setProgress(Integer.parseInt(recrit));critsh.setText(recrit+"%");
    criteria.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
progress=progress-progress%5;
           critsh.setText(progress+"%");
            crit = String.valueOf(progress);
            recrit=crit;
            critt = 100 - Integer.parseInt(crit);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });




return "svsfdfds";
}
    void addsub() {


        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutaddsub, null);

        final EditText etUsername = (EditText) alertLayout.findViewById(R.id.et_username);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setTitle(" Add Subject  ");
        alert.setIcon(R.drawable.ic_add_circle_outline_black_24dp);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                subj = etUsername.getText().toString();
                go();

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();


        criteria = (SeekBar) alertLayout.findViewById(R.id.seekBar);

        criteria.setProgress(Integer.parseInt(recrit));
        final TextView critsh=(TextView)alertLayout.findViewById(R.id.textView38);critsh.setText(recrit+"%");
        criteria.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
progress=progress-progress%5;
                critsh.setText(progress+"%");
                crit = String.valueOf(progress);
                Log.e("kyaaaaaaaaaaa",crit);
                recrit=crit;
                critt = 100 - Integer.parseInt(crit);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    void go() {


        stringArrayList.add(subj);
        SharedPreferences pref=getSharedPreferences("semcurrent",0);
        final String insemnoo=pref.getString("semcurrent","0");

        Log.i("semcurrrrrrrrrrrrrrrrrrrrr",insemnoo);
        DatabaseHandler db = new DatabaseHandler(this);
        db.addContact(new Contact(subj, recrit, "0", "0", "","",insemnoo,"",""));

        SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
        editor.putString("sorting", "0");
        editor.commit();




        SharedPreferences pref1=getSharedPreferences("rearrange",0);
        String insemnoo1=pref1.getString("rearrange","0.0");
String pos="0";
        DatabaseHandler db1 = new DatabaseHandler(this);
        List<Contact> contacts = db1.getAllContacts();

        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if ((cn.getPo().equals(MainActivity.semno))){
                pos= String.valueOf(cn.getID());
            }}}

if(!(insemnoo1.equals("0.0"))) {
    String insemnoo2 = insemnoo1 + Integer.parseInt(pos) + "," + subj + ".";
    SharedPreferences.Editor editor1 = getSharedPreferences("rearrange", 0).edit();
    editor1.putString("rearrange", insemnoo2);
    editor1.commit();
}       stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();

        stringArrayList2 = new ArrayList<String>();

        stringArrayList3 = new ArrayList<String>();
        stringArrayList4 = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listView6);


        checkdata();
        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
        listView.setAdapter(lviewAdapter);
        final CheckedTextView se=(CheckedTextView)findViewById(R.id.checkedTextView);
        final CheckedTextView se1=(CheckedTextView)findViewById(R.id.checkedTextView2);
        final CheckedTextView se2=(CheckedTextView)findViewById(R.id.checkedTextView3);
        final CheckedTextView se3=(CheckedTextView)findViewById(R.id.checkedTextView4);
        se.setVisibility(listView.GONE);
        se1.setVisibility(listView.GONE);
        se2.setVisibility(listView.GONE);
        se3.setVisibility(listView.GONE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                frname = stringArrayList1.get(position);

                sendsubname();
                Intent i = new Intent(MainActivity.this, CaldroidSampleActivity.class);
                startActivity(i);


            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                delsub1 = stringArrayList1.get(position);
                delsub2=delsub1;

                int pos = position;
                gotooptions(pos);
                Log.i("ds", String.valueOf(pos));


                return true;
            }
        });


    }

    void sendsubname() {
        subbb = frname;

    }


    void delet() {

        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if (cn.getName().equals(delsub))
                db.deleteContact(cn);
        }}
        stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();

        listView = (ListView) findViewById(R.id.listView6);


        checkdata();
        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
        listView.setAdapter(lviewAdapter);


    }
void o()
{
    stringArrayList = new ArrayList<String>();


    stringArrayList1 = new ArrayList<String>();
    stringArrayList2 = new ArrayList<String>();

    listView = (ListView) findViewById(R.id.listView6);


    checkdata();
    lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
    listView.setAdapter(lviewAdapter);
}
    void rename(String renamename) {

        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if (cn.getName().equals(delsub2))
            {    cn.setName(renamename);
                Log.i("ghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","egefdgfgfd");
Log.i(cn.getName(),renamename);
          cn.setPhoneNumber(recrit);
            db.updateContact(cn);
        }}}
        stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();

        listView = (ListView) findViewById(R.id.listView6);

checkdata();

        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
        listView.setAdapter(lviewAdapter);


    }
    private void setlistsorting() {


        final CheckedTextView se=(CheckedTextView)findViewById(R.id.checkedTextView);
        final CheckedTextView se1=(CheckedTextView)findViewById(R.id.checkedTextView2);
        final CheckedTextView se2=(CheckedTextView)findViewById(R.id.checkedTextView3);
        final CheckedTextView se3=(CheckedTextView)findViewById(R.id.checkedTextView4);
        se.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {                se.setTextAppearance(R.style.TextAppearance_AppCompat_Body2);
                se.setTextSize(17);}
                if(se.isChecked())
                {
checkdatafornamee(0); SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
                    editor.putString("sorting", "byname0");
                    editor.commit();

                    se.setCheckMarkDrawable(R.drawable.ic_arrow_downward_black_24dp);
                    se.setChecked(false);
                }
                else  if(!(se.isChecked()))
                {checkdatafornamee(1);
                    se.setCheckMarkDrawable(R.drawable.ic_arrow_upward_black_24dp);
                    SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
                    editor.putString("sorting", "byname1");
                    editor.commit();

                    se.setChecked(true);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se1.setTextSize(17);
                se2.setTextSize(17); se3.setTextSize(17);}

            }
        });


        se1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se1.setTextAppearance(R.style.TextAppearance_AppCompat_Body2);
                }
                se1.setTextSize(17);
                if(se1.isChecked())
                {checkdataforpr(0);
                    se1.setCheckMarkDrawable(R.drawable.ic_arrow_downward_black_24dp);
                    SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
                    editor.putString("sorting", "bypresent0");
                    editor.commit();

                    se1.setChecked(false);
                }
                else  if(!(se1.isChecked()))
                {checkdataforpr(1);
                    se1.setCheckMarkDrawable(R.drawable.ic_arrow_upward_black_24dp);
                    SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
                    editor.putString("sorting", "bypresent1");
                    editor.commit();
                    se1.setChecked(true);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se.setTextSize(17);
                se2.setTextSize(17); se3.setTextSize(17);



            }
        });



        se2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { se2.setTextAppearance(R.style.TextAppearance_AppCompat_Body2);
                se2.setTextSize(17);}
                if(se2.isChecked())
                {checkdataforab(0);
                    se2.setCheckMarkDrawable(R.drawable.ic_arrow_downward_black_24dp);
                    SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
                    editor.putString("sorting", "byabsent0");
                    editor.commit();
                    se2.setChecked(false);
                }
                else  if(!(se2.isChecked()))
                {checkdataforab(1);
                    se2.setCheckMarkDrawable(R.drawable.ic_arrow_upward_black_24dp);
                    SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
                    editor.putString("sorting", "byabsent1");
                    editor.commit();
                    se2.setChecked(true);
                }  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se1.setTextSize(17);
                se.setTextSize(17); se3.setTextSize(17);}
            }
        });




        se3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { se3.setTextAppearance(R.style.TextAppearance_AppCompat_Body2);
                se3.setTextSize(17);}
                if(se3.isChecked())
                {checkdataforpercenta(0);
                    se3.setCheckMarkDrawable(R.drawable.ic_arrow_downward_black_24dp);
                    SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
                    editor.putString("sorting", "bypercent0");
                    editor.commit();
                    se3.setChecked(false);
                }
                else  if(!(se3.isChecked()))
                {checkdataforpercenta(1);
                    SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
                    editor.putString("sorting", "bypercent1");
                    editor.commit();
                    se3.setCheckMarkDrawable(R.drawable.ic_arrow_upward_black_24dp);
                    se3.setChecked(true);
                }  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                se1.setTextSize(17);
                se2.setTextSize(17); se.setTextSize(17);}
            }
        });






























    }



    void checkdataforpr(int order) {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        int ch = 1;
        SharedPreferences pref=getSharedPreferences("semcurrent",0);
        semno=pref.getString("semcurrent","0");
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if ((cn.getPo().equals(MainActivity.semno))){
                totalsub = cn.getID() + 1;
            }}}
        String na="",prq="",perq="",perinqc="",sig="";String totq="";
        int i=0;
        for (Contact cn : contacts) {

            if ((cn.getPo().equals(semno))){

                na=na+cn.getName()+".";prq=prq+cn.getPresent()+".";

                int ab = Integer.parseInt(cn.getAbssent());
                int pr = Integer.parseInt(cn.getPresent());

                totq=totq+String.valueOf(pr+ab)+".";
                float per;
                if ((ab == 0) && (pr == 0))
                    per = 0;
                else
                    per = (float) (pr * 100 / (pr + ab));
                int percen = (int) per;

                percenall = percenall + percen;
                perq=perq+String.valueOf(percen) + " %.";

                int incdec;
                if ((pr == 0) && (ab == 0))
                    incdec = 0;
                else
                    incdec = -Integer.parseInt(cn.getPhoneNumber()) + percen;
                int crittt=Integer.parseInt(cn.getPhoneNumber());
                int tot=pr+ab;
                int pp=pr,tott=tot;
                int next=0,nextt=0;
                per =((float)pr/tot)*100;
                float percc=per;
                if((pr==0)&&(ab==0))
                {
                    perinqc=perinqc+("Add the Attendance");
                }

                else if(Integer.parseInt(cn.getPhoneNumber())>per) {
                    while(percc<crittt)
                    {
                        pp++;
                        tott++;
                        percc =((float)pp/tott)*100;
                        next++;
                    }
                    perinqc=perinqc+("Need to attend next "+next+" classes !.");
                }
                else if(crittt<per)  {



                    while(percc>crittt)
                    {

                        tott++;
                        percc =((float)pp/tott)*100;
                        nextt++;
                    }

                    nextt--;
                   if(nextt==1)
                       perinqc=perinqc+("May leave next 1 class !!.");
                  else  if(nextt!=0)
                        perinqc=perinqc+("May leave next "+nextt+" classes !!.");
                    else    perinqc=perinqc+("On the Track !!.");
                }
                else    perinqc=perinqc+("On the Track !!.");





                if (incdec >= 0)
                    sig=sig+( "0.");
                else
                    sig=sig+( "1.");

                ch++;

            }


        }



        String[] naa=na.split("\\."),prr=prq.split("\\."),perqinccc=perinqc.split("\\."),perqqqq=perq.split("\\."),sigqq=sig.split("\\.");
      String[] totot=totq.split("\\.");
        for(int ii=0;ii<naa.length-1;ii++) {


            for (int j = 0; j < naa.length - ii - 1; j++) {

 {
    if (Integer.parseInt(prr[j]) > Integer.parseInt(prr[j + 1])) {
        String temp = prr[j];
        prr[j] = prr[j + 1];
        prr[j + 1] = temp;

        String temp1 = naa[j];
        naa[j] = naa[j + 1];
        naa[j + 1] = temp1;

        String temp2 = perqinccc[j];
        perqinccc[j] = perqinccc[j + 1];
        perqinccc[j + 1] = temp2;

        String temp3 = perqqqq[j];
        perqqqq[j] = perqqqq[j + 1];
        perqqqq[j + 1] = temp3;

        String temp4 = sigqq[j];
        sigqq[j] = sigqq[j + 1];
        sigqq[j + 1] = temp4;


    }
}






            }

        }


        stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();
        stringArrayList3 = new ArrayList<String>();
        stringArrayList4 = new ArrayList<String>();
        if(order==1) {
            for (int jjj = 0; jjj < perqinccc.length; jjj++) {
                stringArrayList1.add(naa[jjj]);
                stringArrayList.add(prr[jjj]+"      Total:  "+totot[jjj]);
                stringArrayList2.add(perqqqq[jjj]);
                stringArrayList3.add(perqinccc[jjj]);
                stringArrayList4.add(sigqq[jjj]);
            }
        }
        else {
            for (int jjj = perqinccc.length-1; jjj >= 0; jjj--) {
                stringArrayList1.add(naa[jjj]);
                stringArrayList.add(prr[jjj]+"      Total:  "+totot[jjj]);
                stringArrayList2.add(perqqqq[jjj]);
                stringArrayList3.add(perqinccc[jjj]);
                stringArrayList4.add(sigqq[jjj]);
            }
        }



        listView = (ListView) findViewById(R.id.listView6);



        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
        listView.setAdapter(lviewAdapter);





    }



    void checkdatafornamee(int order) {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        int ch = 1;
        SharedPreferences pref=getSharedPreferences("semcurrent",0);
        semno=pref.getString("semcurrent","0");
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if ((cn.getPo().equals(MainActivity.semno))){
                totalsub = cn.getID() + 1;
            }}}
        String na="",prq="",perq="",perinqc="",sig="";
        int i=0;
        for (Contact cn : contacts) {

            if ((cn.getPo().equals(semno))){

                na=na+cn.getName()+".";prq=prq+cn.getPresent()+"      Total:  "+(Integer.parseInt(cn.getPresent())+(Integer.parseInt(cn.getAbssent()))) +".";


                int ab = Integer.parseInt(cn.getAbssent());
                int pr = Integer.parseInt(cn.getPresent());
                float per;
                if ((ab == 0) && (pr == 0))
                    per = 0;
                else
                    per = (float) (pr * 100 / (pr + ab));
                int percen = (int) per;

                percenall = percenall + percen;
                perq=perq+String.valueOf(percen) + " %.";

                int incdec;
                if ((pr == 0) && (ab == 0))
                    incdec = 0;
                else
                    incdec = -Integer.parseInt(cn.getPhoneNumber()) + percen;
                int crittt=Integer.parseInt(cn.getPhoneNumber());
                int tot=pr+ab;
                int pp=pr,tott=tot;
                int next=0,nextt=0;
                per =((float)pr/tot)*100;
                float percc=per;
                if((pr==0)&&(ab==0))
                {
                    perinqc=perinqc+("Add the Attendance");
                }

                else if(Integer.parseInt(cn.getPhoneNumber())>per) {
                    while(percc<crittt)
                    {
                        pp++;
                        tott++;
                        percc =((float)pp/tott)*100;
                        next++;
                    }
                    perinqc=perinqc+("Need to attend next "+next+" classes !.");
                }
                else if(crittt<per)  {



                    while(percc>crittt)
                    {

                        tott++;
                        percc =((float)pp/tott)*100;
                        nextt++;
                    }

                    nextt--;
                    if(nextt==1)
                        perinqc=perinqc+("May leave next 1 class !!.");
                    else  if(nextt!=0)
                        perinqc=perinqc+("May leave next "+nextt+" classes !!.");
                    else    perinqc=perinqc+("On the Track !!.");
                }
                else    perinqc=perinqc+("On the Track !!.");






                if (incdec >= 0)
                    sig=sig+( "0.");
                else
                    sig=sig+( "1.");

                ch++;

            }


        }



        String[] naa=na.split("\\."),prr=prq.split("\\."),perqinccc=perinqc.split("\\."),perqqqq=perq.split("\\."),sigqq=sig.split("\\.");
        for(int ii=0;ii<naa.length-1;ii++) {


            for (int j = 0; j < naa.length - ii - 1; j++) {


                if(((naa[j]).compareTo(naa[j+1]))>0)
                {
                    String temp=prr[j];
                    prr[j]=prr[j+1];
                    prr[j+1]=temp;

                    String temp1=naa[j];
                    naa[j]=naa[j+1];
                    naa[j+1]=temp1;

                    String temp2=perqinccc[j];
                    perqinccc[j]=perqinccc[j+1];
                    perqinccc[j+1]=temp2;

                    String temp3=perqqqq[j];
                    perqqqq[j]=perqqqq[j+1];
                    perqqqq[j+1]=temp3;

                    String temp4=sigqq[j];
                    sigqq[j]=sigqq[j+1];
                    sigqq[j+1]=temp4;


                }







            }

        }


        stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();
        stringArrayList3 = new ArrayList<String>();
        stringArrayList4 = new ArrayList<String>();
        if(order==1) {
            for (int jjj = 0; jjj < perqinccc.length; jjj++) {
                stringArrayList1.add(naa[jjj]);
                stringArrayList.add(prr[jjj]);
                stringArrayList2.add(perqqqq[jjj]);
                stringArrayList3.add(perqinccc[jjj]);
                stringArrayList4.add(sigqq[jjj]);
            }
        }
        else {
            for (int jjj = perqinccc.length-1; jjj >= 0; jjj--) {
                stringArrayList1.add(naa[jjj]);
                stringArrayList.add(prr[jjj]);
                stringArrayList2.add(perqqqq[jjj]);
                stringArrayList3.add(perqinccc[jjj]);
                stringArrayList4.add(sigqq[jjj]);
            }
        }



        listView = (ListView) findViewById(R.id.listView6);



        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
        listView.setAdapter(lviewAdapter);





    }



    void checkdataforpercenta(int order) {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        int ch = 1;
        SharedPreferences pref=getSharedPreferences("semcurrent",0);
        semno=pref.getString("semcurrent","0");
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if ((cn.getPo().equals(MainActivity.semno))){
                totalsub = cn.getID() + 1;
            }}}
        String na="",prq="",perq="",perinqc="",sig="";
        int i=0;
        for (Contact cn : contacts) {

            if ((cn.getPo().equals(semno))){

                na=na+cn.getName()+".";prq=prq+cn.getPresent()+"      Total:  "+(Integer.parseInt(cn.getPresent())+(Integer.parseInt(cn.getAbssent()))) +".";


                int ab = Integer.parseInt(cn.getAbssent());
                int pr = Integer.parseInt(cn.getPresent());
                float per;
                if ((ab == 0) && (pr == 0))
                    per = 0;
                else
                    per = (float) (pr * 100 / (pr + ab));
                int percen = (int) per;

                percenall = percenall + percen;
                perq=perq+String.valueOf(percen) + " %.";

                int incdec;
                if ((pr == 0) && (ab == 0))
                    incdec = 0;
                else
                    incdec = -Integer.parseInt(cn.getPhoneNumber()) + percen;
                int crittt=Integer.parseInt(cn.getPhoneNumber());
                int tot=pr+ab;
                int pp=pr,tott=tot;
                int next=0,nextt=0;
                per =((float)pr/tot)*100;
                float percc=per;
                if((pr==0)&&(ab==0))
                {
                    perinqc=perinqc+("Add the Attendance");
                }

                else if(Integer.parseInt(cn.getPhoneNumber())>per) {
                    while(percc<crittt)
                    {
                        pp++;
                        tott++;
                        percc =((float)pp/tott)*100;
                        next++;
                    }
                    perinqc=perinqc+("Need to attend next "+next+" classes !.");
                }
                else if(crittt<per)  {



                    while(percc>crittt)
                    {

                        tott++;
                        percc =((float)pp/tott)*100;
                        nextt++;
                    }

                    nextt--;
                    if(nextt!=0)
                        if(nextt==1)
                            perinqc=perinqc+("May leave next 1 class !!.");
                        else   perinqc=perinqc+("May leave next "+nextt+" classes !!.");
                    else    perinqc=perinqc+("On the Track !!.");
                }
                else    perinqc=perinqc+("On the Track !!.");



                if (incdec >= 0)
                    sig=sig+( "0.");
                else
                    sig=sig+( "1.");

                ch++;

            }


        }



        String[] naa=na.split("\\."),prr=prq.split("\\."),perqinccc=perinqc.split("\\."),perqqqq=perq.split("\\."),sigqq=sig.split("\\.");
        for(int ii=0;ii<naa.length-1;ii++) {


            for (int j = 0; j < naa.length - ii - 1; j++) {


                if(Integer.parseInt(perqqqq[j].replaceAll(" ","").replaceAll("%",""))>Integer.parseInt(perqqqq[j+1].replaceAll(" ","").replaceAll("%","")))
                {
                    String temp=prr[j];
                    prr[j]=prr[j+1];
                    prr[j+1]=temp;

                    String temp1=naa[j];
                    naa[j]=naa[j+1];
                    naa[j+1]=temp1;

                    String temp2=perqinccc[j];
                    perqinccc[j]=perqinccc[j+1];
                    perqinccc[j+1]=temp2;

                    String temp3=perqqqq[j];
                    perqqqq[j]=perqqqq[j+1];
                    perqqqq[j+1]=temp3;

                    String temp4=sigqq[j];
                    sigqq[j]=sigqq[j+1];
                    sigqq[j+1]=temp4;


                }







            }

        }


        stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();
        stringArrayList3 = new ArrayList<String>();
        stringArrayList4 = new ArrayList<String>();
        if(order==1) {
            for (int jjj = 0; jjj < perqinccc.length; jjj++) {
                stringArrayList1.add(naa[jjj]);
                stringArrayList.add(prr[jjj]);
                stringArrayList2.add(perqqqq[jjj]);
                stringArrayList3.add(perqinccc[jjj]);
                stringArrayList4.add(sigqq[jjj]);
            }
        }
        else {
            for (int jjj = perqinccc.length-1; jjj >= 0; jjj--) {
                stringArrayList1.add(naa[jjj]);
                stringArrayList.add(prr[jjj]);
                stringArrayList2.add(perqqqq[jjj]);
                stringArrayList3.add(perqinccc[jjj]);
                stringArrayList4.add(sigqq[jjj]);
            }
        }



        listView = (ListView) findViewById(R.id.listView6);



        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
        listView.setAdapter(lviewAdapter);





    }






    void checkdataforab(int order) {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        int ch = 1;
        SharedPreferences pref=getSharedPreferences("semcurrent",0);
        semno=pref.getString("semcurrent","0");
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if ((cn.getPo().equals(MainActivity.semno))){
                totalsub = cn.getID() + 1;
            }}}
String na="",prq="",perq="",perinqc="",sig="",abbb="";
        int i=0;
        for (Contact cn : contacts) {

            if ((cn.getPo().equals(semno))){

na=na+cn.getName()+".";prq=prq+cn.getPresent()+"      Total:  "+(Integer.parseInt(cn.getPresent())+(Integer.parseInt(cn.getAbssent()))) +".";


                int ab = Integer.parseInt(cn.getAbssent());
                int pr = Integer.parseInt(cn.getPresent());
                float per;
                if ((ab == 0) && (pr == 0))
                    per = 0;
                else
                    per = (float) (pr * 100 / (pr + ab));
                int percen = (int) per;

                percenall = percenall + percen;
                perq=perq+String.valueOf(percen) + " %.";
abbb=abbb+String.valueOf(ab)+".";
                int incdec;
                if ((pr == 0) && (ab == 0))
                    incdec = 0;
                else
                    incdec = -Integer.parseInt(cn.getPhoneNumber()) + percen;
                int crittt=Integer.parseInt(cn.getPhoneNumber());
                int tot=pr+ab;
                int pp=pr,tott=tot;
                int next=0,nextt=0;
                per =((float)pr/tot)*100;
                float percc=per;
                if((pr==0)&&(ab==0))
                {
                    perinqc=perinqc+("Add the Attendance");
                }

                else if(Integer.parseInt(cn.getPhoneNumber())>per) {
                    while(percc<crittt)
                    {
                        pp++;
                        tott++;
                        percc =((float)pp/tott)*100;
                        next++;
                    }
                    perinqc=perinqc+("Need to attend next "+next+" classes !.");
                }
                else if(crittt<per)  {



                    while(percc>crittt)
                    {

                        tott++;
                        percc =((float)pp/tott)*100;
                        nextt++;
                    }

                    nextt--;
                    if(nextt==1)
                        perinqc=perinqc+("May leave next 1 class !!.");
                    else    if(nextt!=0)
                        perinqc=perinqc+("May leave next "+nextt+" classes !!.");
                    else    perinqc=perinqc+("On the Track !!.");
                }
                else    perinqc=perinqc+("On the Track !!.");




                if (incdec >= 0)
                   sig=sig+( "0.");
                else
                    sig=sig+( "1.");

                ch++;

            }


        }



        String[] naa=na.split("\\."),prr=prq.split("\\."),perqinccc=perinqc.split("\\."),perqqqq=perq.split("\\."),sigqq=sig.split("\\."),abbbqq=abbb.split("\\.");
        for(int ii=0;ii<naa.length-1;ii++) {


            for (int j = 0; j < naa.length - ii - 1; j++) {


                if(Integer.parseInt(abbbqq[j])>Integer.parseInt(abbbqq[j+1]))
                {
                    String temp=prr[j];
                    prr[j]=prr[j+1];
                    prr[j+1]=temp;

                    String temp1=naa[j];
                    naa[j]=naa[j+1];
                    naa[j+1]=temp1;

                    String temp2=perqinccc[j];
                    perqinccc[j]=perqinccc[j+1];
                    perqinccc[j+1]=temp2;

                    String temp3=perqqqq[j];
                    perqqqq[j]=perqqqq[j+1];
                    perqqqq[j+1]=temp3;

                    String temp4=sigqq[j];
                    sigqq[j]=sigqq[j+1];
                    sigqq[j+1]=temp4;


                }







            }

        }


        stringArrayList = new ArrayList<String>();


        stringArrayList1 = new ArrayList<String>();
        stringArrayList2 = new ArrayList<String>();
        stringArrayList3 = new ArrayList<String>();
        stringArrayList4 = new ArrayList<String>();
if(order==1) {
    for (int jjj = 0; jjj < perqinccc.length; jjj++) {
        stringArrayList1.add(naa[jjj]);
        stringArrayList.add(prr[jjj]);
        stringArrayList2.add(perqqqq[jjj]);
        stringArrayList3.add(perqinccc[jjj]);
        stringArrayList4.add(sigqq[jjj]);
    }
}
else {
    for (int jjj = perqinccc.length-1; jjj >= 0; jjj--) {
        stringArrayList1.add(naa[jjj]);
        stringArrayList.add(prr[jjj]);
        stringArrayList2.add(perqqqq[jjj]);
        stringArrayList3.add(perqinccc[jjj]);
        stringArrayList4.add(sigqq[jjj]);
    }
}



        listView = (ListView) findViewById(R.id.listView6);



        lviewAdapter = new ListViewAdapter(this, stringArrayList1, stringArrayList, stringArrayList2, stringArrayList3, stringArrayList4);
        listView.setAdapter(lviewAdapter);





    }






void checkdataforrearrange()
{

    SharedPreferences pref1=getSharedPreferences("rearrange",0);
    final String insem122=pref1.getString("rearrange","0.0");


    String[] nameposi = insem122.split("\\.");
    for(int ii=0;ii<nameposi.length;ii++) {
        String nameposssss = nameposi[ii];
        String[] nameposgoset = nameposssss.split("\\,");
        Log.i("nameposgo00000", nameposgoset[0]);

        Log.i("nameposgo11111", nameposgoset[1]);



        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        int ch = 1;
        SharedPreferences pref = getSharedPreferences("semcurrent", 0);
        semno = pref.getString("semcurrent", "0");
        for (Contact cn : contacts) {





                if ((cn.getPo().equals(MainActivity.semno))) {
                    if ((cn.getPo().equals(MainActivity.semno))) {
                        totalsub = cn.getID() + 1;
                    }
                }
            }

            for (Contact cn : contacts) {
                if (cn.getName().equals(nameposgoset[1]))

                {

                if ((cn.getPo().equals(semno))) {


                    stringArrayList1.add(cn.getName());
                    stringArrayList.add( cn.getPresent()+"      Total:  "+(Integer.parseInt(cn.getPresent())+(Integer.parseInt(cn.getAbssent()))));

                    int ab = Integer.parseInt(cn.getAbssent());
                    int pr = Integer.parseInt(cn.getPresent());
                    float per;
                    if ((ab == 0) && (pr == 0))
                        per = 0;
                    else
                        per = (float) (pr * 100 / (pr + ab));
                    int percen = (int) per;

                    percenall = percenall + percen;
                    stringArrayList2.add(String.valueOf(percen) + " %");
                    int incdec;
                    if ((pr == 0) && (ab == 0))
                        incdec = 0;
                    else
                        incdec = -Integer.parseInt(cn.getPhoneNumber()) + percen;
                    int crittt=Integer.parseInt(cn.getPhoneNumber());
                    int tot=pr+ab;
                    int pp=pr,tott=tot;
                    int next=0,nextt=0;
                    per =((float)pr/tot)*100;
                    float percc=per;
                    if((pr==0)&&(ab==0))
                    {
                       stringArrayList3.add("Add the Attendance");
                    }

                    else if(Integer.parseInt(cn.getPhoneNumber())>per) {
                        while(percc<crittt)
                        {
                            pp++;
                            tott++;
                            percc =((float)pp/tott)*100;
                            next++;
                        }
                        stringArrayList3.add("Need to attend next "+next+" classes !.");
                    }
                    else if(crittt<per)  {



                        while(percc>crittt)
                        {

                            tott++;
                            percc =((float)pp/tott)*100;
                            nextt++;
                        }

                        nextt--;
                        if(nextt==1)
                           stringArrayList3.add("May leave next 1 class !!.");
                        else    if(nextt!=0)
                            stringArrayList3.add("May leave next "+nextt+" classes !!.");
                        else   stringArrayList3.add("On the Track !!.");
                    }
                    else    stringArrayList3.add("On the Track !!.");



                    if (incdec >= 0)
                        stringArrayList4.add("0");
                    else
                        stringArrayList4.add("1");

                    ch++;

                }
            }


        }


    }












}





















    void gotosetper(float percentageall)
    {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutforperall, null);
        TextView pe=(TextView)alertLayout.findViewById(R.id.peralllll);




        AlertDialog.Builder alert = new AlertDialog.Builder(this);
alert.setTitle("Overall %");
        alert.setIcon(R.drawable.ic_help_outline_black_24dp);
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch


        final AlertDialog dialog = alert.create();
        dialog.show();
        pe.setText("Your Overall percentage is : "+percentageall+"%"+"\n");
        Button ok=(Button)alertLayout.findViewById(R.id.button11);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(this, Main99Activity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            percenall=0;
            totalsub=0;
checkdataforper();
            float perall=(percenall/(float)totalsub);
            perall=(float)Math.round(perall*100)/100;
            Log.i("prcentageisfgfgf",String.valueOf(perall));
            gotosetper(perall);


        } else if (id==R.id.connect)
        {
            percenall=0;
            totalsub=0;
            checkdataforper();
            float perall=(percenall/(float)totalsub);
            Log.i("prcentageisfgfgf",String.valueOf(perall));
            perall=(float)Math.round(perall*100)/100;
            percentagesending=String.valueOf(perall);



            SharedPreferences sp1=this.getSharedPreferences("login",MODE_PRIVATE);
            String unm=sp1.getString("username","");
            String nam=sp1.getString("name","");
            if(!(unm.equals("")))
            {
ConnectActivity.usernamee=unm;
                ConnectActivity.mynaam=nam;
                Intent i=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(i);

            }


            else
            {
            Intent i=new Intent(MainActivity.this,ConnectActivity.class);
            startActivity(i);}




        }





        else if (id == R.id.nav_slideshow) {

            Intent i=new Intent(MainActivity.this,MainhistoryActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            Intent i=new Intent(MainActivity.this,MainrearrActivity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_manage1){
            final CheckedTextView se=(CheckedTextView)findViewById(R.id.checkedTextView);
            final CheckedTextView se1=(CheckedTextView)findViewById(R.id.checkedTextView2);
            final CheckedTextView se2=(CheckedTextView)findViewById(R.id.checkedTextView3);
            final CheckedTextView se3=(CheckedTextView)findViewById(R.id.checkedTextView4);
            se.setVisibility(listView.VISIBLE);
            se1.setVisibility(listView.VISIBLE);
            se2.setVisibility(listView.VISIBLE);
            se3.setVisibility(listView.VISIBLE);
            SharedPreferences pref1=getSharedPreferences("sorting",0);
            final String whichsorti=pref1.getString("sorting","0");
Log.i("fggfdfgfgffsoerttttt",whichsorti);
            if((whichsorti.equals("byname0"))) {
                se.setCheckMarkDrawable(R.drawable.ic_arrow_downward_black_24dp);
                se.setChecked(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se1.setTextSize(17);
                se2.setTextSize(17);
                se3.setTextSize(17);
            }
          else if((whichsorti.equals("byname1"))) { se.setCheckMarkDrawable(R.drawable.ic_arrow_upward_black_24dp);
                se.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se1.setTextSize(17);
                se2.setTextSize(17);
                se3.setTextSize(17);
            }
            else if((whichsorti.equals("bypresent0"))) { se1.setCheckMarkDrawable(R.drawable.ic_arrow_downward_black_24dp);
                se1.setChecked(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se2.setTextSize(17);
                se.setTextSize(17);
                se3.setTextSize(17);
            }
            else if(((whichsorti.equals("bypresent1")))) { se1.setCheckMarkDrawable(R.drawable.ic_arrow_upward_black_24dp);
                se1.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se2.setTextSize(17);
                se.setTextSize(17);
                se3.setTextSize(17);
            }
            else if((whichsorti.equals("byabsent0"))) { se2.setCheckMarkDrawable(R.drawable.ic_arrow_downward_black_24dp);
                se2.setChecked(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se1.setTextSize(17);
                se.setTextSize(17);
                se3.setTextSize(17);
            }
            else if(whichsorti.equals("byabsent1")) { se2.setCheckMarkDrawable(R.drawable.ic_arrow_upward_black_24dp);
                se2.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se3.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se1.setTextSize(17);
                se.setTextSize(17);
                se3.setTextSize(17);
            }
            else if((whichsorti.equals("bypercent0"))) { se3.setCheckMarkDrawable(R.drawable.ic_arrow_downward_black_24dp);
                se3.setChecked(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se1.setTextSize(17);
                se.setTextSize(17);
                se2.setTextSize(17);
            }
            else if((whichsorti.equals("bypercent1"))) { se3.setCheckMarkDrawable(R.drawable.ic_arrow_upward_black_24dp);
                se3.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se1.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    se2.setTextAppearance(R.style.TextAppearance_AppCompat_Caption);
                }
                se1.setTextSize(17);
                se.setTextSize(17);
                se2.setTextSize(17);
            }

            setlistsorting();
        }




        else if (id == R.id.nav_share) {



Intent i=new Intent(MainActivity.this,MainsettingActivity.class);
            startActivity(i);



        } else if (id == R.id.nav_send) {

            try{startActivity(new Intent( Intent.ACTION_VIEW,(Uri.parse("market://details?id="+getPackageName()))));}


                catch(ActivityNotFoundException anfe){startActivity(new Intent( Intent.ACTION_VIEW,(Uri.parse("http://play.google.com/store/apps/details?id="+getPackageName()))));}

                }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}