package com.lol.lolpro.app.json.Realm;

import android.database.Cursor;
import android.text.Html;

import com.lol.lolpro.app.utillidades.Constants;

/**
 * Created by sergioiglesiasvelasco on 4/9/16.
 */

public class BaseRealmDecorator extends BaseRealm {
    
    private BaseRealm baseRealm;
    
    public BaseRealmDecorator(BaseRealm baseRealm) {
        
        this.baseRealm = baseRealm;
        
    }
    
    @Override
    public String getCss() {
        return baseRealm.getCss();
    }

    @Override
    public String getDd() {
        return baseRealm.getDd();
    }

    @Override
    public String getL() {
        return baseRealm.getL();
    }

    @Override
    public N getN() {
        return baseRealm.getN();
    }

    @Override
    public Integer getProfileiconmax() {
        return baseRealm.getProfileiconmax();
    }

    @Override
    public String getV() {
        return baseRealm.getV();
    }

    @Override
    public String getLg() {
        return baseRealm.getLg();
    }

    @Override
    public String getCdn() {
        return baseRealm.getCdn();
    }

    public String getRutaVersionCampeon() {

        return baseRealm.getCdn() + "/" + baseRealm.getN().getChampion() + "/img/champion/";

    }

    public String getRutaHabilidadesCampeon(int esPasiva) {
        String ruta;

        ruta = baseRealm.getCdn() + "/" + baseRealm.getN().getChampion();

        if (esPasiva == Constants.PASSIVE_YES) {
            ruta += "/img/passive/";
        }
        else {
            ruta += "/img/spell/";
        }

        return ruta;
    }

    public String getRutaAspectosCampeon() {

        return baseRealm.getCdn() + "/img/champion/splash/";

    }

    public String getRutaVersionObjeto() {

        return baseRealm.getCdn() + "/" + baseRealm.getN().getItem() + "/img/item/";

    }

}
