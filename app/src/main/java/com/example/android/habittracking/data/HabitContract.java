package com.example.android.habittracking.data;

/**
 * Created by sarahaldowihy on 10/3/2017 AD.
 */

import android.provider.BaseColumns;

/**
 * API Contract for the Habit Tracking app.
 */
public class HabitContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {
    }

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class HabitEntry implements BaseColumns {
        // Name of database table for Habits
        public final static String TABLE_NAME = "habits";
        /**
         * Unique ID number for the habits
         * Type: INTEGER
         * it's reference from BaseColumns directly
         */

        /**
         * Date of the habit.
         * Type: TEXT
         */
        public final static String COLUMN_HABITS_DATE = "date";
        /**
         * Name of the habit.
         * Type: TEXT
         */
        public final static String COLUMN_HABITS_NAME = "name";
        /**
         * Comment of the habit
         * Type:TEXT
         */
        public final static String COLUMN_HABITS_COMMENT = "comment";
    }
}
