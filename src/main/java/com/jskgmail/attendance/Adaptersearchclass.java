package com.jskgmail.attendance;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JASPREET SINGH on 05-01-2018.
 */

class Adaptersearchclass extends BaseAdapter {
    Activity mcontext;
    ArrayList<String> title;


    int numb=1;
    public  Adaptersearchclass(ConnectActivity context, ArrayList<String> arrayList) {
        mcontext=context;
        title=arrayList;

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
        RelativeLayout r;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Adaptersearchclass.ViewHolder holder;


        LayoutInflater inflater=mcontext.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.simplelistitem,null);
            holder=new Adaptersearchclass.ViewHolder();
            holder.txtviewtitle=(TextView)convertView.findViewById(R.id.textView71);
            holder.r=(RelativeLayout)convertView.findViewById(R.id.r);
            holder.txtviewtitle.setText(title.get(position));
            holder.txtviewtitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConnectActivity.mycllgname=title.get(position);
                    holder.r.setBackgroundColor(Color.LTGRAY);
                    Log.i("mycllgname",ConnectActivity.mycllgname);
                }
            });



        }
        else{
            holder=(Adaptersearchclass.ViewHolder)convertView.getTag();
        }





        return convertView;
    }

}
