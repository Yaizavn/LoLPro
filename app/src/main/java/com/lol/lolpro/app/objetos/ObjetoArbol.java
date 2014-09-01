package com.lol.lolpro.app.objetos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.grids.GridAdapterNombre;
import com.squareup.picasso.Picasso;


public class ObjetoArbol extends Fragment {

    ObjetosGlobal.OnHeadlineSelectedListener mCallback = null;

    public ObjetoArbol() {
        // Required empty public constructor
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
            mCallback = (ObjetosGlobal.OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GridView grid;
        Bundle args = getArguments();
        String[][] datosFrom = (String[][])args.getSerializable("dataFrom");
        String[][] datosInto = (String[][])args.getSerializable("dataInto");
        if (datosFrom != null) {
            grid = (GridView) view.findViewById(R.id.gridViewFrom);
            view.findViewById(R.id.textFrom).setVisibility(View.VISIBLE);
            view.findViewById(R.id.gridViewFrom).setVisibility(View.VISIBLE);

            grid.setAdapter(new GridAdapterNombre(getActivity(), datosFrom, 100));
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    //Send the event to the host activity
                    mCallback.onObjectSelected(Integer.parseInt(v.getTag().toString()));
                }
            });
        }
        else{
            view.findViewById(R.id.textFrom).setVisibility(View.GONE);
            view.findViewById(R.id.gridViewFrom).setVisibility(View.GONE);
        }
        if (datosInto != null) {
            grid = (GridView) view.findViewById(R.id.gridViewInto);
            view.findViewById(R.id.textInto).setVisibility(View.VISIBLE);
            view.findViewById(R.id.gridViewInto).setVisibility(View.VISIBLE);

            grid.setAdapter(new GridAdapterNombre(getActivity(), datosInto, 100));
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView parent, View v, int position, long id) {
                    //Send the event to the host activity
                    mCallback.onObjectSelected(Integer.parseInt(v.getTag().toString()));
                }
            });
        }
        else{
            view.findViewById(R.id.textInto).setVisibility(View.GONE);
            view.findViewById(R.id.gridViewInto).setVisibility(View.GONE);
        }
    }

    /**
     * Se encarga del tratamiento necesario para poder crear la vista de información general de un objeto en particular
     *
     * @param inflater           Sirve para traer un layout hecho en xml como una vista en java
     * @param container          Contenedos para otros elementos View
     * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
     * @return Vista de los objetos
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_objeto_arbol, container, false);
        return view;
    }

    /**
     * Se encaga del tratamiento cuando se va a iniciar el fragment
     * Muestra los tabs que están creados
     */
    @Override
    public void onResume() {
        super.onResume();
        ((ActionBarActivity) getActivity()).getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }
}
