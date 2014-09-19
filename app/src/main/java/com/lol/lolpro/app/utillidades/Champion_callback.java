package com.lol.lolpro.app.utillidades;

/**
 * Created by sergioiglesias on 05/09/14.
 */
public interface Champion_callback {
    /**
     * Método definido en principal que se encarga de el tratamiento al seleccionar a un campeón
     *
     * @param index Posición del campeón seleccionado
     */
    public void onChampionSelected(int index);
}
