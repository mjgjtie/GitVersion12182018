package com.example.tabtodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

public class dbTasks extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "assigntask";
    private static int DATABASE_VERSION = 1;
//    public static String DATABASE_TABLE = "task";
//    private static String KEY_ID = "id";
//    public static String DATABASE_COLUMN = "taskname";


    public static String TABLE_ACCOUNT = "tableaccount";
    private static String KEY_ID = "_id";
    public static String KEY_FULLNAME = "fullname";
    public static String KEY_USERNAME = "username";
    public static String KEY_PASSWORD = "password";

    public static String TABLE_PROJECT = "tableaproject";
    private static String KEY_IDPROJECT = KEY_ID;
    public static String KEY_PROJECTNAME = "projectname";

    public static String TABLE_TEAM = "tableteam";
    private static String KEY_IDTEAM = KEY_ID;
    public static String KEY_FULLNAMEMEMBER = "fullname";
    public static String KEY_IDAC = "_idac";
    public static String KEY_IDPR = "_idpr";

    public static String TABLE_TASK = "tabletask";
    private static String KEY_IDTASK = KEY_ID;
    public static String KEY_TASK = "taskname";
    public static String KEY_IDACC = "_idacc";
    public static String KEY_IDPRO = "_idpro";



    public dbTasks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_TABLE_ACCOUNT = "CREATE TABLE "+ TABLE_ACCOUNT +"("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_FULLNAME + " TEXT , "
            + KEY_USERNAME + " TEXT , "
            + KEY_PASSWORD + " TEXT  "
            + ");";
    private static final String SQL_CREATE_TABLE_PROJECT = "CREATE TABLE "+ TABLE_PROJECT +"("
            + KEY_IDPROJECT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_PROJECTNAME + " TEXT  "
            + ");";
    private static final String SQL_CREATE_TABLE_TEAM = "CREATE TABLE "+ TABLE_TEAM +"("
            + KEY_IDTEAM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_FULLNAMEMEMBER + " TEXT , "
            + KEY_IDAC + " INTEGER , "
            + KEY_IDPR + " INTEGER  "
            + ");";
    private static final String SQL_CREATE_TABLE_TASK = "CREATE TABLE "+ TABLE_TASK +"("
            + KEY_IDTASK + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TASK + " TEXT , "
            + KEY_IDACC + " INTEGER , "
            + KEY_IDPRO + " INTEGER "
            + ");";
    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "create table "+DATABASE_TABLE +
//                "(" +
//                KEY_ID + " integer primary key autoincrement, "+
//                DATABASE_COLUMN + " text" +
//                ")";
//        String sql = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);",DATABASE_TABLE,DATABASE_COLUMN);
//        db.execSQL(sql);
        db.execSQL(SQL_CREATE_TABLE_ACCOUNT);
        db.execSQL(SQL_CREATE_TABLE_PROJECT);
        db.execSQL(SQL_CREATE_TABLE_TEAM);
        db.execSQL(SQL_CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("TAG","upgrading the database from the version "+oldVersion+" to" +newVersion);
        //CLEAR
        db.execSQL("drop table if exists " + TABLE_ACCOUNT);
        db.execSQL("drop table if exists " + TABLE_PROJECT);
        db.execSQL("drop table if exists " + TABLE_TEAM);
        db.execSQL("drop table if exists " + TABLE_TASK);

        //RECREATE
        onCreate(db);
    }

    public void insertProject(String projectname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PROJECTNAME,projectname);


        String nullColumnHack = null;//Accept the value is null

        db.insert(TABLE_PROJECT,nullColumnHack,values);
    }

    public void insertAccount(String fullname,String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FULLNAME,fullname);
        values.put(KEY_USERNAME,username);
        values.put(KEY_PASSWORD,password);

        String nullColumnHack = null;//Accept the value is null

        db.insert(TABLE_ACCOUNT,nullColumnHack,values);
    }

    public void insertTask(String taskname,String _idac,String _idpr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASK,taskname);
        values.put(KEY_IDACC,_idac);
        values.put(KEY_IDPRO,_idpr);

        String nullColumnHack = null;//Accept the value is null

        db.insert(TABLE_TASK,nullColumnHack,values);
    }

    public void insertTeam(String fullname,String _idacc,String _idpro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FULLNAMEMEMBER,fullname);
        values.put(KEY_IDAC,_idacc);
        values.put(KEY_IDPR,_idpro);

        String nullColumnHack = null;//Accept the value is null

        db.insert(TABLE_TEAM,nullColumnHack,values);
    }

    public void deleteTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK,KEY_TASK + " = ?",new String[]{task});
        db.close();
    }

    public ArrayList<String> getTaskList(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TASK,new String[]{KEY_TASK},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(KEY_TASK);
            taskList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }
    public ArrayList<String> getProjectList(){
        ArrayList<String> projectList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PROJECT,new String[]{KEY_PROJECTNAME},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(KEY_PROJECTNAME);
            projectList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return projectList;
    }
    public ArrayList<String> getAccountList(){
        ArrayList<String> projectList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACCOUNT,new String[]{KEY_FULLNAME},null,null,null,null,null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(KEY_FULLNAME);
            projectList.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return projectList;
    }
    public Cursor getAllAccount(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mouse = db.rawQuery("select * from " + DATABASE_NAME,null);
        return  mouse;
    }


}
