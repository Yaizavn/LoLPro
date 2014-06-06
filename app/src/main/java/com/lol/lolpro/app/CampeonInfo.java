package com.lol.lolpro.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Implementa la funcionalidad del fragment
 */
public class CampeonInfo extends Fragment {

    /**
     * Constructor vacío
     */
    public CampeonInfo() {
        // Required empty public constructor
    }

    /**
     * Se encarga del tratamiento necesario para poder crear la vista de información general de un campeón en particular
     *
     * @param inflater           Sirve para traer un layout hecho en xml como una vista en java
     * @param container          Contenedos para otros elementos View
     * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
     * @return Vista de los campeones
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        String[] datos = args.getStringArray("data");
        View view = inflater.inflate(R.layout.fragment_campeon_info, container, false);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getActivity().getResources().getDisplayMetrics());
        if (datos != null){
            ((TextView) view.findViewById(R.id.nombre)).setText(datos[0]);
            ((TextView) view.findViewById(R.id.nick)).setText(datos[1]);
            ((TextView) view.findViewById(R.id.vida)).setText(datos[3]);
            ((TextView) view.findViewById(R.id.regVida)).setText(datos[4]);
            ((TextView) view.findViewById(R.id.danioAtaque)).setText(datos[5]);
            ((TextView) view.findViewById(R.id.armadura)).setText(datos[6]);
            ((TextView) view.findViewById(R.id.velAtaque)).setText(datos[7]);
            ((TextView) view.findViewById(R.id.resMagica)).setText(datos[8]);
            ((TextView) view.findViewById(R.id.velMov)).setText(datos[9]);
            Picasso.with(getActivity()) //
                    .load(datos[10]) //
                    .placeholder(R.drawable.cargar)
                    .error(R.drawable.error)
                    .resize(px, px)
                    .centerCrop() // Keep proportion
                    .into((ImageView) view.findViewById(R.id.Imagen));
        }
        // Inflate the layout for this fragment
        return view;
    }
}