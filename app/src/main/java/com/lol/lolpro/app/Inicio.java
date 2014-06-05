package com.lol.lolpro.app;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


public class Inicio extends Fragment {

    OnHeadlineSelectedListener mCallback =null;

    public interface OnHeadlineSelectedListener{
        public void onChampionSelected (int index);
    }

    public Inicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((Principal)getActivity()).updateTitle(0);
        return inflater.inflate(R.layout.fragment_inicio, container, false);
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

    public void onViewCreated(View view, Bundle savedInstanceState){
        GridView grid = (GridView) view.findViewById(R.id.gridView);
        ListView list = (ListView) view.findViewById(R.id.noticias);
       // grid.setColumnWidth(Float.floatToIntBits(Utils.dipToPixels(this.getActivity(),50)));
        grid.setNumColumns(4);

        BBDDHelper helper = new BBDDHelper(getActivity());
        grid.setAdapter(new GridAdapter(getActivity(), helper.obtenerGratuitos(), 75));
        list.setAdapter(new ListAdapter(getActivity()));
        ((ListAdapter)list.getAdapter()).refresh();

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
    }
}
