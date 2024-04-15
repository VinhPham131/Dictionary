package com.example.dictionaries.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dictionaries.WordAndDefinition;

import java.util.ArrayList;
import java.util.List;

public class DataAccessAnhViet {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DataAccessAnhViet instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DataAccessAnhViet(Context context) {
        this.openHelper = new DatabaseOpenHelperAnhViet(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DataAccessAnhViet getInstance(Context context) {
        if (instance == null) {
            instance = new DataAccessAnhViet(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all words from anh_viet dictionary
     *
     * @return a List of word from dictionary
     */
    public List<String> getWords() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM anh_viet", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public ArrayList<String> getWords(String filter) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM anh_viet where word like '"+ filter +"%' limit 10", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public String getDefinition(String word) {
        String definition = "";
        Cursor cursor = database.rawQuery("SELECT * FROM anh_viet where word='"+ word +"'", null);
        cursor.moveToFirst();

        definition  = cursor.getString(2);

        cursor.close();
        return definition;
    }

    public ArrayList<WordAndDefinition> getWordAndDefinition() {
        ArrayList<WordAndDefinition> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM anh_viet", null);
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
        Cursor cursor = database.rawQuery("SELECT * FROM anh_viet where word like '"+ filter +"%' limit 10", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(new WordAndDefinition(cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}