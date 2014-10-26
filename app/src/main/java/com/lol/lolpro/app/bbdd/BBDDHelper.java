package com.lol.lolpro.app.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.utillidades.Utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que se encarga de la gestión de la base de datos
 */
public class BBDDHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mDatabase;
    private SQLiteDatabase mReadOnlyDatabase;

    private Context contexto;

    /**
     * Constructor que inicializa la base de datos
     *
     * @param context Contiene el activity principal
     */
    public BBDDHelper(Context context) {
        super(context, context.getResources().getString(R.string.app_name), null, 1);
        contexto = context;
    }

    /**
     * Se encarga de crear las tablas de la base de datos
     *
     * @param db Base de datos en la que se crearán las tablas
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE campeones (" +
                "_id INTEGER PRIMARY KEY, key TEXT, nombre TEXT, nick TEXT, historia TEXT, vida TEXT, " +
                "vidaPorNivel TEXT, regeneracionVida TEXT, regeneracionVidaPorNivel TEXT, " +
                "danioAtaque TEXT, danioAtaquePorNivel TEXT, armadura TEXT, armaduraPorNivel TEXT, " +
                "velocidadAtaque TEXT, velocidadAtaquePorNivel TEXT, crit TEXT, critPorNivel TEXT, " +
                "tipoMP TEXT, mana TEXT, manaPorNivel TEXT, regMana TEXT, regManaPorNivel TEXT, " +
                "resistenciaMagica TEXT, resistenciaMagicaPorNivel TEXT, " +
                "velocidadMovimiento TEXT, rutaPrincipal TEXT, esGratis INTEGER)");
        db.execSQL("CREATE TABLE aspectos (" +
                "_id INTEGER PRIMARY KEY, idCampeon INTEGER, nombre TEXT, num INTEGER," +
                "rutaPrincipal TEXT, FOREIGN KEY(idCampeon) REFERENCES campeones(_id))");
        db.execSQL("CREATE TABLE habilidades (" +
                "idCampeon INTEGER, nombre TEXT, descripcion TEXT, tooltip TEXT, coste TEXT," +
                "alcance TEXT, rutaPrincipal TEXT, enfriamiento TEXT, posicion INTEGER, esPasiva INTEGER, esNueva INTEGER, FOREIGN KEY(idCampeon) REFERENCES campeones(_id), PRIMARY KEY (idCampeon, nombre))");
        db.execSQL("CREATE TABLE objetos (" +
                "_id INTEGER PRIMARY KEY" +
                ", name TEXT, base INTEGER, total INTEGER, sell INTEGER, purchasable INTEGER," +
                " description TEXT, plainText TEXT, stacks INTEGER, depth INTEGER, fromOBJ TEXT, intoOBJ TEXT," +
                " hideFromAll INTEGER, requiredChampion INTEGER, full TEXT, FOREIGN KEY(requiredChampion) REFERENCES campeones(_id))");
        //tags TEXT, maps TEXT,
        db.execSQL("CREATE TABLE tagsCampeones (" +
                "idCampeon INTEGER, nombreTag TEXT, FOREIGN KEY(idCampeon) REFERENCES campeones(_id))");
        db.execSQL("CREATE TABLE tagsObjetos (" +
                "idObjeto INTEGER, nombreTag TEXT, hyperTag TEXT, FOREIGN KEY(idObjeto) REFERENCES objetos(_id), PRIMARY KEY (idObjeto, nombreTag))");
        db.execSQL("CREATE TABLE mapas (" +
                "_id INTEGER PRIMARY KEY, nombre TEXT)");
        db.execSQL("CREATE TABLE rutaVersiones (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", ruta TEXT, versionCampeones TEXT, versionObjetos TEXT)");
    }

    /**
     * Método sobreescrito que se encarga del cambio de versión al actualizar las tablas
     *
     * @param db         Base de datos en la que se actualizarán las tablas
     * @param oldVersion versión antigua
     * @param newVersion versión nueva a actualizar
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
        // Mirar ejemplo: https://code.google.com/p/openintents/source/browse/trunk/notepad/NotePad/src/org/openintents/notepad/NotePadProvider.java?r=3878
    }

    @Override
    public void onOpen (SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()){
            //Enable foreign key contraints
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    public void openDatabase(boolean writeMode) {
        // Opening new database
        if (writeMode) {
            mDatabase = getWritableDatabase();
        } else {
            mReadOnlyDatabase = getReadableDatabase();
        }

    }

    public void closeDatabase(boolean writeMode) {
        // Closing database
        if (writeMode) {
            mDatabase.close();
        } else {
            mReadOnlyDatabase.close();
        }
    }

    /**
     * Se encarga de actualizar los datos de un campeón si ha sufrido cambios debido a un cambio de versión
     *
     * @param id                  Identificador único de cada campeón
     * @param nombre              Nombre del campeón
     * @param nick                Nick del campeón
     * @param historia            CampeonHistoria del campeón
     * @param vida                Vida del campeón
     * @param regeneracionVida    Regeneración de vida por segundo del campeón
     * @param danioAtaque         Daño de ataque del campeón
     * @param armadura            Resistencia de ataques físicos del campeón
     * @param velocidadAtaque     Velocidad de ataque por segundo del campeón
     * @param resistenciaMagica   Resistencia a habilidaddes o poder mágico del campeón
     * @param velocidadMovimiento Número de unidades que se desplaza el acmpeón por segundo
     * @param rutaPrincipal       Ruta en la que se encuentra la imagen principal del campeón
     */
    public void insertarCampeon(int id, String key, String nombre, String nick, String historia, String vida,
                                String vidaPorNivel, String regeneracionVida,
                                String regeneracionVidaPorNivel, String danioAtaque,
                                String danioAtaquePorNivel, String armadura,
                                String armaduraPorNivel, String velocidadAtaque,
                                String velocidadAtaquePorNivel, String crit, String critPorNivel,
                                String tipoMP, String mana, String manaPorNivel, String regMana,
                                String regManaPorNivel, String resistenciaMagica,
                                String resistenciaMagicaPorNivel, String velocidadMovimiento,
                                String rutaPrincipal, boolean actualizarBBDD) {
        double velAtaque = Double.parseDouble(velocidadAtaque);
        velAtaque = 1 / (1.6 * (1 + velAtaque));
        velAtaque = Math.rint(velAtaque * 1000) / 1000;

        ContentValues cont = new ContentValues();
        cont.put("key", TextUtils.htmlEncode(key));
        cont.put("nombre", TextUtils.htmlEncode(nombre));
        cont.put("nick", TextUtils.htmlEncode(nick));
        cont.put("historia", TextUtils.htmlEncode(historia));
        cont.put("vida", TextUtils.htmlEncode(vida));
        cont.put("vidaPorNivel", TextUtils.htmlEncode(vidaPorNivel));
        cont.put("regeneracionVida", TextUtils.htmlEncode(regeneracionVida));
        cont.put("regeneracionVidaPorNivel", TextUtils.htmlEncode(regeneracionVidaPorNivel));
        cont.put("danioAtaque", TextUtils.htmlEncode(danioAtaque));
        cont.put("danioAtaquePorNivel", TextUtils.htmlEncode(danioAtaquePorNivel));
        cont.put("armadura", TextUtils.htmlEncode(armadura));
        cont.put("armaduraPorNivel", TextUtils.htmlEncode(armaduraPorNivel));
        cont.put("velocidadAtaque", velAtaque);
        cont.put("velocidadAtaquePorNivel", TextUtils.htmlEncode(velocidadAtaquePorNivel));
        cont.put("crit", TextUtils.htmlEncode(crit));
        cont.put("critPorNivel", TextUtils.htmlEncode(critPorNivel));
        cont.put("tipoMP", TextUtils.htmlEncode(tipoMP));
        cont.put("mana", TextUtils.htmlEncode(mana));
        cont.put("manaPorNivel", TextUtils.htmlEncode(manaPorNivel));
        cont.put("regMana", TextUtils.htmlEncode(regMana));
        cont.put("regManaPorNivel", TextUtils.htmlEncode(regManaPorNivel));
        cont.put("resistenciaMagica", TextUtils.htmlEncode(resistenciaMagica));
        cont.put("resistenciaMagicaPorNivel", TextUtils.htmlEncode(resistenciaMagicaPorNivel));
        cont.put("velocidadMovimiento", TextUtils.htmlEncode(velocidadMovimiento));
        cont.put("rutaPrincipal", TextUtils.htmlEncode(rutaPrincipal));
        cont.put("esGratis", 0);
        String[] whereArgs = new String[]{Integer.toString(id)};
        if(!actualizarBBDD || mDatabase.update("campeones", cont, "_id=?", whereArgs) == 0) {
            cont.put("_id", id);
            mDatabase.insert("campeones", null, cont);
        }
    }

    public void insertarAspectoCampeon(int idAspecto, int idCampeon, String nombre, int numero, String rutaPrincipal, boolean actualizarBBDD) {
        ContentValues cont = new ContentValues();
        cont.put("idCampeon", idCampeon);
        cont.put("nombre", TextUtils.htmlEncode(nombre));
        cont.put("num", numero);
        cont.put("rutaPrincipal", TextUtils.htmlEncode(rutaPrincipal));
        String[] whereArgs = new String[]{Integer.toString(idAspecto)};
        if(!actualizarBBDD || mDatabase.update("aspectos", cont, "_id=?", whereArgs) == 0) {
            cont.put("_id", idAspecto);
            mDatabase.insert("aspectos", null, cont);
        }
    }

    public void insertarHabilidadCampeon(int idCampeon, String nombre, String descripcion, String tooltip, String coste,
                                         String alcance, String rutaPrincipal, String enfriamiento, int posicion, int esPasiva, boolean actualizarBBDD) {
        ContentValues cont = new ContentValues();
        cont.put("descripcion", TextUtils.htmlEncode(descripcion));
        cont.put("tooltip", TextUtils.htmlEncode(tooltip));
        cont.put("coste", TextUtils.htmlEncode(coste));
        cont.put("alcance", TextUtils.htmlEncode(alcance));
        cont.put("rutaPrincipal", TextUtils.htmlEncode(rutaPrincipal));
        cont.put("enfriamiento", TextUtils.htmlEncode(enfriamiento));
        cont.put("esPasiva", esPasiva);
        cont.put("posicion", posicion);
        cont.put ("esNueva", 1);
        String[] whereArgs = new String[]{Integer.toString(idCampeon), TextUtils.htmlEncode(nombre)};
        if (!actualizarBBDD || mDatabase.update("habilidades", cont, "idCampeon=? AND nombre=? ", whereArgs) == 0) {
            cont.put("idCampeon", idCampeon);
            cont.put("nombre", TextUtils.htmlEncode(nombre));
            mDatabase.insert("habilidades", null, cont);
        }
    }

    public void borrarHabilidadesDesfasadas() {
        mDatabase.delete("habilidades", "esNueva=0", null);
        ContentValues cont = new ContentValues();
        cont.put ("esNueva", 0);
        mDatabase.update("habilidades", cont, "esNueva=1",null);
    }

    public void insertarObjeto(int id, String name, int base, int total, int sell, String purchasable,
                               String description, String plainText, String stacks, String depth, String fromOBJ,
                               String intoOBJ, String hideFromAll, String requiredChampion, String full, boolean actualizarBBDD){
        int purch= Boolean.parseBoolean(purchasable) ? 1 : 0;
        String pText = plainText == null ? "" : plainText;
        int stack = stacks == null ? 1 : Integer.parseInt(stacks);
        int dept = depth == null  ? 1 : Integer.parseInt(depth);
        String from = fromOBJ == null ? "" : Utils.clearQuotes(fromOBJ);
        String into = intoOBJ == null ? "" : Utils.clearQuotes(intoOBJ);
        int hide = (hideFromAll == null || !Boolean.parseBoolean(hideFromAll)) ? 0 : 1;
        ContentValues cont = new ContentValues();
        cont.put("name", TextUtils.htmlEncode(name));
        cont.put("base", base);
        cont.put("total", total);
        cont.put("sell", sell);
        cont.put("purchasable", purch);
        cont.put("description", TextUtils.htmlEncode(description));
        cont.put("plainText", TextUtils.htmlEncode(pText));
        cont.put("stacks", stack);
        cont.put("depth", dept);
        cont.put("fromOBJ", TextUtils.htmlEncode(from));
        cont.put("intoOBJ", TextUtils.htmlEncode(into));
        cont.put("hideFromAll", hide);
        if (requiredChampion==null) {
            cont.putNull("requiredChampion");
        }
        else{
            cont.put("requiredChampion", obtenerIDCampeon (requiredChampion));
        }
        cont.put("full", TextUtils.htmlEncode(full));
        String[] whereArgs = new String[]{Integer.toString(id)};
        if(!actualizarBBDD || mDatabase.update("objetos", cont, "_id=?", whereArgs) == 0) {
            cont.put("_id", id);
            mDatabase.insert("objetos", null, cont);
        }
    }

    public void insertarTagObjeto(int idObjeto, String nombreTag, String hyperTag){
        ContentValues cont = new ContentValues();
        cont.put("idObjeto", idObjeto);
        cont.put("nombreTag", TextUtils.htmlEncode(nombreTag));
        cont.put("hyperTag", TextUtils.htmlEncode(hyperTag));
        mDatabase.insert("tagsObjetos", null, cont);
    }

    public void borrarTagsObjeto(int idObjeto){
        String[] whereArgs = new String[]{Integer.toString(idObjeto)};
        mDatabase.delete("tagsObjetos", "idObjeto=?", whereArgs);
    }

   /**
     * Almacena en la base de datos la última url y la última versión de los campeones y lso objetos
     *
     * @param ruta     última ruta conocida para las imágenes de campeones y objetos
     * @param vCampeon última versión concoida para los campeones
     * @param vObjeto  última versión conocida para los objetos
     */
    public void guardarRutaVersiones(String ruta, String vCampeon, String vObjeto) {
        mDatabase.delete("rutaVersiones", null, null);
        ContentValues cont = new ContentValues();
        cont.putNull("_id");
        cont.put("ruta", TextUtils.htmlEncode(ruta));
        cont.put("versionCampeones", TextUtils.htmlEncode(vCampeon));
        cont.put("versionObjetos", TextUtils.htmlEncode(vObjeto));
        mDatabase.insert("rutaVersiones", null, cont);
    }

    /**
     * Se encarga de poner los campeones cuyos ids se encuentran entre los dados en los parámetros como gratuitos
     * y marcar los antiguos campeones gratuitos como no gratuitos
     *
     * @param ids Contiene los ids de los campeones gratuitos esa semana
     */
    public void updateFreeChamps(ArrayList<Integer> ids) {
        ContentValues cont = new ContentValues();
        cont.put("esGratis", 0);
        String[] whereArgs = new String[]{Integer.toString(1)};
        mDatabase.update("campeones", cont, "esGratis=?", whereArgs);
        Iterator<Integer> it = ids.iterator();
        cont = new ContentValues();
        cont.put("esGratis", 1);
        while (it.hasNext()){
            whereArgs = new String[]{Integer.toString(it.next())};
            mDatabase.update("campeones", cont, "_id=?", whereArgs);
        }
    }

    //TODO CHECK RIOT
    public void aniadirMapas(){
        mDatabase.execSQL("INSERT INTO mapas VALUES (1, '" + TextUtils.htmlEncode(contexto.getResources().getString(R.string.mapa_1)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (8, '" + TextUtils.htmlEncode(contexto.getResources().getString(R.string.mapa_8)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (10, '" + TextUtils.htmlEncode(contexto.getResources().getString(R.string.mapa_10)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (12, '" + TextUtils.htmlEncode(contexto.getResources().getString(R.string.mapa_12)) + "')");
    }


    public String obtenerIDCampeon(String name) {
        String[] columns = new String[]{"_id"};
        String[] whereArgs = new String[]{name};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, "nombre LIKE ?", whereArgs, null, null, null);

        String result = "null";
        if (cursor.moveToNext()) {
            result = Integer.toString(cursor.getInt(0));
        }
        cursor.close();
        return result;
    }
    /**
     * Se encarga de obtener el identificador, el nombre y la ruta completa de la imagen principal de todos los campeones
     *
     * @return Array de String en el en que en cada fila encontraremos un campeón. Además:
     * identificador del campeón en la primera columna
     * Nombre del campeón en la segunda columna
     * Ruta de la imagen principal del campeón en la tercera columna
     */
    public String[][] obtenerNombreRutaCampeones() {
        String[] columns = new String[]{"_id", "nombre", "rutaPrincipal"};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, null, null, null, null, "nombre");
        String[][] result = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result[pos][pos2++] = Integer.toString(cursor.getInt(0));
            result[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result[pos][pos2] = Html.fromHtml(cursor.getString(2)).toString();
            pos2 = 0;
            pos++;
        }
        cursor.close();
        return result;
    }

    public String[][] obtenerNombreRutaCampeon(int id) {
        String[] columns = new String[]{"nombre", "rutaPrincipal"};
        String[] whereArgs = new String[]{Integer.toString(id)};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, "_id=?", whereArgs, null, null, null);
        String[][] result = new String[cursor.getCount()][cursor.getColumnCount()+1];
        int pos = 0;
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result[pos][pos2++] = Integer.toString(id);
            result[pos][pos2++] =  Html.fromHtml(cursor.getString(0)).toString();
            result[pos][pos2] = Html.fromHtml(cursor.getString(1)).toString();
        }
        cursor.close();
        return result;
    }

    /**
     * Se encarga de obtener el identificador, el nombre y la ruta completa de la imagen principal de todos los objetos
     *
     * @return Array de String en el en que en cada fila encontraremos un objeto. Además:
     * identificador del objeto en la primera columna
     * Nombre del objeto en la segunda columna
     * Ruta de la imagen principal del objeto en la tercera columna
     */
    public String[][] obtenerNombreRutaObjetos() {
        String[] columns = new String[]{"_id", "name", "full"};
        String[] whereArgs = new String[]{"0"};
        Cursor cursor = mReadOnlyDatabase.query("objetos", columns, "hideFromAll=?", whereArgs, null, null, "name");
        String[][] result = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result[pos][pos2++] = Integer.toString(cursor.getInt(0));
            result[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result[pos][pos2] = Html.fromHtml(cursor.getString(2)).toString();
            pos2 = 0;
            pos++;
        }
        cursor.close();
        return result;
    }

    public String[][] obtenerNombreRutaObjetos(String[] ids) {
        String idsString="";
        for (int j = 0; j < ids.length-1; j++) {
            idsString += "_id=" + ids[j] + " OR ";
        }
        idsString += "_id=" + ids[ids.length-1];
        String[] columns = new String[]{"_id", "name", "full"};
        Cursor cursor = mReadOnlyDatabase.query("objetos", columns, "(" + idsString + ")", null, null, null, null);
        String[][] result = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result[pos][pos2++] = Integer.toString(cursor.getInt(0));
            result[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result[pos][pos2] = Html.fromHtml(cursor.getString(2)).toString();
            pos2 = 0;
            pos++;
        }
        cursor.close();
        return result;
    }

    /**
     * Se encarga de obtener la última ruta y versión de los campeones
     *
     * @return String con la ruta compuesta por la última ruta conocida y la última versión conocida
     */
    public String obtenerRutaVersionCampeon() {
        String[] columns = new String[]{"ruta", "versionCampeones"};
        Cursor cursor = mReadOnlyDatabase.query("rutaVersiones", columns, null, null, null, null, null);
        String result = "";
        if (cursor.moveToNext()) {
            result += Html.fromHtml(cursor.getString(0)).toString() + "/";
            result += Html.fromHtml(cursor.getString(1)).toString() + "/img/champion/";
        }
        cursor.close();
        return result;
    }

    public String obtenerRutaAspectosCampeon() {
        String[] columns = new String[]{"ruta"};
        Cursor cursor = mReadOnlyDatabase.query("rutaVersiones", columns, null, null, null, null, null);
        String result = "";
        if (cursor.moveToNext()) {
            result += Html.fromHtml(cursor.getString(0)).toString() + "/img/champion/splash/";
        }
        cursor.close();
        return result;
    }

    public String obtenerRutaHabilidadesCampeon(int esPasiva) {
        String[] columns = new String[]{"ruta", "versionCampeones"};
        Cursor cursor = mReadOnlyDatabase.query("rutaVersiones", columns, null, null, null, null, null);
        String result = "";
        if (cursor.moveToNext()) {
            if (esPasiva== Constants.PASSIVE_YES) {
                result += Html.fromHtml(cursor.getString(0)).toString() + "/" + Html.fromHtml(cursor.getString(1)).toString() + "/img/passive/";
            }
           else {
                result += Html.fromHtml(cursor.getString(0)).toString() + "/" + Html.fromHtml(cursor.getString(1)).toString() + "/img/spell/";
            }
        }
        cursor.close();
        return result;
    }

    /**
     * Se encarga de obtener la última versión de los campeones
     *
     * @return String con la última versión conocida
     */
    public String obtenerVersionCampeon() {
        String[] columns = new String[]{"versionCampeones"};
        Cursor cursor = mReadOnlyDatabase.query("rutaVersiones", columns, null, null, null, null, null);
        String result = "";
        if (cursor.moveToNext()) {
            result += Html.fromHtml(cursor.getString(0)).toString();
        }
        cursor.close();
        return result;
    }


    /**
     * Se encarga de obtener la última ruta y versión de los objetos
     *
     * @return String con la ruta compuesta por la última ruta conocida y la última versión conocida
     */
    public String obtenerRutaVersionObjeto() {
        String[] columns = new String[]{"ruta", "versionObjetos"};
        Cursor cursor = mReadOnlyDatabase.query("rutaVersiones", columns, null, null, null, null, null);
        String result = "";
        if (cursor.moveToNext()) {
            result += Html.fromHtml(cursor.getString(0)).toString() + "/";
            result += Html.fromHtml(cursor.getString(1)).toString() + "/img/item/";
        }
        cursor.close();
        return result;
    }

    /**
     * Se encarga de obtener la última versión de los objetos
     *
     * @return String con la última versión conocida
     */
    public String obtenerVersionObjeto() {
        String[] columns = new String[]{"versionObjetos"};
        Cursor cursor = mReadOnlyDatabase.query("rutaVersiones", columns, null, null, null, null, null);
        String result = "";
        if (cursor.moveToNext()) {
            result += Html.fromHtml(cursor.getString(0)).toString();
        }
        cursor.close();
        return result;
    }

    /**
     * Se encarga de obtener todos los datos de un campeón para un identificador dado
     *
     * @param id identificador único del campeón a buscar
     * @return Array con:
     * Nombre en la primera posición
     * Nick en la segunda posición
     * CampeonHistoria en la tercera posición
     * Regeneración de vida en la cuarta posición
     * Daño de ataque en la quinta posición
     * Armadura en la sexta posición
     * Velocidad de ataque en la septima posición
     * Resistencia mágica en la octava posición
     * Velocidad de movimiento en la novena posición
     * Ruta de la imagen principal en la decima posición
     */
    public String[] obtenerDatosCampeon(int id) {
        String[] columns = new String[]{"key", "nombre", "nick", "historia", "vida", "vidaPorNivel",
                "regeneracionVida", "regeneracionVidaPorNivel", "danioAtaque", "danioAtaquePorNivel",
                "armadura", "armaduraPorNivel", "velocidadAtaque", "velocidadAtaquePorNivel", "crit",
                "critPorNivel", "tipoMP", "mana", "manaPorNivel", "regMana", "regManaPorNivel", "resistenciaMagica",
                "resistenciaMagicaPorNivel", "velocidadMovimiento", "rutaPrincipal"};
        String[] whereArgs = new String[]{Integer.toString(id)};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, "_id=?", whereArgs, null, null, null);
        String[] result = new String[cursor.getColumnCount()];
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result[pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(2)).toString();
            result[pos2++] = Utils.sanitizeChampStory(Html.fromHtml(cursor.getString(3)).toString());
            result[pos2++] = Html.fromHtml(cursor.getString(4)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(5)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(6)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(7)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(8)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(9)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(10)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(11)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(12)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(13)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(14)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(15)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(16)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(17)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(18)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(19)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(20)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(21)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(22)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(23)).toString();
            result[pos2] = Html.fromHtml(cursor.getString(24)).toString();
        }
        cursor.close();
        return result;
    }

    public String[][] obtenerAspectosCampeon(int idCampeon) {
        String[] columns = new String[]{"_id", "nombre", "rutaPrincipal, num"};
        String[] whereArgs = new String[]{Integer.toString(idCampeon)};
        Cursor cursor = mReadOnlyDatabase.query("aspectos", columns, "idCampeon=?", whereArgs, null, null, null);
        String[][] result = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result[pos][pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result[pos][pos2++] = Html.fromHtml(cursor.getString(2)).toString();
            result[pos][pos2] = Html.fromHtml(cursor.getString(3)).toString();
            pos2 = 0;
            pos++;
        }
        return result;
    }

    public String[][] obtenerHabilidadesCampeon(int idCampeon) {
        String[] columns = new String[]{"nombre", "descripcion", "tooltip", "alcance", "coste",
                "enfriamiento", "rutaPrincipal", "esPasiva"};
        String[] whereArgs = new String[]{Integer.toString(idCampeon)};
        Cursor cursor = mReadOnlyDatabase.query("habilidades", columns, "idCampeon=?", whereArgs, null, null, "posicion");
        String[][] result = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result[pos][pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result[pos][pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(1)).toString());
            result[pos][pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(2)).toString());
            result[pos][pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(3)).toString());
            result[pos][pos2++] = Html.fromHtml(cursor.getString(4)).toString();
            result[pos][pos2++] = Html.fromHtml(cursor.getString(5)).toString();
            result[pos][pos2++] = Html.fromHtml(cursor.getString(6)).toString();
            result[pos][pos2] = Html.fromHtml(cursor.getString(7)).toString();
            pos2 = 0;
            pos++;
        }
        return result;
    }

    /**
     * Se encarga de obtener todos los datos de un objeto para un identificador dado
     *
     * @param id identificador único del objeto a buscar
     * @return Array con:
     * Nombre en la primera posición
     * coste base en la segunda posición
     * coste en la tercera posición
     * Descripción de vida en la cuarta posición
     * Entero que indica con uno que el objeto puede comprarse en la tienda y con 0 que no puede comprarse, en la quinta posición
     * Ruta de la imagen principal del objeto en la sexta posición
     */
    public String[] obtenerDatosObjetos(int id) {
        String[] columns = new String[]{"name", "base", "total", "sell", "purchasable",
                "description", "plainText", "stacks", "depth", "fromOBJ", "intoOBJ", "hideFromAll", "requiredChampion",
                "full"};
        String[] whereArgs = new String[]{Integer.toString(id)};
        Cursor cursor = mReadOnlyDatabase.query("objetos", columns, "_id=?", whereArgs, null, null, null);
        String[] result = new String[cursor.getColumnCount()];
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result[pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result[pos2++] = cursor.getString(1);
            result[pos2++] = cursor.getString(2);
            result[pos2++] = cursor.getString(3);
            result[pos2++] = cursor.getString(4);
            result[pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(5)).toString());
            result[pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(6)).toString());
            result[pos2++] = cursor.getString(7);
            result[pos2++] = cursor.getString(8);
            result[pos2++] = Html.fromHtml(cursor.getString(9)).toString();
            result[pos2++] = Html.fromHtml(cursor.getString(10)).toString();
            result[pos2++] = cursor.getString(11);
            result[pos2++] = cursor.getString(12);
            result[pos2] = Html.fromHtml(cursor.getString(13)).toString();
        }
        cursor.close();
        return result;
    }

    /**
     * Se encarga de obtener un array con los campeones gratuitos de la semana
     *
     * @return En cada fila encontraremos un campeón
     * identificador del campeón en la columna 1
     * Nombre del campeón en la columna 2
     * Ruta principal de la imagen del campeón en la columna 3
     */
    public String[][] obtenerGratuitos() {
        String[] columns = new String[]{"_id", "nombre", "rutaPrincipal"};
        String[] whereArgs = new String[]{"1"};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, "esGratis=?", whereArgs, null, null, "nombre");
        String[][] result = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result[pos][pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result[pos][pos2] = Html.fromHtml(cursor.getString(2)).toString();
            pos2 = 0;
            pos++;
        }
        cursor.close();
        return result;
    }
}