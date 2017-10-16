package com.jskgmail.attendance;

/**
 * Created by JASPREET SINGH on 16-10-2017.
 */

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JASPREET SINGH on 06-08-2017.
 */

public class ListViewAdaptersea extends BaseAdapter {
    Activity mcontext;
    ArrayList<String> title;
    ArrayList<String> description;
String textt="";

    public ListViewAdaptersea(SearchActivity context, ArrayList<String> arrayList, ArrayList<String> arrayList1) {
        mcontext=context;
        title=arrayList;
        description=arrayList1;
    }

    public ListViewAdaptersea(String text, SearchActivity context, ArrayList<String> arrayList, ArrayList<String> arrayList1)
    {

        mcontext=context;
        title=arrayList;
        description=arrayList1;
        textt=text;
        Log.v("sosososo",textt);


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
        TextView txtviewdesc;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        LayoutInflater inflater=mcontext.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.allfriend,null);
            holder=new ViewHolder();
            holder.txtviewtitle=(TextView)convertView.findViewById(R.id.textView48);


            holder.txtviewdesc=(TextView)convertView.findViewById(R.id.textView49);
            holder.txtviewdesc.setVisibility(View.VISIBLE);
            holder.txtviewtitle.setVisibility(View.VISIBLE);



                holder.txtviewtitle.setText(title.get(position));
                holder.txtviewdesc.setText(description.get(position));










        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }





        return convertView;
    }



}

