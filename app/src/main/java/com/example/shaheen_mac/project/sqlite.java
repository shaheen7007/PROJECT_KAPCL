package com.example.shaheen_mac.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shaheen-mac on 13/10/16.
 */

public class sqlite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="kapcl.db";

    public static final String TABLE_NAME="cart_table";
    public static final String UseerTable_NAME="user_table";
    public static final String idcol="_id";
    public static final String ItemNamecol="item";
    public static final String usernameNamecol="Uname";
    public static final String QTYcol="qty";
    public static final String Pricecol="price";

    public static final String Multcol="mult";




    public sqlite(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + idcol + " INTEGER," + ItemNamecol + " TEXT,"+Pricecol+" double,"+QTYcol+" double,"+Multcol+" integer)");
        db.execSQL("create table " + UseerTable_NAME + "(" + usernameNamecol + " TEXT)");
         //inserting to food table


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UseerTable_NAME);
        onCreate(db);
    }
    //insert data to number table
    public boolean Insertdata(String name,String price,String qty,String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues ContentValues=new ContentValues();

        double value = Double.parseDouble(price);
        double QT = Double.parseDouble(qty);
        int ID = Integer.parseInt(id);

        ContentValues.put(ItemNamecol, name);
        ContentValues.put(Pricecol, value);
        ContentValues.put(QTYcol, QT);
        ContentValues.put(idcol, id);
        ContentValues.put(Multcol, 1);


        long result=db.insert(TABLE_NAME,null,ContentValues);

        if(result==-1)
            return false;
        else
            return true;
    }


    public boolean USERInsertdata(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues ContentValues=new ContentValues();

        ContentValues.put(usernameNamecol, name);


        long result=db.insert(UseerTable_NAME,null,ContentValues);

        if(result==-1)
            return false;
        else
            return true;
    }



    public void cleardata()
    {
        SQLiteDatabase sdb=this.getWritableDatabase();
        sdb.delete(TABLE_NAME,null,null);

    }


    public void Usrcleardata()
    {
        SQLiteDatabase sdb=this.getWritableDatabase();
        sdb.delete(UseerTable_NAME,null,null);

    }



    public boolean verification(String id) {
        SQLiteDatabase ssdb=this.getWritableDatabase();
        Cursor c = ssdb.rawQuery("SELECT 1 FROM "+TABLE_NAME+" WHERE "+idcol+"=?", new String[] {id});
        boolean exists = c.moveToFirst();
        c.close();
        return exists;
    }


}

