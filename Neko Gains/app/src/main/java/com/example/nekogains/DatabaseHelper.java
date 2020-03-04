package com.example.nekogains;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

public class DatabaseHelper extends SQLiteOpenHelper implements Serializable {

    private static DatabaseHelper sInstance;

    private static final String DATABASE_NAME = "Workout.db";
    private static final String EXERCISE_TABLE = "Exercises";
    private static final String USER_TABLE = "Users";
    private static final String GAME_TABLE = "Games";
    private static final String EX_NAME = "NAME";
    private static final String EX_CAL = "CALORIES";
    private static final String[] workouts = {"Push-Ups", "Bench-Dips", "Chin-Ups", "Squats", "Lunges", "Calf-Raises", "Planks", "Sit-Ups", "Leg Raises", "Running", "Burpees", "Jumping Jacks"};
    private static final double[] calories = {1, 3, 1, 14, 0.9, 0.3, 3.5, 0.3, 0.7, 15, 13, 9};
    //User Table
    private static final String USER_NAME = "NAME";
    private static final String USER_AGE = "AGE";
    private static final String USER_HABIT = "HABITS";
    private static final String USER_WEIGHT = "WEIGHT";
    private static final String USER_HEIGHT = "HEIGHT";
    private static final String USER_GOAL = "GOAL";
    //Game Table
    private static final String GAME_MONEY = "MONEY";
    private static final String GAME_EXPERIENCE = "EXPERIENCE";


    private SQLiteDatabase db;

    public static synchronized DatabaseHelper getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EXERCISE_TABLE + " (ID INTEGER PRIMARY KEY, NAME TEXT, CALORIES INTEGER);");
        ContentValues contentValues;
        for (int i = 0; i < workouts.length; i++) {
            contentValues = new ContentValues();
            contentValues.put(EX_NAME, workouts[i]);
            contentValues.put(EX_CAL, calories[i]);
            db.insert(EXERCISE_TABLE, null, contentValues);
        }

        db.execSQL("CREATE TABLE " + USER_TABLE + " (ID INTEGER PRIMARY KEY, NAME TEXT, AGE TEXT, HABITS TEXT, WEIGHT TEXT, HEIGHT TEXT, GOAL TEXT, USERNAME TEXT, PASSWORD TEXT, BMI TEXT);");
        //calculates bmi on inserts
        db.execSQL("CREATE TRIGGER trg_bmi AFTER UPDATE ON " + USER_TABLE + " WHEN NEW.HEIGHT > 0 BEGIN UPDATE " + USER_TABLE + " SET BMI=WEIGHT/HEIGHT*HEIGHT; END;");

        db.execSQL("CREATE TABLE " + GAME_TABLE + " (ID INTEGER PRIMARY KEY, MONEY INTEGER, EXPERIENCE INTEGER," +
                " CONSTRAINT fk_users FOREIGN KEY (ID) REFERENCES USERS(ID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE_TABLE);
    }

    //dynamic adding of exercises may not be needed
    public boolean insertNewExercise(String name, String calories) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EX_NAME, name);
        contentValues.put(EX_CAL, calories);
        long result = db.insert(EXERCISE_TABLE, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Adds new user to table with null stats
    public int insertEmptyUser() {
        ContentValues cv = new ContentValues();
        cv.putNull(USER_NAME);
        cv.putNull(USER_AGE);
        cv.putNull(USER_HABIT);
        cv.putNull(USER_WEIGHT);
        cv.putNull(USER_HEIGHT);
        cv.putNull(USER_GOAL);
        long result = db.insert(USER_TABLE, null, cv);
        if (result == -1) {
            return -1;
        } else {
            return (int)result;
        }
    }

    //Adds new user to table with given stats
    public int insertNewUser(String name, String age, String habits, String weight, String height, String goal) {
        ContentValues cv = new ContentValues();
        cv.put(USER_NAME, name);
        cv.put(USER_AGE, age);
        cv.put(USER_HABIT, habits);
        cv.put(USER_WEIGHT, weight);
        cv.put(USER_HEIGHT, height);
        cv.put(USER_GOAL, goal);

        long result = db.insert(USER_TABLE, null, cv);
        if (result == -1) {
            return -1;
        } else {
            return (int)result;
        }
    }

    //Update a row for the user at the given id
    public boolean updateUserData(int id, String row, String contents) {
        System.out.println(row);
        System.out.println(contents);
        ContentValues cv = new ContentValues();
        cv.put(row, contents);

        long result = db.update(USER_TABLE, cv, "ID="+id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Get a row for the user at the given id
    public String getUserData(int id, String row) {
        String data = "";
        Cursor c = null;
        try {
            c = db.rawQuery("SELECT " + row + " FROM " + USER_TABLE + " WHERE ID =?", new String[] {id + ""});
            if(c.getCount() > 0) {
                c.moveToFirst();
                data = c.getString(c.getColumnIndex(row));
            }
        }finally {
            //c.close();
            return data;
        }
    }

    //Insert new game with it's ID tied to the user
    public boolean insertNewGame(String money, String experience) {
        ContentValues cv = new ContentValues();
        cv.put(GAME_MONEY, money);
        cv.put(GAME_EXPERIENCE, experience);

        long result = db.insert(GAME_TABLE, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Update a row for the game at the given id
    public boolean updateGame(int id, String row, int contents) {
        ContentValues cv = new ContentValues();
        cv.put(row, contents);

        long result = db.update(GAME_TABLE, cv, "ID="+id, null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Get a row for the user at the given id
    public String getGameData(int id, String row) {
        String data = "";
        Cursor c = null;
        try {
            c = db.rawQuery("SELECT " + row + " FROM " + GAME_TABLE + " WHERE ID =?", new String[] {id + ""});
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
