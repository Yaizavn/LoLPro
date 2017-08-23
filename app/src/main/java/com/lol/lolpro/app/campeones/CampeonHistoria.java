package com.lol.lolpro.app.campeones;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.R;

/**
 * Implementa la funcionalidad del fragment
 */
public class CampeonHistoria extends Fragment {

    /**
     * Constructor vacío
     */
    public CampeonHistoria() {
        // Required empty public constructor
    }

    /**
     * Se encarga del tratamiento necesario para poder crear la vista
     *
     * @param inflater           Sirve para traer un layout hecho en xml como una vista en java
     * @param container          Contenedos para otros elementos View
     * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
     * @return Vista de la historia de los campeones
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        Champion campeon;
        campeon = args.getParcelable("champion");
        View view = inflater.inflate(R.layout.fragment_campeon_historia, container, false);
        if (campeon != null) {
            TextView historia = (TextView) view.findViewById(R.id.historia_historia);
            TextView nombre = (TextView) view.findViewById(R.id.historia_nombreCampeon);
            // Defino la nueva fuente cargandola desde el fichero .ttf
            Typeface miPropiaTypeFace = Typeface.createFromAsset(getActivity().getAssets(), Constants.FONT_STORY);
            // Aplico el nuevo tipo de letra
            historia.setTypeface(miPropiaTypeFace);
            nombre.setTypeface(miPropiaTypeFace);
            nombre.setText(campeon.getName());
            historia.setText(campeon.getLore());
        }
        return view;
    }
}
