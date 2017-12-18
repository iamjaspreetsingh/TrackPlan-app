package com.jskgmail.attendance;

/**
 * Created by JASPREET SINGH on 18-10-2017.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by JASPREET SINGH on 06-08-2017.
 */

public class ListViewAdteach extends BaseAdapter  {
    Activity mcontext;
    ArrayList<String> title;
    ArrayList<String> description;

int i=0;
    public ListViewAdteach (MainteachersActivity context, ArrayList<String> arrayList, ArrayList<String> arrayList1) {
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
ProgressBar p;
        ImageView img;
        ImageView start;
        TextView st;TextView st1;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;


        LayoutInflater inflater=mcontext.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.adddclasst,null);
            holder=new ViewHolder();
            holder.txtviewtitle=(TextView)convertView.findViewById(R.id.textView69);
holder.p=(ProgressBar)convertView.findViewById(R.id.progressBar4);
holder.img=(ImageView)convertView.findViewById(R.id.imageView4);
            holder.start=(ImageView)convertView.findViewById(R.id.imageButton7);
            holder.txtviewdesc=(TextView)convertView.findViewById(R.id.textView81);
holder.st=(TextView)convertView.findViewById(R.id.textView78);
            holder.st1=(TextView)convertView.findViewById(R.id.textView79);
holder.start.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (i%2==0)
        { holder.start.setImageResource(R.drawable.atttaking);
        takeatt("1",description.get(position)+title.get(position));
            holder.st.setVisibility(View.VISIBLE);
            holder.st1.setVisibility(View.VISIBLE);
            holder.p.setVisibility(View.VISIBLE);
        }
        else  { holder.start.setImageResource(R.drawable.attendtea);
            takeatt("0",description.get(position)+title.get(position));
            holder.st.setVisibility(View.GONE);
            holder.st1.setVisibility(View.GONE);
            holder.p.setVisibility(View.GONE);
        }
    i++;
    }
});



            holder.txtviewtitle.setText(title.get(position));
            String fname;
            fname=description.get(position);


            holder.txtviewdesc.setText(fname);
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color1 = generator.getRandomColor();

            String[] a = title.get(position).split("");
            if ((a.length > 1)) {
                TextDrawable drawable = TextDrawable.builder().beginConfig().withBorder(4).textColor(R.color.caldroid_black).useFont(Typeface.DEFAULT).bold().toUpperCase().endConfig().buildRound(a[1], color1);
                holder.img.setImageDrawable(drawable);
            }


notifyDataSetChanged();


        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }





        return convertView;
    }


void takeatt(String i,String subclass)
{
    FirebaseDatabase database = MainteachersActivity.database;
    DatabaseReference myRef = database.getReference("Colleges");
    DatabaseReference myRef1 = database.getReference("Colleges").child(MainteachersActivity.colname).child(subclass);

    myRef1.child("Take").setValue(i);









}
}

