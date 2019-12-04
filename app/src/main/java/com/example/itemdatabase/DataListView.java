package com.example.itemdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class DataListView extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper userDBhelper;
    Cursor cursor;
    ListView listView;
    ListAdaptor listAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list_view);

        listView=(ListView) findViewById(R.id.list_view);
        userDBhelper=new DatabaseHelper(getApplicationContext());
        sqLiteDatabase=userDBhelper.getReadableDatabase();
        cursor=userDBhelper.getAllData();
        listAdaptor=new ListAdaptor(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listAdaptor);
        if (cursor.moveToNext()){
            do {

                String name,price;
                name=cursor.getString(2);
                price=cursor.getString(3);
                DataProvider dataProvider=new DataProvider(name,price);
                listAdaptor.add(dataProvider);


            }while (cursor.moveToNext());
        }

    }
}
