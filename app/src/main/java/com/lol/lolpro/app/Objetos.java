package com.lol.lolpro.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


public class Objetos extends Fragment {

    OnHeadlineSelectedListener mCallback = null;

    public interface OnHeadlineSelectedListener {
        public void onObjectSelected(int index);
    }

    public Objetos() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //This makes sure that the container activity has implemented the callback interface.
        //If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((Principal) getActivity()).updateTitle(Constants.DRAWER_OBJECT);
        return inflater.inflate(R.layout.fragment_objetos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        BBDDHelper helper = new BBDDHelper(getActivity());
        GridView grid = (GridView) view.findViewById(R.id.gridView);
        grid.setAdapter(new GridAdapter(getActivity(), helper.obtenerRutaObjetos(), 100));
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                //Send the event to the host activity
                mCallback.onObjectSelected(Integer.parseInt(v.getTag().toString()));
            }
        });
        // TOCLOSE
    }
}
