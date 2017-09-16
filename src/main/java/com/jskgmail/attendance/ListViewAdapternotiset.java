package com.jskgmail.attendance;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JASPREET SINGH on 15-08-2017.
 */

public class ListViewAdapternotiset extends BaseAdapter {
    Activity context;
    ArrayList<String> title;

    public ListViewAdapternotiset(MainnotidialActivity context,ArrayList<String> title)
    {
        super();
        this.context=context;
        this.title=title;


    }


    @Override
    public int getCount() {

        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        CheckedTextView txtviewtitle;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final DatabaseHandler db = new DatabaseHandler(context);
        final List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
            Log.i("seeeeeeeeeeeeeee",cn.getName());
            Log.i("seeeeeeeeeeeeeee",cn.get_ttable());}}
        SimpleDateFormat sdf =new SimpleDateFormat("EEEE");
        Date d=new Date();
        String day =sdf.format(d);
        SimpleDateFormat sdf1 =new SimpleDateFormat("ddMMyyyy");
        Date dmy=new Date();
        final String day1 =sdf1.format(dmy)+".";

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
        LayoutInflater inflater=context.getLayoutInflater();


            convertView = inflater.inflate(R.layout.layoutnotiattend, null);
            holder = new ViewHolder();
            holder.txtviewtitle = (CheckedTextView) convertView.findViewById(R.id.textView23);



        holder.txtviewtitle.setText(title.get(position));
            if(!(holder.txtviewtitle.isChecked()))
                holder.txtviewtitle.setCheckMarkDrawable(R.mipmap.questionmark);











        holder.txtviewtitle.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(holder.txtviewtitle.isChecked())
                   {
                       holder.txtviewtitle.setCheckMarkDrawable(R.mipmap.absen);
                       holder.txtviewtitle.setChecked(false);
                       for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
                           if ((cn.get_ttable().contains(ddd))&&(!(cn.get_dateprea().contains(day1)))) {
                               if(cn.getName().equals(holder.txtviewtitle.getText().toString()))
                               {
                               String a = cn.getAbssent();
                               int ab = Integer.parseInt(a);
                               ab++;
                               cn.set_dateprea("-" + day1);
                               Log.i("seeeeeeeeeeeeeee",cn.get_dateprea());
                               cn.setAbssent(String.valueOf(ab));
                               db.updateContact(cn);
                           }}
                           else if((cn.get_ttable().contains(ddd))&&((cn.get_dateprea().contains(day1)))&&(cn.get_dateprea().contains("-"+day1)))
                           {

                           }
                           else if((cn.get_ttable().contains(ddd))&&((cn.get_dateprea().contains(day1)))&&(!(cn.get_dateprea().contains("-"+day1))))
                           {    if(cn.getName().equals(holder.txtviewtitle.getText().toString()))
                           { String a = cn.getAbssent();
                               cn.setPresent(String.valueOf(Integer.parseInt(cn.getPresent())-1));
                               int ab = Integer.parseInt(a);
                               ab++;
                               String newdatepre=cn.get_dateprea().replace(day1,"-"+day1);
                               cn.set_datepreaact(newdatepre);
                               Log.i("seeeeeeeeeeeeeee",cn.get_dateprea());
                               cn.setAbssent(String.valueOf(ab));
                               db.updateContact(cn);

                           }}

                       }}










                   }
                   else{
                       holder.txtviewtitle.setCheckMarkDrawable(R.mipmap.tick);
                       holder.txtviewtitle.setChecked(true);
                       for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
                           if((cn._ttable.contains(ddd))&&(!(cn.get_dateprea().contains(day1))))
                           {    Log.i(String.valueOf(holder.txtviewtitle),cn.getName());
                               if(cn.getName().equals(holder.txtviewtitle.getText().toString()))
                           Log.i("txtvvvvvvvtitit",cn.getName());
                           {  String p=  cn.getPresent();
                               int pr=Integer.parseInt(p);
                               pr++;

                               cn.set_dateprea(day1);
                               Log.i("seeeeeeeeeeeeeee",cn.get_dateprea());
                               cn.setPresent(String.valueOf(pr));
                               db.updateContact(cn);
                           }}
                           else if((cn.get_ttable().contains(ddd))&&((cn.get_dateprea().contains(day1)))&&(cn.get_dateprea().contains("-"+day1)))
                           {   if(cn.getName().equals(holder.txtviewtitle.getText().toString()))
                           {
                               String p=  cn.getPresent();
                               int pr=Integer.parseInt(p);
                               pr++;
                               cn.setAbssent(String.valueOf(Integer.parseInt(cn.getAbssent())-1));
                               String newdatepre=cn.get_dateprea().replace("-"+day1,day1);
                               cn.set_datepreaact(newdatepre);
                               Log.i("seeeeeeeeeeeeeee",cn.get_dateprea());
                               cn.setPresent(String.valueOf(pr));
                               db.updateContact(cn);

                           }}
                           else if((cn.get_ttable().contains(ddd))&&((cn.get_dateprea().contains(day1)))&&(!(cn.get_dateprea().contains("-"+day1))))
                           {

                           }

                       }}
                   }
               }
           });





        return convertView;
    }






}


