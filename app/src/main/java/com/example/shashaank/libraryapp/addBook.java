package com.example.shashaank.libraryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addBook extends AppCompatActivity {
    private EditText edtBook, edtAuth, edtName;

    private Button addBook;
    DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtBook = (EditText)findViewById(R.id.edtBook);
        edtAuth = (EditText)findViewById(R.id.edtAuth);
        edtName = (EditText)findViewById(R.id.edtName);

        addBook = (Button)findViewById(R.id.addButton);



        mydb = new DBHandler(this);
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            // Code to run once
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
            firstBook();

        }

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addBookName = edtBook.getText().toString().trim();
                String addBookAuth = edtAuth.getText().toString().trim();
                String addUser = edtName.getText().toString().trim();
                bookList b1 = new bookList(addBookName, addBookAuth, addUser);
                AddData(b1.get_book(), b1.get_author(), b1.get_location(), b1.get_user());
                Toastmsg("Book has been added!");

                openMainPage();
            }

        });
    }
    public void firstBook(){
            boolean insertData = mydb.addData("", "", "Available", "");
    }
    public void AddData(String book, String author, String location, String user) {
        boolean insertData = mydb.addData(book, author, location, user);
    }

    public void openMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Toastmsg(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }

}
