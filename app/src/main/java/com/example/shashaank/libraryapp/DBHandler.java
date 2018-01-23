package com.example.shashaank.libraryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shashaank on 2018-01-01.
 */

public class DBHandler extends SQLiteOpenHelper{

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
// declaring all the columns and assigning them names
    public static final String DATABASE_NAME = "Books.db";
    public static final String TABLE_NAME = "Book_Table";
    public static final String COL1 = "ID";
    public static final String COL2 = "BOOK";
    public static final String COL3 = "AUTHOR";
    public static final String COL4 = "LOCATION";
    public static final String COL5 = "USER";

    @Override
   //creating the table using the names declared above
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, "
                + COL3 + " TEXT, "
                + COL4 + " TEXT, "
                + COL5 +" TEXT ) ";
        // calls the sqlite database to create the table using code above
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    // updates the table with new data and drops old table 
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String book, String author, String location, String user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, book);
        contentValues.put(COL3, author);
        contentValues.put(COL4, location);
        contentValues.put(COL5, user);
// a variable that checks the result from the sqlitedatabase.insert to see if it the process was successful or not
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        // gets all of the data from the entire table
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor result = sqLiteDatabase.rawQuery(" select * from " + TABLE_NAME, null);
        return result;
    }

    public Cursor rowData(String name){
        //gets all the data from the row using the parameter name
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = sqLiteDatabase.rawQuery(query, null);
        return data;
    }

    public void updateData(int id,String newName, String oldName){
        //updates the name in the table
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        sqLiteDatabase.execSQL(query);
    }
    //updates the author in the table
    public void updateAuth(int id,String newAuth, String oldAuth){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + newAuth + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + oldAuth + "'";
        sqLiteDatabase.execSQL(query);
    }
    //updates the user in the table
    public void updateUser(int id,String newUser, String oldUser){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL5 +
                " = '" + newUser + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL5 + " = '" + oldUser + "'";
        sqLiteDatabase.execSQL(query);
    }
    //updates the location in the table
    public void updateLocation(int id,String newLocation, String oldLocation){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL4 +
                " = '" + newLocation + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL4 + " = '" + oldLocation + "'";
        sqLiteDatabase.execSQL(query);
    }

    public void deleteData(int id, String name, String author, String user,String location){
        //Deletes all the data in a certain row
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1
                + " = '" + id + "'" + " AND " + COL2 + " = '" + name + "'"
                + " AND " + COL3 + " = '" + author + "'"
                + " AND " + COL4 + " = '" + location + "'"
                + " AND " + COL5 + " = '" + user + "'";
        sqLiteDatabase.execSQL(query);
    }


}
