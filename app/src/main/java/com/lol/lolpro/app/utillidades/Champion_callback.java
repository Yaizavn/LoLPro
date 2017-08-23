package com.lol.lolpro.app.utillidades;

import com.lol.lolpro.app.json.Campeones.Champion;

/**
 * Created by sergioiglesias on 05/09/14.
 */
public interface Champion_callback {
    /**
     * Método definido en principal que se encarga de el tratamiento al seleccionar a un campeón
     * @param campeon Campeón seleccionado
     */
    public void onChampionSelected(Champion campeon);
}
