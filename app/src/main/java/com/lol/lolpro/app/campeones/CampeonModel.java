package com.lol.lolpro.app.campeones;

import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Decorator.ChampionDecorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YAIZA on 03/09/2017.
 */

public class CampeonModel {

    private Map<Integer, ChampionDecorator> mCampeones;

    public CampeonModel() {
        this.mCampeones = new HashMap<Integer, ChampionDecorator>();
    }

    public ChampionDecorator getCampeon(Integer id) {
        return mCampeones.get(id);
    }

    public List<ChampionDecorator> getCampeones() {
        return new ArrayList<>(mCampeones.values());
    }

    public List<ChampionDecorator> getCampeonesGratuitos() {
        List<ChampionDecorator> lCampeonesGratuitos= new ArrayList<ChampionDecorator>();
        for (ChampionDecorator championDecorator : mCampeones.values()) {
            if (championDecorator.isGratuito()){
                lCampeonesGratuitos.add (championDecorator);
            }
        }
        return lCampeonesGratuitos;
    }

    public void addCampeon(ChampionDecorator campeon) {
        mCampeones.put(campeon.getId(), campeon);
    }

    public void addCampeon(Collection<ChampionDecorator> lCampeones) {
        if (lCampeones != null && !lCampeones.isEmpty())
        for (ChampionDecorator campeon : lCampeones) {
            addCampeon (campeon);
        }
    }

    public void limpiar() {
        mCampeones.clear();
    }




}
