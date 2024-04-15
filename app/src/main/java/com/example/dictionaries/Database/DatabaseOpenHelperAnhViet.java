package com.example.dictionaries.Database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelperAnhViet extends SQLiteAssetHelper {
    private static final String DATABASE_AnhViet = "anh_viet.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelperAnhViet(Context context) {
        super(context, DATABASE_AnhViet, null, DATABASE_VERSION);
    }
}
