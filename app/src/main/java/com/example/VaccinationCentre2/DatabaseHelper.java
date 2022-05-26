package com.example.VaccinationCentre2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Taskdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create Table Taskdetails(ID integer primary key autoincrement, TASK TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Taskdetails");
    }

    //CRUD OPERATIONS

    public Boolean insertuserdata(String task, String date){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("TASK",task);
        contentValues.put("DATE",date);

        long result=DB.insert("Taskdetails",null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public void updateTask(String newTask,String newDate, int id, String oldTask,String oldDate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE Taskdetails SET oldTask = newTask, oldDay = newDay, oldDate = newDate  WHERE ID = id";

        Log.d(TAG, "updateTask: query: " + query);
        Log.d(TAG, "updateTask: Setting task to " + newTask +newDate);
        db.execSQL(query);
    }



    public void deleteTask(int id, String task){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM Taskdetails WHERE ID=id AND TASK=task";
        Log.d(TAG, "deleteTask: query: " + query);
        Log.d(TAG, "deleteTask: Deleting " + task + " from database.");
        db.execSQL(query);
    }

    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor data=db.rawQuery("Select * from Taskdetails", null);
        return data;


    }

    public Cursor getTaskID(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ID FROM Taskdetails WHERE TASK= task";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDate(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT DATE FROM Taskdetails WHERE  ID= id";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}



