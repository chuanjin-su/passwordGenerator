package com.chuanjinsu.passwdgenerator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String TABLE_HINTLOG = "hintlogs";
    private static final String NAME_DATA = "data.db";
    private static final int VERSION_DATABASE = 4;
    private static String ID = "id";
    private static final String HINT = "hint";
    private static final String REQUEST_CREATION_TABLE =
            "CREATE TABLE " + TABLE_HINTLOG + "(" +
                    ID + " INTEGER PRIMARY KEY NOT NULL, "+
                    HINT + " TEXT NOT NULL);";
    private static final String REQUEST_SUPPRESSION_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_HINTLOG + ";";


    public DatabaseHelper(Context context){
        super(context, NAME_DATA, null, VERSION_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUEST_CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(REQUEST_SUPPRESSION_TABLE);
        onCreate(db);
    }

    public static String getTableHintlog() {
        return TABLE_HINTLOG;
    }

    public static int getVersionDatabase() {
        return VERSION_DATABASE;
    }

    public static String getID() {
        return ID;
    }

    public static String getHINT() {
        return HINT;
    }
}
