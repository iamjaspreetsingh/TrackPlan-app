package com.jskgmail.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JASPREET SINGH on 03-08-2017.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";

    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_PRE="present";
    private static final String KEY_ABS="absent";
    private static final String KEY_TIM_TA="ttable";
    private static final String KEY_DAT_PR="datepr";
    private static final String KEY_POS_LI="pos";
    private static final String KEY_POS_NOTE="note";
    private static final String KEY_POS_DANOTE="datesofnote";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_PRE + " TEXT,"+ KEY_ABS + " TEXT," +KEY_TIM_TA+ " TEXT,"+KEY_DAT_PR+ " TEXT,"+KEY_POS_LI+ " TEXT,"+KEY_POS_NOTE+ " TEXT,"+KEY_POS_DANOTE+ " TEXT"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
}

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }


    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone Number
values.put(KEY_PRE,contact.getPresent());


        values.put(KEY_ABS,contact.getAbssent());
        values.put(KEY_TIM_TA,contact.get_ttable());
        values.put(KEY_DAT_PR,contact.get_dateprea());
        values.put(KEY_POS_LI,contact.getPo());
values.put(KEY_POS_NOTE,contact.getNote());
        values.put(KEY_POS_DANOTE,contact.getNote());
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }


    // Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO,KEY_PRE,KEY_ABS,KEY_TIM_TA,KEY_DAT_PR,KEY_POS_LI,KEY_POS_NOTE,KEY_POS_DANOTE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
        // return contact
        return contact;
    }


    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));

                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contact.setPresent(cursor.getString(3));
                contact.setAbssent(cursor.getString(4));
                contact.set_ttable(cursor.getString(5));
                contact.set_dateprea(cursor.getString(6));
                contact.setPo(cursor.getString(7));
                contact.setNote(cursor.getString(8));
                contact.set_datenotes(cursor.getString(9));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }


    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
        values.put(KEY_PRE,contact.getPresent());
        values.put(KEY_ABS,contact.getAbssent());
        values.put(KEY_TIM_TA,contact.get_ttable());
        values.put(KEY_DAT_PR,contact.get_dateprea());
        values.put(KEY_POS_LI,contact.getPo());
        values.put(KEY_POS_NOTE,contact.getNote());
        values.put(KEY_POS_DANOTE,contact.get_datesofnote());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }

}