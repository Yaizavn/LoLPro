package com.lol.lolpro.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class Campeones extends Fragment {

    OnHeadlineSelectedListener mCallback =null;

    public interface OnHeadlineSelectedListener{
        public void onChampionSelected (int index);
    }

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
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        //This makes sure that the container activity has implemented the callback interface.
        //If not, it throws an exception
        try{
            mCallback = (OnHeadlineSelectedListener) activity;
        }
        catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        GridView grid = (GridView) view.findViewById(R.id.gridView);

        /*new spider("http://gameinfo.euw.leagueoflegends.com/es/game-info/champions/").execute();//"http://gameinfo.euw.leagueoflegends.com/es/game-info/champions/").execute();

        BBDDHelper helper = new BBDDHelper(getActivity());
        helper.guardarDatos("Yaiza", "Villanueva", "Navarro", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, "http://segurosyseguros.es/wp-content/uploads/2011/11/seguros-para-perros.jpg");
        helper.guardarDatos("Pepe", "Villanueva", "Navarro", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, "http://www.perro-obediente.com/blog/wp-content/uploads/2014/04/perro.jpg");
        helper.guardarDatos("Perro", "Villanueva", "Navarro", 1, 2.0f, 3.0f, 4.0f, 5.0f, 7.0f, 6, "http://animalfiel.es/wp-content/uploads/2014/05/perro-1.jpg");*/

        BBDDHelper helper = new BBDDHelper(getActivity());

        //ToDo Evitar enviar los dp deseados para el numero de imágenes
        grid.setAdapter(new GridAdapter(getActivity(), helper.obtenerRutaCampeones(), 100));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //Toast.makeText(getActivity(),
                  //      "position: " + position, Toast.LENGTH_SHORT).show();
                int identificador= Integer.parseInt(v.getTag().toString());
                Toast.makeText(getActivity(),
                        "id: " + identificador, Toast.LENGTH_SHORT).show();
                //Send the event to the host activity
                mCallback.onChampionSelected(identificador);
            }
        });
    }
}
