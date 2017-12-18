package com.jskgmail.attendance;

import android.util.Log;

/**
 * Created by JASPREET SINGH on 18-10-2017.
 */

public class Teacherclass {





    //private variables
    int _id;
    String _name;
    String _classn;
    // Empty constructor
    public Teacherclass()
    {

    }
    // constructor
    public Teacherclass(int id, String name,String classn){
        this._id = id;

        this._name = name;
        this._classn = classn;

    }

    // constructor
    public Teacherclass( String name,String classn){


        this._name = name;
        this._classn = classn;
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
    public String getcName(){
        return this._classn;
    }

    // setting name
    public void setcName(String name){
        this._classn = name;
        Log.i("sd","dsdsds");
    }


}












