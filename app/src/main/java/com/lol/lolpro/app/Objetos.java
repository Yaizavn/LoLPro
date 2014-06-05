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


public class Objetos extends Fragment {

    OnHeadlineSelectedListener mCallback =null;

    public interface OnHeadlineSelectedListener{
        public void onObjectSelected (int index);
    }

    public Objetos() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((Principal)getActivity()).updateTitle(2);
        return inflater.inflate(R.layout.fragment_objetos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        GridView grid = (GridView) view.findViewById(R.id.gridView);

        BBDDHelper helper = new BBDDHelper(getActivity());

       /* helper.guardarObjetos("Objeto1",1,2, "resumen1",1, "http://segurosyseguros.es/wp-content/uploads/2011/11/seguros-para-perros.jpg");
        helper.guardarObjetos("Objeto2",3,4, "resumen2",1, "http://www.perro-obediente.com/blog/wp-content/uploads/2014/04/perro.jpg");
        helper.guardarObjetos("Objeto3",5,6, "resumen3",1, "http://animalfiel.es/wp-content/uploads/2014/05/perro-1.jpg");*/

        grid.setAdapter(new GridAdapter(getActivity(), helper.obtenerRutaObjetos(), 100));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //Toast.makeText(getActivity(),
                //      "position: " + position, Toast.LENGTH_SHORT).show();
                int identificador= Integer.parseInt(v.getTag().toString());
                Toast.makeText(getActivity(),
                        "id: " + identificador, Toast.LENGTH_SHORT).show();
                //Send the event to the host activity
                mCallback.onObjectSelected(identificador);
            }
        });
    }
}
