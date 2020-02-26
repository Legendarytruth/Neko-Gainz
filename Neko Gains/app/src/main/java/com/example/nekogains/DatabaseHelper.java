package com.example.nekogains;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Workout.db";
    public static final String EXERCISE_TABLE = "Exercises";
    public static final String USER_TABLE = "Users";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "CALORIES";
    public static final String[] workouts = {"Push-Ups", "Bench-Dips", "Chin-Ups", "Squats", "Lunges", "Calf-Raises", "Planks", "Sit-Ups", "Leg Raises", "Running", "Burpees", "Jumping Jacks"};
    public static final double[] calories = {1, 3, 1, 14, 0.9, 0.3, 3.5, 0.3, 0.7, 15, 13, 9};
    public static final String COL_4 = "ID";
    public static final String COL_5 = "NAME";
    public static final String COL_6 = "HABITS";
    public static final String COL_8 = "WEIGHT";
    public static final String COL_7 = "HEIGHT";
    public static final String COL_9 = "GOAL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EXERCISE_TABLE + " (ID INTEGER PRIMARY KEY, NAME TEXT, CALORIES INTEGER);");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < workouts.length; i++) {
            contentValues.put(COL_2, workouts[i]);
            contentValues.put(COL_3, calories[i]);
            db.insert(EXERCISE_TABLE, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
    }

    public boolean insertExerciseData(String name, float calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, calories);
        long result = db.insert(EXERCISE_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertUserData(String name, String habit, float weight, float height, String goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, name);
        contentValues.put(COL_6, habit);
        contentValues.put(COL_7, weight);
        contentValues.put(COL_8, height);
        contentValues.put(COL_9, goal);

        long result = db.insert(USER_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
