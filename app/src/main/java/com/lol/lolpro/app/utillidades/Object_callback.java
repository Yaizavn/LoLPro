package com.lol.lolpro.app.utillidades;

import com.lol.lolpro.app.json.Objetos.Item;

/**
 * Created by sergioiglesias on 05/09/14.
 */
public interface Object_callback {
    /**
     * Método definido en principal que se encarga de el tratamiento al seleccionar un objeto
     * @param index Posición del objeto seleccionado
     */
    public void onObjectSelected(Item objectSelected);
}
