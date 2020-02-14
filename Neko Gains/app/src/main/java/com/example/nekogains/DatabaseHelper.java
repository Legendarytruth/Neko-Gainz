package com.example.nekogains;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Workout.db";
    public static final String TABLE_NAME = "Exercises";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "CALORIES";
    public static final String[] workouts = {"Push-Ups", "Bench-Dips", "Chin-Ups", "Squats", "Lunges", "Calf-Raises", "Planks", "Sit-Ups", "Leg Raises", "Running", "Burpees", "Jumping Jacks"};
    public static final double[] calories = {1, 3, 1, 14, 0.9, 0.3, 3.5, 0.3, 0.7, 15, 13, 9};

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME TEXT, CALORIES INTEGER);");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < workouts.length; i++) {
            contentValues.put(COL_2, workouts[i]);
            contentValues.put(COL_3, calories[i]);
            db.insert(TABLE_NAME, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String name, float calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, calories);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
