package com.jskgmail.attendance;

/**
 * Created by JASPREET SINGH on 18-10-2017.
 */

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JASPREET SINGH on 06-08-2017.
 */

public class ListViewAdteach extends BaseAdapter  {
    Activity mcontext;
    ArrayList<String> title;
    ArrayList<String> description;
    ArrayList<String> perce;

    public ListViewAdteach (SearchActivity context, ArrayList<String> arrayList, ArrayList<String> arrayList1,ArrayList<String> arrayList2) {
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
        ImageView img;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        LayoutInflater inflater=mcontext.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.adddclasst,null);
            holder=new ViewHolder();
            holder.txtviewtitle=(TextView)convertView.findViewById(R.id.textView68);


            holder.txtviewdesc=(TextView)convertView.findViewById(R.id.textView69);
            holder.txtper=(TextView)convertView.findViewById(R.id.textView81);
            holder.inc=(TextView)convertView.findViewById(R.id.textView73);
            holder.img=(ImageView)convertView.findViewById(R.id.imageView5);
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






            String a=": "+title.get(position);

            holder.txtviewtitle.setText(a);
            String fname;
            fname=description.get(position);
            if(description.get(position).length()>19)
            {
                String[] fnamess= fname.split(" ");
                Log.d("pppp",fnamess[2]);
                if(fnamess.length>2)
                {fname=" "+fnamess[2]+" "+fnamess[3];}}
            else
                fname=description.get(position).replaceAll(":","");

            holder.txtviewdesc.setText(fname);
            holder.txtper.setText(perce.get(position)+"%");
            Log.d("zzzzz",perce.get(position));
            if(perce.get(position).equals(""))
                perce.set(position,"0.0");
            float in=(Float.valueOf(MainActivity.percentagesending)-Float.valueOf(perce.get(position)));
            in=(float)Math.round(in*100)/100;

            holder.inc.setText(String.valueOf(in)+"%");


            if(in<0)
            {
                holder.img.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                holder.inc.setTextColor(Color.RED);
                in=-in;
            }
            else
            {
                holder.img.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                holder.inc.setTextColor(Color.rgb(0,128,0));
            }
            holder.inc.setText(String.valueOf(in)+"%");




        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }





        return convertView;
    }



}

