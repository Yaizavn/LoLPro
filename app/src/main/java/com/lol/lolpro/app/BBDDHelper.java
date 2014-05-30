package com.lol.lolpro.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

/**
 * Created by sergio on 29/05/14.
 */
public class BBDDHelper extends SQLiteOpenHelper{

    public BBDDHelper(Context context) {
        super(context, context.getResources().getString(R.string.app_name), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE campeones ("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", nombre TEXT, nick TEXT, ciudad TEXT, vida TEXT, regeneracionVida TEXT, " +
                "danioAtaque TEXT, armadura TEXT, velocidadAtaque TEXT, resistenciaMagica TEXT," +
                "velocidadMovimiento TEXT, rutaPrincipal TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
    }

    //TODO coger las cosas como numeros y no como texto separado en dos campos (Ej: vida 240 vidaPorNivel 95)

    public void guardarDatos(String nombre, String nick, String ciudad, String vida,
                             String regeneracionVida, String danioAtaque, String armadura,
                             String velocidadAtaque, String resistenciaMagica,
                             String velocidadMovimiento, String rutaPrincipal) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO campeones VALUES (null, '"+nombre+"','"+nick+"', '"+ciudad+"'," +
                "'"+vida+"', '"+regeneracionVida+"', '"+danioAtaque+"', '"+armadura+"'," +
                "'"+velocidadAtaque+"', '"+resistenciaMagica+"', '"+velocidadMovimiento+"'," +
                "'"+rutaPrincipal+"')");
        db.close();
    }

    public String[][] obtenerRutas() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nombre, rutaPrincipal FROM " +
                "campeones ORDER BY nombre DESC", null);
        String[][] result2 = new String[cursor.getCount()][3];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()){
            result2[pos][pos2++] = Integer.toString(cursor.getInt(0));
            result2[pos][pos2++] = cursor.getString(1);
            result2[pos][pos2] = cursor.getString(2);
            pos2 = 0;
            pos++;
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String[] obtenerDatos(int id) {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre, ciudad, vida, regeneracionVida, danioAtaque, armadura, velocidadAtaque, resistenciaMagica, velocidadMovimiento, rutaPrincipal" +
                " FROM campeones ORDER BY nombre DESC WHERE _id="+id, null);
        String[] result2 = new String[10];
        int pos2 = 0;
        if (cursor.moveToNext()){
            result2[pos2++] = cursor.getString(1);
            result2[pos2++] = cursor.getString(2);
            result2[pos2++] = cursor.getString(3);
            result2[pos2++] = cursor.getString(4);
            result2[pos2++] = cursor.getString(5);
            result2[pos2++] = cursor.getString(6);
            result2[pos2++] = cursor.getString(7);
            result2[pos2++] = cursor.getString(8);
            result2[pos2++] = cursor.getString(9);
            result2[pos2] = cursor.getString(10);
            pos2 = 0;
        }
        cursor.close();
        db.close();
        return result2;
    }

    /*public Vector<String> listarDatos(int cantidad) {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre, nick FROM " +
                "campeones DESC LIMIT " +cantidad, null);
        while (cursor.moveToNext()){
            result.add(cursor.getString(0)+" " +cursor.getString(1));
        }
        cursor.close();
        db.close();
        return result;
    }*/
}