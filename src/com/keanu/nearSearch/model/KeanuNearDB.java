package com.keanu.nearSearch.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.keanu.nearSearch.db.KeanuNearOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014-11-22 .
 */
public class KeanuNearDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "keanu_near";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;
    private static KeanuNearDB keanuNearDB;
    private SQLiteDatabase db;
    /**
     * 将构造方法私有化
     */
    private KeanuNearDB(Context context){
        KeanuNearOpenHelper dbHelper = new KeanuNearOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    /**
     * 获取KeanuNearDB的实例
     */
    public synchronized static KeanuNearDB getInstance(Context context){
        if (keanuNearDB==null){
            keanuNearDB = new KeanuNearDB(context);
        }
        return keanuNearDB;
    }
    /**
     * 添加一级导航到数据库
     */
    public void saveNaviOne(NaviOne naviOne){
        if (naviOne!=null){
            ContentValues values = new ContentValues();
            values.put("navi_one_name",naviOne.getNaviOneName());
            db.insert("navi_one",null,values);
        }
    }
    /**
     * 从数据库读取一级导航
     */
    public List<NaviOne> loadNaviOne(){
        List<NaviOne> list = new ArrayList<NaviOne>();
        Cursor cursor = db.query("navi_one",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                NaviOne naviOne = new NaviOne();
                naviOne.setNaviOneId(cursor.getInt(cursor.getColumnIndex("navi_one_id")));
                naviOne.setNaviOneName(cursor.getString(cursor.getColumnIndex("navi_one_name")));
                list.add(naviOne);
            }while (cursor.moveToNext());
        }
        return  list;
    }
    /**
     * 添加二级导航到数据库
     */
    public void saveNaviTwo(NaviTwo naviTwo){
        if (naviTwo!=null){
            ContentValues values = new ContentValues();
            values.put("navi_two_name", naviTwo.getNaviTwoName());
            values.put("navi_one_id",naviTwo.getNaviOneId());
            db.insert("navi_two",null,values);
        }
    }

    /**
     * 从数据库读取二级导航
     */
    public List<NaviTwo> loadNaviTwo(int navi_one_id){
        List<NaviTwo> list = new ArrayList<NaviTwo>();
        String sql = "select * from navi_two where navi_one_id = ?";
        Cursor cursor = db.rawQuery(sql,new String[]{navi_one_id+""});
        if (cursor.moveToFirst()){
            do {
                NaviTwo naviTwo = new NaviTwo();
                naviTwo.setNaviTwoId(cursor.getInt(cursor.getColumnIndex("navi_two_id")));
                naviTwo.setNaviTwoName(cursor.getString(cursor.getColumnIndex("navi_two_name")));
                naviTwo.setNaviOneId(cursor.getInt(cursor.getColumnIndex("navi_one_id")));
                list.add(naviTwo);
            }while (cursor.moveToNext());
        }
        return  list;
    }
    /**
     * 添加三级导航到数据库
     */
    public void saveNaviThree(NaviThree naviThree){
        if (naviThree!=null){
            ContentValues values = new ContentValues();
            values.put("navi_three_name",naviThree.getNaviThreeName());
            values.put("navi_two_id",naviThree.getNaviTwoId());
            db.insert("navi_three",null,values);
        }
    }
    /**
     * 从数据库读取二级导航
     */
    public List<NaviThree> loadNaviThree(){
        List<NaviThree> list = new ArrayList<NaviThree>();
        Cursor cursor = db.query("navi_three",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                NaviThree naviThree = new NaviThree();
                naviThree.setNaviThreeId(cursor.getInt(cursor.getColumnIndex("navi_three_id")));
                naviThree.setNaviThreeName(cursor.getString(cursor.getColumnIndex("navi_three_name")));
                naviThree.setNaviTwoId(cursor.getInt(cursor.getColumnIndex("navi_two_id")));
                list.add(naviThree);
            }while (cursor.moveToNext());
        }
        return  list;
    }
}
