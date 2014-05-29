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
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre TEXT, nick TEXT, ciudad TEXT, vida INTEGER, regeneracionVida REAL, " +
                "danioAtaque REAL, armadura REAL, velocidadAtaque REAL, resistenciaMagica REAL," +
                "velocidadMovimiento INTEGER, rutaPrincipal TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
    }

    public void guardarDatos(String nombre, String nick, String ciudad, int vida,
                             float regeneracionVida, float danioAtaque, float armadura,
                             float velocidadAtaque, float resistenciaMagica,
                             int velocidadMovimiento, String rutaPrincipal) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO campeones VALUES (null, '"+nombre+"','"+nick+"', '"+ciudad+"'," +
                ""+vida+", "+regeneracionVida+", "+danioAtaque+", "+armadura+"," +
                ""+velocidadAtaque+", "+resistenciaMagica+", "+velocidadMovimiento+"," +
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