package com.lol.lolpro.app;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CargandoBBDD{

    public CargandoBBDD(Context contexto) {
        spider sp= new spider ("http://gameinfo.euw.leagueoflegends.com/es/game-info/champions/");
        sp.execute(contexto);
    }
}
