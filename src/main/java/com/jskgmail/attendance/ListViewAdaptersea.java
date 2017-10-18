package com.jskgmail.attendance;

/**
 * Created by JASPREET SINGH on 16-10-2017.
 */

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JASPREET SINGH on 06-08-2017.
 */

public class ListViewAdaptersea extends BaseAdapter {
    Activity mcontext;
    ArrayList<String> title;
    ArrayList<String> description;
    ArrayList<String> per;
    ArrayList<String> arrayList=SearchActivity.arrayList29;;
    ArrayList<String> arrayList1=SearchActivity.arrayList229;;
    ArrayList<String> arrayList111=SearchActivity.arrayList2299;;



    public ListViewAdaptersea(SearchActivity context, ArrayList<String> arrayList, ArrayList<String> arrayList1, ArrayList<String> arrayList119) {
        mcontext=context;
        title=arrayList;
        description=arrayList1;
        per=arrayList119;
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
ImageButton addas;
        TextView percc;
        TextView fr;
        TextView inc;
        ImageView img;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;


        LayoutInflater inflater=mcontext.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.allfriend,null);
            holder=new ViewHolder();
            holder.txtviewtitle=(TextView)convertView.findViewById(R.id.textView68);


            holder.txtviewdesc=(TextView)convertView.findViewById(R.id.textView69);
            holder.addas=(ImageButton)convertView.findViewById(R.id.imageButton5) ;
            holder.fr=(TextView)convertView.findViewById(R.id.textView54) ;
holder.percc=(TextView)convertView.findViewById(R.id.textView81);
holder.inc=(TextView)convertView.findViewById(R.id.textView53);
holder.img=(ImageView)convertView.findViewById(R.id.imageView6);



                holder.txtviewtitle.setText(title.get(position));
                holder.txtviewdesc.setText(description.get(position));
holder.percc.setText(per.get(position)+"%");
            float in=(Float.valueOf(MainActivity.percentagesending)-Float.valueOf(per.get(position)));
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



            DatabaseFriend db = new DatabaseFriend(mcontext);
            List<Friends> contacts = db.getAllContacts();





            final int[] i = {0};



            for (Friends cn : contacts) {

                if(cn.getName().equals(title.get(position)))
                {   i[0]=1;holder.addas.setImageResource(R.drawable.ic_done_black_24dp);}
            }

            holder.addas.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
if(i[0]==0){
holder.addas.setImageResource(R.drawable.ic_done_black_24dp);
        holder.fr.setText("Friends");
        Log.d("jsjsjs",title.get(position));
        arrayList.add(title.get(position));
        arrayList1.add(description.get(position));
    arrayList111.add(per.get(position));
    DatabaseFriend db = new DatabaseFriend(mcontext);
    db.addContact(new Friends(title.get(position)));


    DatabaseFriend db1 = new DatabaseFriend(mcontext);
    List<Friends> contacts = db1.getAllContacts();

    for (Friends cn : contacts) {
        Log.d("cccccc",cn.getName());
    }


i[0]=1;
go();
    }
        else if(i[0]==1)
{holder.addas.setImageResource(R.drawable.ic_person_add_black_24dp);
    holder.fr.setText("Add Friend");
    Log.d("jsjsjs",title.get(position));
    for(int i=0;i<arrayList.size();i++) {
        if(arrayList.get(i).equals(title.get(position)))
        {

            arrayList.remove(i);
        arrayList1.remove(i);
        arrayList111.remove(i);
        }


        DatabaseFriend db = new DatabaseFriend(mcontext);
        List<Friends> contacts = db.getAllContacts();


        for (Friends cn : contacts) {
if(cn.getName().equals(title.get(position)))
{   db.deleteContact(cn);
db.updateContact(cn);}
            notifyDataSetChanged();
        }






    }

    i[0]=0;
    go();
}


    }





});







        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }





        return convertView;
    }

void go()
{




    ListViewAdapteraddfri adapterr=new ListViewAdapteraddfri((SearchActivity) mcontext,arrayList,arrayList1,arrayList111);
    SearchActivity.friendlist.setAdapter(adapterr);






}
/*
void gogo(final String username)
{String per;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("user");



    myRef.addListenerForSingleValueEvent(new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
if(username.equals(dataSnapshot1.getKey()))
                {
                    Log.d("soso", dataSnapshot1.getKey());
                    Log.d("sosooo", "" + dataSnapshot1.child("name").getValue());
                    if (!(dataSnapshot1.getKey().equals(ConnectActivity.usernamee))) {
                        arrayList.add("" + dataSnapshot1.getKey());
                        arrayList1.add(" : " + dataSnapshot1.child("name").getValue());
                    }
//per =dataSnapshot1.child("percent").getValue();
                }
            }
            ListViewAdapteraddfri adapterr=new ListViewAdapteraddfri((SearchActivity) mcontext,arrayList,arrayList1);
            SearchActivity.friendlist.setAdapter(adapterr);



        } @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }

*/




}

