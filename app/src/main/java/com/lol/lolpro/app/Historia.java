package com.lol.lolpro.app;

import android.app.Activity;
import android.graphics.Typeface;
import android.net.Uri;
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

        View view = inflater.inflate(R.layout.fragment_historia, container, false);
        // Defino la nueva fuente cargandola desde el fichero .ttf
        Typeface miPropiaTypeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Hand_Of_Sean_Demo.ttf");

        // Cargo en una variable tipo TextView el campo de la pantalla
        // identificado con el id poker.

        TextView historia = (TextView)view.findViewById(R.id.historia);
        TextView nombre =(TextView)view.findViewById(R.id.nombreCampeon);

        // Le aplico el nuevo tipo de letra
        historia.setTypeface(miPropiaTypeFace);
        nombre.setTypeface(miPropiaTypeFace);

        historia.setText("Aatrox es un guerrero legendario, uno de los únicos cinco que quedan de una raza antigua conocida como 'Los Oscuros'. Porta su enorme espada con gracia y serenidad, y la desliza entre sus enemigos con un estilo hipnótico y terrible. Con cada enemigo que derriba, Aatrox bebe la sangre que queda en su espada, lo que lo fortalece notablemente, potenciando su brutal y elegante campaña de sacrificios." +
                "Las hazañas de Aatrox son tan antiguas como la misma historia. Se cuenta que hubo una guerra entre dos grandes facciones recordadas solamente como el Protectorado y los Señores Hechiceros. Con el tiempo, los Señores Hechiceros lograron varias victorias aplastantes, llegando casi a erradicar a sus enemigos para siempre. El día del combate final, el ejército del Protectorado se vio superado en número, además de estar agotado y mal equipado. Se prepararon para una derrota inevitable.");
        // Inflate the layout for this fragment
        nombre.setText("AATROX");
        return view;
    }
}
