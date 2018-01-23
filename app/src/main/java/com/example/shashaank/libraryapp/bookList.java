package com.example.shashaank.libraryapp;

/**
 * Created by Shashaank on 2017-12-25.
 */

public class bookList {
    private String _book;
    private String _author;
    private String _user;
    private boolean _location;
    String x = "";
    // declares variables

    // constructor to assign values using paremters
    public bookList(String book, String _author, String _user){
        this._book = book;
        this._author = _author;
        this._user = _user;
        this._location = true;

    }
    // methods to return each of the assigned variables
    public String get_book() {
        return this._book;
    }
    public String get_author() {
        return this._author;
    }
    public String get_user() {
        return this._user;
    }
    // method to return book location, location is automatically true when created
    public String get_location() {
        if (this._location == true){
            x= "Available";
        }
        else{
            x= "Checked Out";
        }
        return x;
    }
}
