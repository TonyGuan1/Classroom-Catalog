package com.example.shashaank.libraryapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewBooks extends AppCompatActivity {

    private ListView bList;
    DBHandler mydb2;

    private String itemAuth, itemLocation, itemUser;
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bList = (ListView)findViewById(R.id.bList);
        mydb2 = new DBHandler(this);
        //instance of the database in this class

        populateListView();
    }

    private void populateListView() {
        // gets all the data so it can display books in the listview
        Cursor data = mydb2.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
            // 1 means that it adds the book to the listview
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        bList.setAdapter(adapter);

        bList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();


                Cursor csr = mydb2.rowData(name);
                while(csr.moveToNext()) {
                    itemId = csr.getInt(0);
                    itemAuth = csr.getString(2);
                    itemLocation = csr.getString(3);
                    itemUser = csr.getString(4);
                }
                // gets all the data from a certain row numbers assosciate with columns,
                // 1 was already found above in the listview so no book variable needed

                if(itemId> -1){
                    Intent editScreenIntent = new Intent(viewBooks.this, EditBooksActivity.class);
                    editScreenIntent.putExtra("id", itemId);
                    editScreenIntent.putExtra("name", name);
                    editScreenIntent.putExtra("auth", itemAuth);
                    editScreenIntent.putExtra("location", itemLocation);
                    editScreenIntent.putExtra("user", itemUser);
                    startActivity(editScreenIntent);
                    //creates an intent to send the information to the edit books page

                }
                else{
                    Toastmsg("No Id from that name...");
                }
                // in case using old list and book is not actually present



            }
        });
    }
    public void Toastmsg(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }
    //method to create toasts

}
