package com.example.nekogains;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Workout.db";
    public static final String EXERCISE_TABLE = "Exercises";
    public static final String USER_TABLE = "Users";
    public static final String GAME_TABLE = "Games";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "CALORIES";
    public static final String[] workouts = {"Push-Ups", "Bench-Dips", "Chin-Ups", "Squats", "Lunges", "Calf-Raises", "Planks", "Sit-Ups", "Leg Raises", "Running", "Burpees", "Jumping Jacks"};
    public static final double[] calories = {1, 3, 1, 14, 0.9, 0.3, 3.5, 0.3, 0.7, 15, 13, 9};
    public static final String COL_5 = "NAME";
    public static final String COL_6 = "HABITS";
    public static final String COL_7 = "WEIGHT";
    public static final String COL_8 = "HEIGHT";
    public static final String COL_9 = "GOAL";
    public static final String COL_10 = "HEIGHT";
    public static final String COL_11 = "GOAL";



    public static final String COL_13 = "MONEY";
    public static final String COL_14 = "EXPERIENCE";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
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

        db.execSQL("CREATE TABLE " + USER_TABLE + " (ID INTEGER PRIMARY KEY, USER_ID INTEGER, NAME TEXT, HABITS TEXT, WEIGHT TEXT, HEIGHT TEXT, GOAL TEXT, USERNAME TEXT, PASSWORD TEXT, BMI TEXT);");
        //calculates bmi on inserts
        db.execSQL("CREATE TRIGGER trg_bmi AFTER UPDATE ON USER_TABLE WHEN experience > 0 BEGIN UPDATE USER_TABLE SET BMI=WEIGHT/HEIGHT*HEIGHT; END;");

        db.execSQL("CREATE TABLE " + GAME_TABLE + " (ID INTEGER PRIMARY KEY, MONEY INTEGER, EXPERIENCE INTEGER, BMI INTEGER," +
                " CONSTRAINT fk_users FOREIGN KEY (USER_ID) REFERENCES USERS(ID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
    }

    //dynamic adding of exercises may not be needed
    public boolean insertNewExercise(String name, String calories) {
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

    public boolean insertNewUser(String name, String habit, String weight, String height, String goal) {
        ContentValues cv = new ContentValues();
        cv.put(COL_5, name);
        cv.put(COL_6, habit);
        cv.put(COL_7, weight);
        cv.put(COL_8, height);
        cv.put(COL_9, goal);

        long result = db.insert(USER_TABLE, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateUserData(int id, String row) {
        ContentValues cv = new ContentValues();
        cv.put(COL_5, row);

        long result = db.update(USER_TABLE, cv, "ID="+id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String getUserData(int id, String row) {
        String data = "";
        Cursor c = null;
        try {
            c = db.rawQuery("SELECT " + row + " FROM" + USER_TABLE + "WHERE " + id + "=?", new String[] {id + ""});
            if(c.getCount() > 0) {
                c.moveToFirst();
                data = c.getString(c.getColumnIndex(row));
            }
            return data;
        }finally {
            c.close();
        }
    }

    public boolean insertNewGame(String money, String experience) {
        ContentValues cv = new ContentValues();
        cv.put(COL_13, money);
        cv.put(COL_14, experience);

        long result = db.insert(GAME_TABLE, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateGame(int id, String contents) {
        ContentValues cv = new ContentValues();
        cv.put(COL_5, contents);

        long result = db.update(USER_TABLE, cv, "USER_ID="+id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String getGameData(int id, String row) {
        String data = "";
        Cursor c = null;
        try {
            c = db.rawQuery("SELECT " + row + " FROM" + GAME_TABLE + "WHERE " + id + "=?", new String[] {id + ""});
            if(c.getCount() > 0) {
                c.moveToFirst();
                data = c.getString(c.getColumnIndex(row));
            }
            return data;
        }finally {
            c.close();
        }
    }
}
