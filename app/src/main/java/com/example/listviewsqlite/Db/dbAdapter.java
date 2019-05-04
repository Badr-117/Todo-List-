package com.example.listviewsqlite.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class dbAdapter {

    Context c;
    SQLiteDatabase db;
    dbHelper helper;

    public dbAdapter(Context c) {
        this.c = c;
        helper=new dbHelper(c);
    }

    //OPEN CON
    public void openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {

        }
    }
    //CLOSE DB
    public void closeDB()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {

        }
    }

    //SAVE
    public boolean add(String task, String date, String time)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Constants.TASK,task);
            cv.put(Constants.DATE,date);
            cv.put(Constants.TIME,time);


            long result=db.insert(Constants.TB_NAME,Constants.ROW_ID,cv);
            if(result>0)
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    //SELECT
    public Cursor retrieve()
    {
        String[] columns={Constants.ROW_ID,Constants.TASK, Constants.DATE, Constants.TIME};

        Cursor c=db.query(Constants.TB_NAME,columns,null,null,null,null,null);
        return c;
    }

    //UPDATE/edit
    public boolean update(String newTask, String  newDate, String newTime,int id)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Constants.TASK,newTask);
            cv.put(Constants.DATE,newDate);
            cv.put(Constants.TIME,newTime);


            int result=db.update(Constants.TB_NAME,cv, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
            if(result>0)
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;

    }

    //DELETE/REMOVE
    public boolean delete(int id)
    {
        try
        {
            int result=db.delete(Constants.TB_NAME,Constants.ROW_ID+" =?",new String[]{String.valueOf(id)});
            if(result>0)
            {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
