package com.lol.lolpro.app.campeones;

import com.lol.lolpro.app.bbdd.BBDDHelper;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Decorator.ChampionDecorator;

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
    private boolean estaCompletoGratuitos;

    public static CampeonManager getInstance() {
        if (instance == null) {
            instance = new CampeonManager();
        }
        return instance;
    }

    private CampeonManager() {
        this.cmCampeones = new CampeonModel();
        estaCompleto = false;
        estaCompletoGratuitos = false;
    }

    public ChampionDecorator getCampeon(int idCampeon) {
        ChampionDecorator campeon = cmCampeones.getCampeon(idCampeon);
        if (campeon == null){
            DBManager.getInstance().openDatabase(false);
            BBDDHelper dbHelper = DBManager.getInstance().getDatabaseHelper();
            campeon = dbHelper.obtenerCampeon(idCampeon);
            DBManager.getInstance().closeDatabase(false);
            cmCampeones.addCampeon(campeon);
        }
        return campeon;
    }

    public Collection<ChampionDecorator> getCampeones(Collection<Integer> cIds) {
        Collection <ChampionDecorator> cCampeones = new ArrayList<ChampionDecorator>();
        for (Integer id : cIds) {
            cCampeones.add (getCampeon(id));
        }
        return cCampeones;
    }

    public List<ChampionDecorator> getCampeones() {
        List<ChampionDecorator> todosCampeones;
        if (!estaCompleto){
            DBManager.getInstance().openDatabase(false);
            BBDDHelper dbHelper = DBManager.getInstance().getDatabaseHelper();
            todosCampeones = dbHelper.obtenerCampeones();
            cmCampeones.addCampeon(todosCampeones);
            DBManager.getInstance().closeDatabase(false);
            estaCompleto = true;
            estaCompletoGratuitos = true;
        }
        else{
            todosCampeones= cmCampeones.getCampeones();
        }
        return todosCampeones;
    }

    public List<ChampionDecorator> getCampeonesGratuitos() {
        List<ChampionDecorator> campeonesGratuitos;
        if (!estaCompletoGratuitos){
            DBManager.getInstance().openDatabase(false);
            BBDDHelper dbHelper = DBManager.getInstance().getDatabaseHelper();
            campeonesGratuitos = dbHelper.obtenerCampeonesGratuitos();
            cmCampeones.addCampeon(campeonesGratuitos);
            DBManager.getInstance().closeDatabase(false);
            estaCompletoGratuitos = true;
        }
        else{
            campeonesGratuitos= cmCampeones.getCampeonesGratuitos();

        }
        return campeonesGratuitos;
    }

}
