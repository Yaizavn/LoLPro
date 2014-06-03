package com.lol.lolpro.app;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class CampeonInfo extends Fragment {


    public CampeonInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String [] datos=null;
        View view= inflater.inflate(R.layout.fragment_campeon_info, container, false);
        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        if (id!=-1){
            BBDDHelper helper= new BBDDHelper(getActivity());
            datos=helper.obtenerDatosCampeon(id);
            ((TextView) view.findViewById(R.id.nombre)).setText(datos[0]);
            ((TextView) view.findViewById(R.id.nick)).setText(datos[1]);
            ((TextView) view.findViewById(R.id.vida)).setText(datos[2]);
            ((TextView) view.findViewById(R.id.regVida)).setText(datos[3]);
            ((TextView) view.findViewById(R.id.danioAtaque)).setText(datos[4]);
            ((TextView) view.findViewById(R.id.armadura)).setText(datos[5]);
            ((TextView) view.findViewById(R.id.velAtaque)).setText(datos[6]);
            ((TextView) view.findViewById(R.id.resMagica)).setText(datos[7]);
            ((TextView) view.findViewById(R.id.velMov)).setText(datos[8]);
        }
        // Inflate the layout for this fragment
        return view;
    }

}