package com.lol.lolpro.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;

import java.util.Vector;

/**
 * Created by sergio on 29/05/14.
 */
public class BBDDHelper extends SQLiteOpenHelper {

    public BBDDHelper(Context context) {
        super(context, context.getResources().getString(R.string.app_name), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE campeones (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", nombre TEXT, nick TEXT, vida TEXT, regeneracionVida TEXT, " +
                "danioAtaque TEXT, armadura TEXT, velocidadAtaque TEXT, resistenciaMagica TEXT," +
                "velocidadMovimiento TEXT, rutaPrincipal TEXT, esGratis INTEGER)");
        db.execSQL("CREATE TABLE objetos (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", nombre TEXT, costeBase INTEGER, coste INTEGER, descripcion TEXT, puedesComprar INTEGER," +
                "resumen TEXT, rutaPrincipal TEXT)");
        db.execSQL("CREATE TABLE rutaVersiones (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", ruta TEXT, versionCampeones TEXT, versionObjetos TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
    }

    //TODO coger las cosas como numeros y no como texto separado en dos campos (Ej: vida 240 vidaPorNivel 95)

    public void guardarCampeones(String nombre, String nick, String vida,
                                 String regeneracionVida, String danioAtaque, String armadura,
                                 String velocidadAtaque, String resistenciaMagica,
                                 String velocidadMovimiento, String rutaPrincipal) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO campeones VALUES (null, '" + nombre + "', '" + nick + "'," +
                "'" + vida + "', '" + regeneracionVida + "', '" + danioAtaque + "', '" + armadura + "'," +
                "'" + velocidadAtaque + "', '" + resistenciaMagica + "', '" + velocidadMovimiento + "'," +
                "'" + rutaPrincipal + "', 0)");
        db.close();
    }

    public void guardarObjetos(String nombre, int costeBase, int coste, String descripcion,
                               int puedesComprar, String resumen, String rutaPrincipal) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO objetos VALUES (null, '" + nombre + "', " + costeBase + ", " + coste + "," +
                "'" + descripcion + "', " + puedesComprar + ", '" + resumen + "','" + rutaPrincipal + "')");
        db.close();
    }

    public void guardarRutaVersiones(String ruta, String vCampeon, String vObjeto) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO rutaVersiones VALUES (null, '" + ruta + "','" + vCampeon + "', '" + vObjeto + "')");
        db.close();
    }

    public void modificarGratuito(int[] id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE campeones SET esGratis=0 WHERE esGratis=1");
        for (int i = 0; i < id.length; i++) {
            db.execSQL("UPDATE campeones SET esGratis=1 WHERE _id=" + id[i]);
        }
        db.close();
    }

    public String[][] obtenerRutas() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nombre, rutaPrincipal FROM " +
                "objetos ORDER BY nombre", null);
        String[][] result2 = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result2[pos][pos2++] = Integer.toString(cursor.getInt(0));
            result2[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result2[pos][pos2] = Html.fromHtml(cursor.getString(2)).toString();
            pos2 = 0;
            pos++;
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String obtenerRutaVersionCampeon() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ruta, versionCampeones FROM " +
                "rutaVersiones", null);
        String result2 = "";
        while (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString() + "/";
            result2 += Html.fromHtml(cursor.getString(1)).toString() + "/img/champion/";
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String obtenerRutaVersionObjeto() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ruta, versionObjetos FROM " +
                "rutaVersiones", null);
        String result2 = "";
        while (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString() + "/";
            result2 += Html.fromHtml(cursor.getString(1)).toString() + "/img/item/";
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String[][] obtenerRutaObjetos() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nombre, rutaPrincipal FROM " +
                "objetos ORDER BY nombre", null);
        String[][] result2 = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result2[pos][pos2++] = Integer.toString(cursor.getInt(0));
            result2[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result2[pos][pos2] = Html.fromHtml(cursor.getString(2)).toString();
            pos2 = 0;
            pos++;
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String[] obtenerDatosCampeon(int id) {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre, nick, vida, regeneracionVida, danioAtaque, armadura, velocidadAtaque, resistenciaMagica, velocidadMovimiento, rutaPrincipal" +
                " FROM campeones WHERE _id=" + id, null);
        String[] result2 = new String[cursor.getColumnCount()];
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result2[pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(2)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(3)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(4)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(5)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(6)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(7)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(8)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(9)).toString();
        }
        cursor.close();
        db.close();
        return result2;
    }


    public String[] obtenerDatosObjetos(int id) {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre, costeBase, coste, descripcion, puedesComprar, resumen, rutaPrincipal" +
                " FROM objetos WHERE _id=" + id, null);
        String[] result2 = new String[cursor.getColumnCount()];
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result2[pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(2)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(3)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(4)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(5)).toString();
            result2[pos2] = Html.fromHtml(cursor.getString(6)).toString();
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String[][] obtenerGratuitos() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nombre, rutaPrincipal" +
                " FROM campeones WHERE esGratis=1", null);
        String[][] result3 = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result3[pos][pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result3[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result3[pos][pos2] = Html.fromHtml(cursor.getString(2)).toString();
            pos2 = 0;
            pos++;
        }
        cursor.close();
        db.close();
        return result3;
    }
}