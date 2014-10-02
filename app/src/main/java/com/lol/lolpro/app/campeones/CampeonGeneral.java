package com.lol.lolpro.app.campeones;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lol.lolpro.app.R;
import com.squareup.picasso.Picasso;

/**
 * Implementa la funcionalidad del fragment
 */
public class CampeonGeneral extends Fragment {

    /**
     * Constructor vacío
     */
    public CampeonGeneral() {
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
        String[] datos = args.getStringArray("data");
        View view = inflater.inflate(R.layout.fragment_campeon_general, container, false);
        if (datos != null){
            ((TextView) view.findViewById(R.id.campeon_nombre)).setText(datos[1]);
            ((TextView) view.findViewById(R.id.campeon_nick)).setText(datos[2]);
            ((TextView) view.findViewById(R.id.campeon_vida)).setText(datos[4]+ "(+" + datos[5] + " por nivel)");
            ((TextView) view.findViewById(R.id.campeon_regVida)).setText(datos[6] + "(+" + datos[7] + " por nivel)");
            ((TextView) view.findViewById(R.id.campeon_danioAtaque)).setText(datos[8] + "(+" + datos[9] + " por nivel)");
            ((TextView) view.findViewById(R.id.campeon_armadura)).setText(datos[10] + "(+" + datos[11] + " por nivel)");
            ((TextView) view.findViewById(R.id.campeon_velAtaque)).setText(datos[12] + "(+" + datos[13] + "% por nivel)");
            String mp=datos[16];
            String [] mpName = getResources().getStringArray((R.array.tipoFuerza));
            boolean encontrado=false;
            for (int i=0; i<mpName.length && !encontrado; i++){
                if (mp.equals(mpName[i]) || mp.contains(mpName[i])){
                    ((TextView) view.findViewById(R.id.campeon_texMana)).setText(getActivity().getResources().getStringArray(R.array.mp)[i]);
                    ((TextView) view.findViewById(R.id.campeon_texRegMana)).setText(getActivity().getResources().getStringArray(R.array.regMP)[i]);
                    encontrado = true;
                }
            }
            if (!encontrado){
                view.findViewById(R.id.campeon_texMana).setVisibility(View.GONE);
                view.findViewById(R.id.campeon_mana).setVisibility(View.GONE);
                view.findViewById(R.id.campeon_imageMana).setVisibility(View.GONE);
                view.findViewById(R.id.campeon_texRegMana).setVisibility(View.GONE);
                view.findViewById(R.id.campeon_regMana).setVisibility(View.GONE);
                view.findViewById(R.id.campeon_imageRegMana).setVisibility(View.GONE);
            }
            ((TextView) view.findViewById(R.id.campeon_mana)).setText(datos[17] + "(+" + datos[18] + " por nivel)");
            ((TextView) view.findViewById(R.id.campeon_regMana)).setText(datos[19]+ "(+" + datos[20] + " por nivel)");
            ((TextView) view.findViewById(R.id.campeon_resMagica)).setText(datos[21] + "(+" + datos[22] + " por nivel)");
            ((TextView) view.findViewById(R.id.campeon_velMov)).setText(datos[23]);
            Picasso.with(getActivity()) //
                    .load(datos[24]) //
                    .placeholder(R.drawable.cargar)
                    .error(R.drawable.error)
                    .into((ImageView) view.findViewById(R.id.campeon_Imagen));
        }
        return view;
    }
}