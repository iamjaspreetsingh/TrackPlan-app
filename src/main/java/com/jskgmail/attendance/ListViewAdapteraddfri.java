package com.jskgmail.attendance;

/**
 * Created by JASPREET SINGH on 18-10-2017.
 */

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by JASPREET SINGH on 06-08-2017.
 */

public class ListViewAdapteraddfri extends BaseAdapter  {
    Activity mcontext;
    ArrayList<String> title;
    ArrayList<String> description;
    ArrayList<String> perce;

    public ListViewAdapteraddfri (SearchActivity context, ArrayList<String> arrayList, ArrayList<String> arrayList1,ArrayList<String> arrayList2) {
        mcontext=context;
        title=arrayList;
        description=arrayList1;
        perce=arrayList2;
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
        TextView txtper;
        TextView inc;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        LayoutInflater inflater=mcontext.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.adddfriend,null);
            holder=new ViewHolder();
            holder.txtviewtitle=(TextView)convertView.findViewById(R.id.textView68);


            holder.txtviewdesc=(TextView)convertView.findViewById(R.id.textView69);
            holder.txtper=(TextView)convertView.findViewById(R.id.textView81);
            holder.inc=(TextView)convertView.findViewById(R.id.textView73);

    /*        holder.txtviewdesc.setVisibility(View.VISIBLE);
            holder.txtviewtitle.setVisibility(View.VISIBLE);


            if(!(textt.equals("")))
            { if((title.get(position).toLowerCase().contains(textt))||(description.get(position).toLowerCase().contains(textt))) {
                holder.txtviewdesc.setVisibility(View.VISIBLE);
                holder.txtviewtitle.setVisibility(View.VISIBLE);

                holder.txtviewtitle.setText(title.get(position));
                holder.txtviewdesc.setText(description.get(position));
            }
            else {
                holder.txtviewdesc.setVisibility(View.GONE);
            holder.txtviewtitle.setVisibility(View.GONE);
            }
            }
            else {

              */








            holder.txtviewtitle.setText(title.get(position));
            String[] de=description.get(position).split("");
            for(int i=0;i<de.length;i++)
            {
                if(i>15)
                    de[i]="";
                if(de[i]==" ")
                    de[i]="?";
            }
            String fullnm= Arrays.toString(de).replaceAll("\\,","").replaceAll("\\[","").replaceAll("\\]","").replaceAll(":","").replaceAll("//?"," ").replaceAll(" ","");
            holder.txtviewdesc.setText(fullnm);
holder.txtper.setText(perce.get(position)+"%");
            Log.d("zzzzz",perce.get(position));
       float in=(Float.valueOf(MainActivity.percentagesending)-Float.valueOf(perce.get(position)));
holder.inc.setText(String.valueOf(in)+"%");








        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }





        return convertView;
    }



}

