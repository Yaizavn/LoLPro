package com.lol.lolpro.app;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



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
        Bundle args = getArguments();
        int id = args.getInt("id", -1);
        if (id!=-1){
            BBDDHelper helper= new BBDDHelper(getActivity());
            datos=helper.obtenerDatos(id);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campeon_info, container, false);
    }

}
