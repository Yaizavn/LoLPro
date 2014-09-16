package com.lol.lolpro.app.campeones;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.grids.GridAdapterSpells;
import com.lol.lolpro.app.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class CampeonHabilidades extends Fragment {

    public CampeonHabilidades() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_campeon_habilidades, container, false);
        GridView grid = (GridView) view.findViewById(R.id.gridHabilidades);
        Bundle args = getArguments();
        String[][] spells = (String[][]) args.getSerializable("spells");
        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);
        grid.setAdapter(new GridAdapterSpells(getActivity(), spells));
        dbMan.closeDatabase(false);
        return view;
    }
}
