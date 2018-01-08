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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bList = (ListView)findViewById(R.id.bList);
        mydb2 = new DBHandler(this);

        populateListView();
    }

    private void populateListView() {
        Cursor data = mydb2.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        bList.setAdapter(adapter);

        bList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = mydb2.getID(name);
                int itemId= -1;
                while(data.moveToNext()){
                    itemId = data.getInt(0);
                }
                Cursor bookData = mydb2.getAuth(name);
                String itemAuth = "";
                while(bookData.moveToNext()){
                    itemAuth = bookData.getString(0);
                }
                Cursor locationData = mydb2.getLocation(name);
                String itemLocation = "";
                while(locationData.moveToNext()){
                    itemLocation = locationData.getString(0);
                }
                Cursor UserData = mydb2.getUser(name);
                String itemUser = "";
                while(UserData.moveToNext()){
                    itemUser = UserData.getString(0);
                }


                if(itemId> -1){
                    Intent editScreenIntent = new Intent(viewBooks.this, EditBooksActivity.class);
                    editScreenIntent.putExtra("id", itemId);
                    editScreenIntent.putExtra("name", name);
                    editScreenIntent.putExtra("auth", itemAuth);
                    editScreenIntent.putExtra("location", itemLocation);
                    editScreenIntent.putExtra("user", itemUser);
                    startActivity(editScreenIntent);

                }
                else{
                    Toastmsg("No Id from that name...");
                }



            }
        });
    }
    public void Toastmsg(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_LONG).show();
    }

}
