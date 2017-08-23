package com.lol.lolpro.app.objetos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lol.lolpro.app.json.Objetos.Item;

/**
 * Adaptador para mostrar los tabs campeón e historia de champion
 */
public class ObjetoPageAdapter extends FragmentStatePagerAdapter {
	private static final int numPages = 2;
	private Item objeto;

	/**
	 * Constructor
	 *
	 * @param fm   Se encarga de la gestión de los fragments
	 * @param objeto Bundle que contiene los elementos asociados a un fragment, en este caso los datos de los campeones
	 */
	public ObjetoPageAdapter(FragmentManager fm, Item objeto) {
		super(fm);
		this.objeto = objeto;
	}

	/**
	 * Se encarga de crear los fragments dentro de champions
	 *
	 * @param position posición del tab que se creará
	 * @return fragment para el tab asociado a la posición
	 */
	@Override
	public Fragment getItem(int position) {

		Bundle bundle;
		Fragment frg;

		frg = null;

		switch (position) {
			case 0:
				frg = new ObjetoGeneral();
				break;
			case 1:
				frg = new ObjetoArbol();
				break;
			default:
				break;
		}

		if (frg != null) {

			bundle = new Bundle();

			bundle.putParcelable("item", objeto);

			frg.setArguments(bundle);

		}

		return frg;

	}

	/**
	 * Se encarga de obtener el número de tabs que tiene champion
	 *
	 * @return
	 */
	@Override
	public int getCount() {
		return numPages;
	}
}
