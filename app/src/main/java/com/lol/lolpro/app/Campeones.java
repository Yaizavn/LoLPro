package com.lol.lolpro.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class Campeones extends Fragment {

    public Campeones() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campeones, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        GridView grid = (GridView) view.findViewById(R.id.gridView);

        BBDDHelper helper = new BBDDHelper(getActivity());
        helper.guardarDatos("Yaiza", "Villanueva", "Navarro", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, "http://segurosyseguros.es/wp-content/uploads/2011/11/seguros-para-perros.jpg");
        helper.guardarDatos("Pepe", "Villanueva", "Navarro", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, "http://www.perro-obediente.com/blog/wp-content/uploads/2014/04/perro.jpg");
        helper.guardarDatos("Perro", "Villanueva", "Navarro", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, "http://animalfiel.es/wp-content/uploads/2014/05/perro-1.jpg");

        grid.setAdapter(new GridAdapter(getActivity(), helper.obtenerRutas()));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getActivity(),
                        "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}


/*
        a = (TextView) findViewById(R.id.textView2);
        b = (TextView) findViewById(R.id.textView3);
        c = new DBCampeones (this);
        SQLiteDatabase g =c.getWritableDatabase();
        this.deleteDatabase("campeones"); //sacar el nombre con this.databaseList (me devuelve un Array de string con los nombres de todas las bbdd del contexto)
        c = new DBCampeones (this);
        c.guardarDatos("a", "b", "c", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, "ruta");
        c.guardarDatos("d", "e", "f", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, "ruta");
        d=c.listarDatos(2);
        a.setText(d.firstElement());
        b.setText(d.get(1));
        */