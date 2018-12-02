package com.example.tabtodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbTasks extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "assigntask";
    private static int DATABASE_VERSION = 1;
    public static String DATABASE_TABLE = "task";
//    private static String KEY_ID = "id";
    public static String DATABASE_COLUMN = "taskname";

    public dbTasks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "create table "+DATABASE_TABLE +
//                "(" +
//                KEY_ID + " integer primary key autoincrement, "+
//                DATABASE_COLUMN + " text" +
//                ")";
        String sql = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);",DATABASE_TABLE,DATABASE_COLUMN);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DATABASE_TABLE);
        onCreate(db);
    }
    public void insertTask(String taskname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_COLUMN,taskname);

        String nullColumnHack = null;//Accept the value is null

        db.insert(DATABASE_TABLE,nullColumnHack,values);
    }

    public void deleteTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,DATABASE_COLUMN + " = ?",new String[]{task});
        db.close();
    }

    public ArrayList<String> getTaskList(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,new String[]{DATABASE_COLUMN},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(DATABASE_COLUMN);
            taskList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }

    public Cursor getAllAccount(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mouse = db.rawQuery("select * from " + DATABASE_NAME,null);
        return  mouse;
    }


}
