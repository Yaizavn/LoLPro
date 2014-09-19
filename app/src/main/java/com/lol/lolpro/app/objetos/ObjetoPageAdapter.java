package com.lol.lolpro.app.objetos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lol.lolpro.app.campeones.CampeonAspectos;
import com.lol.lolpro.app.campeones.CampeonGeneral;
import com.lol.lolpro.app.campeones.CampeonHabilidades;
import com.lol.lolpro.app.campeones.CampeonHistoria;

/**
 * Adaptador para mostrar los tabs campeón e historia de champion
 */
public class ObjetoPageAdapter extends FragmentStatePagerAdapter {
    private static final int numPages = 2;
    private Bundle argsObjeto;

    /**
     * Constructor
     *
     * @param fm   Se encarga de la gestión de los fragments
     * @param args Bundle que contiene los elementos asociados a un fragment, en este caso los datos de los campeones
     */
    public ObjetoPageAdapter(FragmentManager fm, Bundle args) {
        super(fm);
        argsObjeto = args;
    }

    /**
     * Se encarga de crear los fragments dentro de champions
     *
     * @param position posición del tab que se creará
     * @return fragment para el tab asociado a la posición
     */
    @Override
    public Fragment getItem(int position) {
        Fragment frg = null;
        switch (position) {
            case 0:
                frg = new ObjetoGeneral();
                break;
            case 1:
                frg = new ObjetoArbol();
                break;
            default:
                break;
        }
        frg.setArguments(argsObjeto);
        return frg;
    }

    /**
     * Se encarga de obtener el número de tabs que tiene champion
     *
     * @return
     */
    @Override
    public int getCount() {
        return numPages;
    }
}
