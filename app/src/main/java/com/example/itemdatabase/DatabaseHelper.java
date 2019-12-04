package com.example.itemdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Currency;
import android.media.session.PlaybackState;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="item.db";
    public static final String TABLE_NAME="item_table";
    public static final String COL_1="BARCODE";
    public static final String COL_2="ID";
    public static final String COL_3="NAME";
    public static final String COL_4="PRICE";
    public static final String COL_5="BRAND";



    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME +" (BARCODE TEXT,ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PRICE INTEGER,BRAND TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String barcode,String id,String name,String price,String brand){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_1,barcode);
        contentValues.put(COL_2,id);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5,brand);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete(TABLE_NAME,"ID=?",new String[] {id});
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateAllData(String barcode,String id,String name,String price,String brand){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_1,barcode);
        contentValues.put(COL_2,id);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5,brand);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[] {id});
        return true;
    }


}

