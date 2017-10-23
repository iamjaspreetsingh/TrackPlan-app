package com.jskgmail.attendance;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainhistoryActivity extends AppCompatActivity {
    private ArrayList<String> stringArrayList,stringArrayList1;

    static String[] datesac;
    private ListView listView;
int tot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainhistory);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);

        ListViewAdapterhistory lviewAdapter;

                stringArrayList = new ArrayList<String>();
        stringArrayList1 = new ArrayList<String>();
listView=(ListView)findViewById(R.id.listView777);

        lviewAdapter = new ListViewAdapterhistory(this, stringArrayList,stringArrayList1);
        listView.setAdapter(lviewAdapter);
int asd=0;
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
if(!(cn.getPresent().equals("0")))
            asd++;
        }}



       ImageView sad=(ImageView)findViewById(R.id.sad);


        if(asd==0)
        {
            sad.setVisibility(View.VISIBLE);
        }
        else {sad.setVisibility(View.GONE);

        go1();}














    }

    private void go1() {

        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

String alldate="";

        SimpleDateFormat sdf =new SimpleDateFormat("ddMMyyyy");
        Date dmy=new Date();
        String day =sdf.format(dmy);
        Log.i("gfdgffggfdgdf",day);
        for (Contact cn : contacts) {
            if ((cn.getPo().equals(MainActivity.semno))) {
                String[] kk = cn.get_dateprea().split("\\.");
                for (int h = 0; h < kk.length; h++)
                    if (!(kk[h].equals(""))) {
                        if (!(alldate.contains(kk[h]))) {
                            if (Integer.parseInt(kk[h]) < 0)
                                continue;
                            alldate = alldate + (kk[h] + ".");
                        }
                    }
            }
        }                Log.i("ksksksksttttttttttttttttttttttttttkssksk",alldate);


String[] dates=alldate.split("\\.");
datesac=alldate.split("\\.");
        int s=dates.length;
for(int ii=0;ii<s;ii++)
    datesac[ii]=datesac[ii].concat(".");

        for (int i=0;i<s;i++) {
            for (Contact cn : contacts) {
                if ((cn.getPo().equals(MainActivity.semno))) {
                    String dneg = "-" + dates[i];
                    int prrr=0,abbb=0;
                    String []daprab=cn.get_dateprea().split("\\.");
for(int ij=0;ij<daprab.length;ij++)
{ if ((daprab[ij].equals(dates[i].replace("\\.",""))) && (!(daprab[ij].equals(dneg.replace("\\.","")))))
    prrr++;
    if ((daprab[ij].equals(dates[i].replace("\\.",""))) && ((daprab[ij].equals(dneg.replace("\\.","")))))
        abbb++;

}  Log.i("zzkssksk", String.valueOf(prrr));
                    Log.i("zzkssksk", String.valueOf(abbb));

                    if (prrr>abbb) {
                        Log.i("kssksk", cn.get_dateprea());
                        datesac[i] = datesac[i].concat(" , " + cn.getName());
                    }
                    else datesac[i].replace(dates[i],"");
                    Log.i("kssll",datesac[i]);  Log.i("ksslggl",dates[i]);
                }
            }
            Log.i("ksskdfdfdfdfdfdfdfdfdfdsk",datesac[i]);
        }
for(int ii=0;ii<datesac.length-1;ii++) {


    for(int j=0;j<datesac.length-ii-1;j++)
    {

        String[] dd = datesac[j].split("\\. , ");


        int ddd = Integer.parseInt(dd[0]) / 1000000;
        int m = (Integer.parseInt(dd[0]) / 10000) % 100;
        int y = Integer.parseInt(dd[0]) % 10000;
        int dmysortin = y * 10000 + m * 100 + ddd;



        String[] dd1 = datesac[j+1].split("\\. , ");


        int ddd1 = Integer.parseInt(dd1[0]) / 1000000;
        int m1= (Integer.parseInt(dd1[0]) / 10000) % 100;
        int y1 = Integer.parseInt(dd1[0]) % 10000;
        int dmysortin1 = y1 * 10000 + m1 * 100 + ddd1;



        if(dmysortin>dmysortin1)
        {
            String temp=datesac[j];
            datesac[j]=datesac[j+1];
            datesac[j+1]=temp;
        }




    }









}
        for(int ii=0;ii<datesac.length;ii++) {
            String[] dd = datesac[ii].split("\\. , ");



            int ddd = Integer.parseInt(dd[0]) / 1000000;
            int m = (Integer.parseInt(dd[0]) / 10000) % 100;
            int y = Integer.parseInt(dd[0]) % 10000;

            if (day.equals(dd[0]))
                stringArrayList.add(0, "Today ( " + String.valueOf(ddd) + "/" + String.valueOf(m) + "/" + String.valueOf(y) + " )");
            else
                stringArrayList.add(0, String.valueOf(ddd) + "/" + String.valueOf(m) + "/" + String.valueOf(y));

            if (String.valueOf(dd[1]).contains(","))
                stringArrayList1.add(0, "Present in the classes of " + String.valueOf(dd[1]));
            else
                stringArrayList1.add(0, "Present in the class of " + String.valueOf(dd[1]));


        }


        }
/*
    private void go() {
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        int i=0,j=0,go=0;
        String[] sort= new String[tot];
        String nam;
        SimpleDateFormat sdf =new SimpleDateFormat("ddMMyyyy");
        Date dmy=new Date();
        String day =sdf.format(dmy);
        Log.i("gfdgffggfdgdf",day);
        for (Contact cn : contacts) {int ch=0;
            String[] d = cn.get_dateprea().split("\\.");

           int l=0,ii=0;
            int len=d.length;
            while(l<len){

String dddd;





























if(ch==0)
{


                if(Integer.parseInt(d[l])<0)
                {
                    if(-Integer.parseInt(day)==(Integer.parseInt(d[l])))
                    dddd="Today ( "+String.valueOf(-dd)+"/"+String.valueOf(-m)+"/"+String.valueOf(-y)+" ) :";
                    else
                    dddd=String.valueOf(-dd)+"/"+String.valueOf(-m)+"/"+String.valueOf(-y);
               nam= (dddd);
                stringArrayList.add(0,nam);
                    stringArrayList1.add(0," Absent in the class of "+cn.getName());
                }
                else   if(Integer.parseInt(d[l])>0){


                    if(day.contains(String.valueOf(d[l])))
                        dddd="Today ( "+String.valueOf(dd)+"/"+String.valueOf(m)+"/"+String.valueOf(y)+" ) :";
                    else dddd=String.valueOf(dd)+"/"+String.valueOf(m)+"/"+String.valueOf(y);
                    nam= dddd;
                    stringArrayList.add(0,nam);
                    stringArrayList1.add(0," Present in the class of "+cn.getName());

                }


                l++;
            }}


            Log.i("dfqqqqqqqqqqqq",String.valueOf(l));


            sort[j]= joinpart(d,0,"");
            i++;j++;




            ii=0;String ln="";String now1="0";
            int s=stringArrayList.size();
            while(ii<s)

            {if((!(stringArrayList1.get(ii).contains(cn.getName()))))


            {int k;

                for( k=0;k<ii;k++)
                {
                    if(stringArrayList.get(ii).equals(stringArrayList.get(k)))
                    {

                        String now = stringArrayList1.get(ii);
                        now1 = stringArrayList.get(ii);
                        Log.i("ffdfdfdfd", stringArrayList.get(ii));

                        stringArrayList.remove(ii);
                        stringArrayList1.remove(ii);
                        stringArrayList.add(ii,now1);
                        stringArrayList1.add(ii, now + " , " + cn.getName());



                    }
                }











            }
                ii++;
            }





























    }
   }*/






    public String joinpart(String[] a,int i,String prefix)
    {
        if(i<a.length)
            return joinpart(a,i+1,prefix+a[i]);
        return prefix;
    }


}
