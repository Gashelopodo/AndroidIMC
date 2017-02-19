package com.gashe.mimasacorporal.classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gashe on 30/1/17.
 */

public class consultasDataBase extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE_USER = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, pass TEXT)";
    private static final String SQL_CREATE_TABLE_HISTORIAL = "CREATE TABLE historial (id INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, imc FLOAT, created_at DATE)";

    public consultasDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_HISTORIAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void registrarUser(User user){
        SQLiteDatabase query = this.getWritableDatabase();
        Utils utils = new Utils();
        query.execSQL("INSERT INTO users (user, pass) VALUES ('"+utils.codifica(user.getUsuario())+"', '"+utils.codifica(user.getPass())+"') ");
        query.close();
    }

    public void registrarImc(Historial historial){
        SQLiteDatabase query = this.getWritableDatabase();
        query.execSQL("INSERT INTO historial (userId, imc, created_at) VALUES ('"+historial.getUserId()+"', '"+historial.getImc()+"', datetime()) ");
        query.close();
    }

    public void vaciarTabla(){
        SQLiteDatabase query = this.getWritableDatabase();
        query.execSQL("DELETE FROM users");
        query.close();
    }

    public void vaciarHistorial(){
        SQLiteDatabase query = this.getWritableDatabase();
        query.execSQL("DELETE FROM historial");
        query.close();
    }

    public boolean checkLogin(User user){
        boolean bool = false;
        SQLiteDatabase query = this.getReadableDatabase();
        Utils utils = new Utils();
        Cursor cursor = query.rawQuery("SELECT * FROM users WHERE user = '"+utils.codifica(user.getUsuario())+"' AND pass = '"+utils.codifica(user.getPass())+"' ", null);

        if(cursor != null && cursor.getCount() > 0) bool = true;

        cursor.close();
        query.close();

        return bool;
    }

    public int getId(User user){
        int idUser = 0;
        SQLiteDatabase query = this.getReadableDatabase();
        Utils utils = new Utils();
        Cursor cursor = query.rawQuery("SELECT * FROM users WHERE user = '"+utils.codifica(user.getUsuario())+"' AND pass = '"+utils.codifica(user.getPass())+"' ", null);

        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                idUser = cursor.getInt(0);
            }while (cursor.moveToNext());

        }

        cursor.close();
        query.close();

        return idUser;

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

    public List<Historial> mostrarHistorial(int userId){
        List<Historial> historialList = null;

        SQLiteDatabase query = this.getReadableDatabase();
        Cursor cursor = query.rawQuery("SELECT imc, created_at FROM historial WHERE userId = "+userId, null);

        if((cursor != null) && (cursor.getCount() > 0)){

            cursor.moveToFirst();
            historialList = new ArrayList<Historial>();
            String created_at = null;
            float imc = 0;
            Historial historial = null;

            do {
                imc = cursor.getFloat(0);
                created_at = cursor.getString(1);
                historial = new Historial(userId, imc, created_at);
                historialList.add(historial);
            }while (cursor.moveToNext());

            cursor.close();
        }

        query.close();

        return historialList;
    }


}
