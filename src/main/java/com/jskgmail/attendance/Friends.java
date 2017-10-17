package com.jskgmail.attendance;

import android.util.Log;

/**
 * Created by JASPREET SINGH on 18-10-2017.
 */

public class Friends {



    /**
     * Created by JASPREET SINGH on 03-08-2017.
     */


        //private variables
        int _id;
        String _name;

        // Empty constructor
      public Friends()
      {

      }
        // constructor
        public Friends(int id, String name){
            this._id = id;

            this._name = name;

        }

        // constructor
        public Friends( String name){


            this._name = name;

        }

        // getting ID
        public int getID(){
            return this._id;
        }

        // setting id
        public void setID(int id){
            this._id = id;
        }



        // getting name
        public String getName(){
            return this._name;
        }

        // setting name
        public void setName(String name){
            this._name = name;
            Log.i("sd","dsdsds");
        }

        // getting phone number



    }












