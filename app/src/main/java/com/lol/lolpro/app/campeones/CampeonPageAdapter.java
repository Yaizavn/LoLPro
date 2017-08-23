package com.lol.lolpro.app.campeones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.utillidades.Constants;

/**
 * Adaptador para mostrar los tabs campeón e historia de champion
 */
public class CampeonPageAdapter extends FragmentStatePagerAdapter {

	//TODO Hacer que no sea un número a capon
	private static final int numPages = 4;
	private Champion campeon;

	/**
	 * Constructor
	 * @param fm   Se encarga de la gestión de los fragments
	 * @param args Bundle que contiene los elementos asociados a un fragment, en este caso los datos de los campeones
	 */
	public CampeonPageAdapter(FragmentManager fm, Bundle args) {

		super(fm);

		campeon = args.getParcelable("champion");
		notifyDataSetChanged();

	}

	/**
	 * Se encarga de crear los fragments dentro de champions
	 * @param position posición del tab que se creará
	 * @return fragment para el tab asociado a la posición
	 */
	@Override
	public Fragment getItem(int position) {

		Bundle bundle;
		Fragment frg;

		frg = null;
		bundle = new Bundle();

		bundle.putParcelable("champion", campeon);

		switch (position) {
			case Constants.PG_GENERAL_CHAMPION:
				frg = new CampeonGeneral();
				break;
			case Constants.PG_HISTORY_CHAMPION:
				frg = new CampeonHistoria();
				break;
			case Constants.PG_SPELLS_CHAMPION:
				frg = new CampeonHabilidades();
				break;
			case Constants.PG_SKINS_CHAMPION:
				frg = new CampeonAspectos();
				break;
			default:
				break;
		}

		frg.setArguments(bundle);

		return frg;

	}

	/**
	 * Se encarga de obtener el número de tabs que tiene champion
	 * @return
	 */
	@Override
	public int getCount() {

		return numPages;

	}

}
