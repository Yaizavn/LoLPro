package com.lol.lolpro.app;



import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Skins extends Fragment {


    public Skins() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_skins, container, false);
        GridView grid = (GridView) view.findViewById(R.id.gridView);
        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        String[][] datos = (String[][]) args.getSerializable("skins");

        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);

        grid.setAdapter(new GridAdapterNombre(getActivity(), datos, 340));
        dbMan.closeDatabase(false);
        // Inflate the layout for this fragment
        return view;
    }
}
