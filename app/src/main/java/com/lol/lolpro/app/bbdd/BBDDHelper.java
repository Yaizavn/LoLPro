package com.lol.lolpro.app.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Decorator.SkinDecorator;
import com.lol.lolpro.app.json.Campeones.Image;
import com.lol.lolpro.app.json.Campeones.Passive;
import com.lol.lolpro.app.json.Campeones.Skin;
import com.lol.lolpro.app.json.Campeones.Spell;
import com.lol.lolpro.app.json.Campeones.Stats;
import com.lol.lolpro.app.json.Campeones.Var;
import com.lol.lolpro.app.json.EstadoCampeones.BaseEstadoCampeones;
import com.lol.lolpro.app.json.EstadoCampeones.ChampionState;
import com.lol.lolpro.app.json.Objetos.Gold;
import com.lol.lolpro.app.json.Objetos.ImageItem;
import com.lol.lolpro.app.json.Objetos.Item;
import com.lol.lolpro.app.json.Realm.BaseRealm;
import com.lol.lolpro.app.json.Realm.BaseRealmDecorator;
import com.lol.lolpro.app.json.Realm.N;
import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.utillidades.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    public void insertarCampeon(Champion campeon, boolean actualizarBBDD){
        String rutaImagen = getBaseRealm().getRutaVersionCampeon();
        double velAtaque = campeon.getStats().getAttackspeedoffset();
        velAtaque = 1 / (1.6 * (1 + velAtaque));
        velAtaque = Math.rint(velAtaque * 1000) / 1000;

        ContentValues cont = new ContentValues();
        cont.put("key", Utils.htmlEncode(campeon.getKey()));
        cont.put("nombre", Utils.htmlEncode(campeon.getName()));
        cont.put("nick", Utils.htmlEncode(campeon.getTitle()));
        cont.put("historia", Utils.htmlEncode(campeon.getLore()));
        cont.put("vida", Utils.htmlEncode(campeon.getStats().getHp().toString()));
        cont.put("vidaPorNivel", Utils.htmlEncode(campeon.getStats().getHpperlevel().toString()));
        cont.put("regeneracionVida", Utils.htmlEncode(campeon.getStats().getHpregen().toString()));
        cont.put("regeneracionVidaPorNivel", Utils.htmlEncode(campeon.getStats().getHpregenperlevel().toString()));
        cont.put("danioAtaque", Utils.htmlEncode(campeon.getStats().getAttackdamage().toString()));
        cont.put("danioAtaquePorNivel", Utils.htmlEncode(campeon.getStats().getAttackdamageperlevel().toString()));
        cont.put("armadura", Utils.htmlEncode(campeon.getStats().getArmor().toString()));
        cont.put("armaduraPorNivel", Utils.htmlEncode(campeon.getStats().getArmorperlevel().toString()));
        cont.put("velocidadAtaque", velAtaque);
        cont.put("velocidadAtaquePorNivel", Utils.htmlEncode(campeon.getStats().getAttackspeedperlevel().toString()));
        cont.put("crit", Utils.htmlEncode(campeon.getStats().getCrit().toString()));
        cont.put("critPorNivel", Utils.htmlEncode(campeon.getStats().getCritperlevel().toString()));
        cont.put("tipoMP", Utils.htmlEncode(campeon.getPartype()));
        cont.put("mana", Utils.htmlEncode(campeon.getStats().getMp().toString()));
        cont.put("manaPorNivel", Utils.htmlEncode(campeon.getStats().getMpperlevel().toString()));
        cont.put("regMana", Utils.htmlEncode(campeon.getStats().getMpregen().toString()));
        cont.put("regManaPorNivel", Utils.htmlEncode(campeon.getStats().getMpregenperlevel().toString()));
        cont.put("resistenciaMagica", Utils.htmlEncode(campeon.getStats().getSpellblock().toString()));
        cont.put("resistenciaMagicaPorNivel", Utils.htmlEncode(campeon.getStats().getSpellblockperlevel().toString()));
        cont.put("velocidadMovimiento", Utils.htmlEncode(campeon.getStats().getMovespeed().toString()));
        cont.put("rutaPrincipal", Utils.htmlEncode(rutaImagen + campeon.getImage().getFull())); //Por delante añadir rutaImagen
        cont.put("esGratis", 0);
        String[] whereArgs = new String[]{Integer.toString(campeon.getId())};
        if(!actualizarBBDD || mDatabase.update("campeones", cont, "_id=?", whereArgs) == 0) {
            cont.put("_id", campeon.getId());
            mDatabase.insert("campeones", null, cont);
        }
        insertarHabilidadesCampeon (campeon, actualizarBBDD);
        insertarAspectosCampeon (campeon, actualizarBBDD);
        if (actualizarBBDD){
            borrarHabilidadesDesfasadas();
        }
    }

    private void insertarHabilidadesCampeon(Champion campeon, boolean actualizarBBDD) {
        int position = 1;
        ContentValues cont;
        BaseRealmDecorator baseRealmDecorator = getBaseRealm();

        insertarPasivaCampeon(campeon, actualizarBBDD);

        for (Spell spell : campeon.getSpells()) {
            cont = new ContentValues();
            cont.put("descripcion", Utils.htmlEncode(spell.getDescription()));
            cont.put("tooltip", prepararTooltip(spell));
            cont.put("coste", prepararCoste(spell));
            cont.put("alcance", Utils.htmlEncode(spell.getRangeBurn()));
            cont.put("rutaPrincipal", Utils.htmlEncode(baseRealmDecorator.getRutaHabilidadesCampeon(Constants.PASSIVE_NO) + spell.getImage().getFull()));
            cont.put("enfriamiento", Utils.htmlEncode(spell.getCooldownBurn()));
            cont.put("esPasiva", 0);
            cont.put("posicion", position++);
            cont.put ("esNueva", 1);
            String[] whereArgs = new String[]{Integer.toString(campeon.getId()), Utils.htmlEncode(spell.getName())};
            if (!actualizarBBDD || mDatabase.update("habilidades", cont, "idCampeon=? AND nombre=? ", whereArgs) == 0) {
                cont.put("idCampeon", campeon.getId());
                cont.put("nombre", Utils.htmlEncode(spell.getName()));
                mDatabase.insert("habilidades", null, cont);
            }
        }
    }

    private void insertarPasivaCampeon(Champion campeon, boolean actualizarBBDD) {
        Passive pasiva;
        ContentValues cont;
        BaseRealmDecorator baseRealmDecorator = getBaseRealm();

        pasiva = campeon.getPassive();
        cont = new ContentValues();
        cont.put("descripcion", Utils.htmlEncode(pasiva.getDescription()));
        cont.put("tooltip", "");
        cont.put("coste", "");
        cont.put("alcance", "");
        cont.put("rutaPrincipal", Utils.htmlEncode(baseRealmDecorator.getRutaHabilidadesCampeon(Constants.PASSIVE_YES) + pasiva.getImage().getFull()));
        cont.put("enfriamiento", "");
        cont.put("esPasiva", 1);
        cont.put("posicion", 0);
        cont.put ("esNueva", 1);
        String[] whereArgs = new String[]{Integer.toString(campeon.getId()), Utils.htmlEncode(pasiva.getName())};
        if (!actualizarBBDD || mDatabase.update("habilidades", cont, "idCampeon=? AND nombre=? ", whereArgs) == 0) {
            cont.put("idCampeon", campeon.getId());
            cont.put("nombre", Utils.htmlEncode(pasiva.getName()));
            mDatabase.insert("habilidades", null, cont);
        }
    }

    private void insertarAspectosCampeon(Champion campeon, boolean actualizarBBDD) {
        String rutaSkins = getBaseRealm().getRutaAspectosCampeon();
        ContentValues cont;
        for (Skin skin: campeon.getSkins()) {
            cont = new ContentValues();
            cont.put("idCampeon", campeon.getId());
            cont.put("nombre", Utils.htmlEncode(skin.getName()));
            cont.put("num", skin.getNum());
            cont.put("rutaPrincipal", Utils.htmlEncode(rutaSkins + campeon.getKey() + "_" + skin.getNum() + ".jpg"));
            String[] whereArgs = new String[]{Integer.toString(skin.getId())};
            if(!actualizarBBDD || mDatabase.update("aspectos", cont, "_id=?", whereArgs) == 0) {
                cont.put("_id", skin.getId());
                mDatabase.insert("aspectos", null, cont);
            }
        }
    }

    private String prepararTooltip(Spell spell){
        String description = Utils.htmlEncode(spell.getSanitizedTooltip());
        for (Var var: spell.getVars()) {
            description = description.replaceAll("\\{\\{ " + var.getKey() + " \\}\\}", Utils.sanitizeAttackSource (var.getCoeff().get(0).toString(), var.getLink(), contexto));
        }
        for (int i = 1; i<spell.getEffectBurn().size(); i++){
            description = description.replaceAll("\\{\\{ " + "e" + i + " \\}\\}", spell.getEffectBurn().get(i));
        }
        description = description.replaceAll("\\(\\{\\{.+?\\}\\}\\)", " ");
        description = description.replaceAll("\\(\\+\\{\\{.+?\\}\\}%\\)", " ");
        description = description.replaceAll("\\(\\+\\{\\{.+?\\}\\}\\)", " ");
        description = description.replaceAll("\\{\\{.+?\\}\\}%", " ");
        description = description.replaceAll("\\{\\{.+?\\}\\}", " ");

        return description;
    }

    private String prepararCoste(Spell spell){
        String costBurn = spell.getCostBurn();
        String resource = Utils.htmlEncode(spell.getResource());
        resource = resource.replaceAll("\\{\\{ cost \\}\\}", costBurn);

        for (Var var: spell.getVars()) {
            resource = resource.replaceAll("\\{\\{ " + var.getKey() + " \\}\\}", Utils.sanitizeAttackSource (var.getCoeff().get(0).toString(), var.getLink(), contexto));
        }
        for (int i = 1; i<spell.getEffectBurn().size(); i++){
            resource = resource.replaceAll("\\{\\{ " + "e" + i + " \\}\\}", spell.getEffectBurn().get(i));
        }
        return resource;

    }

    private void borrarHabilidadesDesfasadas() {
        mDatabase.delete("habilidades", "esNueva=0", null);
        ContentValues cont = new ContentValues();
        cont.put ("esNueva", 0);
        mDatabase.update("habilidades", cont, "esNueva=1",null);
    }

    public Champion obtenerCampeon(int id) {
        Champion campeon = null;
        Image image;
        Stats stats;
        String[] columns = new String[]{"key", "nombre", "nick", "historia", "vida", "vidaPorNivel",
                "regeneracionVida", "regeneracionVidaPorNivel", "danioAtaque", "danioAtaquePorNivel",
                "armadura", "armaduraPorNivel", "velocidadAtaque", "velocidadAtaquePorNivel", "crit",
                "critPorNivel", "tipoMP", "mana", "manaPorNivel", "regMana", "regManaPorNivel", "resistenciaMagica",
                "resistenciaMagicaPorNivel", "velocidadMovimiento", "rutaPrincipal"};
        String[] whereArgs = new String[]{Integer.toString(id)};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, "_id=?", whereArgs, null, null, null);
        if (cursor.moveToNext()) {
            campeon = new Champion();
            stats = new Stats();
            image = new Image();
            campeon.setId(id);
            campeon.setKey(cursor.getString(0));
            campeon.setName(cursor.getString(1));
            campeon.setTitle(cursor.getString(2));
            campeon.setLore(Utils.sanitizeChampStory(cursor.getString(3)));
            stats.setHp(Double.parseDouble(cursor.getString(4)));
            stats.setHpperlevel(Double.parseDouble(cursor.getString(5)));
            stats.setHpregen(Double.parseDouble(cursor.getString(6)));
            stats.setHpregenperlevel(Double.parseDouble(cursor.getString(7)));
            stats.setAttackdamage(Double.parseDouble(cursor.getString(8)));
            stats.setAttackdamageperlevel(Double.parseDouble(cursor.getString(9)));
            stats.setArmor(Double.parseDouble(cursor.getString(10)));
            stats.setArmorperlevel(Double.parseDouble(cursor.getString(11)));
            stats.setAttackspeedoffset(Double.parseDouble(cursor.getString(12)));
            stats.setAttackspeedperlevel(Double.parseDouble(cursor.getString(13)));
            stats.setCrit(Double.parseDouble(cursor.getString(14)));
            stats.setCritperlevel(Double.parseDouble(cursor.getString(15)));
            campeon.setPartype(cursor.getString(16));
            stats.setMp(Double.parseDouble(cursor.getString(17)));
            stats.setMpperlevel(Double.parseDouble(cursor.getString(18)));
            stats.setMpregen(Double.parseDouble(cursor.getString(19)));
            stats.setMpregenperlevel(Double.parseDouble(cursor.getString(20)));
            stats.setSpellblock(Double.parseDouble(cursor.getString(21)));
            stats.setSpellblockperlevel(Double.parseDouble(cursor.getString(22)));
            stats.setMovespeed(Double.parseDouble(cursor.getString(23)));
            image.setFull(cursor.getString(24));
            campeon.setImage(image);
            campeon.setStats(stats);
            obtenerAspectosCampeon(campeon);
            obtenerHabilidadesCampeon(campeon);
            obtenerPasivaCampeon(campeon);
        }
        cursor.close();
        return campeon;
    }

    public void obtenerAspectosCampeon(Champion campeon) {
        List<Skin> lSkin = new ArrayList<Skin>();
        Skin skin;
        SkinDecorator skinDecorator;
        String[] columns = new String[]{"_id", "nombre", "rutaPrincipal, num"};
        String[] whereArgs = new String[]{Integer.toString(campeon.getId())};
        Cursor cursor = mReadOnlyDatabase.query("aspectos", columns, "idCampeon=?", whereArgs, null, null, "num");
        while (cursor.moveToNext()) {
            skin = new Skin();
            skinDecorator = new SkinDecorator(skin);
            skin.setId(Integer.parseInt (cursor.getString(0)));
            skin.setName(cursor.getString(1));
            skinDecorator.setRuta(cursor.getString (2));
            skin.setNum(Integer.parseInt (cursor.getString (3)));
            lSkin.add(skinDecorator);
        }
        cursor.close();
        campeon.setSkins(lSkin);
    }

    public void obtenerHabilidadesCampeon(Champion campeon) {
        List<Spell> lSpell = new ArrayList<Spell>();
        Spell spell;
        Image image;
        String[] columns = new String[]{"nombre", "descripcion", "tooltip", "alcance", "coste",
                "enfriamiento", "rutaPrincipal", "esPasiva"};
        String[] whereArgs = new String[]{Integer.toString(campeon.getId())};
        Cursor cursor = mReadOnlyDatabase.query("habilidades", columns, "idCampeon=? AND esPasiva = 0", whereArgs, null, null, "posicion");
        while (cursor.moveToNext()) {
            spell = new Spell();
            image = new Image();
            spell.setName(cursor.getString(0));
            spell.setDescription(Utils.sanitizeText(cursor.getString(1)));
            spell.setTooltip(Utils.sanitizeText(cursor.getString(2)));
            spell.setRangeBurn(Utils.sanitizeText(cursor.getString(3)));
            spell.setCostBurn(cursor.getString(4));
            spell.setCooldownBurn(cursor.getString(5));
            image.setFull(cursor.getString(6));
            spell.setImage(image);
            lSpell.add(spell);
        }
        cursor.close();
        campeon.setSpells(lSpell);
    }

    public void obtenerPasivaCampeon(Champion campeon) {
        Passive passive;
        Image image;
        String[] columns = new String[]{"nombre", "descripcion", "tooltip", "alcance", "coste",
                "enfriamiento", "rutaPrincipal", "esPasiva"};
        String[] whereArgs = new String[]{Integer.toString(campeon.getId())};
        Cursor cursor = mReadOnlyDatabase.query("habilidades", columns, "idCampeon=? AND esPasiva = 1", whereArgs, null, null, "posicion");
        if (cursor.moveToNext()) {
            passive = new Passive();
            image = new Image();
            passive.setName(cursor.getString(0));
            passive.setDescription(Utils.sanitizeText(cursor.getString(1)));
            image.setFull(cursor.getString(6));
            passive.setImage(image);
            campeon.setPassive(passive);
        }
        cursor.close();

    }

    public void updateFreeChamps(BaseEstadoCampeones bEstadoCampeones) {
        ContentValues cont = new ContentValues();
        cont.put("esGratis", 0);
        String[] whereArgs = new String[]{"1"};
        mDatabase.update("campeones", cont, "esGratis=?", whereArgs);
        for (ChampionState campeonEstado: bEstadoCampeones.getChampions()) {
            cont = new ContentValues();
            cont.put("esGratis", 1);
            whereArgs = new String[]{Integer.toString(campeonEstado.getId())};
            mDatabase.update("campeones", cont, "_id=?", whereArgs);
        }
    }


    public ArrayList<Champion> obtenerCampeonesGratuitos() {
        ArrayList<Champion> lChampion = new ArrayList<Champion>();
        Champion champion;
        String[] columns = new String[]{"_id", "nombre"};
        String[] whereArgs = new String[]{"1"};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, "esGratis=?", whereArgs, null, null, "nombre");
        while (cursor.moveToNext()) {
            champion = obtenerCampeon(Integer.parseInt(cursor.getString (0)));
            if (champion != null){
                lChampion.add (champion);
            }
        }
        cursor.close();
        return lChampion;
    }

    /* Antiguo ObtenerNombreRutaCampeones*/
    public List<Champion> obtenerCampeones() {
        List <Champion> lChampion = new ArrayList<Champion>();
        Champion champion;
        String[] columns = new String[]{"_id", "nombre"};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, null, null, null, null, "nombre");
        while (cursor.moveToNext()) {
            lChampion.add(obtenerCampeon(cursor.getInt(0)));
        }
        cursor.close();
        return lChampion;
    }


    public void insertarObjeto(Item item, Map<String, String> mHyperTags, boolean actualizarBBDD){
        String rutaImagen = getBaseRealm().getRutaVersionObjeto();
        int purch= item.getGold().getPurchasable() ? 1 : 0;
        String pText = item.getPlaintext() == null ? "" : item.getPlaintext();
        int stack = item.getStacks() == null ? 1 : item.getStacks();
        int dept = item.getDepth() == null  ? 1 : item.getDepth();
        String from = item.getFrom() == null ? "" : Utils.clearCorchetes(item.getFrom().toString());
        String into = item.getInto() == null ? "" : Utils.clearCorchetes(item.getInto().toString());
        int hide = (item.getHideFromAll() == null || !item.getHideFromAll()) ? 0 : 1;
        ContentValues cont = new ContentValues();
        cont.put("name", Utils.htmlEncode(item.getName()));
        cont.put("base", item.getGold().getBase());
        cont.put("total", item.getGold().getTotal());
        cont.put("sell", item.getGold().getSell());
        cont.put("purchasable", purch);
        cont.put("description", Utils.htmlEncode(item.getDescription()));
        cont.put("plainText", Utils.htmlEncode(pText));
        cont.put("stacks", stack);
        cont.put("depth", dept);
        cont.put("fromOBJ", Utils.htmlEncode(from));
        cont.put("intoOBJ", Utils.htmlEncode(into));
        cont.put("hideFromAll", hide);
        if (item.getRequiredChampion()==null) {
            cont.putNull("requiredChampion");
        }
        else{
            cont.put("requiredChampion", obtenerIDCampeon (item.getRequiredChampion()));
        }
        cont.put("full", Utils.htmlEncode(rutaImagen + item.getImage().getFull()));
        String[] whereArgs = new String[]{Integer.toString(item.getId())};
        if(!actualizarBBDD || mDatabase.update("objetos", cont, "_id=?", whereArgs) == 0) {
            cont.put("_id", item.getId());
            mDatabase.insert("objetos", null, cont);
        }
        if (actualizarBBDD) {
            borrarTagsObjeto(item.getId());
        }
        insertarTagsObjeto(item, mHyperTags);
    }

    private Integer obtenerIDCampeon(String name) {
        String[] columns = new String[]{"_id"};
        String[] whereArgs = new String[]{name};
        Cursor cursor = mReadOnlyDatabase.query("campeones", columns, "nombre LIKE ?", whereArgs, null, null, null);

        Integer result = null;
        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        cursor.close();
        return result;
    }

    private void insertarTagsObjeto(Item item, Map<String, String> mHyperTags){
        String hyperTag;
        if (item.getTags() != null && !item.getTags().isEmpty()) {
            for (String tag : item.getTags()) {
                hyperTag = mHyperTags.get(tag.toUpperCase());
                ContentValues cont = new ContentValues();
                cont.put("idObjeto", item.getId());
                cont.put("nombreTag", Utils.htmlEncode(tag));
                if (hyperTag == null){
                    hyperTag="";
                }
                cont.put("hyperTag", Utils.htmlEncode(hyperTag));
                mDatabase.insert("tagsObjetos", null, cont);
            }
        }
    }

    private void borrarTagsObjeto(int idObjeto){
        String[] whereArgs = new String[]{Integer.toString(idObjeto)};
        mDatabase.delete("tagsObjetos", "idObjeto=?", whereArgs);
    }

    public Item obtenerObjeto(int id) {
        return obtenerObjetos (Collections.singletonList(id)).get(0);
    }

    public List<Item> obtenerObjetos(Collection<Integer> cId) {
        List <Item> cItem;
        Item item;
        Gold gold;
        ImageItem imageItem;
        ArrayList<String> aux;
        cItem = null;
        if (cId != null && !cId.isEmpty()) {

            cItem = new ArrayList<Item>();
            String[] columns = new String[]{"_id", "name", "base", "total", "sell", "purchasable",
                    "description", "plainText", "stacks", "depth", "fromOBJ", "intoOBJ",
                    "hideFromAll", "requiredChampion", "full"};
            List<String> parameters = new ArrayList<>();
            List<String> lIds = new ArrayList<>();
            for (Integer id : cId) {
                parameters.add("?");
                lIds.add(id.toString());
            }
            String[] whereArgs = lIds.toArray(new String[0]);
            Cursor cursor = mReadOnlyDatabase.query("objetos", columns, "_id in (" + TextUtils.join(",", parameters) + ")", whereArgs, null, null, null);
            while (cursor.moveToNext()) {
                item = new Item();
                gold = new Gold();
                imageItem = new ImageItem();
                item.setId(cursor.getInt(0));
                item.setName(cursor.getString(1));
                gold.setBase(Integer.parseInt (cursor.getString(2)));
                gold.setTotal(Integer.parseInt (cursor.getString(3)));
                gold.setSell(Integer.parseInt (cursor.getString(4)));
                gold.setPurchasable(cursor.getString(5).equals("1"));
                item.setDescription(Utils.sanitizeText(cursor.getString(6)));
                item.setPlaintext(Utils.sanitizeText(cursor.getString(7)));
                item.setStacks(Integer.parseInt (cursor.getString(8)));
                item.setDepth(Integer.parseInt (cursor.getString(9)));
                if (!cursor.getString(10).trim().isEmpty()) {
                    aux = new ArrayList<String>();
                    Collections.addAll(aux, cursor.getString(10).split(","));
                    item.setFrom(aux);
                }
                if (!cursor.getString(11).trim().isEmpty()) {
                    aux = new ArrayList<String>();

                    Collections.addAll(aux, cursor.getString(11).split(","));
                    item.setInto(aux);
                }
                item.setHideFromAll(cursor.getString(12).equals("1"));
                item.setRequiredChampion(cursor.getString(13));
                imageItem.setFull(cursor.getString(14));
                item.setImage(imageItem);
                item.setGold(gold);
                cItem.add (item);
            }
            cursor.close();

        }

        return cItem;

    }


    /*Antiguo obtenerNombreRutaObjetos*/
    public List<Item> obtenerObjetos() {
        List<Item> lItem = new ArrayList<Item>();
        Item item;
        String[] columns = new String[]{"_id", "name"};
        String[] whereArgs = new String[]{"0"};
        Cursor cursor = mReadOnlyDatabase.query("objetos", columns, "hideFromAll=?", whereArgs, null, null, "name");
        while (cursor.moveToNext()) {
            item = obtenerObjeto(cursor.getInt(0));
            if (item != null){
                lItem.add (item);
            }
        }
        cursor.close();
        return lItem;
    }


    public void guardarRutaVersiones(BaseRealm realm) {
        mDatabase.delete("rutaVersiones", null, null);

        ContentValues cont = new ContentValues();
        cont.putNull("_id");
        cont.put("ruta", Utils.htmlEncode(realm.getCdn()));
        cont.put("versionCampeones", Utils.htmlEncode(realm.getN().getChampion()));
        cont.put("versionObjetos", Utils.htmlEncode(realm.getN().getItem()));
        mDatabase.insert("rutaVersiones", null, cont);
    }

    public BaseRealmDecorator getBaseRealm() {

        N n = new N();
        BaseRealm baseRealm = new BaseRealm();
        baseRealm.setN(n);
        BaseRealmDecorator baseRealmDecorator = new BaseRealmDecorator(baseRealm);

        String[] columns = new String[]{"ruta", "versionCampeones", "versionObjetos"};
        Cursor cursor = mReadOnlyDatabase.query("rutaVersiones", columns, null, null, null, null, null);
        if (cursor.moveToNext()) {
            baseRealm.setCdn(cursor.getString(0));
            n.setChampion(cursor.getString(1));
            n.setItem(cursor.getString(2));
        }
        cursor.close();

        return baseRealmDecorator;

    }

    /**
     * Se encarga de obtener la última versión de los campeones
     *
     * @return String con la última versión conocida
     */
    public String obtenerVersionCampeon() {
        return getBaseRealm().getN().getChampion();
    }

    /**
     * Se encarga de obtener la última versión de los objetos
     *
     * @return String con la última versión conocida
     */
    public String obtenerVersionObjeto() {
        return getBaseRealm().getN().getItem();
    }

    //TODO CHECK RIOT
    public void aniadirMapas(){
        mDatabase.execSQL("INSERT INTO mapas VALUES (1, '" + Utils.htmlEncode(contexto.getResources().getString(R.string.mapa_1)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (8, '" + Utils.htmlEncode(contexto.getResources().getString(R.string.mapa_8)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (10, '" + Utils.htmlEncode(contexto.getResources().getString(R.string.mapa_10)) + "')");
        mDatabase.execSQL("INSERT INTO mapas VALUES (12, '" + Utils.htmlEncode(contexto.getResources().getString(R.string.mapa_12)) + "')");
    }
}