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
        GridView grid;

        grid = (GridView) view.findViewById(R.id.gridView);

        grid.setAdapter(new GridAdapter(getActivity()));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getActivity(),
                        "position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
