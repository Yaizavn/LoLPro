package com.lol.lolpro.app;

import android.content.Context;

public class CargandoBBDD{

    public CargandoBBDD(Context contexto) {
        Spider sp= new Spider("http://gameinfo.euw.leagueoflegends.com/es/game-info/champions/");
        sp.execute(contexto);
    }
}
