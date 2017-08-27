package com.lol.lolpro.app.objetos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.bbdd.BBDDHelper;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.grids.GridAdapterObjetoGlobal;
import com.lol.lolpro.app.json.Objetos.Item;
import com.lol.lolpro.app.utillidades.Object_callback;
import com.lol.lolpro.app.utillidades.Utils;

import java.util.List;


public class ObjetoArbol extends Fragment {

	Object_callback mCallback = null;

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
			mCallback = (Object_callback) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + "must implement Object_callback");
		}
	}

	/**
	 * Se encarga del tratamiento necesario para poder crear la vista de información general de un objeto en particular
	 *
	 * @param inflater		   Sirve para traer un layout hecho en xml como una vista en java
	 * @param container		  Contenedos para otros elementos View
	 * @param savedInstanceState Bundle donde se almacenaran los parámetros del fragment
	 * @return Vista de los objetos
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_objeto_arbol, container, false);
		GridView grid;
		Bundle args = getArguments();

		Item object = args.getParcelable("item");
		DBManager dbManager = DBManager.getInstance();
		dbManager.openDatabase(false);
		BBDDHelper bbddHelper = dbManager.getDatabaseHelper();
		List<Item> lFrom = bbddHelper.obtenerObjetos(Utils.convertListStringToListInt(object.getFrom()));
		List<Item> lInto = bbddHelper.obtenerObjetos(Utils.convertListStringToListInt(object.getInto()));
		if (lFrom != null && !lFrom.isEmpty()) {
			grid = (GridView) view.findViewById(R.id.arbol_gridViewFrom);
			view.findViewById(R.id.arbol_textNoFrom).setVisibility(View.GONE);
			view.findViewById(R.id.arbol_gridViewFrom)
					.setVisibility(View.VISIBLE);

			grid.setAdapter(new GridAdapterObjetoGlobal(getActivity(), lFrom));
			grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView parent, View v, int position, long id) {
					//Send the event to the host activity
					mCallback.onObjectSelected((Item) parent.getItemAtPosition(new Long(id).intValue()));
				}
			});
		}
		else{
			view.findViewById(R.id.arbol_gridViewFrom).setVisibility(View.GONE);
			view.findViewById(R.id.arbol_textNoFrom).setVisibility(View.VISIBLE);
		}
		if (lInto != null && !lInto.isEmpty()) {
			grid = (GridView) view.findViewById(R.id.arbol_gridViewInto);
			view.findViewById(R.id.arbol_textNoInto).setVisibility(View.GONE);
			view.findViewById(R.id.arbol_gridViewInto).setVisibility(View.VISIBLE);

			grid.setAdapter(new GridAdapterObjetoGlobal(getActivity(), lInto));
			grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView parent, View v, int position, long id) {
					//Send the event to the host activity
					mCallback.onObjectSelected((Item) parent.getItemAtPosition(new Long(id).intValue()));
				}
			});
		}
		else{
			view.findViewById(R.id.arbol_gridViewInto).setVisibility(View.GONE);
			view.findViewById(R.id.arbol_textNoInto).setVisibility(View.VISIBLE);
		}
		return view;
	}

	/**
	 * Se encaga del tratamiento cuando se va a iniciar el fragment
	 * Muestra los tabs que están creados
	 */
	@Override
	public void onResume() {
		super.onResume();
		((AppCompatActivity) getActivity()).getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}
}
