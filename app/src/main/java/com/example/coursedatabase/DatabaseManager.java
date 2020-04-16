package com.example.coursedatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context) { super(context, "CoursesDB", null, 1); }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table CourseTable (id integer primary key autoincrement, course text, instructor text, credits text)";
        db.execSQL(sql);
    }

    public void insert(String courseName, String instructorName, String creditsName) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into CourseTable values(null, '"+courseName+"', '"+instructorName+"', '"+creditsName+"')";
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<String> getCourses() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from CourseTable";

        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()) {
            String course = cursor.getString(1);
            list.add(course);
        }
        db.close();
        return list;

    }

    public String[] get(String courseName) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from CourseTable where  course = '"+courseName+"'";
        Cursor cursor = db.rawQuery(sql,null);
        String[] entry = new String[3];
        if(cursor.moveToFirst()) {
            String course = cursor.getString(1);
            String instructor = cursor.getString(2);
            String credits = cursor.getString(3);

            entry[0] = course;
            entry[1] = instructor;
            entry[2] = credits;

        }
        db.close();
        return entry;
    }

    public String deleteCourse(String course) {

        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from CourseTable where course = '"+course+"'";
        db.execSQL(sql);
        return course;
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
