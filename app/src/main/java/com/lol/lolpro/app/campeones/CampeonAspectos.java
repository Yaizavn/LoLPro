package com.lol.lolpro.app.campeones;



import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.grids.GridAdapterSkins;
import com.lol.lolpro.app.R;
import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.utillidades.Constants;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CampeonAspectos extends Fragment {

    public CampeonAspectos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_campeon_aspectos, container, false);
        GridView grid = (GridView) view.findViewById(R.id.gridAspectos);
        Bundle args = getArguments();
        Champion campeon = args.getParcelable("champion");
        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);
        grid.setAdapter(new GridAdapterSkins(getActivity(), campeon));
        dbMan.closeDatabase(false);
        return view;
    }
}
