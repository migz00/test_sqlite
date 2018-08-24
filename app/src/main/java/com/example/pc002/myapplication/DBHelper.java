package com.example.pc002.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Names.db";
    private static final String NAME_TABLE_NAME = "students";
    private static final String NAME_COLUMN_ID = "id";
    private static final String NAME_COLUMN_NAME = "name";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + NAME_TABLE_NAME +
                " (" + NAME_COLUMN_ID +" integer primary key, " + NAME_COLUMN_NAME + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ NAME_TABLE_NAME);
        onCreate(db);
    }
    public ArrayList<String> getNames()
    {
        ArrayList<String> array = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+ NAME_TABLE_NAME, null);
        res.moveToFirst();
        while(!res.isAfterLast())
        {
            array.add(res.getString(res.getColumnIndex(NAME_COLUMN_NAME)));
            res.moveToNext();
        }
        res.close();
        return array;
    }
    public boolean insertStudent(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_COLUMN_NAME, name);
        db.insert(NAME_TABLE_NAME, null, cv);
        cv.clear();
        return true;
    }
    public void clearData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ NAME_TABLE_NAME);
        onCreate(db);
    }
}
