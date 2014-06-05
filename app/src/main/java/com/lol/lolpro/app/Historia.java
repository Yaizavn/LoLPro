package com.lol.lolpro.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Historia extends Fragment {

    public Historia() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        String[] datos = args.getStringArray("data");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historia, container, false);
        // Defino la nueva fuente cargandola desde el fichero .ttf
        Typeface miPropiaTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Hand_Of_Sean_Demo.ttf");

        if(datos != null){
            TextView historia = (TextView)view.findViewById(R.id.historia);
            TextView nombre =(TextView)view.findViewById(R.id.nombreCampeon);

            // Le aplico el nuevo tipo de letra
            historia.setTypeface(miPropiaTypeFace);
            nombre.setTypeface(miPropiaTypeFace);

            nombre.setText(datos[0]);
            historia.setText(datos[2]);
        }
        return view;
    }
}
