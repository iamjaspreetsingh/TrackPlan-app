package com.jskgmail.attendance;

/**
 * Created by JASPREET SINGH on 11-08-2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JASPREET SINGH on 07-08-2017.
 */

/**
 * Created by JASPREET SINGH on 06-08-2017.
 */

public class ListViewAdapterhistory extends BaseAdapter {
    Activity context;
    ArrayList<String> title;
    ArrayList<String> dateinfo;

    public ListViewAdapterhistory(MainhistoryActivity context,ArrayList<String> title,ArrayList<String> dateinfo)
    {
        super();
        this.context=context;
        this.title=title;
this.dateinfo=dateinfo;

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
        TextView txtviewtitle11;
        CheckBox checkBox,checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;


        LayoutInflater inflater=context.getLayoutInflater();
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.layouthistory, null);
            holder = new ViewHolder();
            holder.txtviewtitle = (TextView) convertView.findViewById(R.id.historyitem);
            holder.txtviewtitle11 = (TextView) convertView.findViewById(R.id.textView11);
            holder.txtviewtitle.setText(title.get(position));
            holder.txtviewtitle11.setText(dateinfo.get(position));
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }



        return convertView;
    }


}

