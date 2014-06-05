package com.lol.lolpro.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;

import java.util.Vector;

/**
 * Clase que se encarga de la gestión de la base de datos
 */
public class BBDDHelper extends SQLiteOpenHelper {

    /**
     *
     * @param context
     */
    public BBDDHelper(Context context) {
        super(context, context.getResources().getString(R.string.app_name), null, 1);
    }

    /**
     * Se encarga de crear las tablas de la base de datos
     * @param db Base de datos en la que se crearán las tablas
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE campeones (" +
                "_id INTEGER PRIMARY KEY" +
                ", nombre TEXT, nick TEXT, historia TEXT, vida TEXT, regeneracionVida TEXT, " +
                "danioAtaque TEXT, armadura TEXT, velocidadAtaque TEXT, resistenciaMagica TEXT," +
                "velocidadMovimiento TEXT, rutaPrincipal TEXT, esGratis INTEGER)");
        db.execSQL("CREATE TABLE objetos (" +
                "_id INTEGER PRIMARY KEY" +
                ", nombre TEXT, costeBase INTEGER, coste INTEGER, descripcion TEXT, puedesComprar INTEGER," +
                "rutaPrincipal TEXT)");
        db.execSQL("CREATE TABLE rutaVersiones (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", ruta TEXT, versionCampeones TEXT, versionObjetos TEXT)");
    }

    /**
     * Método sobreescrito que se encarga del cambio de versión al actualizar las tablas
     * @param db Base de datos en la que se actualizarán las tablas
     * @param oldVersion versión antigua
     * @param newVersion versión nueva a actualizar
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
    }

    /**
     * Se encarga de guardar los datos de un campeón en la tabla campeones
     * @param id Identificador único de cada campeón
     * @param nombre Nombre del campeón
     * @param nick Nick del campeón
     * @param historia Historia del campeón
     * @param vida Vida del campeón
     * @param regeneracionVida Regeneración de vida por segundo del campeón
     * @param danioAtaque Daño de ataque del campeón
     * @param armadura Resistencia de ataques físicos del campeón
     * @param velocidadAtaque Velocidad de ataque por segundo del campeón
     * @param resistenciaMagica Resistencia a habilidaddes o poder mágico del campeón
     * @param velocidadMovimiento Número de unidades que se desplaza el acmpeón por segundo
     * @param rutaPrincipal Ruta en la que se encuentra la imagen principal del campeón
     */
    public void guardarCampeones(int id, String nombre, String nick, String historia, String vida,
                                 String regeneracionVida, String danioAtaque, String armadura,
                                 String velocidadAtaque, String resistenciaMagica,
                                 String velocidadMovimiento, String rutaPrincipal) {
        double velAtaque = Double.parseDouble(velocidadAtaque);
        velAtaque = 1 / (1.6 * (1 + velAtaque));
        //Redondeo de tres cifras
        velAtaque = Math.rint(velAtaque * 1000) / 1000;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO campeones VALUES (" + id + ", '" + nombre + "', '" + nick + "','" +
                historia + "', '" + vida + "', '" + regeneracionVida + "', '" + danioAtaque + "', '" + armadura + "'," +
                "'" + velAtaque + "', '" + resistenciaMagica + "', '" + velocidadMovimiento + "'," +
                "'" + rutaPrincipal + "', 0)");
        db.close();
    }

    /**
     * Se encarga de guardar los datos de un objeto en la tabla objetos
     * @param id Identificador único para cada objeto
     * @param nombre Nombre del objeto
     * @param costeBase Coste del objeto sin tener en cuenta la jerarquía
     * @param coste Coste total del objeto sumando todos los objetos de su jerarquía
     * @param descripcion Descrición del objeto
     * @param puedesComprar Indica con 1 que el objeto se encuentra en la tienda y con 0 que no
     * @param rutaPrincipal  Ruta en la que se encuentra la imagen principal del objeto
     */
    public void guardarObjetos(int id, String nombre, int costeBase, int coste, String descripcion,
                               int puedesComprar, String rutaPrincipal) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO objetos VALUES (" + id + ", '" + nombre + "', " + costeBase + ", " + coste + "," +
                "'" + descripcion + "', " + puedesComprar + ", '" + rutaPrincipal + "')");
        db.close();
    }

    /**
     * Se encarga de actualizar los datos de un campeón si ha sufrido cambios debido a un cambio de versión
     * @param id Identificador único de cada campeón
     * @param nombre Nombre del campeón
     * @param nick Nick del campeón
     * @param historia Historia del campeón
     * @param vida Vida del campeón
     * @param regeneracionVida Regeneración de vida por segundo del campeón
     * @param danioAtaque Daño de ataque del campeón
     * @param armadura Resistencia de ataques físicos del campeón
     * @param velocidadAtaque Velocidad de ataque por segundo del campeón
     * @param resistenciaMagica Resistencia a habilidaddes o poder mágico del campeón
     * @param velocidadMovimiento Número de unidades que se desplaza el acmpeón por segundo
     * @param rutaPrincipal Ruta en la que se encuentra la imagen principal del campeón
     */
    public void modificarCampeones(int id, String nombre, String nick, String historia, String vida,
                                   String regeneracionVida, String danioAtaque, String armadura,
                                   String velocidadAtaque, String resistenciaMagica,
                                   String velocidadMovimiento, String rutaPrincipal) {
        double velAtaque = Double.parseDouble(velocidadAtaque);
        velAtaque = 1 / (1.6 * (1 + velAtaque));
        velAtaque = Math.rint(velAtaque * 1000) / 1000;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE campeones SET nombre='" + nombre + "', nick='" + nick + "', historia='" + historia + "', " +
                "vida='" + vida + "', regeneracionVida='" + regeneracionVida + "', danioAtaque='" + danioAtaque + "'," +
                "armadura='" + armadura + "', velocidadAtaque='" + velAtaque + "', resistenciaMagica='" + resistenciaMagica +
                "', velocidadMovimiento='" + velocidadMovimiento + "'," +
                "rutaPrincipal='" + rutaPrincipal + "', esGratis=0 WHERE _id=" + id);
        db.close();
    }

    /**
     * Se encarga de actualizar los datos de un objeto si ha sufrido cambios debido a un cambio de versión
     * @param id Identificador único para cada objeto
     * @param nombre Nombre del objeto
     * @param costeBase Coste del objeto sin tener en cuenta la jerarquía
     * @param coste Coste total del objeto sumando todos los objetos de su jerarquía
     * @param descripcion Descrición del objeto
     * @param puedesComprar Indica con 1 que el objeto se encuentra en la tienda y con 0 que no
     * @param rutaPrincipal  Ruta en la que se encuentra la imagen principal del objeto
     */
    public void modificarObjetos(int id, String nombre, int costeBase, int coste, String descripcion,
                                 int puedesComprar, String rutaPrincipal) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE objetos SET nombre='" + nombre + "', costeBase=" + costeBase + ", coste=" + coste + "," +
                "descripcion='" + descripcion + "', puedesComprar=" + puedesComprar + ", rutaPrincipal='" + rutaPrincipal + "' WHERE _id=" + id);
        db.close();
    }

    public void guardarRutaVersiones(String ruta, String vCampeon, String vObjeto) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO rutaVersiones VALUES (null, '" + ruta + "','" + vCampeon + "', '" + vObjeto + "')");
        db.close();
    }

    public void modificarGratuito(int[] ids) {
        int length = ids.length;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE campeones SET esGratis=0 WHERE esGratis=1");
        for (int i = 0; i < length; i++) {
            db.execSQL("UPDATE campeones SET esGratis=1 WHERE _id=" + ids[i]);
        }
        db.close();
    }

    public String[][] obtenerRutaCampeones() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nombre, rutaPrincipal FROM " +
                "campeones ORDER BY nombre", null);
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
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString() + "/";
            result2 += Html.fromHtml(cursor.getString(1)).toString() + "/img/champion/";
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String obtenerVersionCampeon() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT versionCampeones FROM " +
                "rutaVersiones", null);
        String result2 = "";
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString();
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
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString() + "/";
            result2 += Html.fromHtml(cursor.getString(1)).toString() + "/img/item/";
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String obtenerVersionObjeto() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT versionObjetos FROM " +
                "rutaVersiones", null);
        String result2 = "";
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString();
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
        Cursor cursor = db.rawQuery("SELECT nombre, nick, historia, vida, regeneracionVida, danioAtaque, armadura, velocidadAtaque, resistenciaMagica, velocidadMovimiento, rutaPrincipal" +
                " FROM campeones WHERE _id=" + id, null);
        String[] result2 = new String[cursor.getColumnCount()];
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result2[pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result2[pos2++] = Utils.sanitizeChampStory(Html.fromHtml(cursor.getString(2)).toString());
            result2[pos2++] = Html.fromHtml(cursor.getString(3)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(4)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(5)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(6)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(7)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(8)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(9)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(10)).toString();
        }
        cursor.close();
        db.close();
        return result2;
    }


    public String[] obtenerDatosObjetos(int id) {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre, costeBase, coste, descripcion, puedesComprar, rutaPrincipal" +
                " FROM objetos WHERE _id=" + id, null);
        String[] result2 = new String[cursor.getColumnCount()];
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result2[pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(2)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(3)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(4)).toString();
            result2[pos2] = Html.fromHtml(cursor.getString(5)).toString();
        }
        cursor.close();
        db.close();
        return result2;
    }

    public String[][] obtenerGratuitos() {
        Vector<String> result = new Vector<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, nombre, rutaPrincipal" +
                " FROM campeones WHERE esGratis=1 ORDER BY nombre", null);
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