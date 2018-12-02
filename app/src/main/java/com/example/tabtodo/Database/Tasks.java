package com.example.tabtodo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tabtodo.Tab1task;

import java.util.ArrayList;

public class Tasks extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "ASSIGNTASK";
    private static int DATABASE_VERSION = 1;
    public static String DATABASE_TABLE = "Task";
    private static String KEY_ID = "id";
    public static String DATABASE_COLUMN = "Task Name";


    public Tasks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+ DATABASE_TABLE +
                "(" +
                KEY_ID + " integer primary key autoincrement, "+
                DATABASE_COLUMN + "text " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DATABASE_TABLE);
        onCreate(db);

    }

    public void insertTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_COLUMN,task);
        db.insertWithOnConflict(DATABASE_TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
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
}
