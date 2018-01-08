package com.jskgmail.attendance;

/**
 * Created by JASPREET SINGH on 06-01-2018.
 */

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * Created by JASPREET SINGH on 05-01-2018.
 */

class Adaptersearchclasscllg extends BaseAdapter {
    Activity mcontext;
    ArrayList<String> title;


    int numb=1;
    public  Adaptersearchclasscllg(Activity context, ArrayList<String> arrayList) {
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
        final Adaptersearchclasscllg.ViewHolder holder;


        LayoutInflater inflater=mcontext.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.simplelistitem,null);
            holder=new Adaptersearchclasscllg.ViewHolder();
            holder.txtviewtitle=(TextView)convertView.findViewById(R.id.textView71);
            holder.r=(RelativeLayout)convertView.findViewById(R.id.r);
            holder.txtviewtitle.setText(title.get(position));
            holder.txtviewtitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.r.setBackgroundColor(Color.LTGRAY);
                    Log.i("mycllgname",ConnectActivity.mycllgname);


               /*     DatabaseHandler db = new DatabaseHandler(mcontext);
                    List<Contact> contacts = db.getAllContacts();

                    for (Contact cn : contacts) {if ((cn.getPo().equals(MainActivity.semno))){
                        if (cn.getName().equals(MainActivity.subbb)) {
                            Log.e("cnnameee",cn.getName());
                           cn.setName(title.get(position));
                        }}}
*/
               CaldroidSampleActivity.addclass(title.get(position));




                }
            });



        }
        else{
            holder=(Adaptersearchclasscllg.ViewHolder)convertView.getTag();
        }





        return convertView;
    }

}
