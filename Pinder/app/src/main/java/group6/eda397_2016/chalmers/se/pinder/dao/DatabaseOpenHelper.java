package group6.eda397_2016.chalmers.se.pinder.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;

/**
 * Created by Fredrik Nilsson on 20/04/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Pinder";
    private static final String TASKS_TABLE_NAME = "TASKS";
    private static final String PROFILES_TABLE_NAME = "PROFILES";

    /**
     * Everything that is required for the SKILLS table, more attributes
     * could be added other than languages.
     */
    private static final String SKILLS_TABLE_NAME = "SKILLS";
    private static final String KEY_ID = "id";
    private static final String KEY_LANGUAGE = "language";
    private static final String SKILLS_TABLE_CREATE =
            "CREATE TABLE" + SKILLS_TABLE_NAME + " (" + KEY_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_LANGUAGE + "TEXT" + ")";


    private static final String REQUIRED_SKILLS_TABLE_NAME = "REQUIRED_SKILLS";
    private static final String PROFILE_SKILLS_TABLE_NAME = "PROFILE_SKILLS";
    private static final String TEAMS_TABLE_NAME = "TEAMS";

    private static final String TASK_TABLE_CREATE = "";
    private static final String PROFILE_TABLE_CREATE = "";

    private static final String PROFILE_SKILLS_TABLE_CREATE = "";
    private static final String TASK_SKILLS_TABLE_CREATE = "";
    private static final String TEAMS_TABLE_CREATE = "";




    DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TASK_TABLE_CREATE);
        db.execSQL(PROFILE_TABLE_CREATE);
        db.execSQL(SKILLS_TABLE_CREATE);
        db.execSQL(PROFILE_SKILLS_TABLE_CREATE);
        db.execSQL(TASK_SKILLS_TABLE_CREATE);
        db.execSQL(TEAMS_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
