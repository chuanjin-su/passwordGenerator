package com.chuanjinsu.passwdgenerator;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Vector;

public class HintOperations {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase dataBase;

    public HintOperations(Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public void open(){
        dataBase = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public long addHint(Hint h){
        ContentValues values = new ContentValues();
        values.put(dbHelper.getHINT(), h.getHint());

        long id = dataBase.insert(dbHelper.getTableHintlog(), null, values);
        return id;
    }

    public boolean modifyHint(Hint h){
        ContentValues values = new ContentValues();
        values.put(dbHelper.getID(), h.getId());
        values.put(dbHelper.getHINT(), h.getHint());
        long i = dataBase.update(dbHelper.getTableHintlog(), values, dbHelper.getID() + '=' + h.getId(), null);
        return i>0;
    }

    public boolean deleteHint(int ID){
        long i = dataBase.delete(dbHelper.getTableHintlog(), dbHelper.getID() + '=' + ID, null);
        return i>0;
    }

    public Vector<Hint> listAllTheHints(){
        Vector<Hint> lHints = new Vector<Hint>();
        String[] tabColumn = new String[2];
        tabColumn[0] = dbHelper.getID();
        tabColumn[1] = dbHelper.getHINT();

        Cursor cursor = dataBase.query(dbHelper.getTableHintlog(), tabColumn, null,
                null, null, null, null);
        if (cursor.moveToFirst()==true){
            do{
                int id = cursor.getInt(0);
                String hint = cursor.getString(1);
                Hint h = new Hint(id, hint);
                lHints.add(h);
            } while (cursor.moveToNext());
        }
        return lHints;
    }

    public Vector<Hint> listAllTheHintsReverse(){
        Vector<Hint> lHints = listAllTheHints();
        Vector<Hint> lHintsReverse = new Vector<Hint>();
        for(int i=0; i<lHints.size(); i++){
            lHintsReverse.add(lHints.get(lHints.size()-1-i));
        }
        return lHintsReverse;
    }
}
