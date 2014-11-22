package com.keanu.nearSearch.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2014-11-22 .
 */
public class KeanuNearOpenHelper extends SQLiteOpenHelper {
    /**
     * 一级导航建表
     */
    public static final String CREATE_NAVI_ONE = "create table navi_one ("
            +"navi_one_id integer primary key autoincrement,"
            +"navi_one_name text)";
    /**
     * 二级导航建表
     */
    public static final String CREATE_NAVI_TWO = "create table navi_two ("
            +"navi_two_id integer primary key autoincrement,"
            +"navi_two_name text,"
            +"navi_one_id integer)";
    /**
     * 三级导航建表
     */
    public static final String CREATE_NAVI_THREE = "create table navi_three ("
            +"navi_three_id integer primary key autoincrement,"
            +"navi_three_name text,"
            +"navi_two_id integer)";
    public KeanuNearOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NAVI_ONE);
        db.execSQL(CREATE_NAVI_TWO);
        db.execSQL(CREATE_NAVI_THREE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
