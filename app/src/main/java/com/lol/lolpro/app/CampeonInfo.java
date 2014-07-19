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
            ((TextView) view.findViewById(R.id.vida)).setText(datos[3]+ "(+" + datos[4] + " por nivel)");
            //((TextView) view.findViewById(R.id.vidaPorNivel)).setText(datos[4]);
            ((TextView) view.findViewById(R.id.regVida)).setText(datos[5] + "(+" + datos[6] + " por nivel)");
            //((TextView) view.findViewById(R.id.regVidaPorNivel)).setText(datos[6]);
            ((TextView) view.findViewById(R.id.danioAtaque)).setText(datos[7] + "(+" + datos[8] + " por nivel)");
            //((TextView) view.findViewById(R.id.danioAtaquePorNivel)).setText(datos[8]);
            ((TextView) view.findViewById(R.id.armadura)).setText(datos[9] + "(+" + datos[10] + " por nivel)");
            //((TextView) view.findViewById(R.id.armaduraPorNivel)).setText(datos[10]);
            ((TextView) view.findViewById(R.id.velAtaque)).setText(datos[11] + "(+" + datos[12] + " por nivel)");
            //((TextView) view.findViewById(R.id.velAtaquePorNivel)).setText(datos[12]);
            //((TextView) view.findViewById(R.id.crit)).setText(datos[13] + "(+" + datos[14] + " por nivel)");
            //((TextView) view.findViewById(R.id.critPorNivel)).setText(datos[14]);
            //((TextView) view.findViewById(R.id.tipoMP)).setText(datos[15]);
            ((TextView) view.findViewById(R.id.mana)).setText(datos[16] + "(+" + datos[17] + " por nivel)");
            //((TextView) view.findViewById(R.id.manaPorNivel)).setText(datos[17]);
            ((TextView) view.findViewById(R.id.regMana)).setText(datos[18]+ "(+" + datos[19] + " por nivel)");
            //((TextView) view.findViewById(R.id.regManaPorNivel)).setText(datos[19]);
            ((TextView) view.findViewById(R.id.resMagica)).setText(datos[20] + "(+" + datos[21] + " por nivel)");
            //((TextView) view.findViewById(R.id.resMagicaPorNivel)).setText(datos[21]);
            ((TextView) view.findViewById(R.id.velMov)).setText(datos[22]);
            Picasso.with(getActivity()) //
                    .load(datos[23]) //
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