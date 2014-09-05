package com.lol.lolpro.app.inicio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.lol.lolpro.app.Activity_General;
import com.lol.lolpro.app.R;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.campeones.CampeonGeneral;
import com.lol.lolpro.app.campeones.CampeonesGlobal;
import com.lol.lolpro.app.grids.GridAdapterFreeChamps;
import com.lol.lolpro.app.utillidades.Champion_callback;
import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.utillidades.Utils;

/**
 * Implementa la funcionalidad del fragment
 */
public class InicioGlobal extends Fragment {

    Champion_callback mCallback = null;

    /**
     * Constructor vacío
     */
    public InicioGlobal() {
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
        ((Activity_General) getActivity()).updateTitle(Constants.DRAWER_INITIAL);
        return inflater.inflate(R.layout.fragment_inicio_global, container, false);
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
            throw new ClassCastException(activity.toString() + "must implement Champion_callback");
        }
    }

    /**
     * Método al que se llama una vez seha creado la vista en Oncreate()
     *
     * @param view               Vista en java hecha a partir del layout asociado
     * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
     */
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GridView grid = (GridView) view.findViewById(R.id.gridView);
        ListView list = (ListView) view.findViewById(R.id.noticias);
        grid.setNumColumns(4);

        DBManager dbMan = DBManager.getInstance();
        dbMan.openDatabase(false);
        grid.setAdapter(new GridAdapterFreeChamps(getActivity(), dbMan.getDatabaseHelper().obtenerGratuitos(), 75));
        list.setAdapter(new ListAdapterNoticias(getActivity()));
        if(Utils.hasInternetConnection(getActivity())){
            ((ListAdapterNoticias) list.getAdapter()).refresh();
        }
        else{
            list.setVisibility(View.INVISIBLE);
            view.findViewById(R.id.textNoticias).setVisibility(View.INVISIBLE);
        }

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //Send the event to the host activity
                mCallback.onChampionSelected(Integer.parseInt(v.getTag().toString()));
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String url = view.getTag().toString();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        dbMan.closeDatabase(false);
    }
}
