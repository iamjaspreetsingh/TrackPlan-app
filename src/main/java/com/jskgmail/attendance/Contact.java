package com.jskgmail.attendance;

import android.util.Log;

/**
 * Created by JASPREET SINGH on 03-08-2017.
 */
public class Contact {

    //private variables
    int _id;
    String _name="";
    String _phone_number;
    String _present,_absent;
    String _ttable="";
    String datepre="";
    String po;
    String note;
    String datesofnote="";
    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String name, String _phone_number,String present,String absent,String _ttable,String datepre,String po,String note,String datesofnote){
        this._id = id;

        this._name = name;
        this._phone_number = _phone_number;
        this._present=present;
        this._absent=absent;
        this._ttable=_ttable;
        this.datepre=datepre;
        this.po=po;
        this.note=note;
        this.datesofnote=datesofnote;
    }

    // constructor
    public Contact(String name, String _phone_number,String present,String absent,String _ttable,String datepre,String po,String note,String datesofnote){
        this._name = name;

        this._phone_number = _phone_number;
        this._present=present;
        this._absent=absent;
        this._ttable=_ttable;
        this.datepre=datepre;
        this.po=po;
        this.note=note;
        this.datesofnote=datesofnote;
    }
    public Contact(String name, String _phone_number,String present,String absent,String _ttable,String datepre){
        this._name = name;

        this._phone_number = _phone_number;
        this._present=present;
        this._absent=absent;
        this._ttable=_ttable;
        this.datepre=datepre;

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
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
    public String getPresent()
    {
        return this._present;
    }
    public void setPresent(String present)
    {
        this._present=present;
    }

    public String getAbssent()
    {
        return this._absent;
    }
    public void setAbssent(String absent)
    {
        this._absent=absent;
    }
    public String get_ttable()
    {
        return this._ttable;
    }
    public void setactt(String _ttable)
    {
        this._ttable=(_ttable);
    }
    public void set_ttable(String _ttable)
    {
        this._ttable=this._ttable.concat(_ttable);
    }
    public String get_dateprea()
    {
        return this.datepre;
    }
    public void set_dateprea(String _datep)
    {
        this.datepre=this.datepre.concat(_datep);
    }
    public void set_datepreaact(String _datep)
    {
        this.datepre=(_datep);
    }
    public String getPo()
    {
        return this.po;
    }
    public void setPo(String po)
    {
        this.po=po;
    }

    public String getNote()
    {
        return this.note;
    }
    public void setNote(String note)
    {
        this.note=note;
    }
    public void set_datenotes(String datesofnote)
    {
        this.datesofnote=this.datesofnote.concat(datesofnote);
    }
    public void set_datenotesact(String  datesofnote)
    {
        this.datesofnote=(datesofnote);
    }

    public String get_datesofnote()
    {
        return this.datesofnote;
    }



}