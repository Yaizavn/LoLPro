package com.lol.lolpro.app.campeones;

import com.lol.lolpro.app.bbdd.BBDDHelper;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.json.Campeones.Champion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sergioiglesiasvelasco on 6/6/17.
 */

public class CampeonManager {

    private static CampeonManager instance;
    private CampeonModel cmCampeones;
    private boolean estaCompleto;

    public static CampeonManager getInstance() {
        if (instance == null) {
            instance = new CampeonManager();
        }
        return instance;
    }

    private CampeonManager() {
        this.cmCampeones = new CampeonModel();
        estaCompleto = false;
    }

    public Champion getCampeon(int idCampeon) {
        Champion campeon = cmCampeones.getCampeon(idCampeon);
        if (campeon == null){
            DBManager.getInstance().openDatabase(false);
            BBDDHelper dbHelper = DBManager.getInstance().getDatabaseHelper();
            campeon = dbHelper.obtenerCampeon(idCampeon);
            DBManager.getInstance().closeDatabase(false);
            cmCampeones.addCampeon(campeon);
        }
        return campeon;
    }

    public Collection<Champion> getCampeones(Collection<Integer> cIds) {
        Collection <Champion> cCampeones = new ArrayList<Champion>();
        for (Integer id : cIds) {
            cCampeones.add (getCampeon(id));
        }
        return cCampeones;
    }

    public List<Champion> getCampeones() {
        List<Champion> todosCampeones;
        if (!estaCompleto){
            DBManager.getInstance().openDatabase(false);
            BBDDHelper dbHelper = DBManager.getInstance().getDatabaseHelper();
            todosCampeones = dbHelper.obtenerCampeones();
            DBManager.getInstance().closeDatabase(false);
            estaCompleto = true;
        }
        else{
            todosCampeones= cmCampeones.getCampeones();
        }
        return todosCampeones;
    }

    public List<Champion> getCampeonesGratuitos() {
        List<Champion> CampeonesGratuitos;
        if (!estaCompleto){
            DBManager.getInstance().openDatabase(false);
            BBDDHelper dbHelper = DBManager.getInstance().getDatabaseHelper();
            todosCampeones = dbHelper.obtenerCampeonesGratuitos();
            DBManager.getInstance().closeDatabase(false);
            estaCompleto = true;
        }
        else{
            todosCampeones= cmCampeones.getCampeones();
        }
        return todosCampeones;
    }

}
