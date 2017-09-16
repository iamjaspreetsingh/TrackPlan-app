package com.jskgmail.attendance;

/**
 * Created by JASPREET SINGH on 07-08-2017.
 */

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JASPREET SINGH on 06-08-2017.
 */

public class ListViewAdapterAttendance extends BaseAdapter {
    Activity context;
    ArrayList<String> title;


    public ListViewAdapterAttendance(Main99Activity context,ArrayList<String> title)
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
        TextView txtviewtitle;
CheckBox checkBox,checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;


        LayoutInflater inflater=context.getLayoutInflater();
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.layoutsetattendance, null);
            holder = new ViewHolder();
            holder.txtviewtitle = (TextView) convertView.findViewById(R.id.friendName);
            holder.checkBox=(CheckBox)convertView.findViewById(R.id.checkBox);
            holder.checkBox1=(CheckBox)convertView.findViewById(R.id.checkBox2);
            holder.checkBox2=(CheckBox)convertView.findViewById(R.id.checkBox3);
            holder.checkBox3=(CheckBox)convertView.findViewById(R.id.checkBox4);
            holder.checkBox4=(CheckBox)convertView.findViewById(R.id.checkBox5);
            holder.checkBox5=(CheckBox)convertView.findViewById(R.id.checkBox6);

            DatabaseHandler db = new DatabaseHandler(context);
            List<Contact> contacts = db.getAllContacts();
            for (Contact cn : contacts) {
                if ((cn.getPo().equals(MainActivity.semno))) {
                    if (title.get(position).equals(cn.getName())) {
                        if (cn.get_ttable().contains("0"))
                            holder.checkBox.setChecked(true);

                        if (cn.get_ttable().contains("1"))
                            holder.checkBox1.setChecked(true);

                        if (cn.get_ttable().contains("2"))
                            holder.checkBox2.setChecked(true);

                        if (cn.get_ttable().contains("3"))
                            holder.checkBox3.setChecked(true);

                        if (cn.get_ttable().contains("4"))
                            holder.checkBox4.setChecked(true);

                        if (cn.get_ttable().contains("5"))
                            holder.checkBox5.setChecked(true);


                    }
                }}

                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        DatabaseHandler db = new DatabaseHandler(context);
                        List<Contact> contacts = db.getAllContacts();

                        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
                            if (title.get(position).equals(cn.getName())) {
                                if (holder.checkBox.isChecked()) {

                                    cn.set_ttable("0");
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());

                                }
                                else if (holder.checkBox.isChecked()==false) {

                                    String newtt=cn.get_ttable().replace("0","");
                                    cn.setactt(newtt);
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());

                                }
                            }
                        }}


                    }
                });

                holder.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        DatabaseHandler db = new DatabaseHandler(context);
                        List<Contact> contacts = db.getAllContacts();

                        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
                            if (title.get(position).equals(cn.getName())) {
                                if (holder.checkBox1.isChecked()) {

                                    cn.set_ttable("1");
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());
                                }
                                else if (holder.checkBox.isChecked()==false) {

                                    String newtt=cn.get_ttable().replace("1","");
                                    cn.setactt(newtt);
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());

                                }
                            }}
                        }


                    }
                });

                holder.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        DatabaseHandler db = new DatabaseHandler(context);
                        List<Contact> contacts = db.getAllContacts();

                        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
                            if (title.get(position).equals(cn.getName())) {
                                if (holder.checkBox2.isChecked()) {

                                    cn.set_ttable("2");
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());
                                }
                                else if (holder.checkBox.isChecked()==false) {

                                    String newtt=cn.get_ttable().replace("2","");
                                    cn.setactt(newtt);
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());

                                }
                            }
                        }}


                    }
                });


                holder.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        DatabaseHandler db = new DatabaseHandler(context);
                        List<Contact> contacts = db.getAllContacts();

                        for (Contact cn : contacts) {
                            if ((cn.getPo().equals(MainActivity.semno))) {
                                if (title.get(position).equals(cn.getName())) {
                                    if (holder.checkBox3.isChecked()) {

                                        cn.set_ttable("3");
                                        db.updateContact(cn);
                                        Log.i("dfgdgdg", cn.get_ttable());
                                    }

                                    else if (holder.checkBox.isChecked()==false) {

                                        String newtt=cn.get_ttable().replace("3","");
                                        cn.setactt(newtt);
                                        db.updateContact(cn);
                                        Log.i("dfgdgdg", cn.get_ttable());

                                    }
                                }
                            }
                        }

                    }
                });


                holder.checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        DatabaseHandler db = new DatabaseHandler(context);
                        List<Contact> contacts = db.getAllContacts();

                        for (Contact cn : contacts) {
                            if (title.get(position).equals(cn.getName())) {if ((cn.getPo().equals(MainActivity.semno))){
                                if (holder.checkBox4.isChecked()) {

                                    cn.set_ttable("4");
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());
                                }

                                else if (holder.checkBox.isChecked()==false) {

                                    String newtt=cn.get_ttable().replace("4","");
                                    cn.setactt(newtt);
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());

                                }
                            }}
                        }


                    }
                });


                holder.checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        DatabaseHandler db = new DatabaseHandler(context);
                        List<Contact> contacts = db.getAllContacts();

                        for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))) {
                            if (title.get(position).equals(cn.getName())) {
                                if (holder.checkBox5.isChecked()) {

                                    cn.set_ttable("5");
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());
                                }

                                else if (holder.checkBox.isChecked()==false) {

                                    String newtt=cn.get_ttable().replace("5","");
                                    cn.setactt(newtt);
                                    db.updateContact(cn);
                                    Log.i("dfgdgdg", cn.get_ttable());

                                }
                            }

                        }}


                    }
                });


            holder.txtviewtitle.setText(title.get(position));
        }
        else{
            holder=(ViewHolder)convertView.getTag();

        }



        return convertView;
    }


}

