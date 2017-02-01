package com.gashe.mimasacorporal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gashe on 30/1/17.
 */

public class consultasDataBase extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE_USER = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, pass TEXT)";

    public consultasDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void registrarUser(User user){
        SQLiteDatabase query = this.getWritableDatabase();
        query.execSQL("INSERT INTO users (user, pass) VALUES ('"+user.getUsuario()+"', '"+user.getPass()+"') ");
        query.close();
    }

    public void vaciarTabla(){
        SQLiteDatabase query = this.getWritableDatabase();
        query.execSQL("DELETE FROM users");
        query.close();
    }

    public boolean checkLogin(User user){
        boolean bool;
        SQLiteDatabase query = this.getReadableDatabase();

        Cursor cursor = query.rawQuery("SELECT * FROM users WHERE user = '"+user.getUsuario()+"' AND pass = '"+user.getPass()+"' ", null);

        if(cursor != null && cursor.getCount() > 0) bool = true;
        else bool = false;

        cursor.close();
        query.close();

        return bool;
    }

    public List<User> mostrarUsers(){
        List<User> userList = null;

        SQLiteDatabase query = this.getReadableDatabase();
        Cursor cursor = query.rawQuery("SELECT user, pass FROM users", null);

        if((cursor != null) && (cursor.getCount() > 0)){

            cursor.moveToFirst();
            userList = new ArrayList<User>();
            String usuario = null;
            String pass = null;
            User user = null;

            do {
                usuario = cursor.getString(0);
                pass = cursor.getString(1);
                user = new User(usuario, pass);
                userList.add(user);
            }while (cursor.moveToNext());

            cursor.close();
        }

        query.close();

        return userList;
    }


}
