package com.jskgmail.attendance;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jetradar.desertplaceholder.DesertPlaceholder;

import java.util.ArrayList;
import java.util.List;

public class MainrearrActivity extends AppCompatActivity {


    private MyArrayAdapter mAdapter;
    private ListView mListView;
    private boolean mSortable = false;
    private String mDragString;
    private int mPosition = -1;
  static int pos=0,changed=0;
static String naaaa="";
    int i=0,asd=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_mainrearr);setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        asd=0;

        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts)
            asd++;


        DesertPlaceholder desertPlaceholder = (DesertPlaceholder) findViewById(R.id.placeholder);
        desertPlaceholder.setOnButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // do stuff
            }
        });




        if(asd==0)
        {
            desertPlaceholder.setVisibility(View.VISIBLE);
        }
        else {desertPlaceholder.setVisibility(View.GONE);

        }







        rearranging();
















        check();

        go();
}

private void check()
{
    SharedPreferences pref1=getSharedPreferences("rearrange",0);
    final String insem122=pref1.getString("rearrange","0.0");
    Log.i("rearrangehowto",insem122);
    if(insem122.equals("0.0"))
    { DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {

            if ((cn.getPo().equals(MainActivity.semno))){


                mAdapter.add(cn.getName());

            }}

    }
else {
        String[] nameposi = insem122.split("\\.");
        for(int ii=0;ii<nameposi.length;ii++)
        {
            String nameposssss=nameposi[ii];
            if(nameposssss.contains("\\,"))
            {
            String[] nameposgoset=nameposssss.split("\\,");
           Log.i("nameposgo00000",nameposgoset[0]);

            Log.i("nameposgo11111",nameposgoset[1]);
            mAdapter.add(nameposgoset[1]);

        }}





























    }
}





public void go()
{








    DatabaseHandler db = new DatabaseHandler(this);
    List<Contact> contacts = db.getAllContacts();
    int i=-1;
    String napo="";int fg=1;
    for (Contact cn : contacts) {

        if ((cn.getPo().equals(MainActivity.semno))){

            napo=napo+String.valueOf(fg+",")+cn.getName()+".";

            fg++;
        }}


    FloatingActionButton save=(FloatingActionButton)findViewById(R.id.savee);
    final String finalNapo = napo;
    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SharedPreferences.Editor editor = getSharedPreferences("sorting", 0).edit();
            editor.putString("sorting", "byrearrange");
            editor.commit();




            if(finalNapo.contains("\\.")) {

                String[] rear = finalNapo.split("\\.");
                String[] reariicop = finalNapo.split("\\.");

                String[] rearii = new String[rear.length];
                for (int ii = 0; ii < rear.length; ii++) {

                    for (int jj = 0; jj < rear.length; jj++) {
                        Log.i("yyyyyyyyyyywhywhyyyy", rear[jj]);
                        Log.i("yyyyywhyhyyyy", mAdapter.getItem(ii));
                        if ((reariicop[jj]).contains(String.valueOf(mAdapter.getItem(ii)))) {
                            Log.i("yyyyyyyyyyyyyy", "yyyyyyyyyy");
                            rearii[ii] = String.valueOf(ii + ",") + mAdapter.getItem(ii);

                        }


                    }
                }


                String napoo = "";
                for (int iii = 0; iii < rear.length; iii++) {
                    napoo = napoo + rearii[iii] + ".";
                }


                Log.i("napooooooooooooooooooo", napoo);
                SharedPreferences.Editor editor1 = getSharedPreferences("rearrange", 0).edit();
                editor1.putString("rearrange", napoo);
                editor1.commit();


            }


        }
    });









}














    public void rearranging() {

        mListView = (ListView) findViewById(R.id.listrearr);


        mAdapter = new MyArrayAdapter(this, R.layout.row_string);



            mListView.setAdapter(mAdapter);



        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (!mSortable) {
                    return false;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {

                        int position = mListView.pointToPosition((int) event.getX(), (int) event.getY());
                        if (position < 0) {
                            break;
                        }

                        if (position != mPosition) {
                            mPosition = position;
                            mAdapter.remove(mDragString);
                            mAdapter.insert(mDragString, mPosition);
                        }
                        return true;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE: {
                        stopDrag();
                        return true;
                    }
                }
                return false;
            }
        });



        DatabaseHandler db1 = new DatabaseHandler(this);
        List<Contact> contacts1 = db1.getAllContacts();


    }





    public void startDrag(String string) {
        mPosition = -1;
        mSortable = true;
        mDragString = string;
        mAdapter.notifyDataSetChanged();
    }

    public void stopDrag() {
        mPosition = -1;
        mSortable = false;
        mDragString = null;
        mAdapter.notifyDataSetChanged();


    }

    static class ViewHolder {
        TextView title;
        ImageView handle;
    }


    public class MyArrayAdapter extends ArrayAdapter<String> {

        private ArrayList<String> mStrings = new ArrayList<String>();
        private LayoutInflater mInflater;
        private int mLayout;

        public MyArrayAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.mLayout = textViewResourceId;
        }

        @Override
        public void add(String row) {
            super.add(row);
            mStrings.add(row);

        }

        @Override
        public void insert(String row, int position) {
            super.insert(row, position);
            mStrings.add(position, row);


        }

        @Override
        public void remove(String row) {
            super.remove(row);
            mStrings.remove(row);

        }

        @Override
        public void clear() {
            super.clear();
            mStrings.clear();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(this.mLayout, null);
                assert view != null;
                holder = new ViewHolder();
                holder.title = (TextView) view.findViewById(R.id.title);
                holder.handle = (ImageView) view.findViewById(R.id.handle);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            final String string = mStrings.get(position);

            holder.title.setText(string);


            holder.handle.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        startDrag(string);
                        return true;
                    }
                    return false;
                }
            });


            if (mDragString != null && mDragString.equals(string)) {
                view.setBackgroundColor(Color.parseColor("#9933b5e5"));
            } else {
                view.setBackgroundColor(Color.TRANSPARENT);
            }

            return view;
        }
    }




}