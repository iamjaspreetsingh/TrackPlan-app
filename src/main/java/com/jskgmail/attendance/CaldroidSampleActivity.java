package com.jskgmail.attendance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

@SuppressLint("SimpleDateFormat")
public class CaldroidSampleActivity extends AppCompatActivity {
    String pre,abs,crit,dmy="",dmyyy="";
    float per=0;


    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;

    private void setCustomResourceForDatesA(Date date) {


        if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable((Color.GREEN));

            caldroidFragment.setBackgroundDrawableForDate(blue, date);

            caldroidFragment.setTextColorForDate(R.color.caldroid_white, date);

        }

    }



    private void setCustomResourceForDatesH(Date date) {




        if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable(Color.WHITE);

            caldroidFragment.setBackgroundDrawableForDate(blue, date);

            caldroidFragment.setTextColorForDate(R.color.caldroid_black, date);

        }






    }

    private void setCustomResourceForDatesP(Date date) {


        if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.caldroid_light_red));

            caldroidFragment.setBackgroundDrawableForDate(blue, date);

            caldroidFragment.setTextColorForDate(R.color.caldroid_white, date);

        }

    }
    private void setCustomResourceForDatesNo(Date date) {


        if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.caldroid_holo_blue_dark));

            caldroidFragment.setBackgroundDrawableForDate(blue, date);

            caldroidFragment.setTextColorForDate(R.color.caldroid_white, date);

        }











    }





























    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main333);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        checkdata1();
        FloatingTextButton callButton = (FloatingTextButton) findViewById(R.id.floatingTextButton);
        final ScrollView s=(ScrollView)findViewById(R.id.s);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Colleges");
                //TODO search list and save for the list
firstsubjectclass();


                DatabaseReference myRef1 = database.getReference("Colleges").child(MainteachersActivity.colname).child("subclass").child("Students");

                myRef1.child(ConnectActivity.mynaam).setValue(dmy);


                Snackbar.make(s, "Attendance marked for today", Snackbar.LENGTH_SHORT).show();
            }
        });

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();

        // //////////////////////////////////////////////////////////////////////
        // **** This is to show customized fragment. If you want customized
        // version, uncomment below line ****
//		 caldroidFragment = new CaldroidSampleCustomFragment();

        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            // Uncomment this line to use dark theme
//            args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);

            caldroidFragment.setArguments(args);



            ActionBar actionBar=getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
            actionBar.setDisplayShowHomeEnabled(true);

        }
        checkthefirsttime();
        checkdatescolor();


        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {






final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                dmy=   formatter.format (date);
                final SimpleDateFormat formatter11 = new SimpleDateFormat("dd.MM.yyyy");
                String[] datemoyr=formatter11.format(date).split("\\.");
         int       dayofmonth=Integer.parseInt(datemoyr[0]);
                int month=Integer.parseInt(datemoyr[1]);
             int  year=Integer.parseInt(datemoyr[2]);
                dmyyy=((1000000*dayofmonth)+(10000*month)+year+".");
                String[] dm=dmyyy.split("");
                Log.e(String.valueOf(dm.length),"wdsdsdsdsdsds");
                if(dm.length==9)
                   dmyyy="0"+dmyyy;
Log.i("dadadadadadadad",dmyyy);
                SharedPreferences sp2=getSharedPreferences("yourprefsofp", Activity.MODE_PRIVATE);
                int myvalue2=sp2.getInt("99999",1);

                if(myvalue2==1)

                addpr();

                else
                    addmultipr();































            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;


            }

            @Override
            public void onLongClickDate(Date date, View view) {
                final SimpleDateFormat formatter11 = new SimpleDateFormat("dd.MM.yyyy");
                String[] datemoyr=formatter11.format(date).split("\\.");
                int       dayofmonth=Integer.parseInt(datemoyr[0]);
                int month=Integer.parseInt(datemoyr[1]);
                int  year=Integer.parseInt(datemoyr[2]);
                dmyyy=((1000000*dayofmonth)+(10000*month)+year+".");
                String[] dm=dmyyy.split("");
                if(dm.length==9)
                    dmyyy="0"+dmyyy;
                addnote();

            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {

                }
            }

        };


        // Customize the calendar
        caldroidFragment.setCaldroidListener(listener);

    }



void firstsubjectclass()
{
    LayoutInflater inflater = getLayoutInflater();
    View alertLayout = inflater.inflate(R.layout.layoutsearchclass, null);
SearchView search;
    search= (SearchView) alertLayout.findViewById(R.id.searchView);
  //TODO search....
    AlertDialog.Builder alert = new AlertDialog.Builder(this);

    // this is set the view from XML inside AlertDialog
    alert.setView(alertLayout);
    // disallow cancel of AlertDialog on click of back button and outside touch
    alert.setTitle(" Search your class ");
    alert.setIcon(R.drawable.ic_add_circle_outline_black_24dp);
    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });


    alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {


        }
    });
    AlertDialog dialog = alert.create();
    dialog.show();













}


















































































    protected void resume() {

        checkdata1();
        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();

        // //////////////////////////////////////////////////////////////////////
        // **** This is to show customized fragment. If you want customized
        // version, uncomment below line ****
//		 caldroidFragment = new CaldroidSampleCustomFragment();

        // Setup arguments

        // If Activity is created after rotation

        // If activity is created from fresh
        {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            // Uncomment this line to use dark theme
//            args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);

            caldroidFragment.setArguments(args);
        }checkthefirsttime();
        checkdatescolor();


        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {








                final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                dmy=   formatter.format (date);
                final SimpleDateFormat formatter11 = new SimpleDateFormat("dd.MM.yyyy");
                String[] datemoyr=formatter11.format(date).split("\\.");
                int       dayofmonth=Integer.parseInt(datemoyr[0]);
                int month=Integer.parseInt(datemoyr[1]);
                int  year=Integer.parseInt(datemoyr[2]);
                dmyyy=((1000000*dayofmonth)+(10000*month)+year+".");
                String[] dm=dmyyy.split("");
                if(dm.length==9)
                    dmyyy="0"+dmyyy;
                Log.i("dadadadadadadad",dmyyy);
                SharedPreferences sp2=getSharedPreferences("yourprefsofp",Activity.MODE_PRIVATE);
                int myvalue2=sp2.getInt("99999",1);
                if(myvalue2==1)
                addpr();
else addmultipr();































            }

            @Override
            public void onChangeMonth(int month, int year) {





                String text = "month: " + month + " year: " + year;

            }

            @Override
            public void onLongClickDate(Date date, View view) {


                final SimpleDateFormat formatter11 = new SimpleDateFormat("dd.MM.yyyy");
                String[] datemoyr=formatter11.format(date).split("\\.");
                int       dayofmonth=Integer.parseInt(datemoyr[0]);
                int month=Integer.parseInt(datemoyr[1]);
                int  year=Integer.parseInt(datemoyr[2]);
                dmyyy=((1000000*dayofmonth)+(10000*month)+year+".");
                String[] dm=dmyyy.split("");
                if(dm.length==9)
                    dmyyy="0"+dmyyy;



Log.d("ldldldsldsldsl","londvlcoivck");
                        addnote();


            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {

                }
            }

        };


        // Customize the calendar
        caldroidFragment.setCaldroidListener(listener);

    }





























































void checkthefirsttime(){


    DatabaseHandler db = new DatabaseHandler(this);
    List<Contact> contacts = db.getAllContacts();

    for (Contact cn : contacts) {
        if (cn.getName().equals(MainActivity.subbb)){

            if ((cn.getPo().equals(MainActivity.semno))) {
                if ((cn.getPresent().equals("0")) && (cn.getAbssent().equals("0"))) {

TextView first=(TextView)findViewById(R.id.textView36);
                    TextView first1=(TextView)findViewById(R.id.textView2);
                    TextView first2=(TextView)findViewById(R.id.textView4);
                    TextView first3=(TextView)findViewById(R.id.textView9);
                    TextView first4=(TextView)findViewById(R.id.textView8);
                    TextView first5=(TextView)findViewById(R.id.per);
                    TextView first6=(TextView)findViewById(R.id.pre);
                    TextView first7=(TextView)findViewById(R.id.abs);


                    first1.setVisibility(View.INVISIBLE);
                    first2.setVisibility(View.INVISIBLE);
                    first3.setVisibility(View.INVISIBLE);
                    first4.setVisibility(View.INVISIBLE);
                    first5.setVisibility(View.INVISIBLE);
                    first6.setVisibility(View.INVISIBLE);
                    first7.setVisibility(View.INVISIBLE);
                    first.setVisibility(View.VISIBLE);



                }
            }}}


            }





    void checkdatescolor() {


    DatabaseHandler db = new DatabaseHandler(this);
    List<Contact> contacts = db.getAllContacts();

    for (Contact cn : contacts) {
        if (cn.getName().equals(MainActivity.subbb)){

            if ((cn.getPo().equals(MainActivity.semno))) {




                    String dat = cn.get_dateprea();
                    if (!(dat.equals(""))) {
                        String[] datone = dat.split("\\.");
                        for (int i = 0; i < datone.length; i++) {
                            Log.e("datetetetet", datone[i]);
                            int dd = (Integer.parseInt(datone[i]) / 1000000);
                            int mm;
                            if (Integer.parseInt(datone[i]) > 0)
                                mm = (Integer.parseInt(datone[i]) / 10000) - (100 * dd);
                            else mm = -(Integer.parseInt(datone[i]) / 10000) + (100 * dd);
                            int yy = (Integer.parseInt(datone[i]) % 10000);
                            if (Integer.parseInt(datone[i]) < 0) {
                                Log.i("dddddddddd", String.valueOf(-dd));
                                Log.i("ddmmmmmm", String.valueOf(mm));
                                Log.i("ddddyyyyy", String.valueOf(-yy));
                                Date date = new GregorianCalendar(-yy, mm - 1, -dd).getTime();
                                setCustomResourceForDatesP(date);
                            } else if (Integer.parseInt(datone[i]) > 0) {
                                Log.i("dddddddddd", String.valueOf(dd));
                                Log.i("ddmmmmmm", String.valueOf(mm));
                                Log.i("ddddyyyyy", String.valueOf(yy));
                                Date date = new GregorianCalendar(yy, mm - 1, dd).getTime();
                                setCustomResourceForDatesA(date);
                            } else {
                                Log.i("dddddddddd", String.valueOf(dd));
                                Log.i("ddmmmmmm", String.valueOf(mm));
                                Log.i("ddddyyyyy", String.valueOf(yy));
                                Date date = new GregorianCalendar(yy, mm - 1, dd).getTime();
                                setCustomResourceForDatesH(date);

                        }
                    }
                }










                if(!(cn.get_datesofnote().equals("")))
                {
                    String[] dates=cn.get_datesofnote().split("\\.");

                    for(int ioi=0;ioi<dates.length;ioi++) {

                        String datnote=dates[ioi];
                        Log.i(cn.get_datesofnote(), "papapapapa");
                        int dd = (Integer.parseInt(datnote) / 1000000);
                        int mm;

                        mm = (Integer.parseInt(datnote) / 10000) - (100 * dd);
                        int yy = (Integer.parseInt(datnote) % 10000);
                        Log.i("dsdsjdsjdsjdshjdshjdsj", "goggogogo");
                        Date date = new GregorianCalendar(yy, mm - 1, dd).getTime();
                        setCustomResourceForDatesNo(date);



                    }}












            }
    }
}





















}












void addmultipr()
{





    LayoutInflater inflater = getLayoutInflater();
    View alertLayout = inflater.inflate(R.layout.layoutmutipledate, null);
    TextView nottt=(TextView)alertLayout.findViewById(R.id.textView43);

    TextView notitle=(TextView)alertLayout.findViewById(R.id.headd);


    ImageButton addp=(ImageButton)alertLayout.findViewById(R.id.imageButton);
    ImageButton subp=(ImageButton)alertLayout.findViewById(R.id.imageButton3);
    ImageButton adda=(ImageButton)alertLayout.findViewById(R.id.imageButton2);
    ImageButton suba=(ImageButton)alertLayout.findViewById(R.id.imageButton4);
final TextView prrr=(TextView)alertLayout.findViewById(R.id.textView22);
    final TextView abbb=(TextView)alertLayout.findViewById(R.id.textView26);
    final RadioButton ho = (RadioButton) alertLayout.findViewById(R.id.radioButton8);

    DatabaseHandler db = new DatabaseHandler(this);
    List<Contact> contacts = db.getAllContacts();
    String[] noteindi;
    ho.setChecked(true);
    int prep=0,absp = 0;
    for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))) {
        if (cn.getName().equals(MainActivity.subbb)) {int co=0,co1=0; Log.i(dmyyy,cn.get_dateprea());
            String[] eachd=cn.get_dateprea().split("\\.");
            for(int i=0;i<eachd.length;i++)
                if((eachd[i].equals(dmyyy.replace(".",""))))
                {  co++;ho.setChecked(false);prep=co;}
              else  if((eachd[i].equals("-" + dmyyy.replace(".",""))))
                {  co1++;ho.setChecked(false);absp=co1;}



               Log.i("fgfgfgffllllllllllll",String.valueOf(co));
                prrr.setText(String.valueOf(co));
               abbb.setText(String.valueOf(co1));





            if(!(cn.getNote().equals("")))
            {    Log.i("cocococoiiiicoc", cn.getNote());
                noteindi=cn.getNote().split("\\.");
                for(int joo=0;joo<noteindi.length;joo++)
                {
                    String[] daatee=noteindi[joo].split("\\,");

                    Log.i(daatee[1], dmyyy);
                    if (daatee[1].equals(dmyyy.replace(".",""))) {
                        Log.i("cocococococ", daatee[0]);
                        notitle.setVisibility(View.VISIBLE);
                        nottt.setVisibility(View.VISIBLE);
                        nottt.setText(daatee[0]);

                    }
                }
            }}
    }

    }

    AlertDialog.Builder alert = new AlertDialog.Builder(this);

    alert.setTitle("  "+dmy + "\n");

    alert.setIcon(R.drawable.ic_today_black_24dp);
    // this is set the view from XML inside AlertDialog

    final int finalPrep = prep;
    final int finalAbsp = absp;
    alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {


        @Override
        public void onClick(DialogInterface dialog, int which) {
int p=Integer.parseInt(prrr.getText().toString())- finalPrep;
addpresentm(String.valueOf(p));
            int a=Integer.parseInt(abbb.getText().toString())- finalAbsp;
            addabsentm(String.valueOf(a));



            findper();
            setpreabs();
            resume();





        }
    });
    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {


        @Override
        public void onClick(DialogInterface dialog, int which) {


        }
    });
    alert.setView(alertLayout);
    final AlertDialog dialog = alert.create();
    dialog.show();
    // disallow cancel of AlertDialog on click of back button and outside touch
    addp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

ho.setChecked(false);

            prrr.setText(String.valueOf(Integer.parseInt(prrr.getText().toString())+1));









            TextView first=(TextView)findViewById(R.id.textView36);
            TextView first1=(TextView)findViewById(R.id.textView2);
            TextView first2=(TextView)findViewById(R.id.textView4);
            TextView first3=(TextView)findViewById(R.id.textView9);
            TextView first4=(TextView)findViewById(R.id.textView8);
            TextView first5=(TextView)findViewById(R.id.per);
            TextView first6=(TextView)findViewById(R.id.pre);
            TextView first7=(TextView)findViewById(R.id.abs);


            first1.setVisibility(View.VISIBLE);
            first2.setVisibility(View.VISIBLE);
            first3.setVisibility(View.VISIBLE);
            first4.setVisibility(View.VISIBLE);
            first5.setVisibility(View.VISIBLE);
            first6.setVisibility(View.VISIBLE);
            first7.setVisibility(View.VISIBLE);
            first.setVisibility(View.INVISIBLE);


        }
    });








subp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        ho.setChecked(false);


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();

        if(!(prrr.getText().toString().equals("0")))
        prrr.setText(String.valueOf(Integer.parseInt(prrr.getText().toString())-1));





    }
});


suba.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();
        ho.setChecked(false);
        if(!(abbb.getText().toString().equals("0")))
        abbb.setText(String.valueOf(Integer.parseInt(abbb.getText().toString())-1));

        findper();
        setpreabs();
        resume();




    }
});



    adda.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            abbb.setText(String.valueOf(Integer.parseInt(abbb.getText().toString())+1));


            ho.setChecked(false);






            findper();
            setpreabs();
            resume();





            TextView first=(TextView)findViewById(R.id.textView36);
            TextView first1=(TextView)findViewById(R.id.textView2);
            TextView first2=(TextView)findViewById(R.id.textView4);
            TextView first3=(TextView)findViewById(R.id.textView9);
            TextView first4=(TextView)findViewById(R.id.textView8);
            TextView first5=(TextView)findViewById(R.id.per);
            TextView first6=(TextView)findViewById(R.id.pre);
            TextView first7=(TextView)findViewById(R.id.abs);


            first1.setVisibility(View.VISIBLE);
            first2.setVisibility(View.VISIBLE);
            first3.setVisibility(View.VISIBLE);
            first4.setVisibility(View.VISIBLE);
            first5.setVisibility(View.VISIBLE);
            first6.setVisibility(View.VISIBLE);
            first7.setVisibility(View.VISIBLE);
            first.setVisibility(View.INVISIBLE);













        }
    });

    ho.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            addholiday();
            findper();
            setpreabs();

            resume();

            dialog.cancel();
        }
    });
































}

















    void addpr() {


        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout, null);
TextView nottt=(TextView)alertLayout.findViewById(R.id.textView5);

        TextView notitle=(TextView)alertLayout.findViewById(R.id.textView6);




        RadioButton pr = (RadioButton) alertLayout.findViewById(R.id.radioButton);
        RadioButton ab = (RadioButton) alertLayout.findViewById(R.id.radioButton1);
        RadioButton ho = (RadioButton) alertLayout.findViewById(R.id.radioButton2);

        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        String[] noteindi;
int chhhh=0;
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))) {
            if (cn.getName().equals(MainActivity.subbb)) {

                String[] da=cn.get_dateprea().split("\\.");
                for(int i=0;i<da.length;i++) {
                    if (((da[i].contains(dmyyy.replace(".","")))) && (!(da[i].contains("-" + dmyyy.replace(".",""))))) {
                        pr.setChecked(true);chhhh=1;
                    } else if ((da[i].contains(dmyyy.replace(".",""))) && ((da[i].contains("-" + dmyyy.replace(".",""))))) {
                        ab.setChecked(true);chhhh=1;

                    }
                }
                 if(chhhh==0) ho.setChecked(true);
if(!(cn.getNote().equals("")))
                {    Log.i("cocococoiiiicoc", cn.getNote());
                    noteindi=cn.getNote().split("\\.");
                for(int joo=0;joo<noteindi.length;joo++)
                {
                    String[] daatee=noteindi[joo].split("\\,");

                    Log.i(daatee[1], dmyyy);
                    if (daatee[1].equals(dmyyy.replace(".",""))) {
                    Log.i("cocococococ", daatee[0]);
                        notitle.setVisibility(View.VISIBLE);
                        nottt.setVisibility(View.VISIBLE);
                    nottt.setText(daatee[0]);

                }
                }
            }}
        }

        }

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("  "+dmy + "\n");

        alert.setIcon(R.drawable.ic_today_black_24dp);
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();
        dialog.show();




        // disallow cancel of AlertDialog on click of back button and outside touch
        pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addpresent();
                findper();
                setpreabs();
                resume();





                TextView first=(TextView)findViewById(R.id.textView36);
                TextView first1=(TextView)findViewById(R.id.textView2);
                TextView first2=(TextView)findViewById(R.id.textView4);
                TextView first3=(TextView)findViewById(R.id.textView9);
                TextView first4=(TextView)findViewById(R.id.textView8);
                TextView first5=(TextView)findViewById(R.id.per);
                TextView first6=(TextView)findViewById(R.id.pre);
                TextView first7=(TextView)findViewById(R.id.abs);


                first1.setVisibility(View.VISIBLE);
                first2.setVisibility(View.VISIBLE);
                first3.setVisibility(View.VISIBLE);
                first4.setVisibility(View.VISIBLE);
                first5.setVisibility(View.VISIBLE);
                first6.setVisibility(View.VISIBLE);
                first7.setVisibility(View.VISIBLE);
                first.setVisibility(View.INVISIBLE);










                dialog.cancel();
            }
        });


        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addabsent();
                findper();
                setpreabs();
                resume();





                TextView first=(TextView)findViewById(R.id.textView36);
                TextView first1=(TextView)findViewById(R.id.textView2);
                TextView first2=(TextView)findViewById(R.id.textView4);
                TextView first3=(TextView)findViewById(R.id.textView9);
                TextView first4=(TextView)findViewById(R.id.textView8);
                TextView first5=(TextView)findViewById(R.id.per);
                TextView first6=(TextView)findViewById(R.id.pre);
                TextView first7=(TextView)findViewById(R.id.abs);


                first1.setVisibility(View.VISIBLE);
                first2.setVisibility(View.VISIBLE);
                first3.setVisibility(View.VISIBLE);
                first4.setVisibility(View.VISIBLE);
                first5.setVisibility(View.VISIBLE);
                first6.setVisibility(View.VISIBLE);
                first7.setVisibility(View.VISIBLE);
                first.setVisibility(View.INVISIBLE);












                dialog.cancel();
            }
        });

        ho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addholiday();
                findper();
                setpreabs();

                resume();

                dialog.cancel();
            }
        });







    }



    void addnote() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layoutnote, null);

       final CheckedTextView a=(CheckedTextView)alertLayout.findViewById(R.id.radioButton3);
        final CheckedTextView a1=(CheckedTextView)alertLayout.findViewById(R.id.radioButton4);
        final CheckedTextView a2=(CheckedTextView)alertLayout.findViewById(R.id.radioButton5);
        final CheckedTextView a3=(CheckedTextView)alertLayout.findViewById(R.id.radioButton6);



        final EditText note = (EditText)alertLayout. findViewById(R.id.editText);

        String[]   noteindfgi;

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Contact> contacts = db.getAllContacts();


        for (Contact cn : contacts) {
            if ((cn.getPo().equals(MainActivity.semno))) {
                if (cn.getName().equals(MainActivity.subbb)) {

if(!(cn.getNote().equals(""))) {
    noteindfgi = cn.getNote().split("\\.");
    for (int joo = 0; joo < noteindfgi.length; joo++) {
        String[] daatee = noteindfgi[joo].split("\\,");

        Log.i(daatee[1], dmyyy);
        if (daatee[1].equals(dmyyy.replace(".", "")))
            note.setText(daatee[0]);
    }
}               }}}














        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("  Add the note " + "\n");


        alert.setIcon(R.drawable.ic_event_note_black_24dp);
        // this is set the view from XML inside AlertDialog

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.isChecked())
                {  a.setChecked(false);note.setText(note.getText().toString().replace(" Unwell ",""));
a.setCheckMarkDrawable(R.drawable.ic_radio_button_unchecked_black_24dp);


                }
                else {a.setChecked(true);
                    a.setCheckMarkDrawable(R.drawable.ic_radio_button_checked_black_24dp); note.setText(note.getText().toString()+" Unwell ");
                }
            }
        });
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a1.isChecked()){
                    a1.setChecked(false); note.setText(note.getText().toString().replace(" Mass bunk ",""));
                a1.setCheckMarkDrawable(R.drawable.ic_radio_button_unchecked_black_24dp);}
                else {a1.setChecked(true);  a1.setCheckMarkDrawable(R.drawable.ic_radio_button_checked_black_24dp);
                    note.setText(note.getText().toString()+" Mass bunk ");
            }
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a2.isChecked()==true){
                    a2.setChecked(false); note.setText(note.getText().toString().replace(" Event ",""));
                a2.setCheckMarkDrawable(R.drawable.ic_radio_button_unchecked_black_24dp);}
                else  {if(a2.isChecked()==false) a2.setChecked(true);  a2.setCheckMarkDrawable(R.drawable.ic_radio_button_checked_black_24dp);

                    note.setText(note.getText().toString()+" Event ");

            }}
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a3.isChecked()){
                    a3.setChecked(false); note.setText(note.getText().toString().replace(" Teacher didnot come ",""));
                a3.setCheckMarkDrawable(R.drawable.ic_radio_button_unchecked_black_24dp);}
                else{ a3.setChecked(true);  a3.setCheckMarkDrawable(R.drawable.ic_radio_button_checked_black_24dp);
                    note.setText(note.getText().toString()+" Teacher didnot come ");}
            }
        });
        alert.setView(alertLayout);


        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                List<Contact> contacts = db.getAllContacts();















                String[]   noteindfi;
                for (Contact cn : contacts) {
                    if ((cn.getPo().equals(MainActivity.semno))) {
                        if (cn.getName().equals(MainActivity.subbb)) {









                         noteindfi=cn.getNote().split("\\.");
                            for(int joo=0;joo<noteindfi.length;joo++) {
                                String[] daatee = noteindfi[joo].split("\\,");

                                Log.i(daatee[1], dmyyy);
                                if (daatee[1].equals(dmyyy.replace(".", "")))
                                    cn.setNote(cn.getNote().replace(noteindfi[joo]+".", ""));
                            }






                                    cn.set_datenotesact(cn.get_datesofnote().replace(dmyyy,""));
                            db.updateContact(cn);


                        }


                    }}
                resume();
dialog.dismiss();
            }
        });

        alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nottt = note.getText().toString();








                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                List<Contact> contacts = db.getAllContacts();

                for (Contact cn : contacts) {
                    if ((cn.getPo().equals(MainActivity.semno))) {
                        if (cn.getName().equals(MainActivity.subbb)) {

                            cn.set_datenotes(dmyyy);

                            cn.setNote(cn.getNote()+nottt+","+dmyyy);
                            Log.i("ddsdsdsdsdssdghghghg",cn.getNote());
                            db.updateContact(cn);
                        }


                    }}



resume();



            }
        });


         AlertDialog dialog = alert.create();
        dialog.show();
    }





    void findper()
    {Log.e("dddddddddd",(crit));
        int crittt=Integer.parseInt(crit);
        TextView cr=(TextView)findViewById(R.id.textView8);
        int p=Integer.parseInt(pre);
        int a=Integer.parseInt(abs);
        int tot=p+a;
        int pp=p,tott=tot;
        int next=0,nextt=0;
        per =((float)p/tot)*100;
        float percc=per;
        if((p==0)&&(a==0))
        {
            cr.setText("");
        }

        else if(crittt>per) {
            while(percc<crittt)
            {
                pp++;
                tott++;
                percc =((float)pp/tott)*100;
                next++;
            }
            cr.setText("You need to attend next "+next+" classes !");
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
                cr.setText("You are on the Track !!\n\n You may leave next "+nextt+" classes !!");
            else cr.setText("You are on the Track !!");
        }
        else cr.setText("You are on the Track !!");
    }




    void setpreabs() {

        TextView pr=(TextView)findViewById(R.id.pre);
        TextView ab=(TextView)findViewById(R.id.abs);
        TextView perc=(TextView)findViewById(R.id.per);
        pr.setText((pre));
        ab.setText((abs));
      per=(float)Math.round(per*100)/100;
        perc.setText((per + "%"));

    }

    void addholiday()
    {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            if ((cn.getPo().equals(MainActivity.semno))) {
                if (cn.getName().equals(MainActivity.subbb)) {
                    if (((cn.get_dateprea().contains(dmyyy))) && (!(cn.get_dateprea().contains("-" + dmyyy)))) {
                        cn.setPresent(String.valueOf(Integer.parseInt(cn.getPresent()) - 1));
                        db.updateContact(cn);
                        pre = cn._present;
                        String newdatep=cn.get_dateprea().replace("-"+dmyyy,"").replace(dmyyy,"");
                        cn.set_datepreaact(newdatep);

                        Log.i("abczzzzzzzzzzzzzzz", cn.datepre);
                        db.updateContact(cn);
                    } else if ((cn.get_dateprea().contains(dmyyy)) && ((cn.get_dateprea().contains("-" + dmyyy)))) {
                        cn.setAbssent(String.valueOf(Integer.parseInt(cn.getAbssent()) - 1));
                        db.updateContact(cn);
                        abs = cn._absent;
                        String newdatep=cn.get_dateprea().replace("-"+dmyyy,"").replace(dmyyy,"");
                        cn.set_datepreaact(newdatep);



                        Log.i("abcdzzzzz", cn.datepre);
                        db.updateContact(cn);

                    }




                }
            }

        }
    }


    void addabsentm(String what) {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            if ((cn.getPo().equals(MainActivity.semno))) {
                if (cn.getName().equals(MainActivity.subbb)) {

                    cn.setAbssent(String.valueOf(Integer.parseInt(cn.getAbssent()) + Integer.parseInt(what)));
                    for (int i = 0; i < Integer.parseInt(what); i++) {
                        cn.set_dateprea("-" + dmyyy);
                    }
                    if (Integer.parseInt(what) < 0) {
                        for (int i = Integer.parseInt(what); i <0; i++) {


                            String neww = cn.get_dateprea();
                            Log.i(dmyyy, neww);
                            String new1 = "";
                            if (neww.contains("-" + dmyyy))
                            {   new1 = neww.replaceFirst("-" + dmyyy, "");

                            cn.set_datepreaact(new1);}

                        }
                        }

                        db.updateContact(cn);




                }
            }
        }


    }


    void addpresentm(String what) {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            if ((cn.getPo().equals(MainActivity.semno))) {
                if (cn.getName().equals(MainActivity.subbb)) {


                    cn.setPresent(String.valueOf(Integer.parseInt(cn.getPresent())+Integer.parseInt(what)));
                    for (int i = 0; i < Integer.parseInt(what); i++) {
                        cn.set_dateprea(dmyyy);
                    }
                    String new1 ;
                    new1 = cn.get_dateprea();
if(Integer.parseInt(what)<0)
{ for (int i =Integer.parseInt(what);i<0; i++) {


    String[] neww = cn.get_dateprea().split("\\.");

    for (int jj = 0; jj < neww.length; jj++) {
String dmyyyyyk=dmyyy.replaceAll("\\.","");Log.i(dmyyyyyk,neww[jj]);
        Log.i("dnnd",new1);
        if ((neww[jj].contains(dmyyyyyk)) && (!(neww[jj].contains("-" + dmyyyyyk))))
        {
              new1 = new1.replaceFirst(dmyyyyyk+".","");
            Log.i("ddsdsd",neww[jj]);
jj=neww.length;


        }

    }

}
Log.i("dkndkandkdskjdakjdsnadsdsd",new1);
    cn.set_datepreaact(new1);


 }


}

}
                    db.updateContact(cn);


                }


            }











    void addpresent()
    {

        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if (cn.getName().equals(MainActivity.subbb)) {
                if (((cn.get_dateprea().contains(dmyyy)))&&(!(cn.get_dateprea().contains("-"+dmyyy)))) {
                }  else if ((cn.get_dateprea().contains(dmyyy))&&((cn.get_dateprea().contains("-"+dmyyy)))) {
                    cn.setAbssent(String.valueOf(Integer.parseInt(cn.getAbssent())-1));
                    db.updateContact(cn);
                    abs=cn._absent;
                    int p = Integer.parseInt(cn._present);
                    p++;
                    cn._present = String.valueOf(p);

                    String newdatep=cn.get_dateprea().replace("-"+dmyyy,dmyyy);
                    cn.set_datepreaact(newdatep);
                    Log.i("abczzzzzzzzzzzzzzzzz", cn.datepre);
                    db.updateContact(cn);
                    pre = cn._present;
                }
                else {
                    int p = Integer.parseInt(cn._present);
                    p++;
                    cn._present = String.valueOf(p);

                    cn.set_dateprea(dmyyy);
                    Log.i("abcdzzzzzzzzzz", cn.datepre);
                    db.updateContact(cn);
                    pre = cn._present;
                }


            }
        }}
    }



    void addabsent()
    {

        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            if (cn.getName().equals(MainActivity.subbb))
                if (((cn.get_dateprea().contains(dmyyy))) && ((cn.get_dateprea().contains("-" + dmyyy)))) {
                } else if ((cn.get_dateprea().contains(dmyyy)) && (!(cn.get_dateprea().contains("-" + dmyyy)))) {
                    cn.setPresent(String.valueOf(Integer.parseInt(cn.getPresent()) - 1));
                    db.updateContact(cn);
                    pre= cn._present;
                    int a = Integer.parseInt(cn._absent);
                    a++;
                    cn._absent = String.valueOf(a);
                    String newdatep=cn.get_dateprea().replace(dmyyy,"-"+dmyyy);
                    cn.set_datepreaact(newdatep);

                    Log.i("abcdzbbzzzzzzzzzzzzz", cn.datepre);
                    db.updateContact(cn);

                    abs = cn._absent;
                } else {
                    {
                        int a = Integer.parseInt(cn._absent);
                        a++;
                        cn._absent = String.valueOf(a);

                        cn.set_dateprea("-" + dmyyy);
                        Log.i("abcdzzzzzzzzzzzzz", cn.datepre);
                        db.updateContact(cn);

                        abs = cn._absent;
                    }
                }
        }}
    }



    void checkdata1()
    {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            if ((cn.getPo().equals(MainActivity.semno))) {
                if ((cn.getPo().equals(MainActivity.semno))) {
                    if (cn.getName().equals(MainActivity.subbb)) {
                        String p = cn.getPresent();
                        String a = cn.getAbssent();
                        pre = p;
                        abs = (a);
                        crit = cn.getPhoneNumber();
                        findper();
                        setpreabs();
                    }
                }
            }

        }
    }

































}
    /**
     * Save current states of the Caldroid here
     */



