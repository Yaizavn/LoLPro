package com.lol.lolpro.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Adaptador para mostrar los tabs campeón e historia de champion
 */
public class ChampionPageAdapter extends FragmentStatePagerAdapter {
    private static final int numPages = 2;
    private Bundle argsCampeon;

    /**
     * Constructor
     * @param fm Se encarga de la gestión de los fragments
     * @param args Bundle que contiene los elementos asociados a un fragment, en este caso los datos de los campeones
     */
    public ChampionPageAdapter(FragmentManager fm, Bundle args) {
        super(fm);
        argsCampeon = args;
        notifyDataSetChanged();
    }

    /**
     * Se encarga de crear los fragments dentro de champions
     * @param position posición del tab que se creará
     * @return fragment para el tab asociado a la posición
     */
    @Override
    public Fragment getItem(int position) {
        Fragment frg = null;
        switch(position){
            case 0:
                frg = new CampeonInfo();
                break;
            case 1:
                frg = new Historia();
                break;
            default:
                break;
        }
        frg.setArguments(argsCampeon);
        return frg;
    }

    /**
     * Se encarga de obtener el número de tabs que tiene champion
     * @return
     */
    @Override
    public int getCount() {
        return numPages;
    }
}
