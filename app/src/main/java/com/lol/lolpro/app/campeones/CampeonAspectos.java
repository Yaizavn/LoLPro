package com.lol.lolpro.app.campeones;



import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.grids.GridAdapterSkins;
import com.lol.lolpro.app.R;


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
        GridView grid = (GridView) view.findViewById(R.id.gridFreeChamps);
        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        String[][] datos = (String[][]) args.getSerializable("skins");
        datos[0][1] = args.getStringArray("data")[1];


        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);

        grid.setAdapter(new GridAdapterSkins(getActivity(), datos, 0));
        dbMan.closeDatabase(false);
        // Inflate the layout for this fragment
        return view;
    }
}
