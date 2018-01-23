package com.example.shashaank.libraryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button addBookPage, viewBookPage, aboutUs;
    DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// casting variables to their respective types
        addBookPage = (Button)findViewById(R.id.btn1);
        viewBookPage = (Button)findViewById(R.id.btn2);
        aboutUs = (Button)findViewById(R.id.btn3);

        mydb = new DBHandler(this);
// button to open add book page
        addBookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddBook();
            }
        });
// button to view books
        viewBookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openViewBook();
            }
        });
        // button to view about us page
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutUs();
            }
        });
    }
//toast method
    public void Toastmsg(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }
    // the three methods are to open their respective pages
     public void openAddBook(){
        Intent intent = new Intent(this, addBook.class);
        startActivity(intent);
    }
    public void openViewBook(){
        Intent intent = new Intent(this, viewBooks.class);
        startActivity(intent);
    }
    public void openAboutUs(){
        Intent intent = new Intent(this, AboutUsPage.class);
        startActivity(intent);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up addBookPage, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
