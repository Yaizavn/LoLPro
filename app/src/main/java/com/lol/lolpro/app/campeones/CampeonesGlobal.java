package com.lol.lolpro.app.campeones;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lol.lolpro.app.Activity_General;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.grids.GridAdapterCampeonGlobal;
import com.lol.lolpro.app.grids.GridAdapterObjetoGlobal;
import com.lol.lolpro.app.R;
import com.lol.lolpro.app.utillidades.Champion_callback;

/**
 * Implementa la funcionalidad del fragment
 */
public class CampeonesGlobal extends Fragment {

    Champion_callback mCallback = null;

    /**
     * Constructor vacio
     */
    public CampeonesGlobal() {
        // Required empty public constructor
    }

    /**
     * Se encarga del tratamiento necesario para poder crear la vista
     *
     * @param inflater           Sirve para traer un layout hecho en xml como una vista en java
     * @param container          Contenedos para otros elementos View
     * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
     * @return Vista de los campeones
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((Activity_General) getActivity()).updateTitle(1);

        return inflater.inflate(R.layout.fragment_campeones_global, container, false);
    }

    /**
     * Método al que se llamará una vez el fragment ha sido asociado a un activity
     *
     * @param activity Activity al que está asociado un fragment
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //This makes sure that the container activity has implemented the callback interface.
        //If not, it throws an exception
        try {
            mCallback = (Champion_callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnHeadlineSelectedListener");
        }
    }

    /**
     * Método al que se llama una vez se ha creado la vista en Oncreate()
     *
     * @param view               Vista en java hecha a partir del layout asociado
     * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GridView grid = (GridView) view.findViewById(R.id.gridView);

        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);

        grid.setAdapter(new GridAdapterCampeonGlobal(getActivity(), dbMan.getDatabaseHelper().obtenerRutaCampeones(), 100));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //Send the event to the host activity
                mCallback.onChampionSelected(Integer.parseInt(v.getTag().toString()));
            }
        });
        dbMan.closeDatabase(false);
    }
}
