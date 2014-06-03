package com.lol.lolpro.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ObjetoInfo extends Fragment {

    public ObjetoInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String [] datos=null;
        View view= inflater.inflate(R.layout.fragment_objeto_info, container, false);
        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        if (id!=-1){
            BBDDHelper helper= new BBDDHelper(getActivity());
            datos=helper.obtenerDatosObjetos(id);
            ((TextView) view.findViewById(R.id.nombre)).setText(datos[0]);
            ((TextView) view.findViewById(R.id.costeBase)).setText(datos[1]);
            ((TextView) view.findViewById(R.id.coste)).setText(datos[2]);
            ((TextView) view.findViewById(R.id.descripcion)).setText(datos[3]);
            ((TextView) view.findViewById(R.id.puedesComprar)).setText(datos[4]);
            ((TextView) view.findViewById(R.id.resumen)).setText(datos[5]);
        }
        // Inflate the layout for this fragment
        return view;
    }

}
