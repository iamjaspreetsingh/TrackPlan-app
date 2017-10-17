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
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JASPREET SINGH on 06-08-2017.
 */

public class ListViewAdaptersea extends BaseAdapter {
    Activity mcontext;
    ArrayList<String> title;
    ArrayList<String> description;
    final ArrayList<String> arrayList=new ArrayList<>();
    final ArrayList<String> arrayList1=new ArrayList<>();


    public ListViewAdaptersea(SearchActivity context, ArrayList<String> arrayList, ArrayList<String> arrayList1) {
        mcontext=context;
        title=arrayList;
        description=arrayList1;
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
        TextView fr;

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






                holder.txtviewtitle.setText(title.get(position));
                holder.txtviewdesc.setText(description.get(position));

            DatabaseFriend db = new DatabaseFriend(mcontext);
            List<Friends> contacts = db.getAllContacts();


            for (Friends cn : contacts) {

               if(cn.getName().equals(title.get(position)))
                   gogo(title.get(position));
            }


            final int[] i = {0};
holder.addas.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
if(i[0] %2==0){
holder.addas.setImageResource(R.drawable.ic_done_black_24dp);
        holder.fr.setText("Friends");
        Log.d("jsjsjs",title.get(position));
        arrayList.add(title.get(position));
        arrayList1.add(description.get(position));
    DatabaseFriend db = new DatabaseFriend(mcontext);
    List<Friends> contacts = db.getAllContacts();


    for (Friends cn : contacts) {

        cn.setName(title.get(position));

    }




    go();}
        else
{holder.addas.setImageResource(R.drawable.ic_person_add_black_24dp);
    holder.fr.setText("Add Friend");
    Log.d("jsjsjs",title.get(position));
    for(int i=0;i<arrayList.size();i++) {
        if(arrayList.get(i).equals(title.get(position)))
        {arrayList.remove(i);
        arrayList1.remove(i);}


        DatabaseFriend db = new DatabaseFriend(mcontext);
        List<Friends> contacts = db.getAllContacts();


        for (Friends cn : contacts) {
if(cn.getName().equals(title.get(position)))
    db.deleteContact(cn);
db.updateContact(cn);
        }






    }
}

i[0]++;

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




    ListViewAdapteraddfri adapterr=new ListViewAdapteraddfri((SearchActivity) mcontext,arrayList,arrayList1);
    SearchActivity.friendlist.setAdapter(adapterr);






}
void gogo(final String username)
{
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

                }
            }
            ListViewAdapteraddfri adapterr=new ListViewAdapteraddfri((SearchActivity) mcontext,arrayList,arrayList1);
            SearchActivity.friendlist.setAdapter(adapterr);



        } @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });




}

}

