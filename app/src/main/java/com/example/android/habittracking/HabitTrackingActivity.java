package com.example.android.habittracking;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittracking.data.HabitContract.HabitEntry;
import com.example.android.habittracking.data.HabitDbHelper;

public class HabitTrackingActivity extends AppCompatActivity {
    /**
     * Database helper that will provide us access to the database.
     */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracking);
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);
        //insert data
        insertHabitTracking();
        //read the Data from Cursor
        Cursor cursor = readData();
        //display the data from Cursor
        displayHabitTracking(cursor);
    }

    /**
     * Helper method to insert data into database.
     */
    private void insertHabitTracking() {
        //Gets the database in write mode.
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        //Create a ContentValues objects.
        // where the key is column names.

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABITS_DATE, "2017-9-21");
        values.put(HabitEntry.COLUMN_HABITS_NAME, "Write Java code");
        values.put(HabitEntry.COLUMN_HABITS_COMMENT, "it's so easy ! =)");

        //Insert the new row in the data base , returning the ID of new row.
        // The first argument for db.insert() is the habit table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info.
        db.insert(HabitEntry.TABLE_NAME, null, values);

        // Enter another Habit
        values.put(HabitEntry.COLUMN_HABITS_DATE, "2017-9-20");
        values.put(HabitEntry.COLUMN_HABITS_NAME, "Meeting my friend");
        values.put(HabitEntry.COLUMN_HABITS_COMMENT, "");

        db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    /**
     * Method to read Cursor and return it.
     */
    private Cursor readData() {
        //open a database to read from it.
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABITS_DATE,
                HabitEntry.COLUMN_HABITS_NAME,
                HabitEntry.COLUMN_HABITS_COMMENT};
        //Perform a query on the habits table
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME, //the table to query
                projection, // the columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                HabitEntry.COLUMN_HABITS_DATE); // The sort order BY Date
        return cursor;
    }

    private void displayHabitTracking(Cursor cursor) {

        TextView displayView = (TextView) findViewById(R.id.text_view_habit_tracking);

        try {
            /**
             * Create a header in the Text View that looks like this:
             *
             * --  Habit Tracking --
             * The number of Habits you add is : < number of rows in Cursor >
             * _id - date - name - comment
             */
            displayView.setText("--  Habit Tracking -- " + "\n"
                    + "The number of Habits you add is : " + cursor.getCount() + " habits \n\n");
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABITS_DATE + " - " +
                    HabitEntry.COLUMN_HABITS_NAME + " - " +
                    HabitEntry.COLUMN_HABITS_COMMENT + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABITS_DATE);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABITS_NAME);
            int commentColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABITS_COMMENT);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentComment = cursor.getString(commentColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append("\n" + currentID + " - "
                        + currentDate + " - " + currentName + " - "
                        + currentComment);
            }
        } finally {
            // Close the cursor when done reading from it.
            cursor.close();
        }

    }
}
