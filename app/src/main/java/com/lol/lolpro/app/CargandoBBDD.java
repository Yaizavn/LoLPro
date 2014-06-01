package com.lol.lolpro.app;

import android.content.Context;
import android.widget.Toast;

import com.lol.lolpro.app.web.APIConnection;

public class CargandoBBDD{

    public CargandoBBDD(Context contexto) {
        APIConnection api = new APIConnection(contexto);
        api.connect2API(APIConnection.IMAGENES);
        api.connect2API(APIConnection.CAMPEONES);
        api.connect2API(APIConnection.OBJETOS);
        Toast.makeText(contexto, "He terminado, preparate para caagrte en los pantalones xD", Toast.LENGTH_LONG);
        //Spider sp= new Spider("http://gameinfo.euw.leagueoflegends.com/es/game-info/champions/");
        //sp.execute(contexto);
    }
}
