package com.lol.lolpro.app.campeones;

import com.lol.lolpro.app.json.Campeones.Champion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YAIZA on 03/09/2017.
 */

public class CampeonModel {

    private Map<Integer, Champion> mCampeones;

    public CampeonModel() {
        this.mCampeones = new HashMap<Integer, Champion>();
    }

    public Champion getCampeon(Integer id) {
        return mCampeones.get(id);
    }

    public void addCampeon(Champion campeon) {
        mCampeones.put(campeon.getId(), campeon);
    }

    public void limpiar() {
        mCampeones.clear();
    }

    public List<Champion> getCampeones() {
        return new ArrayList<>(mCampeones.values());
    }
}
