package com.example.android.habittracking.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracking.data.HabitContract.HabitEntry;

/**
 * Created by sarahaldowihy on 10/3/2017 AD.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    // Name of the database file
    private static final String DATABASE_NAME = "habitTracking.db";
    // DataBase version.
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@Link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABITS_DATE + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABITS_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABITS_COMMENT + " TEXT);";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME);
        onCreate(db);
    }
}
