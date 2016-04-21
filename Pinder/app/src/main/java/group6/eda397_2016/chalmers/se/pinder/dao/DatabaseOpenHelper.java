package group6.eda397_2016.chalmers.se.pinder.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;

/**
 * Created by Fredrik Nilsson on 20/04/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Pinder";




    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL(createTaskTable());
        db.execSQL(createSkillsTable());
        db.execSQL(createRequiredSkillsTable());
        db.execSQL(createProfileSkillsTable());
        db.execSQL(createTeamsTable());
        */
        db.execSQL(createProfileTable());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addProfile(String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        db.insert("PROFILES", null, values);
        db.close();
    }

    public void getProfile(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM PROFILES",null);
        c.moveToFirst();
        c.moveToLast();
        System.out.println(c.getString(c.getColumnIndex("name")));
    }

    private String createSkillsTable() {
        /**
         * Everything that is required for the SKILLS table, more attributes
         * could be added other than languages.
         */
        final String SKILLS_TABLE_NAME = "SKILLS";
        final String KEY_ID_SKILLS = "id";
        final String KEY_LANGUAGE_SKILLS = "language";
        final String SKILLS_TABLE_CREATE =
                "CREATE TABLE" + SKILLS_TABLE_NAME + " (" + KEY_ID_SKILLS +
                        " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        KEY_LANGUAGE_SKILLS + "TEXT" + ")";
        return SKILLS_TABLE_CREATE;
    }

    private String createRequiredSkillsTable() {
        final String REQUIRED_SKILLS_TABLE_NAME = "REQUIRED_SKILLS";
        final String KEY_ID = "id";
        final String TASK_ID = "task id";
        final String SKILL_ID = "skill id";
        final String REQUIRED_SKILLS_TABLE_CREATE =
                "CREATE TABLE" + REQUIRED_SKILLS_TABLE_NAME +
                        " (" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        TASK_ID+" INTEGER," +
                        "FOREIGN KEY (TASK_ID) REFERENCES TASKS (id)), " +
                        "SKILL_ID INTEGER, " +
                        "FOREIGN KEY (SKILL_ID) REFERENCES SKILLS (id)) " +
                        ")";

        return REQUIRED_SKILLS_TABLE_CREATE;
    }

    private String createProfileSkillsTable() {
        final String PROFILE_SKILLS_TABLE_NAME = "PROFILE_SKILLS";
        final String KEY_ID = "id";
        final String PROFILE_ID = "profile id";
        final String TASK_ID = "task id";
        final String PROFILE_SKILLS_TABLE_CREATE =
                "CREATE TABLE" + PROFILE_SKILLS_TABLE_NAME +
                        " (" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        PROFILE_ID+" INTEGER," +
                        "FOREIGN KEY (" + PROFILE_ID + ") REFERENCES PROFILES (id)), " +
                        "SKILL_ID INTEGER, " +
                        "FOREIGN KEY (SKILL_ID) REFERENCES SKILLS (id)) " +
                        ")";

        return PROFILE_SKILLS_TABLE_CREATE;
    }

    private String createTeamsTable(){
        final String TEAMS_TABLE_NAME = "TEAMS";
        final String KEY_ID = "id";
        final String PROFILE_ID = "profile id";
        final String TASK_ID = "task id";
        final String TEAMS_TABLE_CREATE =
                "CREATE TABLE " + TEAMS_TABLE_NAME + " (" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        PROFILE_ID + " INTEGER, " +
                        "FOREIGN KEY (" + PROFILE_ID + ") REFERENCES PROFILES (id)), " +
                        TASK_ID + " INTEGER, " +
                        "FOREIGN KEY (" + TASK_ID + ") REFERENCES TASKS (id), " +
                        PROFILE_ID + " INTEGER, " +
                        "FOREIGN KEY (" + PROFILE_ID + ") REFERENCES PROFILES (id)" +
                        ") "
                ;
        return TEAMS_TABLE_CREATE;
    }

    private String createTaskTable() {
        final String TASK_TABLE_NAME = "TASKS";
        final String KEY_ID = "id";
        final String TASK_NAME = "name";
        final String TASK_TABLE_CREATE =
                "CREATE TABLE " + TASK_TABLE_NAME +" (" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        TASK_NAME + " TEXT" +
                        ") ";
        return TASK_TABLE_CREATE;
    }



    private String createProfileTable() {
        final String PROFILES_TABLE_NAME = "PROFILES";
        final String KEY_ID = "id";
        final String PROFILE_NAME = "name";
        final String PROFILE_TABLE_CREATE =
                "CREATE TABLE " + PROFILES_TABLE_NAME +" (" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        PROFILE_NAME + " TEXT" +
                ") ";
        return PROFILE_TABLE_CREATE;
    }
}
