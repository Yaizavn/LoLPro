package com.lol.lolpro.app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;

/**
 * Clase que se encarga de la gestión de la base de datos
 */
public class BBDDHelper extends SQLiteOpenHelper {

    /**
     * Constructor que inicializa la base de datos
     * @param context Contiene el activity principal
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

    /**
     * Almacena en la base de datos la última url y la última versión de los campeones y lso objetos
     * @param ruta última ruta conocida para las imágenes de campeones y objetos
     * @param vCampeon última versión concoida para los campeones
     * @param vObjeto última versión conocida para los objetos
     */
    public void guardarRutaVersiones(String ruta, String vCampeon, String vObjeto) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO rutaVersiones VALUES (null, '" + ruta + "','" + vCampeon + "', '" + vObjeto + "')");
        db.close();
    }

    /**
     * Se encarga de poner los campeones cuyos ids se encuentran entre los dados en los parámetros como gratuitos
     * y marcar los antiguos campeones gratuitos como no gratuitos
     * @param ids Contiene los ids de los campeones gratuitos esa semana
     */
    public void modificarGratuito(int[] ids) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE campeones SET esGratis=0 WHERE esGratis=1");
        for (int id:ids) {
            db.execSQL("UPDATE campeones SET esGratis=1 WHERE _id=" + id);
        }
        db.close();
    }

    /**
     * Se encarga de obtener el identificador, el nombre y la ruta completa de la imagen principal de todos los campeones
     * @return Array de String en el en que en cada fila encontraremos un campeón. Además:
     *  identificador del campeón en la primera columna
     *  Nombre del campeón en la segunda columna
     *  Ruta de la imagen principal del campeón en la tercera columna
     *  */
    public String[][] obtenerRutaCampeones() {
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

    /**
     * Se encarga de obtener el identificador, el nombre y la ruta completa de la imagen principal de todos los objetos
     * @return Array de String en el en que en cada fila encontraremos un objeto. Además:
     *  identificador del objeto en la primera columna
     *  Nombre del objeto en la segunda columna
     *  Ruta de la imagen principal del objeto en la tercera columna
     */
    public String[][] obtenerRutaObjetos() {
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

    /**
     * Se encarga de obtener la última ruta y versión de los campeones
     * @return String con la ruta compuesta por la última ruta conocida y la última versión conocida
     */
    public String obtenerRutaVersionCampeon() {
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

    /**
     * Se encarga de obtener la última versión de los campeones
     * @return String con la última versión conocida
     */
    public String obtenerVersionCampeon() {
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


    /**
     * Se encarga de obtener la última ruta y versión de los objetos
     * @return String con la ruta compuesta por la última ruta conocida y la última versión conocida
     */
    public String obtenerRutaVersionObjeto() {
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

    /**
     * Se encarga de obtener la última versión de los objetos
     * @return String con la última versión conocida
     */
    public String obtenerVersionObjeto() {
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

    /**
     * Se encarga de obtener todos los datos de un campeón para un identificador dado
     * @param id identificador único del campeón a buscar
     * @return Array con:
     *  Nombre en la primera posición
     *  Nick en la segunda posición
     *  Historia en la tercera posición
     *  Regeneración de vida en la cuarta posición
     *  Daño de ataque en la quinta posición
     *  Armadura en la sexta posición
     *  Velocidad de ataque en la septima posición
     *  Resistencia mágica en la octava posición
     *  Velocidad de movimiento en la novena posición
     *  Ruta de la imagen principal en la decima posición
     */
    public String[] obtenerDatosCampeon(int id) {
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
            result2[pos2] = Html.fromHtml(cursor.getString(10)).toString();
        }
        cursor.close();
        db.close();
        return result2;
    }

    /**
     * Se encarga de obtener todos los datos de un objeto para un identificador dado
     * @param id identificador único del objeto a buscar
     * @return Array con:
     *  Nombre en la primera posición
     *  Coste base en la segunda posición
     *  Coste en la tercera posición
     *  Descripción de vida en la cuarta posición
     *  Entero que indica con uno que el objeto puede comprarse en la tienda y con 0 que no puede comprarse, en la quinta posición
     *  Ruta de la imagen principal del objeto en la sexta posición
     *  */
    public String[] obtenerDatosObjetos(int id) {
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

    /**
     * Se encarga de obtener un array con los campeones gratuitos de la semana
     * @return En cada fila encontraremos un campeón
     *  identificador del campeón en la columna 1
     *  Nombre del campeón en la columna 2
     *  Ruta principal de la imagen del campeón en la columna 3
     *
     */
    public String[][] obtenerGratuitos() {
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