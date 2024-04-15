package com.example.dictionaries.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dictionaries.WordAndDefinition;

import java.util.ArrayList;
import java.util.List;

public class DataAccessVietAnh {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DataAccessVietAnh instance;
    private DataAccessVietAnh(Context context) {
        this.openHelper = new DatabaseOpenHelperVietAnh(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DataAccessVietAnh getInstance(Context context) {
        if (instance == null) {
            instance = new DataAccessVietAnh(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    public List<String> getVietAnhWords() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM viet_anh", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<String> getVietAnhWords(String filter) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM viet_anh where word like '"+ filter +"%' limit 10", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public String getVietAnhDefinition(String word) {
        String definition = "";
        Cursor cursor = database.rawQuery("SELECT * FROM viet_anh where word='"+ word +"'", null);
        cursor.moveToFirst();

        definition  = cursor.getString(2);

        cursor.close();
        return definition;
    }

    public ArrayList<WordAndDefinition> getWordAndDefinition() {
        ArrayList<WordAndDefinition> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM viet_anh", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new WordAndDefinition(cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<WordAndDefinition> getWordAndDefinition(String filter) {
        ArrayList<WordAndDefinition> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM viet_anh where word like '"+ filter +"%' limit 10", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new WordAndDefinition(cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
