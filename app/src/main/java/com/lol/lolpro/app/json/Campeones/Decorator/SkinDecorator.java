package com.lol.lolpro.app.json.Campeones.Decorator;

import com.lol.lolpro.app.json.Campeones.Skin;

import java.util.Map;

/**
 * Created by sergioiglesiasvelasco on 3/9/16.
 */

public class SkinDecorator extends Skin {
    private Skin skin;
    private String ruta;
    public SkinDecorator(Skin skin){
        this.skin = skin;
    }

    @Override
    public Integer getId() {
        return skin.getId();
    }

    @Override
    public String getName() {
        return skin.getName();
    }

    @Override
    public Integer getNum() {
        return skin.getNum();
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
