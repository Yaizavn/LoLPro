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
                "alcance TEXT, rutaPrincipal TEXT, enfriamiento TEXT, posicion INTEGER, esPasiva INTEGER, FOREIGN KEY(idCampeon) REFERENCES campeones(_id), PRIMARY KEY (idCampeon, nombre))");
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
     * Se encarga de guardar los datos de un campeón en la tabla campeones
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
    public void guardarCampeones(int id, String key, String nombre, String nick, String historia, String vida,
                                 String vidaPorNivel, String regeneracionVida,
                                 String regeneracionVidaPorNivel, String danioAtaque,
                                 String danioAtaquePorNivel, String armadura,
                                 String armaduraPorNivel, String velocidadAtaque,
                                 String velocidadAtaquePorNivel, String crit, String critPorNivel,
                                 String tipoMP, String mana, String manaPorNivel, String regMana,
                                 String regManaPorNivel, String resistenciaMagica,
                                 String resistenciaMagicaPorNivel, String velocidadMovimiento,
                                 String rutaPrincipal) {
        double velAtaque = Double.parseDouble(velocidadAtaque);
        velAtaque = 1 / (1.6 * (1 + velAtaque));
        //Redondeo de tres cifras
        velAtaque = Math.rint(velAtaque * 1000) / 1000;
        mDatabase.execSQL("INSERT INTO campeones VALUES (" + id + ", '" + TextUtils.htmlEncode(key) + "', '" + TextUtils.htmlEncode(nombre) + "', '" + TextUtils.htmlEncode(nick) + "','" +
                TextUtils.htmlEncode(historia) + "', '" + TextUtils.htmlEncode(vida) + "', '" + TextUtils.htmlEncode(vidaPorNivel) + "', '" + TextUtils.htmlEncode(regeneracionVida) + "', " +
                " '" + TextUtils.htmlEncode(regeneracionVidaPorNivel) + "', '" + TextUtils.htmlEncode(danioAtaque) + "', '" + TextUtils.htmlEncode(danioAtaquePorNivel) + "', " +
                " '" + TextUtils.htmlEncode(armadura) + "','" + TextUtils.htmlEncode(armaduraPorNivel) + "', '" + velAtaque + "', " +
                " '" + TextUtils.htmlEncode(velocidadAtaquePorNivel) + "', '" + TextUtils.htmlEncode(crit) + "', '" + TextUtils.htmlEncode(critPorNivel) + "', " +
                " '" + TextUtils.htmlEncode(tipoMP) + "', '" + TextUtils.htmlEncode(mana) + "', '" + TextUtils.htmlEncode(manaPorNivel) + "', '" + TextUtils.htmlEncode(regMana) + "', '" + TextUtils.htmlEncode(regManaPorNivel) + "'," +
                " '" + TextUtils.htmlEncode(resistenciaMagica) + "', '" + TextUtils.htmlEncode(resistenciaMagicaPorNivel) + "', " +
                " '" + TextUtils.htmlEncode(velocidadMovimiento) + "', '" + TextUtils.htmlEncode(rutaPrincipal) + "', 0)");
    }

    public void guardarAspectos(int id, int idCampeon, String nombre, int numero, String rutaPrincipal ) {
       try{
           mDatabase.execSQL("INSERT INTO aspectos VALUES (" + id + ", " + idCampeon + ", '" + TextUtils.htmlEncode(nombre) + "','" +
                   numero + "', '" + TextUtils.htmlEncode(rutaPrincipal) + "')");
       }catch (SQLiteException e){
           Log.e("Error foreign key", "Foreign key does not exist");
       }
    }

    public void guardarHabilidades(int idCampeon, String nombre, String descripcion, String tooltip, String coste,
                                   String alcance, String rutaPrincipal, String enfriamiento, int posicion, int esPasiva) {
        try{
            mDatabase.execSQL("INSERT INTO habilidades VALUES (" + idCampeon + ", '" + TextUtils.htmlEncode(nombre) + "','" +
                    TextUtils.htmlEncode(descripcion) + "', '" + TextUtils.htmlEncode(tooltip) + "', '" +
                    TextUtils.htmlEncode(coste) + "','" + TextUtils.htmlEncode(alcance) + "','" +
                    TextUtils.htmlEncode(rutaPrincipal) + "', '" + TextUtils.htmlEncode(enfriamiento) + "', " +
                    posicion + ", " + esPasiva + ")");
        }catch (SQLiteException e){
            Log.e("Error foreign key", "Foreign key does not exist");
        }
    }

    public void guardarObjetos(int id, String name, int base, int total, int sell, String purchasable,
                               String description, String plainText, String stacks, String depth, String fromOBJ,
                               String intoOBJ, String hideFromAll, String requiredChampion, String full) {
        int purch= Boolean.parseBoolean(purchasable) ? 1 : 0;
        String pText = plainText == null ? "" : plainText;
        int stack = stacks == null ? 1 : Integer.parseInt(stacks);
        int dept = depth == null  ? 1 : Integer.parseInt(depth);
        String from = fromOBJ == null ? "" : Utils.clearQuotes(fromOBJ);
        String into = intoOBJ == null ? "" : Utils.clearQuotes(intoOBJ);
        int hide = (hideFromAll == null || !Boolean.parseBoolean(hideFromAll)) ? 0 : 1;
        String reqChampion = requiredChampion == null ? "null" : obtenerIDCampeon (requiredChampion);
        mDatabase.execSQL("INSERT INTO objetos VALUES (" + id + ", '" + TextUtils.htmlEncode(name) + "', "
                + base + ", " + total + ", " + sell + ", " +  purch + ", '" + TextUtils.htmlEncode(description) + "', '"
                + TextUtils.htmlEncode(pText) + "', "+stack+", "+dept+", '" + TextUtils.htmlEncode(from) + "', '"
                + TextUtils.htmlEncode(into) + "', " + hide + ", " + reqChampion + ", '" + TextUtils.htmlEncode(full) + "')");
    }

    public void guardarTagObjeto(int idObjeto, String nombreTag, String hyperTag){
        mDatabase.execSQL("INSERT INTO tagsObjetos VALUES (" + idObjeto + ", '" + TextUtils.htmlEncode(nombreTag) + "','"
                + TextUtils.htmlEncode(hyperTag) + "')");
    }

    public String obtenerIDCampeon(String name) {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT _id FROM " +
                "campeones WHERE nombre LIKE '" + name + "'", null);
        String result = "null";
        if (cursor.moveToNext()) {
            result = String.valueOf(cursor.getInt(0));
        }
        cursor.close();
        return result;
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
    public void modificarCampeones(int id, String key, String nombre, String nick, String historia, String vida,
                                   String vidaPorNivel, String regeneracionVida,
                                   String regeneracionVidaPorNivel, String danioAtaque,
                                   String danioAtaquePorNivel, String armadura,
                                   String armaduraPorNivel, String velocidadAtaque,
                                   String velocidadAtaquePorNivel, String crit, String critPorNivel,
                                   String tipoMP, String mana, String manaPorNivel, String regMana,
                                   String regManaPorNivel, String resistenciaMagica,
                                   String resistenciaMagicaPorNivel, String velocidadMovimiento,
                                   String rutaPrincipal) {
        double velAtaque = Double.parseDouble(velocidadAtaque);
        velAtaque = 1 / (1.6 * (1 + velAtaque));
        velAtaque = Math.rint(velAtaque * 1000) / 1000;

        ContentValues cont = new ContentValues();
        cont.put("_id", id);
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
        mDatabase.insertWithOnConflict("campeones", null, cont, SQLiteDatabase.CONFLICT_REPLACE);

/*
        mDatabase.execSQL("INSERT OR REPLACE INTO campeones VALUES (" + id + ", '" + TextUtils.htmlEncode(key) + "', '" + TextUtils.htmlEncode(nombre) + "_aaaa', '" + TextUtils.htmlEncode(nick) + "','" +
                TextUtils.htmlEncode(historia) + "', '" + TextUtils.htmlEncode(vida) + "', '" + TextUtils.htmlEncode(vidaPorNivel) + "', '" + TextUtils.htmlEncode(regeneracionVida) + "', " +
                " '" + TextUtils.htmlEncode(regeneracionVidaPorNivel) + "', '" + TextUtils.htmlEncode(danioAtaque) + "', '" + TextUtils.htmlEncode(danioAtaquePorNivel) + "', " +
                " '" + TextUtils.htmlEncode(armadura) + "','" + TextUtils.htmlEncode(armaduraPorNivel) + "', '" + velAtaque + "', " +
                " '" + TextUtils.htmlEncode(velocidadAtaquePorNivel) + "', '" + TextUtils.htmlEncode(crit) + "', '" + TextUtils.htmlEncode(critPorNivel) + "', " +
                " '" + TextUtils.htmlEncode(tipoMP) + "', '" + TextUtils.htmlEncode(mana) + "', '" + TextUtils.htmlEncode(manaPorNivel) + "', '" + TextUtils.htmlEncode(regMana) + "', '" + TextUtils.htmlEncode(regManaPorNivel) + "'," +
                " '" + TextUtils.htmlEncode(resistenciaMagica) + "', '" + TextUtils.htmlEncode(resistenciaMagicaPorNivel) + "', " +
                " '" + TextUtils.htmlEncode(velocidadMovimiento) + "', '" + TextUtils.htmlEncode(rutaPrincipal) + "', 0)");
                */
    }

    public void modificarAspectosCampeon(int idAspecto, int idCampeon, String nombre, int numero, String rutaPrincipal) {

        ContentValues cont = new ContentValues();
        cont.put("_id", idAspecto);
        cont.put("idCampeon", idCampeon);
        cont.put("nombre", TextUtils.htmlEncode(nombre));
        cont.put("num", numero);
        cont.put("rutaPrincipal", TextUtils.htmlEncode(rutaPrincipal));
        mDatabase.insertWithOnConflict("aspectos", null, cont, SQLiteDatabase.CONFLICT_REPLACE);
        /*mDatabase.execSQL("UPDATE aspectos SET nombre='" + TextUtils.htmlEncode(nombre) + "', num=" + numero + ", " +
                "rutaPrincipal='" + TextUtils.htmlEncode(rutaPrincipal) + "' WHERE _id=" + idAspecto);*/
    }

    public void modificarHabilidadesCampeon(int idCampeon, String nombre, String descripcion, String tooltip, String coste,
                                            String alcance, String rutaPrincipal, String enfriamiento, int esPasiva, int posicion) {
        ContentValues cont = new ContentValues();
        cont.put("idCampeon", idCampeon);
        cont.put("nombre", TextUtils.htmlEncode(nombre));
        cont.put("descripcion", TextUtils.htmlEncode(descripcion));
        cont.put("tooltip", TextUtils.htmlEncode(tooltip));
        cont.put("coste", TextUtils.htmlEncode(coste));
        cont.put("alcance", TextUtils.htmlEncode(alcance));
        cont.put("rutaPrincipal", TextUtils.htmlEncode(rutaPrincipal));
        cont.put("enfriamiento", TextUtils.htmlEncode(enfriamiento));
        cont.put("esPasiva", esPasiva);
        cont.put("posicion", posicion);
        mDatabase.insertWithOnConflict("habilidades", null, cont, SQLiteDatabase.CONFLICT_REPLACE);
        /*mDatabase.execSQL("UPDATE habilidades SET descripcion='" + TextUtils.htmlEncode(descripcion) + "', " +
                "tooltip='" + TextUtils.htmlEncode(tooltip) + "'," +
                "coste='" + TextUtils.htmlEncode(coste) + "', alcance='" + TextUtils.htmlEncode(alcance) + "', enfriamiento='" + TextUtils.htmlEncode(enfriamiento) + "', rutaPrincipal='" + TextUtils.htmlEncode(rutaPrincipal) + "'," +
                " posicion="+posicion+", esPasiva=" + esPasiva + " WHERE idCampeon=" + idCampeon + " && nombre='" + TextUtils.htmlEncode(nombre) + "'");*/
    }

    public void modificarObjetos(int id, String name, int base, int total, int sell, String purchasable,
                                 String description, String plainText, String stacks, String depth, String fromOBJ,
                                 String intoOBJ, String hideFromAll, String requiredChampion, String full) {
        int purch= Boolean.parseBoolean(purchasable) ? 1 : 0;
        String pText = plainText == null ? "" : plainText;
        int stack = stacks == null ? 1 : Integer.parseInt(stacks);
        int dept = depth == null  ? 1 : Integer.parseInt(depth);
        String from = fromOBJ == null ? "" : Utils.clearQuotes(fromOBJ);
        String into = intoOBJ == null ? "" : Utils.clearQuotes(intoOBJ);
        int hide = (hideFromAll == null || !Boolean.parseBoolean(hideFromAll)) ? 0 : 1;
        ContentValues cont = new ContentValues();
        cont.put("_id", id);
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
        mDatabase.insertWithOnConflict("objetos", null, cont, SQLiteDatabase.CONFLICT_REPLACE);

        /*mDatabase.execSQL("UPDATE objetos SET name='" + TextUtils.htmlEncode(name) + "', base=" + base + ", " +
                "total=" + total + ", sell=" + sell + ", purchasable=" + purch + "," +
                "description='" +TextUtils.htmlEncode(description) + "', plainText='" + TextUtils.htmlEncode(pText) + "', " +
                "stacks=" + stack + ", depth=" + dept + ", fromOBJ='" + TextUtils.htmlEncode(from) + "', " +
                "intoOBJ='" + TextUtils.htmlEncode(into) + "', hideFromAll=" + hide + ", " +
                "requiredChampion=" + reqChampion + ", full='" + TextUtils.htmlEncode(full) +
                "' WHERE _id=" + id);*/
    }

    /**
     * Almacena en la base de datos la última url y la última versión de los campeones y lso objetos
     *
     * @param ruta     última ruta conocida para las imágenes de campeones y objetos
     * @param vCampeon última versión concoida para los campeones
     * @param vObjeto  última versión conocida para los objetos
     */
    public void guardarRutaVersiones(String ruta, String vCampeon, String vObjeto) {
        mDatabase.execSQL("DELETE FROM rutaVersiones");
        mDatabase.execSQL("INSERT INTO rutaVersiones VALUES (null, '" + TextUtils.htmlEncode(ruta) + "','" + TextUtils.htmlEncode(vCampeon) + "', '" + TextUtils.htmlEncode(vObjeto) + "')");
    }

    /**
     * Se encarga de poner los campeones cuyos ids se encuentran entre los dados en los parámetros como gratuitos
     * y marcar los antiguos campeones gratuitos como no gratuitos
     *
     * @param ids Contiene los ids de los campeones gratuitos esa semana
     */
    public void modificarGratuito(ArrayList<Integer> ids) {
        mDatabase.execSQL("UPDATE campeones SET esGratis=0 WHERE esGratis=1");
        Iterator<Integer> it = ids.iterator();
        while (it.hasNext()){
            mDatabase.execSQL("UPDATE campeones SET esGratis=1 WHERE _id=" + it.next());
        }
    }

    //TO CHECK RIOT
    public void aniadirMapas(){
        mDatabase.execSQL("INSERT INTO mapas VALUES (1, '" + TextUtils.htmlEncode(contexto.getResources().getString(R.string.mapa_1)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (8, '" + TextUtils.htmlEncode(contexto.getResources().getString(R.string.mapa_8)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (10, '" + TextUtils.htmlEncode(contexto.getResources().getString(R.string.mapa_10)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (12, '" + TextUtils.htmlEncode(contexto.getResources().getString(R.string.mapa_12)) + "')");
    }

    /**
     * Se encarga de obtener el identificador, el nombre y la ruta completa de la imagen principal de todos los campeones
     *
     * @return Array de String en el en que en cada fila encontraremos un campeón. Además:
     * identificador del campeón en la primera columna
     * Nombre del campeón en la segunda columna
     * Ruta de la imagen principal del campeón en la tercera columna
     */
    public String[][] obtenerRutaCampeones() {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT _id, nombre, rutaPrincipal FROM " +
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
        return result2;
    }

    public String[][] obtenerRutaCampeones(int id) {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT nombre, rutaPrincipal FROM " +
                "campeones WHERE _id="+id, null);
        String[][] result2 = new String[cursor.getCount()][cursor.getColumnCount()+1];
        int pos = 0;
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result2[pos][pos2++] = Integer.toString(id);
            result2[pos][pos2++] =  Html.fromHtml(cursor.getString(0)).toString();
            result2[pos][pos2] = Html.fromHtml(cursor.getString(1)).toString();
        }
        cursor.close();
        return result2;
    }

    /**
     * Se encarga de obtener el identificador, el nombre y la ruta completa de la imagen principal de todos los objetos
     *
     * @return Array de String en el en que en cada fila encontraremos un objeto. Además:
     * identificador del objeto en la primera columna
     * Nombre del objeto en la segunda columna
     * Ruta de la imagen principal del objeto en la tercera columna
     */
    public String[][] obtenerRutaObjetos() {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT _id, name, full FROM " +
                "objetos WHERE hideFromAll == 0 ORDER BY name", null);
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
        return result2;
    }

    public String[][] obtenerRutaObjetos(String[] ids) {
        String idsString="";
        for (int j=0; j< ids.length-1; j++) {
            idsString += "_id=" + ids[j] + " OR ";
        }
        idsString += "_id=" + ids[ids.length-1];
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT _id, name, full FROM " +
                "objetos WHERE (" + idsString + ")", null);
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
        return result2;
    }

    /**
     * Se encarga de obtener la última ruta y versión de los campeones
     *
     * @return String con la ruta compuesta por la última ruta conocida y la última versión conocida
     */
    public String obtenerRutaVersionCampeon() {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT ruta, versionCampeones FROM " +
                "rutaVersiones", null);
        String result2 = "";
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString() + "/";
            result2 += Html.fromHtml(cursor.getString(1)).toString() + "/img/champion/";
        }
        cursor.close();
        return result2;
    }

    public String obtenerRutaAspectosCampeon() {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT ruta FROM " +
                "rutaVersiones", null);
        String result2 = "";
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString() + "/img/champion/splash/";
        }
        cursor.close();
        return result2;
    }

    public String obtenerRutaHabilidadesCampeon(int esPasiva) {
        Cursor cursor;
        cursor = mReadOnlyDatabase.rawQuery("SELECT ruta, versionCampeones FROM " +
                "rutaVersiones", null);
        String result2 = "";
        if (cursor.moveToNext()) {
            if (esPasiva==1) {
                result2 += Html.fromHtml(cursor.getString(0)).toString() + "/" + Html.fromHtml(cursor.getString(1)).toString() + "/img/passive/";
            }
           else {
                result2 += Html.fromHtml(cursor.getString(0)).toString() + "/" + Html.fromHtml(cursor.getString(1)).toString() + "/img/spell/";
            }
        }
        cursor.close();
        return result2;
    }

    /**
     * Se encarga de obtener la última versión de los campeones
     *
     * @return String con la última versión conocida
     */
    public String obtenerVersionCampeon() {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT versionCampeones FROM " +
                "rutaVersiones", null);
        String result2 = "";
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString();
        }
        cursor.close();
        return result2;
    }


    /**
     * Se encarga de obtener la última ruta y versión de los objetos
     *
     * @return String con la ruta compuesta por la última ruta conocida y la última versión conocida
     */
    public String obtenerRutaVersionObjeto() {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT ruta, versionObjetos FROM " +
                "rutaVersiones", null);
        String result2 = "";
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString() + "/";
            result2 += Html.fromHtml(cursor.getString(1)).toString() + "/img/item/";
        }
        cursor.close();
        return result2;
    }

    /**
     * Se encarga de obtener la última versión de los objetos
     *
     * @return String con la última versión conocida
     */
    public String obtenerVersionObjeto() {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT versionObjetos FROM " +
                "rutaVersiones", null);
        String result2 = "";
        if (cursor.moveToNext()) {
            result2 += Html.fromHtml(cursor.getString(0)).toString();
        }
        cursor.close();
        return result2;
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
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT key, nombre, nick, historia, vida, vidaPorNivel, " +
                "regeneracionVida, regeneracionVidaPorNivel, danioAtaque, danioAtaquePorNivel, " +
                "armadura, armaduraPorNivel, velocidadAtaque, velocidadAtaquePorNivel, crit, " +
                "critPorNivel, tipoMP, mana, manaPorNivel, regMana, regManaPorNivel, resistenciaMagica, " +
                "resistenciaMagicaPorNivel, velocidadMovimiento, rutaPrincipal " +
                "FROM campeones WHERE _id=" + id, null);
        String[] result2 = new String[cursor.getColumnCount()];
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result2[pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(2)).toString();
            result2[pos2++] = Utils.sanitizeChampStory(Html.fromHtml(cursor.getString(3)).toString());
            result2[pos2++] = Html.fromHtml(cursor.getString(4)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(5)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(6)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(7)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(8)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(9)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(10)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(11)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(12)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(13)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(14)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(15)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(16)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(17)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(18)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(19)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(20)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(21)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(22)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(23)).toString();
            result2[pos2] = Html.fromHtml(cursor.getString(24)).toString();
        }
        cursor.close();
        return result2;
    }

    public String[][] obtenerAspectosCampeon(int idCampeon) {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT _id, nombre, rutaPrincipal, num " +
               "FROM aspectos WHERE idCampeon=" + idCampeon, null);
        String[][] result4 = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result4[pos][pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result4[pos][pos2++] = Html.fromHtml(cursor.getString(1)).toString();
            result4[pos][pos2++] = Html.fromHtml(cursor.getString(2)).toString();
            result4[pos][pos2] = Html.fromHtml(cursor.getString(3)).toString();
            pos2 = 0;
            pos++;
        }
        return result4;
    }

    public String[][] obtenerHabilidadesCampeon(int idCampeon) {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT nombre, descripcion, tooltip, alcance, " +
                "coste, enfriamiento, rutaPrincipal, esPasiva " +
                "FROM habilidades WHERE idCampeon=" + idCampeon + " ORDER BY posicion", null);
        String[][] result4 = new String[cursor.getCount()][cursor.getColumnCount()];
        int pos = 0;
        int pos2 = 0;
        while (cursor.moveToNext()) {
            result4[pos][pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result4[pos][pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(1)).toString());
            result4[pos][pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(2)).toString());
            result4[pos][pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(3)).toString());
            result4[pos][pos2++] = Html.fromHtml(cursor.getString(4)).toString();
            result4[pos][pos2++] = Html.fromHtml(cursor.getString(5)).toString();
            result4[pos][pos2++] = Html.fromHtml(cursor.getString(6)).toString();
            result4[pos][pos2] = Html.fromHtml(cursor.getString(7)).toString();
            pos2 = 0;
            pos++;
        }
        return result4;
    }

    /**
     * Se encarga de obtener todos los datos de un objeto para un identificador dado
     *
     * @param id identificador único del objeto a buscar
     * @return Array con:
     * Nombre en la primera posición
     * Coste base en la segunda posición
     * Coste en la tercera posición
     * Descripción de vida en la cuarta posición
     * Entero que indica con uno que el objeto puede comprarse en la tienda y con 0 que no puede comprarse, en la quinta posición
     * Ruta de la imagen principal del objeto en la sexta posición
     */
    public String[] obtenerDatosObjetos(int id) {
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT name, base, total, sell," +
                "purchasable, description, plainText, stacks, depth, fromOBJ, intoOBJ, hideFromAll, " +
                "requiredChampion, full FROM objetos " +
                "WHERE _id=" + id, null);
        String[] result2 = new String[cursor.getColumnCount()];
        int pos2 = 0;
        if (cursor.moveToNext()) {
            result2[pos2++] = Html.fromHtml(cursor.getString(0)).toString();
            result2[pos2++] = cursor.getString(1);
            result2[pos2++] = cursor.getString(2);
            result2[pos2++] = cursor.getString(3);
            result2[pos2++] = cursor.getString(4);
            result2[pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(5)).toString());
            result2[pos2++] = Utils.sanitizeText(Html.fromHtml(cursor.getString(6)).toString());
            result2[pos2++] = cursor.getString(7);
            result2[pos2++] = cursor.getString(8);
            result2[pos2++] = Html.fromHtml(cursor.getString(9)).toString();
            result2[pos2++] = Html.fromHtml(cursor.getString(10)).toString();
            result2[pos2++] = cursor.getString(11);
            result2[pos2++] = cursor.getString(12);
            result2[pos2] = Html.fromHtml(cursor.getString(13)).toString();
        }
        cursor.close();
        return result2;
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
        Cursor cursor = mReadOnlyDatabase.rawQuery("SELECT _id, nombre, rutaPrincipal" +
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
        return result3;
    }
}