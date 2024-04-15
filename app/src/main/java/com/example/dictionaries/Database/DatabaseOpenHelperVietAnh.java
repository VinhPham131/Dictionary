package com.example.dictionaries.Database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelperVietAnh extends SQLiteAssetHelper {
    private static final String DATABASE_VietAnh = "viet_anh.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelperVietAnh(Context context) {
        super(context, DATABASE_VietAnh, null, DATABASE_VERSION);
    }
}
