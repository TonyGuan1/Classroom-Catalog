package com.example.shashaank.libraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class EditBooksActivity extends AppCompatActivity {
    private Button btnSave, btnDel, btnViewBooks, btnAddBook;
    private EditText editBook, editAuth, editUser;
    private ToggleButton toggleCheckout;

    DBHandler mydb;

    private String selectName, selectAuth, selectUser, selectLocation;
    private int selectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContentView(R.layout.content_edit_books);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnDel = (Button)findViewById(R.id.btnDelete);
        btnViewBooks = (Button)findViewById(R.id.btnList);
        btnAddBook = (Button)findViewById(R.id.btnOpenAdd);

        toggleCheckout = (ToggleButton)findViewById(R.id.toggleCheckout);

        editBook = (EditText) findViewById(R.id.editable_item);
        editAuth = (EditText) findViewById(R.id.editable_auth);
        editUser = (EditText) findViewById(R.id.editable_user);
        mydb = new DBHandler(this);

        //get intent from other class
        Intent recieveIntent = getIntent();
        selectId = recieveIntent.getIntExtra("id", -1);
        selectName = recieveIntent.getStringExtra("name");
        selectAuth = recieveIntent.getStringExtra("auth");
        selectLocation = recieveIntent.getStringExtra("location");
        selectUser = recieveIntent.getStringExtra("user");
        selectLocation = recieveIntent.getStringExtra("location");

        editBook.setText(selectName);
        editAuth.setText(selectAuth);
        editUser.setText(selectUser);

        if (selectLocation.equals("Available")){
            toggleCheckout.setChecked(false);
        }
        else{
            toggleCheckout.setChecked(true);
        }

        toggleCheckout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mydb.updateLocation(selectId,"Checked Out", "Available");
                    Toastmsg("Book has been checked out");
                }
                else{
                    mydb.updateLocation(selectId,"Available", "Checked Out" );
                    Toastmsg("Book has been returned to the library");
                }

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String edtbook = editBook.getText().toString().trim();
                 String edtAuth = editAuth.getText().toString().trim();
                 String edtUser = editUser.getText().toString().trim();
                 mydb.updateData(selectId, edtbook, selectName);
                 mydb.updateAuth(selectId, edtAuth, selectAuth);
                 mydb.updateUser(selectId, edtUser, selectUser);
                 openMainPage();

                 Toastmsg("Book has been Updated!");
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.deleteData(selectId, selectName, selectAuth, selectUser, selectLocation);
                openMainPage();
                Toastmsg("Book has been Deleted");

            }
        });

        btnViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewBook();
            }
        });

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddBook();
            }
        });
    }
    public void Toastmsg(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }
    public void openViewBook(){
        Intent intent = new Intent(this, viewBooks.class);
        startActivity(intent);
    }
    public void openMainPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openAddBook(){
        Intent intent = new Intent(this, addBook.class);
        startActivity(intent);
    }

}
